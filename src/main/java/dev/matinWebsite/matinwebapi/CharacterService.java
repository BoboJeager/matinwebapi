package dev.matinWebsite.matinwebapi;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {
    @Autowired
    private CharacterRepository characterRepository;

    List<Characters> AllCharacters(){return characterRepository.findAll();}

    Characters Character(String id){
        ObjectId mongoId = new ObjectId(id);
        return  characterRepository.findById(mongoId).orElse(null);
    }

    Characters CreateCharacter(Characters character){return  characterRepository.save(character);}

}
