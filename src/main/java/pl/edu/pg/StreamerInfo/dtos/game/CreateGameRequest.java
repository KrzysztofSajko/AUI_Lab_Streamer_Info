package pl.edu.pg.StreamerInfo.dtos.game;

import lombok.*;
import pl.edu.pg.StreamerInfo.models.Game;
import pl.edu.pg.StreamerInfo.models.GenreReduced;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateGameRequest {
    private String name;
    private String abbreviation;
    private String description;
    private Long genre;

    public static Function<CreateGameRequest, Game> dtoToEntityMapper(
            Function<Long, GenreReduced> genreFunction
    ){
        return request -> Game.builder()
                .name(request.getName())
                .abbreviation(request.getAbbreviation())
                .description(request.getDescription())
                .genre(genreFunction.apply(request.getGenre()))
                .build();
    }
}
