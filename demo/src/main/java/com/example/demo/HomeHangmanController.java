package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.Word;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class HomeHangmanController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public String home(Model model) {
        List<Character> alphabet10 = Arrays.asList('A','B','C','D','E','F','G','H','I','J');
        List<Character> alphabet20 = Arrays.asList('K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T');
        List<Character> alphabet30 = Arrays.asList('U', 'V', 'W', 'X', 'Y', 'Z');
        model.addAttribute("alphabet10", alphabet10);
        model.addAttribute("alphabet20", alphabet20);
        model.addAttribute("alphabet30", alphabet30);

        String apiURL = "http://localhost:8080/api/word";
        Word MysteryWord = restTemplate.getForObject(apiURL, Word.class);

        if (MysteryWord != null && MysteryWord.getDef() != null)
            model.addAttribute("mysteryDef", MysteryWord.getDef());
        else
            model.addAttribute("mysteryDef", "No definition available");


        // bloc pour vérifier si l'API a bien renvoyé quelque chose
        if (MysteryWord != null && MysteryWord.getWord() != null) {
            List<Character> wordCharacters = MysteryWord.getWord().chars()
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.toList());
            model.addAttribute("mysteryWord", wordCharacters);
            model.addAttribute("mysteryWordStr", MysteryWord.getWord());
        } else {
            model.addAttribute("mysteryWord", new ArrayList<Character>());
            model.addAttribute("mysteryWordStr", "");
        }
        return "index"; // corresponds to src/main/resources/templates/index.html
    }
}