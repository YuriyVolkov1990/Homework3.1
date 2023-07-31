package ru.hogwarts.school.repository.impl;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;

public abstract class FacultyRepositoryImpl implements FacultyRepository {
    private final Map<Long, Faculty> facultyMap = new HashMap<>();
    private Long generatedFacultyId = 0L;
    @Override
    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(generatedFacultyId++);
        facultyMap.put(faculty.getId(), faculty);
        return faculty;
    }

    @Override
    public Faculty readFacultyById(Long facultyId) {
        return facultyMap.get(facultyId);
    }

    @Override
    public Faculty updateFaculty(Long facultyId, Faculty faculty) {
        facultyMap.put(facultyId, faculty);
        return faculty;
    }

    @Override
    public Faculty deleteFaculty(Long facultyId) {
        return facultyMap.remove(facultyId);
    }

    @Override
    public Collection<Faculty> getFacultyByColor(String color) {
        List<Faculty> result = new ArrayList<>();
        for (Faculty faculty : facultyMap.values()) {
            if (Objects.equals(faculty.getColor(), color)) {
                result.add(faculty);
            }
        }
        return result;
    }
    //    public Collection<Faculty> getFacultyByColor(String color) {
//        return facultyMap.values().stream()
//                .filter(f -> f.getColor().equalsIgnoreCase(color))
//                .collect(Collectors.toList());
//    }
}
