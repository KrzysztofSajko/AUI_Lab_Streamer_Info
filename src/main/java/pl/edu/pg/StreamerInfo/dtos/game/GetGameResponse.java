package pl.edu.pg.StreamerInfo.dtos.game;

import lombok.*;
import pl.edu.pg.StreamerInfo.models.Game;
import pl.edu.pg.StreamerInfo.models.Genre;
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
public class GetGameResponse {
    private Long id;
    private String name;
    private String abbreviation;
    private String description;
    private Genre genre;
    @Singular
    @ToString.Exclude
    private List<String> streamers;

    public static Function<Game, GetGameResponse> entityToDtoMapper(){
        return game -> GetGameResponse.builder()
                .id(game.getId())
                .name(game.getName())
                .abbreviation(game.getAbbreviation())
                .description(game.getDescription())
                .genre(game.getGenre())
                .streamers(game.getStreamers()
                        .stream()
                        .map(Streamer::getName)
                        .collect(Collectors.toList()))
                .build();
    }
}
