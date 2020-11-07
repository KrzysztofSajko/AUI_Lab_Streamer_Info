package pl.edu.pg.StreamerInfo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.StreamerInfo.dtos.streamer.CreateStreamerRequest;
import pl.edu.pg.StreamerInfo.dtos.streamer.GetStreamerResponse;
import pl.edu.pg.StreamerInfo.dtos.streamer.GetStreamersResponse;
import pl.edu.pg.StreamerInfo.dtos.streamer.UpdateStreamerRequest;
import pl.edu.pg.StreamerInfo.services.GameService;
import pl.edu.pg.StreamerInfo.services.StreamerService;

@RestController
@RequestMapping("api/streamers")
public class StreamerController {
    private GameService gameService;
    private StreamerService streamerService;

    @Autowired
    public StreamerController(GameService gameService, StreamerService streamerService){
        this.gameService = gameService;
        this.streamerService = streamerService;
    }

    @GetMapping
    public ResponseEntity<GetStreamersResponse> getStreamers(){
        return ResponseEntity.ok(GetStreamersResponse
                .entityToDtoMapper()
                .apply(streamerService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetStreamerResponse> getStreamer(@PathVariable("id") String id){
        return streamerService.find(id)
                .map(value -> ResponseEntity.ok(GetStreamerResponse
                        .entityToDtoMapper()
                        .apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createStreamer(@RequestBody CreateStreamerRequest request, UriComponentsBuilder builder){
        var streamer = CreateStreamerRequest
                .dtoToEntityMapper(name -> gameService.find(name).orElseThrow()).apply(request);
        streamer = streamerService.create(streamer);
        return ResponseEntity.created(builder.pathSegment("api", "streamers", "{id}")
                .buildAndExpand(streamer.getName()).toUri()).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateStreamer(@RequestBody UpdateStreamerRequest request, @PathVariable("id") String id){
        var streamer = streamerService.find(id);
        if (streamer.isPresent()){
            UpdateStreamerRequest.dtoToEntityMapper(name -> gameService.find(name).orElseThrow()).apply(streamer.get(), request);
            streamerService.update(streamer.get());
            return ResponseEntity.accepted().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStreamer(@PathVariable("id") String id){
        var streamer = streamerService.find(id);
        if (streamer.isPresent()){
            streamerService.delete(streamer.get());
            return ResponseEntity.accepted().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
