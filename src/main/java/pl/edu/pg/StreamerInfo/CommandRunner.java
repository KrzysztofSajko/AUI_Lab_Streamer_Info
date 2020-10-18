package pl.edu.pg.StreamerInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.edu.pg.StreamerInfo.services.GameService;
import pl.edu.pg.StreamerInfo.services.GenreService;
import pl.edu.pg.StreamerInfo.services.StreamerService;

@Component
public class CommandRunner implements CommandLineRunner {
    private GenreService genreService;
    private GameService gameService;
    private StreamerService streamerService;

    @Autowired
    public CommandRunner(GenreService genreService,
                         GameService gameService,
                         StreamerService streamerService){

        this.genreService = genreService;
        this.gameService = gameService;
        this.streamerService = streamerService;
    }
    @Override
    public void run(String... args) throws Exception {
        genreService.findAll().forEach(System.out::println);
    }
}
