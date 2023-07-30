package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.*;

@Service
public class StudentService {
    private final Map<Long, Student> studentMap = new HashMap<>();
    private Long generatedStudentId = 0L;

    public Student createStudent(Student student) {
        student.setId(generatedStudentId++);
        studentMap.put(student.getId(), student);
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

    private int getAge(Student student) {
        return student.getAge();
    }

    //    public Collection<Student> getStudentsByAge(int age) {
//        return studentMap.values().stream()
//                .filter(s -> s.getAge() == age)
//                .collect(Collectors.toList());
//}
    public Collection<Student> getStudentsByAge(int age) {
        List<Student> result = new ArrayList<>();
        for (Student student : studentMap.values()) {
            if (student.getAge() == age) {
                result.add(student);
            }
        }
        return result;
    }
}

