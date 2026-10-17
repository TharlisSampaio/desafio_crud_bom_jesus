package com.desafiobomjesus.bom_jesus_api.service;

import com.desafiobomjesus.bom_jesus_api.dto.StudentRequest;
import com.desafiobomjesus.bom_jesus_api.dto.StudentResponse;
import com.desafiobomjesus.bom_jesus_api.entity.Student;

import java.util.List;

public interface StudentService {
    StudentResponse findById(Long id);
    StudentResponse findByName(String name);
    List<StudentResponse> getAll();
    StudentResponse createStudent(StudentRequest studentRequest);
    StudentResponse updateStudent(Long id, StudentRequest studentRequest);
    void deleteById(Long id);
}
