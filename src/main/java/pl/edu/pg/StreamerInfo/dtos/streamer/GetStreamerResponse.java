package pl.edu.pg.StreamerInfo.dtos.streamer;

import lombok.*;
import pl.edu.pg.StreamerInfo.models.Game;
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
public class GetStreamerResponse {
    private String name;
    private String description;
    @Singular
    @ToString.Exclude
    private List<String> playedGames;
    public static Function <Streamer, GetStreamerResponse> entityToDtoMapper(){
        return streamer -> GetStreamerResponse.builder()
                .name(streamer.getName())
                .description(streamer.getDescription())
                .playedGames(streamer.getPlayedGames()
                        .stream()
                        .map(Game::getName)
                        .collect(Collectors.toList()))
                .build();
    }
}
