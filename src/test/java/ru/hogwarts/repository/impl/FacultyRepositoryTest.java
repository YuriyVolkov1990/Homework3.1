package ru.hogwarts.repository.impl;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
import java.util.Map;


public class FacultyRepositoryTest {
 @Test
public void createReadDeleteUpdate() {
    Faculty griffindor = new Faculty(0L, "Griffindor", "red");
    Faculty slitheryn = new Faculty(1L, "Slitheryn", "green");
    Map<Long,Faculty> e = new HashMap<>();
    e.put(griffindor.getId(), griffindor);
    e.put(slitheryn.getId(), slitheryn);
    assertEquals(griffindor, e.get(0L));
    assertEquals(slitheryn, e.get(1L));
    e.remove(1L);
    assertNotEquals(slitheryn, e.get(1L));
    Faculty fizmat = new Faculty(1L, "fizmat", "black");
    e.put(1L, fizmat);
    assertEquals(fizmat,e.get(1L));
 }

}
