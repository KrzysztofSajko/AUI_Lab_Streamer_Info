package pl.edu.pg.StreamerInfo.dtos.streamer;

import lombok.*;
import pl.edu.pg.StreamerInfo.models.Streamer;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetStreamerResponse {
    private Long id;
    private String name;
    private String description;

    public static Function <Streamer, GetStreamerResponse> entityToDtoMapper(){
        return streamer -> GetStreamerResponse.builder()
                .id(streamer.getId())
                .name(streamer.getName())
                .description(streamer.getDescription())
                .build();
    }
}
