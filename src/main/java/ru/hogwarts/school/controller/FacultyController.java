package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty createdFaculty = facultyService.createFaculty(faculty);
        return ResponseEntity.ok(createdFaculty);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> readFacultyById(@PathVariable Long id) {
        Faculty readedFaculty = facultyService.readFacultyById(id);
        if (readedFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(readedFaculty);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable Long id, @RequestBody Faculty faculty) {
        Faculty updatedFaculty = facultyService.updateFaculty(id, faculty);
        if (updatedFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id) {
        readFacultyById(id);
        Faculty deletedFaculty = facultyService.deleteFaculty(id);
        return ResponseEntity.ok(deletedFaculty);
    }

    @GetMapping()
    public ResponseEntity<Collection<Faculty>> getAll() {
        return ResponseEntity.ok(facultyService.getAll());
    }

    @GetMapping("/filtered")
    public ResponseEntity<List<Faculty>> getFacultyByColor(@RequestParam(required = false) String color) {
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.getFacultyByColor(color));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/by-color-or-name")
    public Collection<Faculty> filteredByColorOrName(@RequestParam String colorOrName) {
        return facultyService.getAllByNameOrColor(colorOrName, colorOrName);
    }

    @GetMapping("/by-student")
    public Faculty getByStudent(Long studentId) {
        return facultyService.getByStudentId(studentId);
    }
}
