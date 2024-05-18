package dev.matinWebsite.matinwebapi;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

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

    @PostMapping
    public ResponseEntity<Object> CreateCharacter(@RequestBody Map<String, Object> req) {
        try {
            String name = (String) req.getOrDefault("name", "");
            String imageurl = (String) req.getOrDefault("imageurl", "");
            String race = (String) req.getOrDefault("race", "");
            String alignment = (String) req.getOrDefault("alignment", "");
            String charClass = (String) req.getOrDefault("charClass", "");
            int level = (int) req.getOrDefault("level", 1);
            List<String> colors = (List<String>) req.getOrDefault("colors", new ArrayList<String>());
            List<Map<String, Object>> statsList = (List<Map<String, Object>>) req.getOrDefault("stats", new ArrayList<>());
            List<Stat> stats = convertToStatsArray(statsList);
            String ideals = (String) req.getOrDefault("ideals", "");
            String bonds = (String) req.getOrDefault("bonds", "");
            String flaws = (String) req.getOrDefault("flaws", "");
            String background = (String) req.getOrDefault("background", "");

            Characters newCharacter = new Characters();
            newCharacter.setImageurl(imageurl);
            newCharacter.setName(name);
            newCharacter.setRace(race);
            newCharacter.setAlignment(alignment);
            newCharacter.setCharClass(charClass);
            newCharacter.setLevel(level);
            newCharacter.setColors(colors);
            newCharacter.setStats(stats);
            newCharacter.setIdeals(ideals);
            newCharacter.setBonds(bonds);
            newCharacter.setFlaws(flaws);
            newCharacter.setBackground(background);

            Characters createdChar = characterService.CreateCharacter(newCharacter);
            return new ResponseEntity<>(createdChar, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> PatchCharacter(@PathVariable("id") String id, @RequestBody Map<String,Object> patchBody)
    {
        try{
            Characters patchedCharacter = characterService.PatchCharacter(id, patchBody);
            if(patchedCharacter == null){
                return new ResponseEntity<>(NOT_FOUND);
            }
            return new ResponseEntity<>(patchedCharacter, OK);
        }catch (Exception e){
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    private List<Stat> convertToStatsArray(List<Map<String, Object>> statsList) {
        List<Stat> stats = new ArrayList<>();
        for (Map<String, Object> statMap : statsList) {
            String abilities = (String) statMap.get("abilities");
            int val = (int) statMap.get("val");
            String mod = (String) statMap.get("mod");
            stats.add(new Stat(abilities, val, mod));
        }
        return stats;
    }

}
