package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.exception.AvatarNotFoundException;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.lang.String;

@Service
public class AvatarService {
    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;
    @Value("${path.to.avatars.folder}")
    private Path avatarPath;

    @Autowired
    public AvatarService(AvatarRepository avatarRepository, StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
    }

    public Long uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        Files.createDirectories(avatarPath);
        int dotIndex = avatarFile.getOriginalFilename().lastIndexOf(".");
        String fileExtension = avatarFile.getOriginalFilename().substring(dotIndex + 1);
        Path filePath = avatarPath.resolve(studentId + "." + fileExtension);
        byte[] data = avatarFile.getBytes();
        Files.write(filePath, data, StandardOpenOption.CREATE);
        Avatar avatar = new Avatar();
        avatar.setStudent(studentRepository.getReferenceById(studentId));
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setData(data);
        avatar.setFilePath(filePath.toAbsolutePath().toString());
        avatarRepository.save(avatar);
        return avatar.getId();
    }
}