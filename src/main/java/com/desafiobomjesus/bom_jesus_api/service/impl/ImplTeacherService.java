package com.desafiobomjesus.bom_jesus_api.service.impl;

import com.desafiobomjesus.bom_jesus_api.dto.TeacherRequest;
import com.desafiobomjesus.bom_jesus_api.entity.Teacher;
import com.desafiobomjesus.bom_jesus_api.repository.TeacherRepository;
import com.desafiobomjesus.bom_jesus_api.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplTeacherService implements TeacherService {

    private final TeacherRepository teacherRepository;

    public ImplTeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher findById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    public Teacher findByName(String name) {
        return null;
    }

    @Override
    public List<Teacher> findAll() {
        return List.of();
    }

    @Override
    public Teacher createdTeacher(TeacherRequest teacherRequest) {
        return null;
    }

    @Override
    public Teacher updateTeacher(Long id, TeacherRequest teacherRequest) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
