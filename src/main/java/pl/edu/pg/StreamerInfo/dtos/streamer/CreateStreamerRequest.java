package pl.edu.pg.StreamerInfo.dtos.streamer;

import lombok.*;
import pl.edu.pg.StreamerInfo.models.GameReduced;
import pl.edu.pg.StreamerInfo.models.Streamer;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateStreamerRequest {
    private String name;
    private String description;
    @Singular
    private List<Long> playedGames;

    public static Function<CreateStreamerRequest, Streamer> dtoToEntityMapper(
            Function<Long, GameReduced> gameFunction
    ){
        return request -> Streamer.builder()
                .name(request.getName())
                .description(request.getDescription())
                .playedGames(request.playedGames
                        .stream()
                        .map(gameFunction)
                        .collect(Collectors.toSet()))
                .build();
    }
}
