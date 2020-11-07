package pl.edu.pg.StreamerInfo.dtos.game;

import lombok.*;
import pl.edu.pg.StreamerInfo.models.Game;
import pl.edu.pg.StreamerInfo.models.Genre;

import java.util.function.BiFunction;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateGameRequest {
    private String abbreviation;
    private String description;
    private String genre;

    public static BiFunction<Game, UpdateGameRequest, Game> dtoToEntityMapping(
            Function<String, Genre> genreFunction
    ){
        return (game, request) -> {
            game.setAbbreviation(request.getAbbreviation());
            game.setDescription(request.getDescription());
            game.setGenre(genreFunction.apply(request.getGenre()));
            return game;
        };
    }
}
