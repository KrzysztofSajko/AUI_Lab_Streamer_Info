package pl.edu.pg.StreamerInfo.dtos.event;

import lombok.*;
import pl.edu.pg.StreamerInfo.models.Genre;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateGenreRequestEvent {
    private Long id;

    public static Function<Genre, CreateGenreRequestEvent> entityToDtoMapper(){
        return genre -> CreateGenreRequestEvent.builder()
                .id(genre.getId())
                .build();
    }
}
