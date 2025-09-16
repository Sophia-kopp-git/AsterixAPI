package org.example.asterixapi.service;

import org.example.asterixapi.dto.CharacterDTO;
import org.example.asterixapi.model.Character;
import org.example.asterixapi.repository.CharacterRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CharacterService {

    private final IdService idService;
    private final CharacterRepo repo;

    public CharacterService(IdService idService, CharacterRepo repo) {
        this.idService = idService;
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

    public Character addCharacter(CharacterDTO characterDto) {
        Character character = new Character(idService.getUuid(),
                characterDto.name(), characterDto.age(), characterDto.profession());
        return repo.save(character);
    }

    public Character updateExistingCharacter(String id, CharacterDTO characterDto) {
        Character oldCharacter = repo.findById(id).orElse(null);
        if (oldCharacter != null) {
            return repo.save(oldCharacter
                    .withAge(characterDto.age())
                    .withName(characterDto.name()));
        } else {
            throw new NoSuchElementException("No Character with id: " + id);
        }
    }

    public void deleteCharacter(String id) {
        repo.deleteById(id);
    }

}
