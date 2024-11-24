package org.example.studentmanagement.repository;

import org.example.studentmanagement.entities.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point,Long > {


//
//    // Tìm kiếm theo studentId và subjectId
//    List<Point> findByStudentIdAndSubjectId(Long studentId, Long subjectId);
//
//    // Tìm kiếm theo studentId
//    List<Point> findByStudentId(Long studentId);
//
//    // Tìm kiếm theo subjectId
//    List<Point> findBySubjectId(Long subjectId);
}
