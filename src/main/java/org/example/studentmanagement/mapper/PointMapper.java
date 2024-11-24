package org.example.studentmanagement.mapper;

import org.example.studentmanagement.dto.request.PointRequest;
import org.example.studentmanagement.dto.request.StudentRequest;
import org.example.studentmanagement.dto.response.PointResponse;
import org.example.studentmanagement.dto.response.StudentResponse;
import org.example.studentmanagement.entities.Point;
import org.example.studentmanagement.entities.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
@Mapper(componentModel = "spring")
public interface PointMapper {
    @Mapping(target = "studentId", source = "point.student.maSv")
    @Mapping(target = "subjectId", source = "point.subject.maMh")
    PointResponse toPointResponse(Point point);

    @Mapping(target = "pointRequest.subjectId", ignore = true)
    @Mapping(target = "pointRequest.studentId", ignore = true)
    Point toPoint(PointRequest pointRequest);

    void updatePoint(@MappingTarget Point point, PointRequest pointRequest );

    List<PointResponse> toPointResponseList(List<Point> points);
}
