package ru.hogwarts.school.model;

import jakarta.persistence.*;

@Entity
public class Avatar {
    @Id
    @GeneratedValue
    private Long id;
    private String filePAth;
    private long fileSize;
    private String mediaType;
    private byte[] data;
    @OneToOne
    private Student student;
}
