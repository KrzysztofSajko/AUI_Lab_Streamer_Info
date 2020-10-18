package pl.edu.pg.StreamerInfo.datastore;

import org.springframework.stereotype.Component;
import pl.edu.pg.StreamerInfo.models.Game;
import pl.edu.pg.StreamerInfo.models.Streamer;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@Component
public class DataStore {
    private Set<Game> games = new HashSet<>();
    private Set<Streamer> streamers = new HashSet<>();

    public synchronized Stream<Game> fetchGames(){
        return games.stream();
    }

    public synchronized void addGame(Game game){
        games.add(game);
    }

    public synchronized void updateGame(Game game){
        fetchGames()
                .filter(origin -> origin.getId().equals(game.getId()))
                .findFirst()
                .ifPresent(original ->{
                    games.remove(original);
                    games.add(game);
                });
    }

    public synchronized void deleteGame(Game game){
        games.remove(game);
    }

    public synchronized Stream<Streamer> fetchStreamers(){
        return streamers.stream();
    }

    public synchronized void addStreamer(Streamer streamer){
        streamers.add(streamer);
    }

    public synchronized void updateStreamer(Streamer streamer){
        fetchStreamers()
                .filter(origin -> origin.getId().equals(streamer.getId()))
                .findFirst()
                .ifPresent(original ->{
                    streamers.remove(original);
                    streamers.add(streamer);
                });
    }

    public synchronized void deleteStreamer(Streamer streamer){
        streamers.remove(streamer);
    }

}
