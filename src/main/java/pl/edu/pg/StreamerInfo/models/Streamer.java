package pl.edu.pg.StreamerInfo.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Streamer {
    private Long id;
    private String name;
    private String description;
    @ToString.Exclude
    private List<Game> playedGames;
}
