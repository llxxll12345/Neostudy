package org.example.cards.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class FileController {
    @CrossOrigin
    @PostMapping(path = "/files")
    public ResponseEntity heandleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            System.out.println("File name: " + file.getOriginalFilename() +
                    "Size: " + file.getSize());
            File fileToSave = new File("/Users/charlieliu/Documents/codes/springserver/src/main/resources/img/" + file.getOriginalFilename());
            file.transferTo(fileToSave);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.
                    status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }
}
