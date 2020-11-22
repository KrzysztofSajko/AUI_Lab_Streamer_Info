package pl.edu.pg.StreamerInfo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.StreamerInfo.models.Game;
import pl.edu.pg.StreamerInfo.models.GenreReduced;
import pl.edu.pg.StreamerInfo.models.StreamerReduced;
import pl.edu.pg.StreamerInfo.repositories.GameEventRepository;
import pl.edu.pg.StreamerInfo.repositories.GameRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final GameRepository repository;
    private final GameEventRepository eventRepository;

    @Autowired
    public GameService(GameRepository repository, GameEventRepository eventRepository){
        this.repository = repository;
        this.eventRepository = eventRepository;
    }

    public List<Game> findAll(){
        return repository.findAll();
    }

    public List<Game> findAllByGenre(GenreReduced genreReduced){
        return repository.findAllByGenre(genreReduced);
    }

    public List<Game> findAllByStreamer(StreamerReduced streamerReduced){
        return repository.findAllByStreamer(streamerReduced);
    }

    public Optional<Game> find(Long id){
        return repository.findById(id);
    }

    public Optional<Game> find(String name) { return repository.findByName(name);}

    public Optional<Game> findByIdAndStreamerId(Long streamerId, Long gameId){
        return repository.findByIdAndStreamerId(gameId, streamerId);
    }

    public Optional<Game> findByIdAndGenreId(Long genreId, Long gameId){
        return repository.findByIdAndGenreId(gameId, genreId);
    }

    @Transactional
    public Game create(Game game){
        eventRepository.create(game);
        return repository.save(game);
    }

    @Transactional
    public void update(Game game){
        repository.save(game);
    }

    @Transactional
    public void delete(Game game){
        repository.delete(game);
        eventRepository.delete(game);
    }
}
