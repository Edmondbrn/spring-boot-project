package com.example.demo.repository;

import com.example.demo.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WordRepository extends JpaRepository<Word, Long> {

    @Query(value = "SELECT * FROM english_words ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Word findRandomWord();
}
