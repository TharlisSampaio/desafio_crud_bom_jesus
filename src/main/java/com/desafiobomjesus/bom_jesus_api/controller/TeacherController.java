package com.desafiobomjesus.bom_jesus_api.controller;

import com.desafiobomjesus.bom_jesus_api.dto.TeacherRequest;
import com.desafiobomjesus.bom_jesus_api.dto.TeacherResponse;
import com.desafiobomjesus.bom_jesus_api.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService teacherService;
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    // GET byId
    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponse> findById(@PathVariable Long id){
        try {
            var teacher = teacherService.findById(id);
            return ResponseEntity.ok().body(teacher);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(params = "name")
    public ResponseEntity<TeacherResponse> findByName(@RequestParam String name){
        try {
            var teacher = teacherService.findByName(name);
            return ResponseEntity.ok().body(teacher);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Get getAllTeacher
    @GetMapping("/")
    public ResponseEntity<List<TeacherResponse>> findAll() {
        try {
            var teachers = teacherService.findAll();
            return ResponseEntity.ok().body(teachers);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // POST createdTeacher
    @PostMapping()
    public ResponseEntity<TeacherResponse> createTeacher(@RequestBody TeacherRequest teacherRequest) {
        try {
            var teacher = teacherService.createdTeacher(teacherRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(teacher);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PUT updateTeacherById
    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponse> update(@PathVariable Long id, @RequestBody TeacherRequest teacherRequest) {
        System.out.println("TeacherController.update " + teacherRequest);
        try {
            var teacher = teacherService.updateTeacher(id, teacherRequest);
            System.out.println("update antes do return");
            return ResponseEntity.ok().body(teacher);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // DELETE deleteTeacherById
    @DeleteMapping("/{id}")
    public ResponseEntity<TeacherResponse> delete(@PathVariable Long id) {
        try {
            teacherService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
