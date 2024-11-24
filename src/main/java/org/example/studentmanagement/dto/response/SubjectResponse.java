package org.example.studentmanagement.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubjectResponse {
    Long maMh;
    String name; // Tên môn học
    String credit; // Số tín chỉ
    String description; // Mô tả môn học
}
