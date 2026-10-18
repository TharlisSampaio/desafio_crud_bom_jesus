package com.desafiobomjesus.bom_jesus_api.service.impl;

import com.desafiobomjesus.bom_jesus_api.dto.AddressRequest;
import com.desafiobomjesus.bom_jesus_api.dto.AddressResponse;
import com.desafiobomjesus.bom_jesus_api.dto.StudentRequest;
import com.desafiobomjesus.bom_jesus_api.dto.StudentResponse;
import com.desafiobomjesus.bom_jesus_api.entity.Address;
import com.desafiobomjesus.bom_jesus_api.entity.Student;
import com.desafiobomjesus.bom_jesus_api.entity.enums.Status;
import com.desafiobomjesus.bom_jesus_api.mapper.StudentMapper;
import com.desafiobomjesus.bom_jesus_api.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ImplStudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private ImplStudentService implStudentService;

    private StudentRequest validRequest() {
        AddressRequest address = new AddressRequest(
                "Rua das Flores, 100", "Curitiba", "PR", "80000-000", "Brasil"
        );

        return new StudentRequest(
                "Maria Silva", "41999999999", "maria.silva@email.com",
                "2024001", null, List.of(address)
        );
    }

    private StudentResponse validResponse() {
        AddressResponse address = new AddressResponse(
                1L, "Rua das Flores, 100", "Curitiba", "PR", "80000-000", "Brasil"
        );

        return new StudentResponse(
                1L, "Maria Silva", "41999999999", "maria.silva@email.com",
                "2024001", Status.ACTIVE, null, List.of(address)
        );
    }

    @Test
    void createStudent() {
        Student saved = StudentMapper.toEntity(validRequest());
        saved.setId(1L);
        Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(saved);

        StudentResponse response = implStudentService.createStudent(validRequest());

        Assertions.assertEquals(1L, response.id());
        Assertions.assertEquals("Maria Silva", response.name());
        Assertions.assertEquals("2024001", response.studentNumber());
        Assertions.assertEquals(Status.ACTIVE, response.status());
        Assertions.assertEquals(1, response.addresses().size());
        Assertions.assertEquals("Curitiba", response.addresses().getFirst().city());
    }

    @Test
    void shouldReturnEmptyListWhenThereAreNoStudents() {
        Mockito.when(studentRepository.findAll()).thenReturn(Collections.emptyList());

        List<StudentResponse> result = implStudentService.getAll();

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnListStudents() {
        Student student = new Student();
        student.setId(1L);
        student.setName("Maria Silva");
        student.setPhoneNumber("41999999999");
        student.setEmailAddress("maria.silva@email.com");
        student.setStudentNumber("2024001");

        Address address = new Address();
        address.setStreet("Rua das Flores, 100");
        address.setCity("Curitiba");
        address.setState("PR");
        address.setZipCode("80000-000");
        address.setCountry("Brasil");
        student.addAddress(address);

        Mockito.when(studentRepository.findAll()).thenReturn(List.of(student));

        List<StudentResponse> result = implStudentService.getAll();

        Assertions.assertEquals(1, result.size());
        StudentResponse response = result.getFirst();
        Assertions.assertEquals(1L, response.id());
        Assertions.assertEquals("Maria Silva", response.name());
        Assertions.assertEquals("2024001", response.studentNumber());
        Assertions.assertEquals(Status.ACTIVE, response.status());
        Assertions.assertEquals(1, response.addresses().size());
        Assertions.assertEquals("Curitiba", response.addresses().getFirst().city());

        Mockito.verify(studentRepository).findAll();
    }

    @Test
    void whenIdDoesNotExist() {
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> implStudentService.findById(1L));
    }

    @Test
    void whenIdExist() {
        Student student = StudentMapper.toEntity(validRequest());
        student.setId(1L);
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        StudentResponse response = implStudentService.findById(1L);
        Assertions.assertEquals(1L, response.id());
    }
}