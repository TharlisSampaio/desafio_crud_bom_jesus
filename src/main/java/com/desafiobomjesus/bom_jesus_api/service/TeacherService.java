package com.desafiobomjesus.bom_jesus_api.service;

import com.desafiobomjesus.bom_jesus_api.dto.TeacherRequest;
import com.desafiobomjesus.bom_jesus_api.dto.TeacherResponse;
import com.desafiobomjesus.bom_jesus_api.entity.Teacher;

import java.util.List;

public interface TeacherService {
    TeacherResponse findById(Long id);
    TeacherResponse findByName(String name);
    List<TeacherResponse> findAll();
    TeacherResponse createdTeacher(TeacherRequest teacherRequest);
    TeacherResponse updateTeacher(Long id, TeacherRequest teacherRequest);
    void deleteById(Long id);
}
