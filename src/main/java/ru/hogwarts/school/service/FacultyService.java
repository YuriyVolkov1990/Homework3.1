package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty readFacultyById(Long facultyId) {
        return facultyRepository.findById(facultyId).orElseThrow(FacultyNotFoundException::new);
    }

    public Faculty updateFaculty(Long facultyId, Faculty faculty) {
        Faculty existingFaculty = facultyRepository.findById(facultyId)
                .orElseThrow(FacultyNotFoundException::new);
        existingFaculty.setColor(faculty.getColor());
        existingFaculty.setName(faculty.getName());
        return facultyRepository.save(existingFaculty);
    }

    public Faculty deleteFaculty(Long facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId).orElseThrow(FacultyNotFoundException::new);
        facultyRepository.delete(faculty);
        return faculty;
    }

    public Faculty getById(Long facultyId) {
        return facultyRepository.findById(facultyId).orElseThrow(FacultyNotFoundException::new);
    }
    public Collection<Faculty> getAll() {
        return facultyRepository.findAll();
    }
    public List<Faculty> getFacultyByColor(String color) {
        return facultyRepository.findAllByColor(color);
    }
}
