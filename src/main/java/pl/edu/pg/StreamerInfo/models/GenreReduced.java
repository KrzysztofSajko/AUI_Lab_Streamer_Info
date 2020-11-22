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
@Table(name = "genres")
public class GenreReduced {
    @Id
    private Long id;

    @OneToMany(mappedBy = "genre")
    @ToString.Exclude
    @Singular("gameReduced")
    @EqualsAndHashCode.Exclude
    private Set<GameReduced> games;
}
