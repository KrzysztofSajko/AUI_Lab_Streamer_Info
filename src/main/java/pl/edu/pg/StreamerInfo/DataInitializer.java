package pl.edu.pg.StreamerInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.StreamerInfo.services.GameService;
import pl.edu.pg.StreamerInfo.services.StreamerService;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {
    private GameService gameService;
    private StreamerService streamerService;

    @Autowired
    public DataInitializer(GameService gameService, StreamerService streamerService){
        this.gameService = gameService;
        this.streamerService = streamerService;
    }

    @PostConstruct
    public void init(){
        // initialize data here
    }
}
