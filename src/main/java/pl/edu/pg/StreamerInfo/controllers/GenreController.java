package pl.edu.pg.StreamerInfo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.StreamerInfo.dtos.genre.CreateGenreRequest;
import pl.edu.pg.StreamerInfo.services.GameService;
import pl.edu.pg.StreamerInfo.services.GenreService;
import pl.edu.pg.StreamerInfo.services.StreamerService;

import java.util.HashSet;

@RestController
@RequestMapping("api/genres")
public class GenreController {
    private final GenreService genreService;
    private final GameService gameService;
    private final StreamerService streamerService;

    @Autowired
    public GenreController(GenreService genreService, GameService gameService, StreamerService streamerService){
        this.genreService = genreService;
        this.gameService = gameService;
        this.streamerService = streamerService;
    }

    @PostMapping("")
    public ResponseEntity<Void> createStreamer(@RequestBody CreateGenreRequest request, UriComponentsBuilder builder){
        var genre = CreateGenreRequest.dtoToEntityMapper().apply(request);
        genreService.create(genre);
        return ResponseEntity.created(builder.pathSegment("api", "genres", "{genreId}")
                .buildAndExpand(genre.getId()).toUri()).build();
    }

    @DeleteMapping("{genreId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("genreId") Long id) {
        var genre = genreService.find(id);
        if (genre.isPresent()) {
            var games = gameService.findAllByGenre(genre.get());
            for (var game : games) {
                var streamers = streamerService.findAllByGame(game);
                for (var streamer : streamers) {
                    var playedGames = new HashSet<>(gameService.findAllByStreamer(streamer));
                    playedGames.remove(game);
                    streamer.setPlayedGames(playedGames);
                    streamerService.update(streamer);
                }
                gameService.delete(game);
            }
            genreService.delete(genre.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
