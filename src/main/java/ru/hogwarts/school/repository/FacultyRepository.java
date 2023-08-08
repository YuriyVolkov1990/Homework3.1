package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.spel.ast.OpAnd;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {
    List<Faculty> findAllByColor(String color);

    List<Faculty> findAllByColorLikeIgnoreCaseOrNameLikeIgnoreCase(String color, String name);

//    List<Faculty> findAllByStudents(List<Student> students);
}
