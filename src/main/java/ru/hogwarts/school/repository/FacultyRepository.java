package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {

    Faculty createFaculty(Faculty faculty);

    Faculty readFacultyById(Long facultyId);

    Faculty updateFaculty(Long facultyId, Faculty faculty);

    Faculty deleteFaculty(Long facultyId);

    Collection<Faculty> getFacultyByColor(String color);
}
