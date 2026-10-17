package com.desafiobomjesus.bom_jesus_api.service;

import com.desafiobomjesus.bom_jesus_api.dto.TeacherRequest;
import com.desafiobomjesus.bom_jesus_api.entity.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher findById(Long id);
    Teacher findByName(String name);
    List<Teacher> findAll();
    Teacher createdTeacher(TeacherRequest teacherRequest);
    Teacher updateTeacher(Long id, TeacherRequest teacherRequest);
    void deleteById(Long id);
}
