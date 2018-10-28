package org.example.cards.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class GetImgController {
    @CrossOrigin
    @GetMapping(path = "/getImg")
    @ResponseBody
    public String handleImageGet(@RequestParam String filename) {
        return filename;
    }
}
