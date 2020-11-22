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
public class StreamerReduced {
    @Id
    private Long id;

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
