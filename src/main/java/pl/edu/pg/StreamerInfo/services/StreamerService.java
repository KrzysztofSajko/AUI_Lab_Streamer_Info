package pl.edu.pg.StreamerInfo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.StreamerInfo.models.GameReduced;
import pl.edu.pg.StreamerInfo.models.GenreReduced;
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

    public List<Streamer> findAllByGame(GameReduced gameReduced){
        return repository.findAllByGame(gameReduced);
    }

    public List<Streamer> findAllByGame(Long gameId){
        return repository.findAllByGame(gameId);
    }

    public List<Streamer> findAll(GenreReduced genreReduced){
        return repository.findAllByGenre(genreReduced);
    }

    public List<Streamer> findAllByGenre(Long id){
        return repository.findAllByGenreId(id);
    }

    public Optional<Streamer> findByIdAndGenreId(Long streamerId, Long genreId){
        return repository.findAllByIdAndGenreId(streamerId, genreId);
    }

    public Optional<Streamer> findByIdAndGameId(Long streamerId, Long gameId){
        return repository.findAllByIdAndGameId(streamerId, gameId);
    }

    public Optional<Streamer> find(Long id){
        return repository.findById(id);
    }

    @Transactional
    public Streamer create(Streamer streamer){
        return repository.save(streamer);
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
