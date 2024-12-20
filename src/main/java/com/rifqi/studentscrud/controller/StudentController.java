/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rifqi.studentscrud.controller;

import com.rifqi.studentscrud.domain.Student;
import com.rifqi.studentscrud.service.StudentService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author USER
 */
@RestController
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        List<Student> studentList = studentService.getAllStudents();
        return studentList;
    }

    @GetMapping("/students/{nim}")
    public Student getStudentByNim(@PathVariable String nim) {
        Student student = studentService.getStudentByNim(nim);
        return student;
    }

    @PostMapping("/students")
    public Student createStudent(@RequestBody @Valid Student student) {
        studentService.saveStudent(student);
        return student;
    }

    @PutMapping("/students/{nim}")
    public ResponseEntity<String> updateStudent(@PathVariable String nim, @Valid @RequestBody Student student) {
        if (!studentService.isStudentExist(nim)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data Mahasiswa tidak ditemukan!");
        }

        studentService.updateStudent(nim, student);
        return ResponseEntity.ok("Data Mahasiswa berhasil diupdate");
    }

    @DeleteMapping("/students/{nim}")
    public ResponseEntity<String> deleteStudent(@PathVariable String nim, @Valid @RequestBody Student student) {
        studentService.deleteStudentByNim(nim);
        return ResponseEntity.ok("Mahasiswa dengan NIM " + nim + " berhasil dihapus");
    }
}
