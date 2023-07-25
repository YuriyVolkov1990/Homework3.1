package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

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
    public Faculty readFacultyById(Long facultyId) {
        return facultyMap.get(facultyId);
    }

    public Faculty updateFaculty(Long facultyId, Faculty faculty) {
        facultyMap.put(facultyId, faculty);
        return faculty;
    }
    public Faculty deleteFaculty(Long facultyId) {
        return facultyMap.remove(facultyId);
    }

}
