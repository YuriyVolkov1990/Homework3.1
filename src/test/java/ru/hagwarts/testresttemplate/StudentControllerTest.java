package ru.hagwarts.testresttemplate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.Application;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StudentControllerTest {
    public static final Student GARRY = new Student(null, "Garry", 16);
    public static final Student DRAKO = new Student(null, "Drako", 17);
    @Autowired
    TestRestTemplate testRestTemplate;
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    StudentRepository studentRepository;

    @BeforeEach
    void init() {
        testRestTemplate.postForEntity("/faculty", GARRY, Student.class);
        testRestTemplate.postForEntity("/faculty", DRAKO, Student.class);
    }

    @AfterEach
    void clearDb() {
        studentRepository.deleteAll();
        facultyRepository.deleteAll();
    }

    @Test
    void create() {
        ResponseEntity<Student> resp = createStudent("Germ", 15);
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().getName()).isEqualTo("Germ");
        assertThat(resp.getBody().getAge()).isEqualTo(15);
    }

    private ResponseEntity<Student> createStudent(String name, int age) {
        Student request = new Student();
        request.setName(name);
        request.setAge(age);
        ResponseEntity<Student> resp = testRestTemplate.postForEntity("/student", request, Student.class);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resp.getBody()).isNotNull();
        return resp;
    }
}
