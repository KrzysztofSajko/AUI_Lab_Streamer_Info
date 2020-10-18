package pl.edu.pg.StreamerInfo.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Game {
    private Long id;
    private String name;
    private String description;
    private String genre;
}
