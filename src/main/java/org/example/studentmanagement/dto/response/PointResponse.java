package org.example.studentmanagement.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.studentmanagement.entities.Student;
import org.example.studentmanagement.entities.Subject;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PointResponse {
    Long maDiem;

    String studentId;

    String subjectId;

    Double score;

    String grade;

    String notes;
}
