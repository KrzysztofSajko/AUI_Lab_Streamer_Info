package pl.edu.pg.StreamerInfo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.StreamerInfo.dtos.game.CreateGameRequest;
import pl.edu.pg.StreamerInfo.dtos.game.GetGameResponse;
import pl.edu.pg.StreamerInfo.dtos.game.GetGamesResponse;
import pl.edu.pg.StreamerInfo.dtos.game.UpdateGameRequest;
import pl.edu.pg.StreamerInfo.services.GameService;
import pl.edu.pg.StreamerInfo.services.GenreService;
import pl.edu.pg.StreamerInfo.services.StreamerService;

import java.util.HashSet;

@RestController
@RequestMapping("api/streamers/{streamerId}/games")
public class StreamersGameController {
    private final GenreService genreService;
    private final GameService gameService;
    private final StreamerService streamerService;

    @Autowired
    public StreamersGameController(GenreService genreService, GameService gameService, StreamerService streamerService){
        this.genreService = genreService;
        this.gameService = gameService;
        this.streamerService = streamerService;
    }

    @GetMapping
    public ResponseEntity<GetGamesResponse> getGames(@PathVariable("streamerId") Long streamerId){
        return streamerService.find(streamerId)
                .map(streamer -> ResponseEntity.ok(GetGamesResponse
                        .entityToDtoMapper()
                        .apply(gameService.findAllByStreamer(streamer))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetGameResponse> getGame(@PathVariable("streamerId") Long streamerId, @PathVariable("id") Long id){
        return gameService.findByIdAndStreamerId(streamerId, id)
                .map(game -> ResponseEntity.ok(GetGameResponse
                        .entityToDtoMapper()
                        .apply(game)))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Void> createGame(@PathVariable("streamerId") Long streamerId,
                                           @RequestBody CreateGameRequest request,
                                           UriComponentsBuilder builder){
        var streamer = streamerService.find(streamerId);
        if(streamer.isPresent()) {
            var game = CreateGameRequest
                    .dtoToEntityMapper(genreId -> genreService.find(genreId).orElseThrow()).apply(request);
            game = gameService.create(game);
            return ResponseEntity.created(builder.pathSegment("api", "streamers", "{streamerId}", "games", "{id}")
                    .buildAndExpand(streamer.get().getId(), game.getId()).toUri()).build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateGame(@PathVariable("streamerId") Long streamerId,
                                           @RequestBody UpdateGameRequest request,
                                           @PathVariable("id") long id){
        var game = gameService.findByIdAndStreamerId(streamerId, id);
        if (game.isPresent()){
            UpdateGameRequest.dtoToEntityMapper()
                    .apply(game.get(), request);
            gameService.update(game.get());
            return ResponseEntity.accepted().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable("streamerId") Long streamerId,
                                           @PathVariable("id") long id){
        var game = gameService.findByIdAndStreamerId(streamerId, id);
        if (game.isPresent()){
            var streamers = streamerService.findAllByGame(game.get());
            for (var streamer : streamers){
                var games = new HashSet<>(gameService.findAllByStreamer(streamer));
                games.remove(game.get());
                streamer.setPlayedGames(games);
                streamerService.update(streamer);
            }
            gameService.delete(game.get());
            return ResponseEntity.accepted().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
