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
public class GameReduced {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "genre")
    @EqualsAndHashCode.Exclude
    private GenreReduced genreReduced;

    @ManyToMany(mappedBy = "playedGames")
    @ToString.Exclude
    @Singular
    @EqualsAndHashCode.Exclude
    private Set<Streamer> streamers;
}
