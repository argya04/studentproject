/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rifqi.studentscrud.service;

import com.rifqi.studentscrud.domain.Student;
import com.rifqi.studentscrud.repository.StudentEntity;
import com.rifqi.studentscrud.repository.StudentRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import static java.util.stream.Collectors.toList;
import org.springframework.stereotype.Service;

/**
 *
 * @author USER
 */
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        List<StudentEntity> studentEntities = studentRepository.findAll();
        return studentEntities.stream()
                .map(this::map)
                .collect(toList());
    }

    public Student getStudentByNim(String nim) {
        StudentEntity studentEntity = studentRepository.findByNim(nim);
        if (studentEntity == null) {
            throw new RuntimeException("Mahasiswa tidak ditemukan");
        }

        return this.map(studentEntity);
    }

    private Student map(StudentEntity entity) {
        Student student = new Student();
        student.setId(entity.getId());
        student.setNim(entity.getNim());
        student.setFullName(entity.getFullName());
        student.setAddress(entity.getAddress());
        student.setDateOfBirth(LocalDate.parse(entity.getDateOfBirth(), formatter));

        return student;
    }

    @Transactional
    public void saveStudent(Student student) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setNim(student.getNim());
        studentEntity.setFullName(student.getFullName());
        studentEntity.setAddress(student.getAddress());
        studentEntity.setDateOfBirth(student.getDateOfBirth().toString());

        studentRepository.save(studentEntity);
    }

    @Transactional
    public void updateStudent(String nim, Student student) {
        StudentEntity studentEntity = studentRepository.findByNim(nim);

        studentEntity.setFullName(student.getFullName());
        studentEntity.setAddress(student.getAddress());
        studentEntity.setDateOfBirth(student.getDateOfBirth().toString());

        studentRepository.save(studentEntity);
    }

    @Transactional
    public void deleteStudentByNim(String nim) {
        StudentEntity studentEntity = studentRepository.findByNim(nim);
        if (studentEntity == null) {
            throw new RuntimeException("Mahasiswa dengan NIM " + nim + " tidak ditemukan");
        }
        studentRepository.delete(studentEntity);
    }

    public boolean isStudentExist(String nim) {
        return studentRepository.findByNim(nim) != null;
    }
}
