package com.desafiobomjesus.bom_jesus_api.service.impl;

import com.desafiobomjesus.bom_jesus_api.dto.StudentRequest;
import com.desafiobomjesus.bom_jesus_api.dto.StudentResponse;
import com.desafiobomjesus.bom_jesus_api.entity.Student;
import com.desafiobomjesus.bom_jesus_api.entity.enums.Status;
import com.desafiobomjesus.bom_jesus_api.mapper.StudentMapper;
import com.desafiobomjesus.bom_jesus_api.repository.StudentRepository;
import com.desafiobomjesus.bom_jesus_api.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ImplStudentService implements StudentService {
    final private StudentRepository studentRepository;

    public ImplStudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentResponse findById(Long id) {
        var student = studentRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return StudentMapper.toStudentResponse(student);
    }

    @Override
    public StudentResponse findByName(String name) {
        var student = studentRepository.findByName(name);
        return StudentMapper.toStudentResponse(student);
    }

    @Override
    public List<StudentResponse> getAll() {
        List<Student> students = studentRepository.findAll();
        List<StudentResponse> studentsResponse = new ArrayList<StudentResponse>();
        students.forEach(student -> studentsResponse.add(StudentMapper.toStudentResponse(student)));
        return studentsResponse;
    }

    @Override
    public StudentResponse createStudent(StudentRequest studentRequest) {
        Student student = StudentMapper.toEntity(studentRequest);
        student = studentRepository.save(student);
        return StudentMapper.toStudentResponse(student);
    }



    @Override
    public StudentResponse updateStudent(Long id, StudentRequest studentRequest) {
        var entity = studentRepository.findById(id);

        if (entity.isEmpty()) {
            throw new RuntimeException("Estudante não encontrado: " + id);
        }

        Student student = entity.get();

        if (studentRequest.name() != null) {
            student.setName(studentRequest.name());
        }

        if (studentRequest.emailAddress() != null) {
            student.setEmailAddress(studentRequest.emailAddress());
        }

        if (studentRequest.phoneNumber() != null) {
            student.setPhoneNumber(studentRequest.phoneNumber());
        }

        if (studentRequest.studentNumber() != null) {
            student.setStudentNumber(studentRequest.studentNumber());
        }

        if (studentRequest.photo() != null) {
            student.setPhoto(studentRequest.photo());
        }

        student = studentRepository.save(student);

        return StudentMapper.toStudentResponse(student);
    }



    @Override
    public void deleteById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(NoSuchElementException::new);
        student.setStatus(Status.DISABLE);
        studentRepository.save(student);
    }
}
