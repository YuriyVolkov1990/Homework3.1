package ru.hagwarts.controller_mockmvc;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.Application;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FacultyControllerTest {
    public static final Faculty PHIL = new Faculty(null, "phil", "red");
    public static final Faculty LANG = new Faculty(null, "lang", "black");
    @Autowired
    TestRestTemplate testRestTemplate;
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    StudentRepository studentRepository;
    @BeforeEach
    void init() {
        testRestTemplate.postForEntity("/faculty", PHIL, Faculty.class);
        testRestTemplate.postForEntity("/faculty", LANG, Faculty.class);
    }

    @AfterEach
    void clearDb() {
        studentRepository.deleteAll();
        facultyRepository.deleteAll();
    }
    @Test
    void create() {
        ResponseEntity<Faculty> resp = createFaculty("math", "blue");
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().getName()).isEqualTo("math");
        assertThat(resp.getBody().getColor()).isEqualTo("blue");
    }
    @Test
    void getById() {
        ResponseEntity<Faculty> faculty = createFaculty("math", "blue");
        assertThat(faculty.getBody()).isNotNull();
        Long id = faculty.getBody().getId();
        ResponseEntity<Faculty> resp = testRestTemplate.getForEntity("/faculty/" + id, Faculty.class);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().getId()).isEqualTo(id);
        assertThat(resp.getBody().getName()).isEqualTo("math");
        assertThat(resp.getBody().getColor()).isEqualTo("blue");
    }

    @Test
    void getAll() {
        ResponseEntity<Collection> forEntity = testRestTemplate.getForEntity("/faculty", Collection.class);
        assertThat(forEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(forEntity.getBody()).isNotNull();
        Collection<Faculty> body = forEntity.getBody();
        assertThat(body.isEmpty()).isFalse();
        assertThat(body.size()).isEqualTo(2);
//        assertThat(body).contains(PHIL);
//        assertThat(body).contains(LANG);
    }
    @Test
    void update() {
        ResponseEntity<Faculty> response = createFaculty("math", "blue");

        Faculty faculty = response.getBody();
        assertThat(faculty).isNotNull();
        faculty.setColor("red");

        testRestTemplate.put("/faculty/" + faculty.getId(), faculty);

        response = testRestTemplate.getForEntity("/faculty/" + faculty.getId(), Faculty.class);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getColor()).isEqualTo("red");

    }
    @Test
    void delete() {
        ResponseEntity<Faculty> response = createFaculty("math", "blue");
        testRestTemplate.delete("/faculty/" + response.getBody().getId());
        response = testRestTemplate.getForEntity("/faculty/" + response.getBody().getId(), Faculty.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    @Test
    void filtered() {
        ResponseEntity<Collection> response = testRestTemplate.getForEntity("/faculty/filtered?color=black", Collection.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isEqualTo(1);
    }

    @Test
    void byStudent() {
        ResponseEntity<Faculty> faculty = createFaculty("math", "blue");
        Faculty expectedFaculty = faculty.getBody();
        Student student = new Student();
        student.setFaculty(expectedFaculty);
        ResponseEntity<Student> studentResp = testRestTemplate.postForEntity("/student", student, Student.class);
        assertThat(studentResp.getBody()).isNotNull();
        Long studentId = studentResp.getBody().getId();
        faculty = testRestTemplate.getForEntity("/faculty/by-student?studentId=" + studentId,Faculty.class);
        assertThat(faculty.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(faculty.getBody()).isNotNull();
        assertThat(faculty.getBody()).isEqualTo(expectedFaculty);
    }
    @Test
    void filteredByColor() {
        ResponseEntity<Collection> response = testRestTemplate.getForEntity("/faculty/by-color-or-name?colorOrName=black", Collection.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isEqualTo(1);
    }
    private ResponseEntity<Faculty> createFaculty(String name, String color) {
        Faculty request = new Faculty();
        request.setName(name);
        request.setColor(color);
        ResponseEntity<Faculty> resp = testRestTemplate.postForEntity("/faculty", request, Faculty.class);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resp.getBody()).isNotNull();
        return resp;
    }
}
