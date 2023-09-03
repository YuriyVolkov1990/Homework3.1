package ru.hogwarts.school.dto;

public class AvatarDto {
    private Long id;
    private Long studentId;
    private String name;

    public AvatarDto() {
    }

    public AvatarDto(Long id, Long studentId, String name) {
        this.id = id;
        this.studentId = studentId;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
