package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

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
    public ResponseEntity<Faculty> readFacultyById(  Long id) {
        Faculty readedFaculty = facultyService.readFacultyById(id);
        if (readedFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(readedFaculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty updatedFaculty = facultyService.updateFaculty(faculty.getId(),faculty);
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
    @GetMapping("/filtered")
    public Collection<Faculty> getFacultyByColor(@RequestParam("color") String color) {
        return facultyService.getFacultyByColor(color);
    }
}
