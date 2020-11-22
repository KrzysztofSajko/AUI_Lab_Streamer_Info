package pl.edu.pg.StreamerInfo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.StreamerInfo.dtos.game.CreateGameRequest;
import pl.edu.pg.StreamerInfo.services.GameService;
import pl.edu.pg.StreamerInfo.services.GenreService;
import pl.edu.pg.StreamerInfo.services.StreamerService;

import java.util.HashSet;

@RestController
@RequestMapping("api/games")
public class GameController {
    private final GameService gameService;
    private final GenreService genreService;
    private final StreamerService streamerService;

    @Autowired
    public GameController(GameService gameService, GenreService genreService, StreamerService streamerService){
        this.gameService = gameService;
        this.genreService = genreService;
        this.streamerService = streamerService;
    }

    @PostMapping("")
    public ResponseEntity<Void> create(@RequestBody CreateGameRequest request, UriComponentsBuilder builder){
        var game = CreateGameRequest.dtoToEntityMapper(
                genreId -> genreService.find(genreId)
                        .orElseThrow())
                .apply(request);
        gameService.create(game);
        return ResponseEntity.created(builder.pathSegment("api", "games", "{id}")
                .buildAndExpand(game.getId()).toUri()).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        var game = gameService.find(id);
        if(game.isPresent()){
            var streamers = streamerService.findAllByGame(game.get());
            for (var streamer : streamers){
                var games = new HashSet<>(gameService.findAll(streamer));
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
