package com.desafiobomjesus.bom_jesus_api.service.impl;

import com.desafiobomjesus.bom_jesus_api.dto.*;
import com.desafiobomjesus.bom_jesus_api.entity.Teacher;
import com.desafiobomjesus.bom_jesus_api.entity.enums.Status;
import com.desafiobomjesus.bom_jesus_api.mapper.TeacherMapper;
import com.desafiobomjesus.bom_jesus_api.repository.StudentRepository;
import com.desafiobomjesus.bom_jesus_api.repository.TeacherRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ImplTeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private ImplTeacherService implTeacherService;

    private TeacherRequest validRequest() {
        AddressRequest address = new AddressRequest(
                "Rua das Flores, 100", "Curitiba", "PR", "80000-000", "Brasil"
        );

        return new TeacherRequest(
                "Jean", "68999887744",
                "jean@ufac.com", BigDecimal.valueOf(12000.00), List.of(address)
        );
    }

    @Test
    void shouldCreateTeacher() {
        Teacher saved = TeacherMapper.toEntity(validRequest());
        saved.setId(1L);
        Mockito.when(teacherRepository.save(Mockito.any(Teacher.class))).thenReturn(saved);

        TeacherResponse response = implTeacherService.createdTeacher(validRequest());

        Assertions.assertEquals(1L, response.id());
        Assertions.assertEquals("Jean", response.name());
        Assertions.assertEquals("68999887744", response.phoneNumber());
        Assertions.assertEquals("jean@ufac.com", response.emailAddress());
        Assertions.assertEquals(Status.ACTIVE,  response.status());
        Assertions.assertEquals(1, response.address().size());
    }

    @Test
    void shouldReturnEmptyListWhenThereAreNoStudents() {
        Mockito.when(teacherRepository.findAll()).thenReturn(Collections.emptyList());

        List<TeacherResponse> result = implTeacherService.findAll();

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnListTeacher() {
        Teacher teacher = TeacherMapper.toEntity(validRequest());
        teacher.setId(1L);

        Mockito.when(teacherRepository.findAll()).thenReturn(Collections.singletonList(teacher));
        List<TeacherResponse> result = implTeacherService.findAll();

        Assertions.assertEquals(1, result.size());
    }

    @Test
    void whenIdExist() {
        Teacher teacher = TeacherMapper.toEntity(validRequest());
        teacher.setId(1L);

        Mockito.when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        TeacherResponse response = implTeacherService.findById(1L);

        Assertions.assertEquals(1L, response.id());
    }

    @Test
    void whenIdNotExist() {
        Mockito.when(teacherRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> implTeacherService.findById(1L));
    }
}