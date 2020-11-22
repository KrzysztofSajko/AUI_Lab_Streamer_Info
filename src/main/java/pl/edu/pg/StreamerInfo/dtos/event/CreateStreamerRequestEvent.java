package pl.edu.pg.StreamerInfo.dtos.event;

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
public class CreateStreamerRequestEvent {
    private Long id;

    public static Function<Streamer, CreateStreamerRequestEvent> entityToDtoMapper(){
        return streamer -> CreateStreamerRequestEvent.builder()
                .id(streamer.getId())
                .build();
    }
}
