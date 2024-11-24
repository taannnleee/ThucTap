package org.example.studentmanagement.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentRequest {

    String phone;

    String email;

    String fullName;

    LocalDate dateOfBirth;

    String imagePath;

    boolean status;
}