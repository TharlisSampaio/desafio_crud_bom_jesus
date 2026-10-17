package com.desafiobomjesus.bom_jesus_api.controller;

import com.desafiobomjesus.bom_jesus_api.dto.StudentRequest;
import com.desafiobomjesus.bom_jesus_api.dto.StudentResponse;
import com.desafiobomjesus.bom_jesus_api.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET byId
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> findById(@PathVariable Long id) {
        try {
            var student = studentService.findById(id);
            return ResponseEntity.ok().body(student);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // GET  byName
    @GetMapping(params = "name")
    public ResponseEntity<StudentResponse> findByName(@RequestParam String name) {
        try {
            var student = studentService.findByName(name);
            return ResponseEntity.ok().body(student);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // GET allStudents
    @GetMapping("/")
    public ResponseEntity<List<StudentResponse>> findAll() {
        var students = studentService.getAll();
        return ResponseEntity.ok().body(students);
    }

    // POST createdStudent
    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@RequestBody StudentRequest studentRequest) {
        try {
            var student = this.studentService.createStudent(studentRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(student);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // UPDATE student
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable Long id, @RequestBody StudentRequest studentRequest) {
        try {
            studentService.updateStudent(id, studentRequest);
            return ResponseEntity.ok().body(studentService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // DELETE deleteStudent
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable Long id) {
        try {
            studentService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
