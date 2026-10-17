package com.desafiobomjesus.bom_jesus_api.mapper;

import com.desafiobomjesus.bom_jesus_api.dto.StudentRequest;
import com.desafiobomjesus.bom_jesus_api.dto.StudentResponse;
import com.desafiobomjesus.bom_jesus_api.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    private StudentMapper() {}

    public static Student toEntity(StudentRequest studentRequest) {
        Student student = new Student();
        student.setName(studentRequest.name());
        student.setEmailAddress(studentRequest.emailAddress());
        student.setStudentNumber(studentRequest.studentNumber());
        student.setPhoneNumber(studentRequest.phoneNumber());
        studentRequest.address().forEach(address -> student.addAddress(AddressMapper.toEntity(address)));
        return student;
    }

    public static StudentResponse toStudentResponse(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getPhoneNumber(),
                student.getEmailAddress(),
                student.getStudentNumber(),
                student.getPhoto(),
                student.getAddress().stream().map(
                        address -> AddressMapper.toAddressResponse(address)
                ).toList()
        );
    }
}
