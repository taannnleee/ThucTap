package org.example.studentmanagement.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.studentmanagement.dto.request.PointRequest;
import org.example.studentmanagement.dto.response.PointResponse;
import org.example.studentmanagement.entities.Point;
import org.example.studentmanagement.entities.Student;
import org.example.studentmanagement.entities.Subject;
import org.example.studentmanagement.mapper.PointMapper;
import org.example.studentmanagement.repository.PointRepository;
import org.example.studentmanagement.repository.StudentRepository;
import org.example.studentmanagement.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Service
public class PointService {
    PointRepository pointRepository;
    StudentService studentService;
    SubjectService subjectService;

    PointMapper pointMapper;

    public PointResponse createPoint(PointRequest newPoint) {
        Point point = pointMapper.toPoint(newPoint);

        // lấy đối tượng student và subject tư id truyền xuống
        Student student =  studentService.getStudentById(Long.valueOf(newPoint.getStudentId()));
        Subject subject =  subjectService.getSubjectById(Long.valueOf(newPoint.getSubjectId()));

        point.setStudent(student);
        point.setSubject(subject);
        Point pointTemp = pointRepository.save(point);


        PointResponse pointResponse = pointMapper.toPointResponse(pointTemp);
        return pointResponse;
    }

    public List<PointResponse> getAllPoints() {
        return pointMapper.toPointResponseList(pointRepository.findAll());
    }

    public Point updatePoint(Long id, PointRequest updatedPoint) {
        Point point = getPointById(id);
        pointMapper.updatePoint(point, updatedPoint);
        point = pointRepository.save(point);
        return point;
    }

    public Point getPointById(Long id) {
        Point point = pointRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Point with id " + id + " not found")
        );
        return point;
    }

    public void deletePoint(Long id) {
        Point point =  getPointById(id);
        pointRepository.deleteById(point.getMaDiem());
//        studentService.deleteStudent(point.getStudent().getMaSv());
//        subjectService.deleteSubject(point.getSubject().getMaMh());

    }
//
//    public List<Point> searchPoints(Long studentId, Long subjectId) {
//        try {
//            if (studentId != null && subjectId != null) {
//                // Tìm kiếm theo cả studentId và subjectId
//                return pointRepository.findByStudentIdAndSubjectId(studentId, subjectId);
//            } else if (studentId != null) {
//                // Tìm kiếm theo studentId
//                return pointRepository.findByStudentId(studentId);
//            } else if (subjectId != null) {
//                // Tìm kiếm theo subjectId
//                return pointRepository.findBySubjectId(subjectId);
//            } else {
//                // Trả về tất cả nếu không có tham số tìm kiếm
//                return pointRepository.findAll();
//            }
//        } catch (Exception e) {
//
//            throw e; // Hoặc xử lý exception phù hợp
//        }
//    }

}
