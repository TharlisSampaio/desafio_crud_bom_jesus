package com.desafiobomjesus.bom_jesus_api.mapper;

import com.desafiobomjesus.bom_jesus_api.dto.TeacherRequest;
import com.desafiobomjesus.bom_jesus_api.dto.TeacherResponse;
import com.desafiobomjesus.bom_jesus_api.entity.Teacher;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {
    private TeacherMapper() {}

    public static Teacher toEntity(TeacherRequest teacherRequest) {
        Teacher teacher = new Teacher();
        teacher.setName(teacherRequest.name());
        teacher.setEmailAddress(teacherRequest.emailAddress());
        teacher.setPhoneNumber(teacherRequest.phoneNumber());
        teacher.setSalary(teacherRequest.salary());
        teacherRequest.address().forEach(a -> teacher.addAddress(AddressMapper.toEntity(a)));
        return teacher;
    }

    public static TeacherResponse toTeacherResponse(Teacher teacher) {
        return new TeacherResponse(
                teacher.getId(),
                teacher.getName(),
                teacher.getPhoneNumber(),
                teacher.getEmailAddress(),
                teacher.getStatus(),
                teacher.getSalary(),
                teacher.getAddress()
                        .stream()
                        .map(a -> AddressMapper.toAddressResponse(a)).toList()
        );
    }
}
