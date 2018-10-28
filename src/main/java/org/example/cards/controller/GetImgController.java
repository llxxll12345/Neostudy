package org.example.cards.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import javax.activation.FileTypeMap;
import javax.activation.MimetypesFileTypeMap;

@Controller
public class GetImgController {

    final String base = "/Users/charlieliu/Documents/codes/springserver/src/main/";

    @CrossOrigin
    @GetMapping(path = "/img", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> getImage(String name) throws IOException {

        ClassPathResource imgFile = new ClassPathResource("img/" + name);
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }
}
