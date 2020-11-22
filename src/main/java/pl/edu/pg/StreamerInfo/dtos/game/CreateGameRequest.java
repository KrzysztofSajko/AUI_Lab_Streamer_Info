package pl.edu.pg.StreamerInfo.dtos.game;

import lombok.*;
import pl.edu.pg.StreamerInfo.models.GameReduced;
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
    private Long id;
    private Long genreId;

    public static Function<CreateGameRequest, GameReduced> dtoToEntityMapper(
            Function<Long, GenreReduced> genreFunction
    ){
        return request -> GameReduced.builder()
                .id(request.getId())
                .genre(genreFunction.apply(request.getGenreId()))
                .build();
    }
}
