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
@Table(name = "streamers")
public class Streamer {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String name;
    private String description;
    @ManyToMany
    @JoinTable(
            name = "streamed_games",
            joinColumns = @JoinColumn(name = "streamer"),
            inverseJoinColumns = @JoinColumn(name = "playedGame")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Singular
    private Set<Game> playedGames;
}
