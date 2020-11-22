package pl.edu.pg.StreamerInfo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.StreamerInfo.dtos.genre.CreateGenreRequest;
import pl.edu.pg.StreamerInfo.dtos.genre.GetGenreResponse;
import pl.edu.pg.StreamerInfo.dtos.genre.GetGenresResponse;
import pl.edu.pg.StreamerInfo.dtos.genre.UpdateGenreRequest;
import pl.edu.pg.StreamerInfo.services.GenreService;


@RestController
@RequestMapping("api/genres")
public class GenreController {
    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService){
        this.genreService = genreService;
    }

    @GetMapping
    public ResponseEntity<GetGenresResponse> getGenres(){
        return ResponseEntity.ok(GetGenresResponse
                .entityToDtoMapper()
                .apply(genreService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetGenreResponse> getGenre(@PathVariable("id") Long id){
        return genreService.find(id)
                .map(value -> ResponseEntity.ok(GetGenreResponse
                        .entityToDtoMapper()
                        .apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createGenre(@RequestBody CreateGenreRequest request, UriComponentsBuilder builder){
        var genre = CreateGenreRequest
                .dtoToEntityMapper().apply(request);
        genre = genreService.create(genre);
        return ResponseEntity.created(builder.pathSegment("api", "genres", "{id}")
        .buildAndExpand(genre.getName()).toUri()).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateGenre(@RequestBody UpdateGenreRequest request, @PathVariable("id") Long id){
        var genre = genreService.find(id);
        if (genre.isPresent()){
            UpdateGenreRequest.dtoToEntityMapper().apply(genre.get(), request);
            genreService.update(genre.get());
            return ResponseEntity.accepted().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable("id") Long id){
        var genre = genreService.find(id);
        if (genre.isPresent()){
            genreService.delete(genre.get());
            return ResponseEntity.accepted().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
