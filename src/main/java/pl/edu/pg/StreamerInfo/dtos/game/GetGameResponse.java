package pl.edu.pg.StreamerInfo.dtos.game;

import lombok.*;
import pl.edu.pg.StreamerInfo.models.Game;

import java.util.function.Function;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetGameResponse {
    private Long id;
    private String name;
    private String abbreviation;
    private String description;
    private String genre;

    public static Function<Game, GetGameResponse> entityToDtoMapper(){
        return game -> GetGameResponse.builder()
                .id(game.getId())
                .name(game.getName())
                .abbreviation(game.getAbbreviation())
                .description(game.getDescription())
                .genre(game.getGenre().getName())
                .build();
    }
}
