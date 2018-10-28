package org.example.cards.controller;

import org.example.cards.config.GlobalConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    @CrossOrigin
    @GetMapping(path = "/setFromTo")
    @ResponseBody
    public String setFromTo(@RequestParam String from, @RequestParam String to){
        GlobalConfig.setFrom(from);
        GlobalConfig.setTo(to);
        return "Success";
    }
}
