package com.desafiobomjesus.bom_jesus_api.config;

import com.desafiobomjesus.bom_jesus_api.entity.Address;
import com.desafiobomjesus.bom_jesus_api.entity.Student;
import com.desafiobomjesus.bom_jesus_api.entity.Teacher;
import com.desafiobomjesus.bom_jesus_api.repository.StudentRepository;
import com.desafiobomjesus.bom_jesus_api.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public void run(String... args) {
        if (studentRepository.count() == 0) {
            studentRepository.saveAll(List.of(
                    student("Maria Silva", "41999990001", "maria.silva@email.com", "2024001",
                            address("Rua das Flores, 100", "Curitiba", "PR", "80000-000")),
                    student("Carlos Souza", "41999990002", "carlos.souza@email.com", "2024002",
                            address("Av. Sete de Setembro, 2500", "Curitiba", "PR", "80230-010")),
                    student("Ana Oliveira", "41999990003", "ana.oliveira@email.com", "2024003",
                            address("Rua XV de Novembro, 450", "Curitiba", "PR", "80020-310"),
                            address("Rua Marechal Deodoro, 300", "Curitiba", "PR", "80010-010"))
            ));
        }

        if (teacherRepository.count() == 0) {
            teacherRepository.saveAll(List.of(
                    teacher("João Pereira", "41988880001", "joao.pereira@email.com", "5500.00",
                            address("Rua Voluntários da Pátria, 700", "Curitiba", "PR", "80020-000")),
                    teacher("Fernanda Lima", "41988880002", "fernanda.lima@email.com", "6200.00",
                            address("Av. República Argentina, 1200", "Curitiba", "PR", "80620-010"))
            ));
        }
    }

    private Student student(String name, String phone, String email, String studentNumber,
                            Address... addresses) {
        Student student = new Student();
        student.setName(name);
        student.setPhoneNumber(phone);
        student.setEmailAddress(email);
        student.setStudentNumber(studentNumber);
        for (Address a : addresses) {
            student.addAddress(a); // preenche os dois lados da relação
        }
        return student;
    }

    private Teacher teacher(String name, String phone, String email, String salary,
                            Address... addresses) {
        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setPhoneNumber(phone);
        teacher.setEmailAddress(email);
        teacher.setSalary(new BigDecimal(salary)); // AJUSTE: se seu salary for double, use Double.parseDouble(salary)
        for (Address a : addresses) {
            teacher.addAddress(a);
        }
        return teacher;
    }

    private Address address(String street, String city, String state, String zipCode) {
        Address address = new Address();
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setZipCode(zipCode);
        address.setCountry("Brasil");
        return address;
    }
}
