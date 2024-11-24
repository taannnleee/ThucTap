package org.example.studentmanagement.repository;

import org.example.studentmanagement.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long > {
    @Query("SELECT s FROM Student s " +
            "WHERE (:fullName IS NULL OR LOWER(s.fullName) LIKE LOWER(CONCAT('%', :fullName, '%'))) " +
            "AND (:email IS NULL OR LOWER(s.email) LIKE LOWER(CONCAT('%', :email, '%'))) " +
            "AND (:maSv IS NULL OR s.maSv = :maSv)")
    List<Student> searchByCriteria(@Param("fullName") String fullName,
                                   @Param("email") String email,
                                   @Param("maSv") String maSv);
}
