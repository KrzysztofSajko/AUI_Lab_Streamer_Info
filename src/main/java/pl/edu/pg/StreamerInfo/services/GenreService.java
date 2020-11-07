package pl.edu.pg.StreamerInfo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.StreamerInfo.models.Genre;
import pl.edu.pg.StreamerInfo.repositories.GenreRepository;

import javax.transaction.Transactional;
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

    public Optional<Genre> find(Long id){
        return repository.findById(id);
    }

    public Optional<Genre> find(String name) {return repository.findByName(name); }

    @Transactional
    public Genre create(Genre genre){
        return repository.save(genre);
    }

    @Transactional
    public void update(Genre genre){
        repository.save(genre);
    }

    @Transactional
    public void delete(Genre genre){
        repository.delete(genre);
    }
}
