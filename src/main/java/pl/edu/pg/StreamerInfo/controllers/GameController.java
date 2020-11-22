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
@RequestMapping("api/games")
public class GameController {
    private final GenreService genreService;
    private final GameService gameService;
    private final StreamerService streamerService;

    @Autowired
    public GameController(GenreService genreService, GameService gameService, StreamerService streamerService){
        this.genreService = genreService;
        this.gameService = gameService;
        this.streamerService = streamerService;
    }

    @GetMapping
    public ResponseEntity<GetGamesResponse> getGames(){
        return ResponseEntity.ok(GetGamesResponse
                .entityToDtoMapper()
                .apply(gameService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetGameResponse> getGame(@PathVariable("id") long id){
        return gameService.find(id)
                .map(value -> ResponseEntity.ok(GetGameResponse
                        .entityToDtoMapper()
                        .apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createGame(@RequestBody CreateGameRequest request, UriComponentsBuilder builder){
        var game = CreateGameRequest
                .dtoToEntityMapper(genreId -> genreService.find(genreId).orElseThrow()).apply(request);
        game = gameService.create(game);
        return ResponseEntity.created(builder.pathSegment("api", "games", "{id}")
                .buildAndExpand(game.getId()).toUri()).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateGame(@RequestBody UpdateGameRequest request, @PathVariable("id") long id){
        var game = gameService.find(id);
        if (game.isPresent()){
            UpdateGameRequest.dtoToEntityMapper().apply(game.get(), request);
            gameService.update(game.get());
            return ResponseEntity.accepted().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable("id") long id){
        var game = gameService.find(id);
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
