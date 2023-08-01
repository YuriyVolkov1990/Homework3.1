package ru.hogwarts.school.repository.impl;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;
import java.util.function.Function;

public class FacultyRepositoryImpl implements FacultyRepository {
    private final Map<Long, Faculty> facultyMap = new HashMap<>();
    private Long generatedFacultyId = 0L;
    @Override
    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(generatedFacultyId++);
        facultyMap.put(faculty.getId(), faculty);
        return faculty;
    }

    @Override
    public Faculty readFacultyById(Long facultyId) {
        return facultyMap.get(facultyId);
    }

    @Override
    public Faculty updateFaculty(Long facultyId, Faculty faculty) {
        facultyMap.put(facultyId, faculty);
        return faculty;
    }

    @Override
    public Faculty deleteFaculty(Long facultyId) {
        return facultyMap.remove(facultyId);
    }


    @Override
    public Collection<Faculty> getFacultyByColor(String color) {
        List<Faculty> result = new ArrayList<>();
        for (Faculty faculty : facultyMap.values()) {
            if (Objects.equals(faculty.getColor(), color)) {
                result.add(faculty);
            }
        }
        return result;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Faculty> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Faculty> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Faculty> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Faculty getOne(Long aLong) {
        return null;
    }

    @Override
    public Faculty getById(Long aLong) {
        return null;
    }

    @Override
    public Faculty getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Faculty> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Faculty> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Faculty> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Faculty> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Faculty> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Faculty> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Faculty, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Faculty> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Faculty> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Faculty> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<Faculty> findAll() {
        return null;
    }

    @Override
    public List<Faculty> findAllById(Iterable<Long> longs) {
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
    public void delete(Faculty entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Faculty> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Faculty> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Faculty> findAll(Pageable pageable) {
        return null;
    }
    //    public Collection<Faculty> getFacultyByColor(String color) {
//        return facultyMap.values().stream()
//                .filter(f -> f.getColor().equalsIgnoreCase(color))
//                .collect(Collectors.toList());
//    }
}
