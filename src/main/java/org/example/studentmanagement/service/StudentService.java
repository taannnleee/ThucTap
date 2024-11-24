package org.example.studentmanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.studentmanagement.dto.request.StudentRequest;
import org.example.studentmanagement.dto.response.StudentResponse;
import org.example.studentmanagement.entities.Point;
import org.example.studentmanagement.entities.Student;
import org.example.studentmanagement.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.studentmanagement.repository.StudentRepository;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    StudentMapper studentMapper;

    public StudentResponse createStudent(StudentRequest newStudent) {
        Student student =  studentMapper.toStudent(newStudent);
        Student studentTemp = studentRepository.save(student);

        StudentResponse studentResponse = studentMapper.toStudentResponse(studentTemp);
        return studentResponse;
    }

    public List<StudentResponse> getAllStudents() {
        return studentMapper.toStudentResponseList(studentRepository.findAll());
    }

    public Student updateStudent(Long id, StudentRequest updatedStudent) {

        Student student =  getStudentById(id);
        studentMapper.updateStudent(student,updatedStudent);
        student = studentRepository.save(student);
        return student;

    }


    public Student getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Student with id " + id + " not found")
        );
        return student;
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> searchStudents(String fullName, String email, String maSv) {
        // Gọi repository để tìm kiếm
        if (!StringUtils.hasText(fullName) && !StringUtils.hasText(email) && !StringUtils.hasText(maSv)) {
            return studentRepository.findAll();
        }
        return studentRepository.searchByCriteria(fullName, email, maSv);
    }

}
