/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rifqi.studentscrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author USER
 */
public interface StudentRepository extends JpaRepository<StudentEntity,Long>{
    StudentEntity findByNim(String nim);

}
