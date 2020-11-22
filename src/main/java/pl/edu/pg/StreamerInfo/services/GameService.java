package pl.edu.pg.StreamerInfo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.StreamerInfo.models.GameReduced;
import pl.edu.pg.StreamerInfo.models.GenreReduced;
import pl.edu.pg.StreamerInfo.models.Streamer;
import pl.edu.pg.StreamerInfo.repositories.GameRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final GameRepository repository;

    @Autowired
    public GameService(GameRepository gameRepository){
        this.repository = gameRepository;
    }

    public List<GameReduced> findAll(){
        return repository.findAll();
    }

    public Optional<GameReduced> find(Long id){
        return repository.findById(id);
    }

    public List<GameReduced> findAll(Streamer streamer){
        return repository.findAllByStreamer(streamer);
    }

    public List<GameReduced> findAll(GenreReduced genre){
        return repository.findAllByGenre(genre);
    }

    @Transactional
    public GameReduced create(GameReduced game){
        return repository.save(game);
    }

    @Transactional
    public void delete(GameReduced game){
        repository.delete(game);
    }
}
