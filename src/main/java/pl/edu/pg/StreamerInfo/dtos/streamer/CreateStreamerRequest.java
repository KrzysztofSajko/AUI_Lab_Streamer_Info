package pl.edu.pg.StreamerInfo.dtos.streamer;

import lombok.*;
import pl.edu.pg.StreamerInfo.models.Game;
import pl.edu.pg.StreamerInfo.models.StreamerReduced;

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
    private Long id;

    public static Function<CreateStreamerRequest, StreamerReduced> dtoToEntityMapper(){
        return request -> StreamerReduced.builder()
                .id(request.getId())
                .build();
    }
}
