package pl.edu.pg.StreamerInfo.dtos.genre;

import lombok.*;
import pl.edu.pg.StreamerInfo.models.GenreReduced;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateGenreRequest {
    private Long id;

    public static Function<CreateGenreRequest, GenreReduced> dtoToEntityMapper(){
        return request -> GenreReduced.builder()
                .id(request.getId())
                .build();
    }
}
