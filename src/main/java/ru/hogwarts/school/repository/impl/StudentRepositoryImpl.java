package ru.hogwarts.school.repository.impl;

import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;

public abstract class StudentRepositoryImpl implements StudentRepository {

    private final Map<Long, Student> studentMap = new HashMap<>();
    private Long generatedStudentId = 0L;
    @Override
    public Student createStudent(Student student) {
        student.setId(generatedStudentId++);
        studentMap.put(student.getId(), student);
        return student;

    }

    @Override
    public Student readStudentById(Long studentId) {
        return studentMap.get(studentId);
    }

    @Override
    public Student updateStudent(Long studentId, Student student) {
        studentMap.put(studentId, student);
        return student;
    }

    @Override
    public Student deleteStudent(Long studentId) {
        return studentMap.remove(studentId);
    }

    @Override
    public Collection<Student> getStudentsByAge(int age) {
        List<Student> result = new ArrayList<>();
        for (Student student : studentMap.values()) {
            if (student.getAge() == age) {
                result.add(student);
            }
        }
        return result;
    }
    //    public Collection<Student> getStudentsByAge(int age) {
//        return studentMap.values().stream()
//                .filter(s -> s.getAge() == age)
//                .collect(Collectors.toList());
//}
}
