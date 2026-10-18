package com.desafiobomjesus.bom_jesus_api.controller;

import com.desafiobomjesus.bom_jesus_api.dto.TeacherRequest;
import com.desafiobomjesus.bom_jesus_api.dto.TeacherResponse;
import com.desafiobomjesus.bom_jesus_api.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
        return ResponseEntity.ok(teacherService.findById(id));
    }

    @GetMapping(params = "name")
    public ResponseEntity<TeacherResponse> findByName(@RequestParam String name){
        return ResponseEntity.ok(teacherService.findByName(name));
    }

    // Get getAllTeacher
    @GetMapping
    public ResponseEntity<List<TeacherResponse>> findAll() {
        return ResponseEntity.ok(teacherService.findAll());
    }

    // POST createdTeacher
    @PostMapping()
    public ResponseEntity<TeacherResponse> createTeacher(@RequestBody TeacherRequest teacherRequest) {
        TeacherResponse response = teacherService.createdTeacher(teacherRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    // PUT updateTeacherById
    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponse> update(@PathVariable Long id, @RequestBody TeacherRequest teacherRequest) {
        return ResponseEntity.ok(teacherService.updateTeacher(id, teacherRequest));
    }

    // DELETE deleteTeacherById
    @DeleteMapping("/{id}")
    public ResponseEntity<TeacherResponse> delete(@PathVariable Long id) {
        teacherService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
