package pl.edu.pg.StreamerInfo.dtos.genre;

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
public class GetGenresResponse {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Genre{
        private String name;
    }

    @Singular
    @ToString.Exclude
    private List<Genre> genres;

    public static Function<Collection<pl.edu.pg.StreamerInfo.models.Genre>, GetGenresResponse> entityToDtoMapper(){
        return genres -> {
            GetGenresResponseBuilder responseBuilder = GetGenresResponse.builder();
            genres.stream()
                    .map(genre -> Genre.builder()
                            .name(genre.getName())
                            .build())
                    .forEach(responseBuilder::genre);
            return responseBuilder.build();
        };
    }
}
