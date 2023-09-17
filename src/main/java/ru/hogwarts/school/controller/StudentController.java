package ru.hogwarts.school.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;
import ru.hogwarts.school.model.Student;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final AvatarService avatarService;

    public StudentController(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarService = avatarService;
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
        return studentService.getByAge(min, max);
    }

    @GetMapping("/by-faculty")
    public Collection<Student> byFaculty(@RequestParam Long facultyId) {
        return studentService.getByFaculty(facultyId);
    }

    @PostMapping(value = "/{studentId}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> uploadAvatar(@PathVariable Long studentId, @RequestParam MultipartFile avatar) throws IOException {
        try {
            return ResponseEntity.ok(avatarService.uploadAvatar(studentId, avatar));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/count")
    public Long count() {
        return studentService.count();
    }

    @GetMapping("/average-age")
    public Double averageAge() {
        return studentService.averageAge();
    }
    @GetMapping("/lastN")
    public List<Student> getLastN(@RequestParam int num) {
        return studentService.getLastStudents(num);
    }

    @GetMapping("/stream/names-by-first-symbol")
    public List<String> getBySymbol(char symbol) {
        return studentService.getNamesStartedBy(symbol);
    }

    @GetMapping("/stream/average-age")
    public double getAverageAge() {
        return studentService.getAverageAge();
    }
}
