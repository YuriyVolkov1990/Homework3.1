package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.StudentService;
import ru.hogwarts.school.model.Student;
@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/create")
    public Student createStudent(Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("/read")
    public Student readFacultyById(Long id) {
        return studentService.readStudentById(id);
    }

    @GetMapping("/update")
    public Student updateStudent(Long studentId, Student student) {
        return studentService.updateStudent(studentId, student);
    }

    @GetMapping("/delete")
    public Student deleteStudent(Long studentId) {
        return studentService.deleteStudent(studentId);
    }
}
