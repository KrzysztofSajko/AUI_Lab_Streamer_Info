package pl.edu.pg.StreamerInfo.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "games")
public class Game {
    @Id
    private String name;
    private String abbreviation;
    private String description;
    @ManyToOne
    @JoinColumn(name = "genre")
    private Genre genre;
    @ManyToMany(mappedBy = "playedGames")
    @ToString.Exclude
    @Singular
    private Set<Streamer> streamers;
}
