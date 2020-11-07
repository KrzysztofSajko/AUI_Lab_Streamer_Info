package pl.edu.pg.StreamerInfo.dtos.genre;

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
public class CreateGenreRequest {
    private String name;
    private String description;

    public static Function<CreateGenreRequest, Genre> dtoToEntityMapper(){
        return request -> Genre.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }
}
