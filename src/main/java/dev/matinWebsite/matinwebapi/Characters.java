package dev.matinWebsite.matinwebapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "characters")

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Characters {
    @Id
    private ObjectId id;

    private String imageurl;
    private String name;
    private String race;
    private String alignment;
    private String charClass;
    private int level;
    private String[] colors;
    private Stat[] stats;

    private String ideals;
    private String bonds;
    private String flaws;
    private String background;

}

