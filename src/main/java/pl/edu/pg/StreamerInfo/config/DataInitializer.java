package pl.edu.pg.StreamerInfo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.StreamerInfo.models.GameReduced;
import pl.edu.pg.StreamerInfo.models.GenreReduced;
import pl.edu.pg.StreamerInfo.models.Streamer;
import pl.edu.pg.StreamerInfo.services.GameService;
import pl.edu.pg.StreamerInfo.services.GenreService;
import pl.edu.pg.StreamerInfo.services.StreamerService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer {
    private final StreamerService streamerService;
    private final GameService gameService;
    private final GenreService genreService;

    @Autowired
    public DataInitializer(StreamerService streamerService, GenreService genreService, GameService gameService){
        this.streamerService = streamerService;
        this.genreService = genreService;
        this.gameService = gameService;
    }

    @PostConstruct
    public void init(){
        //init genres
        var mmorpg = GenreReduced.builder()
                .id(1L)
                .build();
        genreService.create(mmorpg);

        var moba = GenreReduced.builder()
                .id(2L)
                .build();
        genreService.create(moba);

        var autoBattler = GenreReduced.builder()
                .id(3L)
                .build();
        genreService.create(autoBattler);

        var roguelike = GenreReduced.builder()
                .id(4L)
                .build();
        genreService.create(roguelike);

        var rpg = GenreReduced.builder()
                .id(5L)
                .build();
        genreService.create(rpg);

        var fps = GenreReduced.builder()
                .id(6L)
                .build();
        genreService.create(fps);

        var cardGame = GenreReduced.builder()
                .id(7L)
                .build();
        genreService.create(cardGame);

        var socialDeductionGame = GenreReduced.builder()
                .id(8L)
                .build();
        genreService.create(socialDeductionGame);

        // init games
        var wow = GameReduced.builder()
                .id(1L)
                .genreReduced(mmorpg)
                .build();
        gameService.create(wow);

        var tft = GameReduced.builder()
                .id(2L)
                .genreReduced(autoBattler)
                .build();
        gameService.create(tft);

        var hs = GameReduced.builder()
                .id(3L)
                .genreReduced(cardGame)
                .build();
        gameService.create(hs);

        var tesSkyrim = GameReduced.builder()
                .id(4L)
                .genreReduced(rpg)
                .build();
        gameService.create(tesSkyrim);

        var amongUs = GameReduced.builder()
                .id(5L)
                .genreReduced(socialDeductionGame)
                .build();
        gameService.create(amongUs);

        // init streamers
        var black = Streamer.builder()
                .name("BlackFireIce")
                .description("Mostly strategy oriented games, e-sports commentator.")
                .playedGames(new HashSet<>(Set.of()))
                .build();
        streamerService.create(black);

        var jdotb = Streamer.builder()
                .name("jdotb")
                .description("World class wow m+ healer, known for showing his chest hair on streams to his audience content.")
                .playedGames(new HashSet<>(Set.of()))
                .build();
        streamerService.create(jdotb);

        var sco = Streamer.builder()
                .name("Sco")
                .description("World class tank, multiple R2WF winner, gm of Method guild and founder of Method gaming org.")
                .playedGames(new HashSet<>(Set.of()))
                .build();
        streamerService.create(sco);

        var jorbs = Streamer.builder()
                .name("Jorbs")
                .description("Big brain dude, 200 iq plays are a norm for him")
                .playedGames(new HashSet<>(Set.of()))
                .build();
        streamerService.create(jorbs);
    }
}
