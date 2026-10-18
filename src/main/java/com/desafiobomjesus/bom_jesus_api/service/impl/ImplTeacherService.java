package com.desafiobomjesus.bom_jesus_api.service.impl;

import com.desafiobomjesus.bom_jesus_api.dto.TeacherRequest;
import com.desafiobomjesus.bom_jesus_api.dto.TeacherResponse;
import com.desafiobomjesus.bom_jesus_api.entity.Teacher;
import com.desafiobomjesus.bom_jesus_api.entity.enums.Status;
import com.desafiobomjesus.bom_jesus_api.mapper.TeacherMapper;
import com.desafiobomjesus.bom_jesus_api.repository.TeacherRepository;
import com.desafiobomjesus.bom_jesus_api.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ImplTeacherService implements TeacherService {

    private final TeacherRepository teacherRepository;

    public ImplTeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public TeacherResponse findById(Long id) {
        var teacher = teacherRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return TeacherMapper.toTeacherResponse(teacher);
    }

    @Override
    public TeacherResponse findByName(String name) {
        var teacher = teacherRepository.findByName(name);
        return TeacherMapper.toTeacherResponse(teacher);
    }

    @Override
    public List<TeacherResponse> findAll() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherResponse> teachersResponse = new ArrayList<>();
        teachers.forEach(teacher -> teachersResponse.add(TeacherMapper.toTeacherResponse(teacher)));
        return teachersResponse;
    }

    @Override
    public TeacherResponse createdTeacher(TeacherRequest teacherRequest) {
        Teacher teacher = TeacherMapper.toEntity(teacherRequest);
        teacher = teacherRepository.save(teacher);
        return TeacherMapper.toTeacherResponse(teacher);
    }

    @Override
    public TeacherResponse updateTeacher(Long id, TeacherRequest teacherRequest) {
        var entity = teacherRepository.findById(id);

        if (entity.isEmpty()) {
            throw new NoSuchElementException();
        }

        Teacher teacher = entity.get();

        if (teacherRequest.name() != null) {
            teacher.setName(teacherRequest.name());
        }
        if (teacherRequest.emailAddress() != null) {
            teacher.setEmailAddress(teacherRequest.emailAddress());
        }
        if (teacherRequest.phoneNumber() != null) {
            teacher.setPhoneNumber(teacherRequest.phoneNumber());
        }

        teacherRepository.save(teacher);

        return TeacherMapper.toTeacherResponse(teacher);
    }

    @Override
    public void deleteById(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(NoSuchElementException::new);
        teacher.setStatus(Status.DISABLE);
        teacherRepository.save(teacher);
    }
}
