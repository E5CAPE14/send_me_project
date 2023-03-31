package ru.team.sm.applicationsendme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.team.sm.applicationsendme.dao.abstracts.UserDao;
import ru.team.sm.applicationsendme.model.user.entity.User;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController("/api/image")
public class FileController {
    @Value("${upload.path}")
    private String uploadPath;
    private final UserDao userDao;
    @Autowired
    public FileController(UserDao userDao) {
        this.userDao = userDao;
    }


    @PostMapping("/save")
    public ResponseEntity<?> uploadImage(@AuthenticationPrincipal User user,
                                         @RequestParam("file")MultipartFile file) throws IOException {
        if(file != null){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "\\" + resultFilename));
            user.setLinkImage(resultFilename);
        }
        userDao.update(user);
        return ResponseEntity.ok("Файл загружен.");
    }

}
