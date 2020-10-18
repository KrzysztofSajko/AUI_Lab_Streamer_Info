package pl.edu.pg.StreamerInfo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.StreamerInfo.models.Game;
import pl.edu.pg.StreamerInfo.models.Genre;
import pl.edu.pg.StreamerInfo.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    private GenreRepository repository;

    @Autowired
    public GenreService(GenreRepository repository){
        this.repository = repository;
    }

    public List<Genre> findAll(){
        return repository.findAll();
    }

    public Optional<Genre> find(String name){
        return repository.findByKey(name);
    }

    public void add(Genre genre){
        repository.add(genre);
    }

    public void update(Genre genre){
        repository.update(genre);
    }

    public void delete(Genre genre){
        repository.delete(genre);
    }
}
