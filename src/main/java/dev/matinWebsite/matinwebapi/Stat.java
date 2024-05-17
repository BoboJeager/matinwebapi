package dev.matinWebsite.matinwebapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Stat {
    private String abilities;
    private int val;
    private String mod;
}
