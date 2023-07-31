package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;

@Service
public class StudentService {

    private final StudentRepository studentRepository ;


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.createStudent(student);
    }

    public Student readStudentById(Long studentId) {
        return studentRepository.readStudentById(studentId);
    }

    public Student updateStudent(Long studentId, Student student) {
        studentRepository.updateStudent(studentId, student);
        return student;
    }

    public Student deleteStudent(Long studentId) {
        return studentRepository.deleteStudent(studentId);
    }


    public Collection<Student> getStudentsByAge(int age) {
        return studentRepository.getStudentsByAge(age);
    }
}

