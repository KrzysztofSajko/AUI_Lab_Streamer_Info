package pl.edu.pg.StreamerInfo.dtos.genre;

import lombok.*;
import pl.edu.pg.StreamerInfo.models.Game;
import pl.edu.pg.StreamerInfo.models.Genre;

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
public class GetGenreResponse {
    private String name;
    private String description;
    @Singular
    @ToString.Exclude
    private List<String> games;

    public static Function<Genre, GetGenreResponse> entityToDtoMapper(){
        return genre -> GetGenreResponse.builder()
                .name(genre.getName())
                .description(genre.getDescription())
                .games(genre.getGames()
                        .stream()
                        .map(Game::getName)
                        .collect(Collectors.toList()))
                .build();
    }
}
