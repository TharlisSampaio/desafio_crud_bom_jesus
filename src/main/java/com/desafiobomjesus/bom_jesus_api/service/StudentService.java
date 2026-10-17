package com.desafiobomjesus.bom_jesus_api.service;

import com.desafiobomjesus.bom_jesus_api.dto.StudentRequest;
import com.desafiobomjesus.bom_jesus_api.entity.Student;

import java.util.List;

public interface StudentService {
    Student findById(Long id);
    Student findByName(String name);
    List<Student> getAll();
    Student createStudent(StudentRequest studentRequest);
    Student updateStudent(Long id, StudentRequest studentRequest);
    void deleteById(Long id);
}
