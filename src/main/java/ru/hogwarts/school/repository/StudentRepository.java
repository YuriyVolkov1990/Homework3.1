package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findAllByAge(int age);

    List<Student> findAllByAgeBetween(int min,int max);

    @Query(value = "select count(*) from student", nativeQuery = true)
    Long countAll();
    @Query(value = "select avg(age) from student", nativeQuery = true)
    Double averageAge();
    @Query(value = "select * from student order by id desc limit :num", nativeQuery = true)
    List<Student> findLastStudent(@Param("num") int num);
}
