package org.example.asterixapi.repository;

import org.example.asterixapi.model.Character;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepo extends MongoRepository<Character, String> {

    List<Character> getCharactersByProfessionIgnoreCase(String profession);
    List<Character> getCharactersByAgeIsLessThanEqual(int age);

    String id(String id);
}
