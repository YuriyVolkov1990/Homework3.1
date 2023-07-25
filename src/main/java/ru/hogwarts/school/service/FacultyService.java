package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;
@Service
public class FacultyService {
    private final Map<Long, Faculty> facultyMap = new HashMap<>();
    private Long generatedFacultyId = 1L;

    public Faculty createFaculty(Faculty faculty) {
        facultyMap.put(generatedFacultyId, faculty);
        generatedFacultyId++;
        return faculty;
    }
    public Faculty readFacultyById(Long studentId) {
        return facultyMap.get(studentId);
    }

    public Faculty updateFaculty(Long studentId, Faculty faculty) {
        facultyMap.put(studentId, faculty);
        return faculty;
    }
    public Faculty deleteFaculty(Long studentId) {
        return facultyMap.remove(studentId);
    }

}
