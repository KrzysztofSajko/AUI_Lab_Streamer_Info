package pl.edu.pg.StreamerInfo.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@ToString
@EqualsAndHashCode
public class Genre {
    private String name;
    private String description;
}
