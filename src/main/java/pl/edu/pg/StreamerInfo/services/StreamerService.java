package pl.edu.pg.StreamerInfo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.StreamerInfo.models.Game;
import pl.edu.pg.StreamerInfo.models.Genre;
import pl.edu.pg.StreamerInfo.models.Streamer;
import pl.edu.pg.StreamerInfo.repositories.StreamerRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StreamerService {
    private StreamerRepository repository;

    @Autowired
    public StreamerService(StreamerRepository repository){
        this.repository = repository;
    }

    public List<Streamer> findAll(){
        return repository.findAll();
    }

    public List<Streamer> findAll(Game game){
        return repository.findAllByGame(game);
    }

    public List<Streamer> findAll(Genre genre){
        return repository.findAllByGenre(genre);
    }

    public Optional<Streamer> find(String name){
        return repository.findById(name);
    }

    @Transactional
    public void add(Streamer streamer){
        repository.save(streamer);
    }

    @Transactional
    public void update(Streamer streamer){
        repository.save(streamer);
    }

    @Transactional
    public void delete(Streamer streamer){
        repository.delete(streamer);
    }
}
