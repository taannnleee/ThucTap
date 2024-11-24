package org.example.studentmanagement.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.studentmanagement.dto.request.PointRequest;
import org.example.studentmanagement.dto.response.PointResponse;
import org.example.studentmanagement.entities.Point;
import org.example.studentmanagement.service.PointService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/point")
@Slf4j
public class PointController {
    PointService pointService;

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<PointResponse> createPoint(@RequestBody @Valid PointRequest newPoint) {
        log.info("Received request to create point: {}", newPoint);
        try {
            PointResponse point = pointService.createPoint(newPoint);
            return ResponseEntity.status(HttpStatus.CREATED).body(point);
        } catch (Exception e) {
            log.error("Error creating point: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // READ ALL
    @GetMapping("/get-all")
    public ResponseEntity<List<PointResponse>> getAllPoints() {
        log.info("Received request to get all points.");
        try {
            List<PointResponse> points = pointService.getAllPoints();
            return ResponseEntity.ok(points);
        } catch (Exception e) {
            log.error("Error fetching points: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // UPDATE
    @PutMapping("update/{id}")
    public ResponseEntity<Point> updatePoint(@PathVariable Long id, @RequestBody PointRequest updatedPoint) {
        log.info("Received request to update point with id {}: {}", id, updatedPoint);
        try {
            Point point = pointService.updatePoint(id, updatedPoint);
            return ResponseEntity.ok(point);
        } catch (Exception e) {
            log.error("Error updating point with id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePoint(@PathVariable Long id) {
        log.info("Received request to delete point with id: {}", id);
        try {
            pointService.deletePoint(id);
            return ResponseEntity.ok("Delete point successful");
        } catch (Exception e) {
            log.error("Error deleting point with id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting point");
        }
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<Point>> searchPoints(
//            @RequestParam(required = false) Long studentId,
//            @RequestParam(required = false) Long subjectId) {
//        log.info("Received request to search points with studentId: {}, subjectId: {}", studentId, subjectId);
//        try {
//            List<Point> points = pointService.searchPoints(studentId, subjectId);
//            if (points.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(points);
//            }
//            return ResponseEntity.ok(points);
//        } catch (Exception e) {
//            log.error("Error searching points: {}", e.getMessage(), e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
}