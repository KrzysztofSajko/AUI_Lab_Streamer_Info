package pl.edu.pg.StreamerInfo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.StreamerInfo.dtos.streamer.CreateStreamerRequest;
import pl.edu.pg.StreamerInfo.dtos.streamer.GetStreamerResponse;
import pl.edu.pg.StreamerInfo.dtos.streamer.GetStreamersResponse;
import pl.edu.pg.StreamerInfo.dtos.streamer.UpdateStreamerRequest;
import pl.edu.pg.StreamerInfo.services.GameService;
import pl.edu.pg.StreamerInfo.services.StreamerService;

@Controller
@RequestMapping("/api/games/{gameId}/streamers")
public class GameStreamerController {
    private final GameService gameService;
    private final StreamerService streamerService;

    @Autowired
    public GameStreamerController(GameService gameService, StreamerService streamerService){
        this.gameService = gameService;
        this.streamerService = streamerService;
    }

    @GetMapping
    public ResponseEntity<GetStreamersResponse> getStreamers(@PathVariable("gameId") Long gameId){
        return ResponseEntity.ok(GetStreamersResponse
                .entityToDtoMapper()
                .apply(streamerService.findAllByGame(gameId)));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetStreamerResponse> getStreamer(@PathVariable("gameId") Long gameId, @PathVariable("id") Long id){
        return streamerService.findByIdAndGameId(id, gameId)
                .map(value -> ResponseEntity.ok(GetStreamerResponse
                        .entityToDtoMapper()
                        .apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createStreamer(@PathVariable("gameId") Long gameId, @RequestBody CreateStreamerRequest request, UriComponentsBuilder builder){
        var streamer = CreateStreamerRequest
                .dtoToEntityMapper(name -> gameService.find(name).orElseThrow()).apply(request);
        streamer = streamerService.create(streamer);
        return ResponseEntity.created(builder.pathSegment("api", "streamers", "{id}")
                .buildAndExpand(streamer.getId()).toUri()).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateStreamer(@PathVariable("gameId") Long gameId, @RequestBody UpdateStreamerRequest request, @PathVariable("id") Long id){
        var streamer = streamerService.findByIdAndGameId(id, gameId);
        if (streamer.isPresent()){
            UpdateStreamerRequest.dtoToEntityMapper(gId -> gameService.find(gId).orElseThrow()).apply(streamer.get(), request);
            streamerService.update(streamer.get());
            return ResponseEntity.accepted().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStreamer(@PathVariable("gameId") Long gameId,@PathVariable("id") Long id){
        var streamer = streamerService.findByIdAndGameId(id, gameId);
        if (streamer.isPresent()){
            streamerService.delete(streamer.get());
            return ResponseEntity.accepted().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
