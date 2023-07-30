package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final Map<Long, Faculty> facultyMap = new HashMap<>();
    private Long generatedFacultyId = 1L;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(generatedFacultyId++);
        facultyMap.put(faculty.getId(), faculty);
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

    //    public Collection<Faculty> getFacultyByColor(String color) {
//        return facultyMap.values().stream()
//                .filter(f -> f.getColor().equalsIgnoreCase(color))
//                .collect(Collectors.toList());
//    }
    public Collection<Faculty> getFacultyByColor(String color) {
        List<Faculty> result = new ArrayList<>();
        for (Faculty faculty : facultyMap.values()) {
            if (Objects.equals(faculty.getColor(), color)) {
                result.add(faculty);
            }
        }
        return result;
    }
}
