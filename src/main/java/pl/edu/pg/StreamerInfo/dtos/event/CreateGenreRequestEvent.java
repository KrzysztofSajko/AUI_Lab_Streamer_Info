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
    private String name;

    public static Function<Genre, CreateGenreRequestEvent> entityToDtoMapper(){
        return request -> CreateGenreRequestEvent.builder()
                .name(request.getName())
                .build();
    }
}
