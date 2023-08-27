package ru.hogwarts.school.service;

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
public class StudentService {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student readStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
    }

    public Student updateStudent(Long studentId, Student student) {
        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(StudentNotFoundException::new);
        existingStudent.setAge(student.getAge());
        existingStudent.setName(student.getName());
        return studentRepository.save(existingStudent);
    }

    public Student deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
        studentRepository.delete(student);
        return student;
    }

    public Student getById(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);
    }
    public Collection<Student> getAll() {
        return studentRepository.findAll();
    }
    public List<Student> getStudentsByAge(int age) {
        return studentRepository.findAllByAge(age);
    }
    public Collection<Student> getByAge(int min, int max) {
        return studentRepository.findAllByAgeBetween(min, max);
    }

    public Collection<Student> getByFaculty(Long facultyId) {
        return facultyRepository.findById(facultyId)
                .map(Faculty::getStudents)
                .orElseThrow(FacultyNotFoundException::new);
   }
   public Long count() {
       return studentRepository.countAll();
   }
   public Double averageAge() {
       return studentRepository.averageAge();
   }

    public List<Student> getLastStudents(int num) {
        return studentRepository.findLastStudent(num);
    }

}

