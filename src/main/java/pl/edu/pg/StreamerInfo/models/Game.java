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
    @GeneratedValue
    private Long id;
    private String name;
    private String abbreviation;
    private String description;

    @ManyToOne
    @JoinColumn(name = "genre")
    @EqualsAndHashCode.Exclude
    private Genre genre;

    @ManyToMany(mappedBy = "playedGames")
    @ToString.Exclude
    @Singular
    @EqualsAndHashCode.Exclude
    private Set<Streamer> streamers;
}
