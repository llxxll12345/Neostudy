package org.example.cards.controller;

import org.example.cards.utils.Translator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class TranslateController {
    @CrossOrigin
    @GetMapping(path = "/getTrans")
    @ResponseBody
    public String traslateHandle(@RequestParam String text, @RequestParam String from,
                               @RequestParam String to) {
        /*/getTrans?text=apple&from=en&ru=to*/
        String result = Translator.translate(text, from, to);
        System.out.println(result);
        return result;
    }
}