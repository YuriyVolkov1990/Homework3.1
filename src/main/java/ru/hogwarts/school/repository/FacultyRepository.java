package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.spel.ast.OpAnd;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {
    List<Faculty> findAllByColor(String color);

    Faculty createFaculty(Faculty faculty);

    Faculty readFacultyById(Long facultyId);

    Faculty updateFaculty(Long facultyId, Faculty faculty);

    Faculty deleteFaculty(Long facultyId);

    Collection<Faculty> getFacultyByColor(String color);
}
