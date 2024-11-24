package org.example.studentmanagement.controller;

import jakarta.validation.Valid;
import org.example.studentmanagement.dto.request.StudentRequest;
import org.example.studentmanagement.dto.request.SubjectRequest;
import org.example.studentmanagement.dto.response.StudentResponse;
import org.example.studentmanagement.dto.response.SubjectResponse;
import org.example.studentmanagement.entities.Student;
import org.example.studentmanagement.entities.Subject;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.studentmanagement.repository.SubjectRepository;
import org.example.studentmanagement.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.example.studentmanagement.service.SubjectService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/student")
@Slf4j
public class StudentController {
    StudentService studentService;
    // CREATE
    @PostMapping("/create")
    public ResponseEntity<StudentResponse> createStudent(@RequestBody  @Valid StudentRequest newStudent) {
        log.info("Received request to create student: {}", newStudent);
        try {
            StudentResponse student = studentService.createStudent(newStudent);
            return ResponseEntity.status(HttpStatus.CREATED).body(student);
        } catch (Exception e) {
            log.error("Error creating student: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // READ ALL
    @GetMapping("/get-all")
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        log.info("Received request to get all students.");
        try {
            List<StudentResponse> students = studentService.getAllStudents();
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            log.error("Error fetching students: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    // UPDATE
    @PutMapping("update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody StudentRequest updatedStudent) {
        log.info("Received request to update student with id {}: {}", id, updatedStudent);
        try {
            Student student = studentService.updateStudent(id, updatedStudent);
            return ResponseEntity.ok(student);
        } catch (Exception e) {
            log.error("Error updating student with id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        log.info("Received request to delete student with id: {}", id);
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.ok("Delete student successful");
        } catch (Exception e) {
            log.error("Error deleting student with id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting student");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchStudents(
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String maSv) {
        log.info("Received request to search students with name: {}, email: {}, maSv: {}", fullName, email, maSv);
        try {
            List<Student> students = studentService.searchStudents(fullName, email, maSv);
            if (students.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(students); // Không tìm thấy sinh viên
            }
            return ResponseEntity.ok(students); // Trả về kết quả tìm kiếm
        } catch (Exception e) {
            log.error("Error searching students: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Lỗi server
        }
    }



}