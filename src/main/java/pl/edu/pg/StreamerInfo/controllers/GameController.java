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

@RestController
@RequestMapping("api/games")
public class GameController {
    private GenreService genreService;
    private GameService gameService;

    @Autowired
    public GameController(GenreService genreService, GameService gameService){
        this.genreService = genreService;
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<GetGamesResponse> getGames(){
        return ResponseEntity.ok(GetGamesResponse
                .entityToDtoMapper()
                .apply(gameService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetGameResponse> getGame(@PathVariable("id") String id){
        return gameService.find(id)
                .map(value -> ResponseEntity.ok(GetGameResponse
                        .entityToDtoMapper()
                        .apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createGame(@RequestBody CreateGameRequest request, UriComponentsBuilder builder){
        var game = CreateGameRequest
                .dtoToEntityMapper(name -> genreService.find(name).orElseThrow()).apply(request);
        game = gameService.create(game);
        return ResponseEntity.created(builder.pathSegment("api", "games", "{id}")
                .buildAndExpand(game.getName()).toUri()).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateGame(@RequestBody UpdateGameRequest request, @PathVariable("id") String id){
        var game = gameService.find(id);
        if (game.isPresent()){
            UpdateGameRequest.dtoToEntityMapper(name -> genreService.find(name).orElseThrow()).apply(game.get(), request);
            gameService.update(game.get());
            return ResponseEntity.accepted().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable("id") String id){
        var game = gameService.find(id);
        if (game.isPresent()){
            gameService.delete(game.get());
            return ResponseEntity.accepted().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
