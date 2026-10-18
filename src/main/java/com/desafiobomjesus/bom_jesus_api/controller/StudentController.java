package com.desafiobomjesus.bom_jesus_api.controller;

import com.desafiobomjesus.bom_jesus_api.dto.StudentRequest;
import com.desafiobomjesus.bom_jesus_api.dto.StudentResponse;
import com.desafiobomjesus.bom_jesus_api.exception.ApiError;
import com.desafiobomjesus.bom_jesus_api.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student", description = "Registration and consultation of students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET byId
    @GetMapping("/{id}")
    @Operation(summary = "Find student by Id")
    @ApiResponse(responseCode = "200", description = "Student found")
    @ApiResponse(responseCode = "404", description = "Student not found",
            content = @Content(schema = @Schema(implementation = ApiError.class)))
    public ResponseEntity<StudentResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.findById(id));
    }

//    // GET  byName
//    @GetMapping(params = "name")
//    public ResponseEntity<StudentResponse> findByName(@RequestParam String name) {
//        return ResponseEntity.ok(studentService.findByName(name));
//    }

    // GET allStudents
    @GetMapping
    @Operation(summary = "List all students")
    @ApiResponse(responseCode = "200", description = "Should return list empty or not empty")
    public ResponseEntity<List<StudentResponse>> findAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    // POST createdStudent
    @PostMapping
    @Operation(summary = "Registry student", description = "Creates a student with at least one address. The initial status is ACTIVE.")
    @ApiResponse(responseCode = "201", description = "Student created")
    @ApiResponse(responseCode = "400", description = "Invalid data",
            content = @Content(schema = @Schema(implementation = ApiError.class)))
    @ApiResponse(responseCode = "409", description = "Registry exist",
            content = @Content(schema = @Schema(implementation = ApiError.class)))
    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody StudentRequest studentRequest) {
        StudentResponse response = studentService.createStudent(studentRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    // UPDATE student
    @PutMapping("/{id}")
    @Operation(summary = "Update student by Id",
            description = "Update your name, contact information, and registration number. Addresses remain unchanged.")
    @ApiResponse(responseCode = "200", description = "Upadte student")
    @ApiResponse(responseCode = "400", description = "Invalid data",
            content = @Content(schema = @Schema(implementation = ApiError.class)))
    @ApiResponse(responseCode = "404", description = "Student not found",
            content = @Content(schema = @Schema(implementation = ApiError.class)))
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable Long id, @RequestBody StudentRequest studentRequest) {
        return ResponseEntity.ok(studentService.updateStudent(id, studentRequest));
    }

    // DELETE deleteStudent
    @DeleteMapping("/{id}")
    @Operation(summary = "Disable a student", description = "Logical exclusion: changes the status to DISABLE, without deleting the registry entry.")
    @ApiResponse(responseCode = "204", description = "Disable student")
    @ApiResponse(responseCode = "404", description = "Student not found",
            content = @Content(schema = @Schema(implementation = ApiError.class)))
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable Long id) {
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
