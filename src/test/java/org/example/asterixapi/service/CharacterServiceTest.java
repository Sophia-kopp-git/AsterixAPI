package org.example.asterixapi.service;

import org.example.asterixapi.dto.CharacterDTO;
import org.example.asterixapi.repository.CharacterRepo;
import org.junit.jupiter.api.Test;
import org.example.asterixapi.model.Character;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CharacterServiceTest {

    @Test
    void getCharacters() {
        //given
        CharacterDTO character1 = new CharacterDTO("testName1", 5, "testProfession1");
        CharacterDTO character2 = new CharacterDTO("testName2", 15, "testProfession2");
        CharacterRepo mockRepo = mock(CharacterRepo.class);
        IdService mockIdService = mock(IdService.class);

        CharacterService service = new CharacterService(mockIdService, mockRepo);

        List<Character> response = new ArrayList<>(
                Arrays.asList(new Character("1", character1.name(), character1.age(), character1.profession()),
                        new Character("2", character2.name(), character2.age(), character2.profession())));

        //when
        when(mockRepo.findAll()).thenReturn(response);
        List<Character> actual = service.getCharacters();
        //then
        verify(mockRepo).findAll();
        assertEquals(response,actual);
    }

    @Test
    void getCharacterById() {
        //given
        Character character = new Character("1", "name1", 22, "profession1");
        CharacterRepo mockRepo = mock(CharacterRepo.class);
        IdService mockIdService = mock(IdService.class);
        CharacterService service = new CharacterService(mockIdService, mockRepo);


        when(mockRepo.findById("1")).thenReturn(Optional.of(character));
        //when
        Character actual = service.getCharacterById("1");
        //then
        verify(mockRepo).findById("1");
        assertEquals(character, actual);
    }

    @Test
    void addCharacter() {
        CharacterDTO characterDTO = new CharacterDTO("name1", 22, "profession1");
        Character character = new Character("1","name1", 22, "profession1");
        CharacterRepo mockRepo = mock(CharacterRepo.class);
        IdService mockIdService = mock(IdService.class);
        CharacterService service = new CharacterService(mockIdService, mockRepo);
        when(mockIdService.getUuid()).thenReturn("1");
        when(mockRepo.save(character)).thenReturn(character);
        when(mockRepo.findById("1")).thenReturn(Optional.of(character));

        //when
        Character actual = service.addCharacter(characterDTO);
        //then
        verify(mockIdService).getUuid();
        verify(mockRepo).save(character);
        verify(mockRepo).findById("1");
        assertEquals(character, actual);
    }

    @Test
    void updateExistingCharacter() {
        //Given
        CharacterDTO characterDTO = new CharacterDTO("name1", 22, "profession1");
        Character character = new Character("1","name1", 22, "profession1");
        CharacterRepo mockRepo = mock(CharacterRepo.class);
        IdService mockIdService = mock(IdService.class);
        CharacterService service = new CharacterService(mockIdService, mockRepo);
        //when
        //then
    }

    @Test
    void deleteCharacter() {
        //Given
        CharacterDTO characterDTO = new CharacterDTO("name1", 22, "profession1");
        Character character = new Character("1","name1", 22, "profession1");
        CharacterRepo mockRepo = mock(CharacterRepo.class);
        IdService mockIdService = mock(IdService.class);
        CharacterService service = new CharacterService(mockIdService, mockRepo);
        //when
        //then
    }
}