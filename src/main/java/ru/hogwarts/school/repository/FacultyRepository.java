package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {

    public Faculty createFaculty(Faculty faculty);

    public Faculty readFacultyById(Long facultyId);

    public Faculty updateFaculty(Long facultyId, Faculty faculty);

    public Faculty deleteFaculty(Long facultyId);

    public Collection<Faculty> getFacultyByColor(String color);
}
