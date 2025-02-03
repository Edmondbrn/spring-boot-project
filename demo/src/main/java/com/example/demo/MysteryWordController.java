package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.WordRepository;
import com.example.demo.entity.Word;

@RestController
public class MysteryWordController {

    private final WordRepository wordRepository;

    public MysteryWordController(WordRepository wordRepository){
        this.wordRepository = wordRepository;
    }

    @GetMapping("/api/word")
    public Word getMysteryWord() {
        Word randomWord = this.wordRepository.findRandomWord();
        return randomWord;
        // return "Hello, Spring Boot!";
    }
}
