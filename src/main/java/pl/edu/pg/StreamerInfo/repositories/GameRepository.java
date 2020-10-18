package pl.edu.pg.StreamerInfo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pg.StreamerInfo.datastore.DataStore;
import pl.edu.pg.StreamerInfo.models.Game;
import pl.edu.pg.StreamerInfo.models.Genre;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class GameRepository implements Repository<Game, String> {
    private DataStore dataStore;

    @Autowired
    public GameRepository(DataStore dataStore){
        this.dataStore = dataStore;
    }

    @Override
    public List<Game> findAll() {
        return dataStore
                .fetchGames()
                .collect(Collectors.toList());
    }

    public List<Game> findAllByGenre(Genre genre){
        return dataStore
                .fetchGames()
                .filter(game -> game
                        .getGenre()
                        .equals(genre))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Game> findByKey(String id) {
        return dataStore
                .fetchGames()
                .filter(game -> game
                        .getName()
                        .equals(id))
                .findFirst();
    }

    @Override
    public void add(Game entity) {
        dataStore.addGame(entity);
    }

    @Override
    public void update(Game entity) {
        dataStore.updateGame(entity);
    }

    @Override
    public void delete(Game entity) {
        dataStore.deleteGame(entity);
    }
}
