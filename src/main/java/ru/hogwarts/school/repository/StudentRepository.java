package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student,Long> {

    Student createStudent(Student student);

    Student readStudentById(Long studentId);

    Student updateStudent(Long studentId, Student student);

    Student deleteStudent(Long studentId);

    Collection<Student> getStudentsByAge(int age);

}
