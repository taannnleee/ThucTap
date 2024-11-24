package org.example.studentmanagement.entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student  implements  Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long maSv;

    @Column(nullable = false, length = 15)
    String phone;

    @Column(nullable = false)
    String email;

    @Column(nullable = false, length = 100)
    String fullName;

    @Column(nullable = false)
    LocalDate dateOfBirth;

    @Column(length = 255)
    String imagePath;

    @Column(nullable = false)
    boolean status;
}
