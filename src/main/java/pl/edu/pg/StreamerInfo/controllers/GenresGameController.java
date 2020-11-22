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
@RequestMapping("api/genres/{genreId}/games")
public class GenresGameController {
    private final GenreService genreService;
    private final GameService gameService;
    private final StreamerService streamerService;

    @Autowired
    public GenresGameController(GenreService genreService, GameService gameService, StreamerService streamerService){
        this.genreService = genreService;
        this.gameService = gameService;
        this.streamerService = streamerService;
    }

    @GetMapping
    public ResponseEntity<GetGamesResponse> getGames(@PathVariable("genreId") Long genreId){
        return genreService.find(genreId)
                .map(genre -> ResponseEntity.ok(GetGamesResponse
                        .entityToDtoMapper()
                        .apply(gameService.findAllByGenre(genre))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetGameResponse> getGame(@PathVariable("genreId") Long genreId, @PathVariable("id") Long id){
        return gameService.findByIdAndGenreId(genreId, id)
                .map(game -> ResponseEntity.ok(GetGameResponse
                        .entityToDtoMapper()
                        .apply(game)))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Void> createGame(@PathVariable("genreId") Long genreId,
                                           @RequestBody CreateGameRequest request,
                                           UriComponentsBuilder builder){
        var genre = genreService.find(genreId);
        if(genre.isPresent()) {
            var game = CreateGameRequest
                    .dtoToEntityMapper(genId -> genreService.find(genId).orElseThrow()).apply(request);
            game = gameService.create(game);
            return ResponseEntity.created(builder.pathSegment("api", "genres", "{genreId}", "games", "{id}")
                    .buildAndExpand(genre.get().getId(), game.getId()).toUri()).build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateGame(@PathVariable("genreId") Long genreId,
                                           @RequestBody UpdateGameRequest request,
                                           @PathVariable("id") long id){
        var game = gameService.findByIdAndGenreId(genreId, id);
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
    public ResponseEntity<Void> deleteGame(@PathVariable("genreId") Long genreId,
                                           @PathVariable("id") long id){
        var game = gameService.findByIdAndGenreId(genreId, id);
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
