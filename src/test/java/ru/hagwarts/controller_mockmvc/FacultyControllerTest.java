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
import ru.hogwarts.school.repository.FacultyRepository;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FacultyControllerTest {
    @Autowired
    TestRestTemplate testRestTemplate;
    @Autowired
    FacultyRepository facultyRepository;
    @BeforeEach
    void init() {
        testRestTemplate.postForEntity("/faculty", new Faculty(null, "phil","red"), Faculty.class);
        testRestTemplate.postForEntity("/faculty", new Faculty(null, "lang", "black"), Faculty.class);
    }

    @AfterEach
    void clearDb() {
        facultyRepository.deleteAll();
    }
    @Test
    void create() {
        ResponseEntity<Faculty> resp = getFacultyResponseEntity("math", "blue");
        assertThat(resp.getBody().getName()).isEqualTo("math");
        assertThat(resp.getBody().getColor()).isEqualTo("blue");
    }
    @Test
    void getById() {
        ResponseEntity<Faculty> faculty = getFacultyResponseEntity("math", "blue");
        Long id = faculty.getBody().getId();
        ResponseEntity<Faculty> resp = testRestTemplate.getForEntity("/faculty/" + id, Faculty.class);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().getId()).isEqualTo(id);
        assertThat(resp.getBody().getName()).isEqualTo("math");
        assertThat(resp.getBody().getColor()).isEqualTo("blue");
    }

    private ResponseEntity<Faculty> getFacultyResponseEntity(String name, String color) {
        Faculty request = new Faculty();
        request.setName("math");
        request.setColor("blue");
        ResponseEntity<Faculty> resp = testRestTemplate.postForEntity("/faculty", request, Faculty.class);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resp.getBody()).isNotNull();
        return resp;
    }
}
