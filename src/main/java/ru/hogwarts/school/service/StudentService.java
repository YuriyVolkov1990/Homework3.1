package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;
@Service
public class StudentService {
    private final Map<Long, Student> studentMap = new HashMap<>();
    private Long generatedStudentId = 1L;

    public Student createStudent(Student student) {
        studentMap.put(generatedStudentId, student);
        generatedStudentId++;
        return student;
    }
    public Student readStudentById(Long studentId) {
        return studentMap.get(studentId);
    }

    public Student updateStudent(Long studentId, Student student) {
        studentMap.put(studentId, student);
        return student;
    }
    public Student deleteStudent(Long studentId) {
        return studentMap.remove(studentId);
    }
}
