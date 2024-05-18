package dev.matinWebsite.matinwebapi;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

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

    Characters PatchCharacter(String id, Map<String,Object> payload){
        ObjectId msgId = new ObjectId(id);
        Characters character = characterRepository.findById(msgId).orElse(null);
        if (character == null){
            return null;
        }
        for (Map.Entry<String, Object> entry: payload.entrySet()){
            String fieldname = entry.getKey();
            Object val = entry.getValue();

            try {
                Field field = Characters.class.getDeclaredField(fieldname);
                field.setAccessible(true);
                field.set(character, val);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return characterRepository.save(character);
    }

}
