/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rifqi.studentscrud.controller;

import com.rifqi.studentscrud.domain.Student;
import com.rifqi.studentscrud.service.StudentService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author USER
 */
@RestController
public class StudentController {
    private final StudentService studentService;
    
    private StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    
    @GetMapping("/students")
    public List<Student>getStudents(){
        List<Student> studentList = studentService.getAllStudents();
        return studentList;
    }
    
    @GetMapping("/students/{nim}")
    public Student getStudentByNim(@PathVariable String nim){
        Student student = studentService.getStudetByNim(nim);
        return student;
    }
}
