package dev.matinWebsite.matinwebapi;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/characters")
public class CharacterController {
    @Autowired
    private CharacterService characterService;

    @GetMapping
    public ResponseEntity<List<Characters>> AllCharacters(){
        List<Characters> allChar = characterService.AllCharacters();
        if (allChar.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<List<Characters>>(allChar, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Characters> Character(@PathVariable("id") String id){
        Characters character = characterService.Character(id);
        if (character.equals(null)){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Characters>(character, HttpStatus.OK);
    }
}
