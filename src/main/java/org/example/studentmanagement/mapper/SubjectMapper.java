package org.example.studentmanagement.mapper;

import org.example.studentmanagement.dto.request.StudentRequest;
import org.example.studentmanagement.dto.request.SubjectRequest;
import org.example.studentmanagement.dto.response.StudentResponse;
import org.example.studentmanagement.dto.response.SubjectResponse;
import org.example.studentmanagement.entities.Student;
import org.example.studentmanagement.entities.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
@Mapper(componentModel = "spring")
public interface SubjectMapper {
    SubjectResponse toSubjectResponse(Subject student);
    Subject toSubject(SubjectRequest student);
    void updateSubject(@MappingTarget Subject student, SubjectRequest studentRequest );
    List<SubjectResponse> toSubjectResponseList(List<Subject> students);
}
