package org.example.asterixapi.service;

import org.example.asterixapi.model.Character;
import org.example.asterixapi.repository.CharacterRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class CharacterService {

    private final CharacterRepo repo;

    public CharacterService(CharacterRepo repo) {
        this.repo = repo;
    }

    public List<Character> getCharacters() {
        return repo.findAll();
    }

    public Character getCharacterById(String id) {
        return repo.findById(id).orElse(null);
    }

    public List<Character> getCharacterByProfession(String profession) {
        return repo.getCharactersByProfessionIgnoreCase(profession);
    }

    public double getAvgAgeByProfession(String profession) {
        return repo.getCharactersByProfessionIgnoreCase(profession).stream()
                .mapToDouble(x -> x.age())
                .average()
                .orElse(0);
    }

    public Character addCharacter(Character character) {
        return repo.save(character);
    }

    public Character updateExistingCharacter(String id, Character character) {
        Character oldCharacter = repo.findById(id).orElse(null);
        if (oldCharacter == null) {
            repo.save(oldCharacter
                    .withAge(character.age())
                    .withName(character.name()));
        }
        return character;
    }

    public void deleteCharacter(String id) {
        repo.deleteById(id);
    }

}
