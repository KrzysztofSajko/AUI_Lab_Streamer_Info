package pl.edu.pg.StreamerInfo.dtos.genre;

import lombok.*;
import pl.edu.pg.StreamerInfo.models.Genre;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetGenreResponse {
    private Long id;
    private String name;
    private String description;

    public static Function<Genre, GetGenreResponse> entityToDtoMapper(){
        return genre -> GetGenreResponse.builder()
                .id(genre.getId())
                .name(genre.getName())
                .description(genre.getDescription())
                .build();
    }
}
