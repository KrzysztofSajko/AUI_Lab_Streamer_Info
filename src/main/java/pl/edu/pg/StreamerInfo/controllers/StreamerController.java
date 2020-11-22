package pl.edu.pg.StreamerInfo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.StreamerInfo.dtos.streamer.CreateStreamerRequest;
import pl.edu.pg.StreamerInfo.services.StreamerService;


@RestController
@RequestMapping("api/streamers")
public class StreamerController {
    private final StreamerService streamerService;

    @Autowired
    public StreamerController(StreamerService streamerService){
        this.streamerService = streamerService;
    }

    @PostMapping("")
    public ResponseEntity<Void> createStreamer(@RequestBody CreateStreamerRequest request, UriComponentsBuilder builder){
        var streamer = CreateStreamerRequest.dtoToEntityMapper().apply(request);
        streamerService.create(streamer);
        return ResponseEntity.created(builder.pathSegment("api", "streamers", "{streamerId}")
                .buildAndExpand(streamer.getId()).toUri()).build();
    }

    @DeleteMapping("{streamerId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("streamerId") Long id) {
        var streamer = streamerService.find(id);
        if (streamer.isPresent()) {
            streamerService.delete(streamer.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
