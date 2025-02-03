package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String word;
    private String def;
    private String pos;
    // Default constructor is required by JPA
    public Word() {}
    public Word(String word, String def, String pos) {
        this.word = word;
        this.def = def;
        this.pos = pos;
    }
    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getWord() {
        return this.word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public String getDef() {
        return this.def;
    }
    public void setDef(String def) {
        this.def = def;
    }    
    public String getPos() {
        return this.pos;
    }
    public void setPos(String pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "Word{" +
               "id=" + this.id +
               ", word='" + this.word + '\'' +
               ", pos='" + this.pos + '\'' +
               ", def='" + this.def + '\'' +
               '}';
    }
}
