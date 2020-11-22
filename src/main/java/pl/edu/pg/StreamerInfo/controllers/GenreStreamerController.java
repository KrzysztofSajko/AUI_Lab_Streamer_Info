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
@RequestMapping("/api/genres/{genreId}/streamers")
public class GenreStreamerController {
    private final GameService gameService;
    private final StreamerService streamerService;

    @Autowired
    public GenreStreamerController(GameService gameService, StreamerService streamerService){
        this.gameService = gameService;
        this.streamerService = streamerService;
    }

    @GetMapping
    public ResponseEntity<GetStreamersResponse> getStreamers(@PathVariable("genreId") Long genreId){
        return ResponseEntity.ok(GetStreamersResponse
                .entityToDtoMapper()
                .apply(streamerService.findAllByGenre(genreId)));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetStreamerResponse> getStreamer(@PathVariable("genreId") Long genreId, @PathVariable("id") Long id){
        return streamerService.findByIdAndGenreId(id, genreId)
                .map(value -> ResponseEntity.ok(GetStreamerResponse
                        .entityToDtoMapper()
                        .apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createStreamer(@PathVariable("genreId") Long genreId, @RequestBody CreateStreamerRequest request, UriComponentsBuilder builder){
        var streamer = CreateStreamerRequest
                .dtoToEntityMapper(name -> gameService.find(name).orElseThrow()).apply(request);
        streamer = streamerService.create(streamer);
        return ResponseEntity.created(builder.pathSegment("api", "streamers", "{id}")
                .buildAndExpand(streamer.getId()).toUri()).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateStreamer(@PathVariable("genreId") Long genreId, @RequestBody UpdateStreamerRequest request, @PathVariable("id") Long id){
        var streamer = streamerService.findByIdAndGenreId(id, genreId);
        if (streamer.isPresent()){
            UpdateStreamerRequest.dtoToEntityMapper(gameId -> gameService.find(gameId).orElseThrow()).apply(streamer.get(), request);
            streamerService.update(streamer.get());
            return ResponseEntity.accepted().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStreamer(@PathVariable("genreId") Long genreId, @PathVariable("id") Long id){
        var streamer = streamerService.findByIdAndGenreId(id, genreId);
        if (streamer.isPresent()){
            streamerService.delete(streamer.get());
            return ResponseEntity.accepted().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
