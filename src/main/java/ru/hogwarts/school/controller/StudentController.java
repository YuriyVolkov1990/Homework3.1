package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.StudentService;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> readStudentById(@PathVariable Long id) {
        Student readedStudent = studentService.readStudentById(id);
        if (readedStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(readedStudent);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(student.getId(), student);
        if (updatedStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        readStudentById(id);
        Student deletedStudent = studentService.deleteStudent(id);
        return ResponseEntity.ok(deletedStudent);
    }

    @GetMapping()
    public ResponseEntity<Collection<Student>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/filtered")
    public ResponseEntity<Collection<Student>> getStudentsByAge(@RequestParam(required = false) int age) {
        if (age > 0) {
            return ResponseEntity.ok(studentService.getStudentsByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/age-between")
    public Collection<Student> ageBetween(@RequestParam int min, @RequestParam int max) {
        return studentService.getByAge(min,max);
    }
}
