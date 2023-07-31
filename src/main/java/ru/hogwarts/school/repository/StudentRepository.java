package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student,Long> {

    public Student createStudent(Student student);

    public Student readStudentById(Long studentId);

    public Student updateStudent(Long studentId, Student student);

    public Student deleteStudent(Long studentId);

    public Collection<Student> getStudentsByAge(int age);

}
