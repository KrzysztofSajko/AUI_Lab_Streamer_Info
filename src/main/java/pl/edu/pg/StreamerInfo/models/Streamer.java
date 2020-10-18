package pl.edu.pg.StreamerInfo.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@SuperBuilder
@ToString
@EqualsAndHashCode
public class Streamer {
    private String name;
    private String description;
    @ToString.Exclude
    private List<Game> playedGames;
}
