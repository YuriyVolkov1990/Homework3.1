package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.dto.AvatarDto;
import ru.hogwarts.school.exception.AvatarNotFoundException;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.lang.String;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvatarService {
    private static final Logger logger = LoggerFactory.getLogger(AvatarService.class);
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
        logger.info("Запускаем метод uploadAvatar");
        logger.debug("Размер файла = " + avatarFile.getSize());
        Files.createDirectories(avatarPath);
        int dotIndex = avatarFile.getOriginalFilename().lastIndexOf(".");
        String fileExtension = avatarFile.getOriginalFilename().substring(dotIndex + 1);
        Path filePath = avatarPath.resolve(studentId + "." + fileExtension);
        byte[] data = avatarFile.getBytes();
        Files.write(filePath, data, StandardOpenOption.CREATE);

        Student studentReference = studentRepository.getReferenceById(studentId);
        Avatar avatar = avatarRepository.findFirstByStudent(studentReference).orElse(new Avatar());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setData(data);
        avatar.setFilePath(filePath.toAbsolutePath().toString());
        avatarRepository.save(avatar);
        return avatar.getId();
    }

    public Avatar getById(Long id) {
        logger.info("Запускаем метод getById");
        return avatarRepository.findById(id).orElseThrow(AvatarNotFoundException::new);
    }

    public List<AvatarDto> getPage(int num, int elementNum) {
        logger.info("Запускаем метод getPage");
        return avatarRepository.findAll(PageRequest.of(num, elementNum))
                .getContent()
                .stream()
                .map(a -> new AvatarDto(a.getId(), a.getStudent().getId(), a.getStudent().getName()))
                .collect(Collectors.toList());
    }
}