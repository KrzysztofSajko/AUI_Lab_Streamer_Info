package pl.edu.pg.StreamerInfo.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLInsert;

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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String abbreviation;
    private String description;

    @ManyToOne
    @JoinColumn(name = "genre")
    @EqualsAndHashCode.Exclude
    private GenreReduced genreReduced;

    @ManyToMany(mappedBy = "playedGames")
    @ToString.Exclude
    @Singular("streamerReduced")
    @EqualsAndHashCode.Exclude
    private Set<StreamerReduced> streamersReduced;
}
