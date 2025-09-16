package org.example.asterixapi.controller;

import org.example.asterixapi.model.Character;
import org.example.asterixapi.repository.CharacterRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asterix/characters")
public class CharacterController {

    private final CharacterRepo repo;

    public CharacterController(CharacterRepo repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Character> getCharacters(){
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Character getCharacterById(@PathVariable String id){
        return repo.findById(id).orElse(null);
    }

    @GetMapping("/search")
    public List<Character> getCharacterByProfession(@RequestParam String profession){
        return repo.getCharactersByProfessionIgnoreCase(profession);
    }
    @GetMapping("/avgAge")
    public double getAvgAgeByProfession(@RequestParam String profession){
        return repo.getCharactersByProfessionIgnoreCase(profession).stream().mapToDouble(x-> x.age()).average().orElse(0);
    }


    @PostMapping
    public Character addCharacter(@RequestBody Character character){
        repo.save(character);
        return character;
    }

    @PutMapping("/{id}")
    public Character updateExistingCharacter(@PathVariable String id, @RequestBody Character character){
        Character oldCharacter = repo.findById(id).orElse(null);
        if(oldCharacter == null){
            repo.save(oldCharacter.withAge(character.age())
                    .withName(character.name()));
        }
        return character;
    }

    @DeleteMapping("/{id}")
    public void deleteCharacter(@PathVariable String id){
        repo.deleteById(id);
    }

}
