package ru.hogwarts.school.repository.impl;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;
import java.util.function.Function;

public class StudentRepositoryImpl implements StudentRepository {

    private final Map<Long, Student> studentMap = new HashMap<>();
    private Long generatedStudentId = 0L;
    @Override
    public Student createStudent(Student student) {
        student.setId(generatedStudentId++);
        studentMap.put(student.getId(), student);
        return student;

    }

    @Override
    public Student readStudentById(Long studentId) {
        return studentMap.get(studentId);
    }

    @Override
    public Student updateStudent(Long studentId, Student student) {
        studentMap.put(studentId, student);
        return student;
    }

    @Override
    public Student deleteStudent(Long studentId) {
        return studentMap.remove(studentId);
    }

    @Override
    public Collection<Student> getStudentsByAge(int age) {
        List<Student> result = new ArrayList<>();
        for (Student student : studentMap.values()) {
            if (student.getAge() == age) {
                result.add(student);
            }
        }
        return result;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Student> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Student> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Student> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Student getOne(Long aLong) {
        return null;
    }

    @Override
    public Student getById(Long aLong) {
        return null;
    }

    @Override
    public Student getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Student> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Student> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Student> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Student> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Student> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Student> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Student, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Student> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Student> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Student> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public List<Student> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Student entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Student> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Student> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return null;
    }
    //    public Collection<Student> getStudentsByAge(int age) {
//        return studentMap.values().stream()
//                .filter(s -> s.getAge() == age)
//                .collect(Collectors.toList());
//}
}
