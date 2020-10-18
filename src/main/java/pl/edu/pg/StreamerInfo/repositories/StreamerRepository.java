package pl.edu.pg.StreamerInfo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pg.StreamerInfo.datastore.DataStore;
import pl.edu.pg.StreamerInfo.models.Streamer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class StreamerRepository implements Repository<Streamer, String>{
    private DataStore dataStore;

    @Autowired
    public StreamerRepository(DataStore dataStore){
        this.dataStore = dataStore;
    }


    @Override
    public List<Streamer> findAll() {
        return dataStore.fetchStreamers().collect(Collectors.toList());
    }

    public List<Streamer> findAllByGame(String gameName){
        return dataStore
                .fetchStreamers()
                .filter(streamer -> streamer
                        .getPlayedGames()
                        .stream()
                        .anyMatch(game -> game.getName().equals(gameName))
                ).collect(Collectors.toList());
    }

    @Override
    public Optional<Streamer> findByKey(String id) {
        return dataStore.fetchStreamers().filter(streamer -> streamer.getName().equals(id)).findFirst();
    }

    @Override
    public void add(Streamer entity) {
        dataStore.addStreamer(entity);
    }

    @Override
    public void update(Streamer entity) {
        dataStore.updateStreamer(entity);
    }

    @Override
    public void delete(Streamer entity) {
        dataStore.deleteStreamer(entity);
    }
}
