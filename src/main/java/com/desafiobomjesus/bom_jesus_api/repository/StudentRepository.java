package com.desafiobomjesus.bom_jesus_api.repository;

import com.desafiobomjesus.bom_jesus_api.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
