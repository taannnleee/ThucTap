package org.example.studentmanagement.repository;

import org.example.studentmanagement.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {
    @Query("SELECT s FROM Subject s " +
            "WHERE (:name IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:credit IS NULL OR s.credit = :credit) " +
            "AND (:description IS NULL OR LOWER(s.description) LIKE LOWER(CONCAT('%', :description, '%')))")
    List<Subject> searchByCriteria(@Param("name") String name,
                                   @Param("credit") Integer credit,
                                   @Param("description") String description);
}
