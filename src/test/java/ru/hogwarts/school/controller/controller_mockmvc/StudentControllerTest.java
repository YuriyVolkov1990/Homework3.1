package ru.hogwarts.school.controller.controller_mockmvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {
    @SpyBean
    StudentService studentService;
    @MockBean
    StudentRepository studentRepository;
    @MockBean
    FacultyRepository facultyRepository;
    @MockBean
    AvatarService avatarService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getById() throws Exception {
        Student student = new Student(1L,"ivan",20);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        mockMvc.perform(MockMvcRequestBuilders.get("/student/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("ivan"))
                .andExpect(jsonPath("$.age").value(20));
    }

    @Test
    void create() throws Exception {
        Student student = new Student(1L,"ivan",20);
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        mockMvc.perform(post("/student")
                .content(objectMapper.writeValueAsString(student))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("ivan"))
                .andExpect(jsonPath("$.age").value(20));
    }

    @Test
    void update() throws Exception {// с этим методом возникает проблема, я её пока не решил, думаю
        Student student = new Student(1L,"ivan",20);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        mockMvc.perform(put("/student/" + student.getId())
                        .content(objectMapper.writeValueAsString(student))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("ivan"))
                .andExpect(jsonPath("$.age").value(20));
    }
    @Test
    void delete_s() throws Exception {
        Student student = new Student(1L,"ivan",20);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        mockMvc.perform(delete("/student/" + student.getId())
                        .content(objectMapper.writeValueAsString(student))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("ivan"))
                .andExpect(jsonPath("$.age").value(20));
    }

    @Test
    void ageBetweenTest() throws Exception {
        when(studentRepository.findAllByAgeBetween(0, 20)).thenReturn(Arrays.asList(
                new Student(1L, "ivan", 20),
                new Student(2L, "dima", 21)
        ));
        mockMvc.perform(MockMvcRequestBuilders.get("/student/age-between?min=0&max=20")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
        }
}
