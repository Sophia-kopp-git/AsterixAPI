package org.example.asterixapi.controller;

import org.example.asterixapi.model.Character;
import org.example.asterixapi.service.CharacterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asterix/characters")
public class CharacterController {

    private final CharacterService service;

    public CharacterController(CharacterService service) {
        this.service = service;
    }


    @GetMapping
    public List<Character> getCharacters() {
        return service.getCharacters();
    }

    @GetMapping("/{id}")
    public Character getCharacterById(@PathVariable String id) {
        return service.getCharacterById(id);
    }

    @GetMapping("/search")
    public List<Character> getCharacterByProfession(@RequestParam String profession) {
        return service.getCharacterByProfession(profession);
    }

    @GetMapping("/avgAge")
    public double getAvgAgeByProfession(@RequestParam String profession) {
        return service.getAvgAgeByProfession(profession);
    }


    @PostMapping
    public Character addCharacter(@RequestBody Character character) {
        return service.addCharacter(character);
    }

    @PutMapping("/{id}")
    public Character updateExistingCharacter(@PathVariable String id, @RequestBody Character character) {
        return service.updateExistingCharacter(id, character);
    }

    @DeleteMapping("/{id}")
    public void deleteCharacter(@PathVariable String id) {
        service.deleteCharacter(id);
    }

}
