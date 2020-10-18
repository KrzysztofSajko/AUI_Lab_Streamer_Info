package pl.edu.pg.StreamerInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.StreamerInfo.services.GameService;
import pl.edu.pg.StreamerInfo.services.GenreService;
import pl.edu.pg.StreamerInfo.services.StreamerService;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {
    private GameService gameService;
    private StreamerService streamerService;
    private GenreService genreService;

    @Autowired
    public DataInitializer(GameService gameService,
                           StreamerService streamerService,
                           GenreService genreService){

        this.gameService = gameService;
        this.streamerService = streamerService;
        this.genreService = genreService;
    }

    @PostConstruct
    public void init(){
        // initialize data here
    }
}
