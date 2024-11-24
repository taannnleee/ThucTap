package org.example.studentmanagement.dto.request;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubjectRequest {
    String name; // Tên môn học
    String credit; // Số tín chỉ
    String description; // Mô tả môn học
}
