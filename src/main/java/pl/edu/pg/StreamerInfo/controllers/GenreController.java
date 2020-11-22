package pl.edu.pg.StreamerInfo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.StreamerInfo.dtos.genre.CreateGenreRequest;
import pl.edu.pg.StreamerInfo.services.GenreService;

@RestController
@RequestMapping("api/genres")
public class GenreController {
    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService){
        this.genreService = genreService;
    }

    @PostMapping("")
    public ResponseEntity<Void> createStreamer(@RequestBody CreateGenreRequest request, UriComponentsBuilder builder){
        var genre = CreateGenreRequest.dtoToEntityMapper().apply(request);
        genreService.create(genre);
        return ResponseEntity.created(builder.pathSegment("api", "genres", "{genreId}")
                .buildAndExpand(genre.getId()).toUri()).build();
    }

    @DeleteMapping("{genreId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("gerneId") Long id) {
        var genre = genreService.find(id);
        if (genre.isPresent()) {
            genreService.delete(genre.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
