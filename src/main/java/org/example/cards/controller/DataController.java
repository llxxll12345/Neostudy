package org.example.cards.controller;

import org.example.cards.data.CardRepository;
import org.example.cards.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/Cards")
public class DataController {
    @Autowired
    private CardRepository cardRepository;

    @GetMapping(path="/add")
    public @ResponseBody String addNewUser(@RequestParam String name,
                                           @RequestParam String translation,
                                           @RequestParam String link) {
        Card card = new Card(name, translation, link);
        cardRepository.save(card);
        System.out.println("saved: " + name + ", " + translation + ", " + link);
        System.out.print(cardRepository.findById(1));
        return "Saved";
    }

    @RequestMapping(path="/")
    public @ResponseBody String getDefault() {
        return "User system";
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Card getCardById(@PathVariable int id) {
        return cardRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException());
    }

    @CrossOrigin
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Card> getAllUsers() {
        System.out.println("Request received!");
        // This returns a JSON or XML with the users
        return cardRepository.findAll();
    }
}
