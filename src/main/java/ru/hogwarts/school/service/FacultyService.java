package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.createFaculty(faculty);
    }

    public Faculty readFacultyById(Long facultyId) {
        return facultyRepository.readFacultyById(facultyId);
    }

    public Faculty updateFaculty(Long facultyId, Faculty faculty) {
        return facultyRepository.updateFaculty(facultyId, faculty);
    }

    public Faculty deleteFaculty(Long facultyId) {
        return facultyRepository.deleteFaculty(facultyId);
    }


    public Collection<Faculty> getFacultyByColor(String color) {
        return facultyRepository.getFacultyByColor(color);
    }
}
