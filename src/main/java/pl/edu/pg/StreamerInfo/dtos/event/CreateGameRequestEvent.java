package pl.edu.pg.StreamerInfo.dtos.event;

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
public class CreateGameRequestEvent {
    private Long id;
    private Long genreId;

    public static Function<Game, CreateGameRequestEvent> entityToDtoMapper(){
        return game -> CreateGameRequestEvent.builder()
                .id(game.getId())
                .genreId(game.getGenre().getId())
                .build();
    }
}
