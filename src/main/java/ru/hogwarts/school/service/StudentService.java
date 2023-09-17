package ru.hogwarts.school.service;

import jakarta.transaction.Transactional;
import org.apache.commons.lang3.CharUtils;
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
import java.util.stream.Collectors;

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
    public List<String> getNamesStartedBy (char firstSymbol) {
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .filter(name -> Character.toLowerCase(name.charAt(0))
                        == Character.toLowerCase(firstSymbol))
                .collect(Collectors.toList());
    }
    public double getAverageAge() {
        return studentRepository.findAll()
                .stream()
                .mapToInt(Student::getAge)
                .average()
                .orElseThrow(StudentNotFoundException::new);
    }
    public void print() {
        List<Student> all = studentRepository.findAll();
        System.out.println(all.get(0).getName());
        System.out.println(all.get(1).getName());

        new Thread(()->{
            System.out.println(all.get(2).getName());
            System.out.println(all.get(3).getName());
        }).start();
        new Thread(()->{
            System.out.println(all.get(5).getName());
            System.out.println(all.get(6).getName());
        }).start();
    }
    public void printSync() {
        List<Student> all = studentRepository.findAll();
        printSync(all.get(0).getName());
        printSync(all.get(1).getName());

        new Thread(()->{
            printSync(all.get(2).getName());
            printSync(all.get(3).getName());
        }).start();
        new Thread(()->{
            printSync(all.get(4).getName());
            printSync(all.get(5).getName());
        }).start();
    }

    public synchronized void printSync(String name) {
        System.out.println(name);
    }
}

