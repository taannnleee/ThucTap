package org.example.studentmanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.studentmanagement.dto.request.SubjectRequest;
import org.example.studentmanagement.dto.response.SubjectResponse;
import org.example.studentmanagement.entities.Subject;
import org.example.studentmanagement.mapper.SubjectMapper;
import org.example.studentmanagement.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    SubjectMapper subjectMapper;

    public SubjectResponse createSubject(SubjectRequest newSubject) {
        Subject subject = subjectMapper.toSubject(newSubject);
        Subject subjectTemp = subjectRepository.save(subject);

        SubjectResponse subjectResponse = subjectMapper.toSubjectResponse(subjectTemp);
        return subjectResponse;
    }

    public List<SubjectResponse> getAllSubjects() {
        return subjectMapper.toSubjectResponseList(subjectRepository.findAll());
    }

    public SubjectResponse updateSubject(Long id, SubjectRequest updatedSubject) {

        Subject subject = getSubjectById(id);
        subjectMapper.updateSubject(subject, updatedSubject);
        subject = subjectRepository.save(subject);
        SubjectResponse subjectResponse = subjectMapper.toSubjectResponse(subject);
        return subjectResponse;
    }

    public Subject getSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Subject with id " + id + " not found")
        );
        return subject;
    }

    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }

    public List<Subject> searchSubjects(String name, Integer credit, String description) {
        // Search criteria check
        if (!StringUtils.hasText(name) && credit == null && !StringUtils.hasText(description)) {
            return subjectRepository.findAll(); // If no criteria, return all subjects
        }
        return subjectRepository.searchByCriteria(name, credit, description); // Apply search criteria
    }
}
