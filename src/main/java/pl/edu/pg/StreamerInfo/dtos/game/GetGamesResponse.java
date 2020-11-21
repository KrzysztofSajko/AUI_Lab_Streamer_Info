package pl.edu.pg.StreamerInfo.dtos.game;

import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetGamesResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Game{
        private Long id;
        private String name;
    }

    @Singular
    private List<Game> games;

    public static Function<Collection<pl.edu.pg.StreamerInfo.models.Game>, GetGamesResponse> entityToDtoMapper(){
        return games -> {
          GetGamesResponseBuilder responseBuilder = GetGamesResponse.builder();
          games.stream()
                  .map(game -> Game.builder()
                          .id(game.getId())
                          .name(game.getName())
                          .build())
                  .forEach(responseBuilder::game);
          return responseBuilder.build();
        };
    }
}
