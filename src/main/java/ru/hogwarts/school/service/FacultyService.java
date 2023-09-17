package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;

@Service
public class FacultyService {
    private static final Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;
    @Autowired
    public FacultyService(FacultyRepository facultyRepository,
                          StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Запускаем метод createFaculty");
        return facultyRepository.save(faculty);
    }

    public Faculty readFacultyById(Long facultyId) {
        logger.info("Запускаем метод readFacultyById");
        return facultyRepository.findById(facultyId).orElseThrow(FacultyNotFoundException::new);
    }

    public Faculty updateFaculty(Long facultyId, Faculty faculty) {
        logger.info("Запускаем метод updateFaculty");
        Faculty existingFaculty = facultyRepository.findById(facultyId)
                .orElseThrow(FacultyNotFoundException::new);
        existingFaculty.setColor(faculty.getColor());
        existingFaculty.setName(faculty.getName());
        return facultyRepository.save(existingFaculty);
    }

    public Faculty deleteFaculty(Long facultyId) {
        logger.info("Запускаем метод deleteFaculty");
        Faculty faculty = facultyRepository.findById(facultyId).orElseThrow(FacultyNotFoundException::new);
        facultyRepository.delete(faculty);
        return faculty;
    }

    public Collection<Faculty> getAll() {
        logger.info("Запускаем метод getAll");
        return facultyRepository.findAll();
    }
    public List<Faculty> getFacultyByColor(String color) {
        logger.info("Запускаем метод getFacultyByColor");
        return facultyRepository.findAllByColor(color);
    }

    public Collection<Faculty> getAllByNameOrColor(String color, String name) {
        logger.info("Запускаем метод getAllByNameOrColor");
        return facultyRepository.findAllByColorLikeIgnoreCaseOrNameLikeIgnoreCase(color, name);
    }

    public Faculty getByStudentId(Long studentId) {
        logger.info("Запускаем метод getByStudentId");
        return studentRepository.findById(studentId)
                .map(Student::getFaculty)
                .orElseThrow(StudentNotFoundException::new);
    }

    public String getLongestName() {
        return facultyRepository.findAll()
                .stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .orElseThrow(FacultyNotFoundException::new);
    }
}
