package org.example.studentmanagement.mapper;
import org.example.studentmanagement.dto.request.StudentRequest;
import org.example.studentmanagement.dto.response.StudentResponse;
import org.example.studentmanagement.entities.Student;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentResponse toStudentResponse(Student student);
    Student toStudent(StudentRequest student);
    void updateStudent(@MappingTarget Student student, StudentRequest studentRequest );
    List<StudentResponse> toStudentResponseList(List<Student> students);
}
