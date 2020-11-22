package pl.edu.pg.StreamerInfo.dtos.streamer;

import lombok.*;
import pl.edu.pg.StreamerInfo.models.GameReduced;
import pl.edu.pg.StreamerInfo.models.Streamer;


import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateStreamerRequest {
    private String name;
    private String description;
    @Singular
    private List<Long> playedGames;

    public static BiFunction<Streamer, UpdateStreamerRequest, Streamer> dtoToEntityMapper(
            Function<Long, GameReduced> gameFunction
    ){
        return (streamer, request) -> {
            streamer.setName(request.getName());
            streamer.setDescription(request.getDescription());
            streamer.setPlayedGames(request.playedGames
                    .stream()
                    .map(gameFunction)
                    .collect(Collectors.toSet()));
            return streamer;
        };
    }
}
