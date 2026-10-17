package com.desafiobomjesus.bom_jesus_api.service.impl;

import com.desafiobomjesus.bom_jesus_api.dto.StudentRequest;
import com.desafiobomjesus.bom_jesus_api.entity.Student;
import com.desafiobomjesus.bom_jesus_api.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplStudentService implements StudentService {
    @Override
    public Student findById(Long id) {
        return null;
    }

    @Override
    public Student findByName(String name) {
        return null;
    }

    @Override
    public List<Student> getAll() {
        return List.of();
    }

    @Override
    public Student createStudent(StudentRequest studentRequest) {
        return null;
    }

    @Override
    public Student updateStudent(Long id, StudentRequest studentRequest) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
