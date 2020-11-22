package pl.edu.pg.StreamerInfo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.StreamerInfo.models.GenreReduced;
import pl.edu.pg.StreamerInfo.repositories.GenreRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    private final GenreRepository repository;

    @Autowired
    public GenreService(GenreRepository repository){
        this.repository = repository;
    }

    public List<GenreReduced> findAll(){
        return repository.findAll();
    }

    public Optional<GenreReduced> find(Long id){
        return repository.findById(id);
    }

    @Transactional
    public GenreReduced create(GenreReduced genreReduced){
        return repository.save(genreReduced);
    }

    @Transactional
    public void delete(GenreReduced genreReduced){
        repository.delete(genreReduced);
    }
}
