package ru.hagwarts.testresttemplate;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.Application;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
        testRestTemplate.postForEntity("/student", GARRY, Student.class);
        testRestTemplate.postForEntity("/student", DRAKO, Student.class);
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
    @Test
    void getById() {
        ResponseEntity<Student> student = createStudent("Germ", 15);
        assertThat(student.getBody()).isNotNull();
        Long id = student.getBody().getId();
        ResponseEntity<Student> resp = testRestTemplate.getForEntity("/student/" + id, Student.class);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().getId()).isEqualTo(id);
        assertThat(resp.getBody().getName()).isEqualTo("Germ");
        assertThat(resp.getBody().getAge()).isEqualTo(15);
    }
    @Test
    void getAll() {
        ResponseEntity<Collection> forEntity = testRestTemplate.getForEntity("/student", Collection.class);
        assertThat(forEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(forEntity.getBody()).isNotNull();
        Collection<Student> body = forEntity.getBody();
        assertThat(body.isEmpty()).isFalse();
        assertThat(body.size()).isEqualTo(2);
    }
    @Test
    void update() {
        ResponseEntity<Student> response = createStudent("Germ", 15);

        Student student = response.getBody();
        assertThat(student).isNotNull();
        student.setAge(20);
        testRestTemplate.put("/student", student);

        response = testRestTemplate.getForEntity("/student/" + student.getId(), Student.class);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getAge()).isEqualTo(20);
    }
    @Test
    void delete() {
        ResponseEntity<Student> response = createStudent("Germ", 15);
        assertThat(response.getBody()).isNotNull();
        testRestTemplate.delete("/student/" + response.getBody().getId());
        response = testRestTemplate.getForEntity("/student/" + response.getBody().getId(), Student.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    @Test
    void filtered() {
        ResponseEntity<Collection> response = testRestTemplate.getForEntity("/student/filtered?age=17", Collection.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isEqualTo(1);
    }
    @Test
    void byFaculty() {
        ResponseEntity<Collection> response = testRestTemplate.getForEntity("/student", Collection.class);
        Collection expectedCollection = response.getBody();
        System.out.println("================");
        System.out.println(expectedCollection);//взял коллекцию из init(), faculty=null
        System.out.println("================");
        Faculty faculty = new Faculty(1L,"dfsdf","rerew");
        faculty.setStudents((List<Student>) response.getBody());
        System.out.println("---------------");
        System.out.println(faculty.getStudents());//привязал коллекцию студентов к факультету через setStudents, получаю обратно коллекцию через getStudents, но всё равно faculty=null
        System.out.println("---------------");
        ResponseEntity<Faculty> facultyResp = testRestTemplate.postForEntity("/faculty", faculty, Faculty.class);
        assertThat(facultyResp.getBody()).isNotNull();
        Long facultyId = facultyResp.getBody().getId();
        response = testRestTemplate.getForEntity("/student/by-faculty?facultyId=" + facultyId, Collection.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        System.out.println("++++++++++++++++");
        System.out.println(response.getBody());//после попытки достать студентов по номеру факультета получаю пустую коллекцию(видимо потому, что faculty=null)
        System.out.println("++++++++++++++++");
        assertThat(response.getBody()).isEqualTo(expectedCollection);
        // не понимаю, что не так
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
