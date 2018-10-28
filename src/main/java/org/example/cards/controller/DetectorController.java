package org.example.cards.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.example.cards.utils.Detector;
import org.yaml.snakeyaml.util.ArrayUtils;

@Controller
public class DetectorController {
    @CrossOrigin
    @PostMapping(path = "/detector")
    @ResponseBody
    public List<String> heandleDetectFileUpload(@RequestParam("file") MultipartFile file) {
        ArrayList<String> results = new ArrayList<>();
        try {
            System.out.println("Detect name: " + file.getOriginalFilename() +
                    "Size: " + file.getSize());
            String location = "/Users/charlieliu/Documents/codes/springserver/src/main/resources/img/" + file.getOriginalFilename();
            File fileToSave = new File(location);
            file.transferTo(fileToSave);
            try {
                results = Detector.detectLabels(location, System.out);
                for (String s : results) {
                    System.out.println(s);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
            results.clear();
            results.add(ResponseEntity.
                    status(HttpStatus.INTERNAL_SERVER_ERROR).build().toString());
        }
        results.add(ResponseEntity.status(HttpStatus.OK).build().toString());
        return results;
    }
}
