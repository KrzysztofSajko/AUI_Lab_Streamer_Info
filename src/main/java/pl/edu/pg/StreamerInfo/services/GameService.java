package pl.edu.pg.StreamerInfo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.StreamerInfo.repositories.GameRepository;

@Service
public class GameService {
    private GameRepository repository;

    @Autowired
    public GameService(GameRepository repository){
        this.repository = repository;
    }
}
