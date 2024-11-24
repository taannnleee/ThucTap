package org.example.studentmanagement.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.studentmanagement.dto.request.StudentRequest;
import org.example.studentmanagement.dto.request.SubjectRequest;
import org.example.studentmanagement.dto.response.StudentResponse;
import org.example.studentmanagement.dto.response.SubjectResponse;
import org.example.studentmanagement.entities.Subject;
import org.example.studentmanagement.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/subject")
@Slf4j
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<SubjectResponse> createSubject(@RequestBody @Valid SubjectRequest newSubject) {
        log.info("Received request to create subject: {}", newSubject);
        try {
            SubjectResponse subjectResponse = subjectService.createSubject(newSubject);
            return ResponseEntity.status(HttpStatus.CREATED).body(subjectResponse);
        } catch (Exception e) {
            log.error("Error creating subject: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // READ ALL
    @GetMapping("/get-all")
    public ResponseEntity<List<SubjectResponse>> getAllSubjects() {
        log.info("Received request to get all subjects.");
        try {
            List<SubjectResponse> subjects = subjectService.getAllSubjects();
            return ResponseEntity.ok(subjects);
        } catch (Exception e) {
            log.error("Error fetching subjects: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<SubjectResponse> updateSubject(@PathVariable Long id, @RequestBody @Valid SubjectRequest updatedSubject) {
        log.info("Received request to update subject with id {}: {}", id, updatedSubject);
        try {
            SubjectResponse subjectResponse = subjectService.updateSubject(id, updatedSubject);
            return ResponseEntity.ok(subjectResponse);
        } catch (Exception e) {
            log.error("Error updating subject with id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable Long id) {
        log.info("Received request to delete subject with id: {}", id);
        try {
            subjectService.deleteSubject(id);
            return ResponseEntity.ok("Delete subject successful");
        } catch (Exception e) {
            log.error("Error deleting subject with id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting subject");
        }
    }

    // SEARCH
    @GetMapping("/search")
    public ResponseEntity<List<Subject>> searchSubjects(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer credit,
            @RequestParam(required = false) String description) {
        log.info("Received request to search subjects with name: {}, credit: {}, description: {}", name, credit, description);
        try {
            List<Subject> subjects = subjectService.searchSubjects(name, credit, description);
            if (subjects.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(subjects); // No subjects found
            }
            return ResponseEntity.ok(subjects); // Return found subjects
        } catch (Exception e) {
            log.error("Error searching subjects: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Internal server error
        }
    }
}
