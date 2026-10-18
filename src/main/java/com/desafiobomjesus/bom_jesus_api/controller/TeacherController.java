package com.desafiobomjesus.bom_jesus_api.controller;

import com.desafiobomjesus.bom_jesus_api.dto.TeacherRequest;
import com.desafiobomjesus.bom_jesus_api.dto.TeacherResponse;
import com.desafiobomjesus.bom_jesus_api.exception.ApiError;
import com.desafiobomjesus.bom_jesus_api.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@Tag(name = "Teacher", description = "Registration and consultation od techer")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // GET byId
    @GetMapping("/{id}")
    @Operation(summary = "Find teacher by Id")
    @ApiResponse(responseCode = "200", description = "Teacher found")
    @ApiResponse(responseCode = "404", description = "Teacher not found",
            content = @Content(schema = @Schema(implementation = ApiError.class)))
    public ResponseEntity<TeacherResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(teacherService.findById(id));
    }

//    @GetMapping(params = "name")
//    public ResponseEntity<TeacherResponse> findByName(@RequestParam String name){
//        return ResponseEntity.ok(teacherService.findByName(name));
//    }

    // Get getAllTeacher
    @GetMapping
    @Operation(summary = "List all teachers")
    @ApiResponse(responseCode = "200", description = "Should return list empty or not empty")
    public ResponseEntity<List<TeacherResponse>> findAll() {
        return ResponseEntity.ok(teacherService.findAll());
    }

    // POST createdTeacher
    @PostMapping()
    @Operation(summary = "Registry teacher", description = "Creates a teacher with at least one address. The initial status is ACTIVE.")
    @ApiResponse(responseCode = "201", description = "Teacher created")
    @ApiResponse(responseCode = "400", description = "Invalid data",
            content = @Content(schema = @Schema(implementation = ApiError.class)))
    @ApiResponse(responseCode = "409", description = "Registry exist",
            content = @Content(schema = @Schema(implementation = ApiError.class)))
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
    @Operation(summary = "Update teacher by Id",
            description = "Update your name, contact information, and registration number. Addresses remain unchanged.")
    @ApiResponse(responseCode = "200", description = "Upadte teacher")
    @ApiResponse(responseCode = "400", description = "Invalid data",
            content = @Content(schema = @Schema(implementation = ApiError.class)))
    @ApiResponse(responseCode = "404", description = "Teacher not found",
            content = @Content(schema = @Schema(implementation = ApiError.class)))
    public ResponseEntity<TeacherResponse> update(@PathVariable Long id, @RequestBody TeacherRequest teacherRequest) {
        return ResponseEntity.ok(teacherService.updateTeacher(id, teacherRequest));
    }

    // DELETE deleteTeacherById
    @DeleteMapping("/{id}")
    @Operation(summary = "Disable a teacher", description = "Logical exclusion: changes the status to DISABLE, without deleting the registry entry.")
    @ApiResponse(responseCode = "204", description = "Disable teachet")
    @ApiResponse(responseCode = "404", description = "Teacher not found",
            content = @Content(schema = @Schema(implementation = ApiError.class)))
    public ResponseEntity<TeacherResponse> delete(@PathVariable Long id) {
        teacherService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
