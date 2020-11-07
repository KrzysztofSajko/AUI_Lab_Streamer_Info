package pl.edu.pg.StreamerInfo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.StreamerInfo.models.Game;
import pl.edu.pg.StreamerInfo.models.Genre;
import pl.edu.pg.StreamerInfo.repositories.GameRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private GameRepository repository;

    @Autowired
    public GameService(GameRepository repository){
        this.repository = repository;
    }

    public List<Game> findAll(){
        return repository.findAll();
    }

    public List<Game> findAll(Genre genre){
        return repository.findAllByGenre(genre);
    }

    public Optional<Game> find(String name){
        return repository.findById(name);
    }

    @Transactional
    public Game create(Game game){
        return repository.save(game);
    }

    @Transactional
    public void update(Game game){
        repository.save(game);
    }

    @Transactional
    public void delete(Game game){
        repository.delete(game);
    }
}
