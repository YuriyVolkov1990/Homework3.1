package ru.hogwarts.repository.impl;

import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class StudentRepositoryTest {
 @Test
public void createReadDeleteUpdate() {
    Student garry = new Student(0L, "Garry", 16);
    Student ron = new Student(1L, "Ron", 16);
    Map<Long,Student> e = new HashMap<>();
    e.put(garry.getId(), garry);
    e.put(ron.getId(), ron);
    assertEquals(garry, e.get(0L));
    assertEquals(ron, e.get(1L));
    e.remove(1L);
    assertNotEquals(ron, e.get(1L));
    Student i = new Student(1L, "i", 32);
    e.put(1L, i);
    assertEquals(i,e.get(1L));
 }

}
