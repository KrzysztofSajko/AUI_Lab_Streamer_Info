package pl.edu.pg.StreamerInfo.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "genre")
    @ToString.Exclude
    @Singular
    @EqualsAndHashCode.Exclude
    private List<Game> games;
}
