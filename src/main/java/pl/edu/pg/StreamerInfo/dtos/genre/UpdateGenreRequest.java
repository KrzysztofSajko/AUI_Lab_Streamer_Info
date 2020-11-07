package pl.edu.pg.StreamerInfo.dtos.genre;

import lombok.*;
import pl.edu.pg.StreamerInfo.models.Genre;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateGenreRequest {
    private String description;

    public static BiFunction<Genre, UpdateGenreRequest, Genre> dtoToEntityMapping(){
        return (genre, request) -> {
           genre.setDescription(request.getDescription());
           return genre;
        };
    }
}
