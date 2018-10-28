package org.example.cards.controller;

import org.example.cards.config.GlobalConfig;
import org.example.cards.data.CardRepository;
import org.example.cards.model.Card;
import org.example.cards.utils.MyTranslator;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private CardRepository cardRepository;

    final String base = "/Users/charlieliu/Documents/codes/springserver/src/main/";
    final String baseUrl = "http://128.61.45.90:8080/img?name=";

    @CrossOrigin
    @PostMapping(path = "/detector")
    @ResponseBody
    public ResponseEntity heandleDetectFileUpload(@RequestParam("file") MultipartFile file) {
        ArrayList<String> results = new ArrayList<>();
        try {
            System.out.println("Detect name: " + file.getOriginalFilename() +
                    "Size: " + file.getSize());
            String location = base + "resources/img/" + file.getOriginalFilename();
            File fileToSave = new File(location);
            try {
                file.transferTo(fileToSave);
                results = Detector.detectLabels(location, System.out);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.
                        status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            for (String s : results) {
                System.out.println(s);
            }
            String text = results.get(0);
            try {
                System.out.println("from: " + GlobalConfig.getFrom() + "to: " + GlobalConfig.getTo());
                String texts = MyTranslator.translate(text, GlobalConfig.getFrom(), GlobalConfig.getTo());
                cardRepository.save(new Card(text, texts, baseUrl + file.getOriginalFilename()));
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.
                        status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            return ResponseEntity.
                    status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            results.clear();
            return ResponseEntity.
                    status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
