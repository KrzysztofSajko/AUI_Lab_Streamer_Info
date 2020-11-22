package pl.edu.pg.StreamerInfo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.StreamerInfo.models.Game;
import pl.edu.pg.StreamerInfo.models.GenreReduced;
import pl.edu.pg.StreamerInfo.models.StreamerReduced;
import pl.edu.pg.StreamerInfo.repositories.StreamerRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StreamerService {
    private final StreamerRepository repository;

    @Autowired
    public StreamerService(StreamerRepository repository){
        this.repository = repository;
    }

    public List<StreamerReduced> findAll(){
        return repository.findAll();
    }

    public List<StreamerReduced> findAllByGame(Game game){
        return repository.findAllByGame(game);
    }

    public List<StreamerReduced> findAll(GenreReduced genreReduced){
        return repository.findAllByGenre(genreReduced);
    }

    public Optional<StreamerReduced> find(Long id){
        return repository.findById(id);
    }

    @Transactional
    public StreamerReduced create(StreamerReduced streamerReduced){
        return repository.save(streamerReduced);
    }

    @Transactional
    public void update(StreamerReduced streamerReduced){
        repository.save(streamerReduced);
    }

    @Transactional
    public void delete(StreamerReduced streamerReduced){
        repository.delete(streamerReduced);
    }
}
