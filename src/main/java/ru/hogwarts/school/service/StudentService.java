package ru.hogwarts.school.service;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.FacultyNotFoundException;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;

@Service
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final AvatarRepository avatarRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.avatarRepository = avatarRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Запускаем метод createStudent");
        return studentRepository.save(student);
    }

    public Student readStudentById(Long studentId) {
        logger.info("Запускаем метод readStudentById");
        return studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
    }

    public Student updateStudent(Long studentId, Student student) {
        logger.info("Запускаем метод updateStudent");
        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(StudentNotFoundException::new);
        existingStudent.setAge(student.getAge());
        existingStudent.setName(student.getName());
        return studentRepository.save(existingStudent);
    }
    @Transactional
    public Student deleteStudent(Long studentId) {
        logger.info("Запускаем метод deleteStudent");
        avatarRepository.deleteByStudentId(studentId);
        Student student = studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
        studentRepository.delete(student);
        return student;
    }

    public Student getById(Long studentId) {
        logger.info("Запускаем метод getById");
        return studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
    }
    public Collection<Student> getAll() {
        logger.info("Запускаем метод getAll");
        return studentRepository.findAll();
    }
    public List<Student> getStudentsByAge(int age) {
        logger.info("Запускаем метод getStudentsByAge");
        return studentRepository.findAllByAge(age);
    }
    public Collection<Student> getByAge(int min, int max) {
        logger.info("Запускаем метод getByAge");
        return studentRepository.findAllByAgeBetween(min, max);
    }

    public Collection<Student> getByFaculty(Long facultyId) {
        logger.info("Запускаем метод getByFaculty");
        return facultyRepository.findById(facultyId)
                .map(Faculty::getStudents)
                .orElseThrow(FacultyNotFoundException::new);
   }
   public Long count() {
       logger.info("Запускаем метод count");
       return studentRepository.countAll();
   }
   public Double averageAge() {
       logger.info("Запускаем метод averageAge");
       return studentRepository.averageAge();
   }

    public List<Student> getLastStudents(int num) {
        logger.info("Запускаем метод getLastStudents");
        return studentRepository.findLastStudent(num);
    }

}

