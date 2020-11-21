package pl.edu.pg.StreamerInfo.dtos.streamer;

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
public class GetStreamersResponse {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Streamer{
        private Long id;
        private String name;
    }

    @Singular
    private List<Streamer> streamers;

    public static Function<Collection<pl.edu.pg.StreamerInfo.models.Streamer>, GetStreamersResponse> entityToDtoMapper(){
        return streamers -> {
          GetStreamersResponseBuilder responseBuilder = GetStreamersResponse.builder();
          streamers.stream()
                  .map(streamer -> Streamer.builder()
                          .id(streamer.getId())
                          .name(streamer.getName())
                          .build())
                  .forEach(responseBuilder::streamer);
          return responseBuilder.build();
        };
    }
}
