package pl.edu.pg.StreamerInfo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.StreamerInfo.models.Game;
import pl.edu.pg.StreamerInfo.models.GenreReduced;
import pl.edu.pg.StreamerInfo.models.StreamerReduced;
import pl.edu.pg.StreamerInfo.services.GameService;
import pl.edu.pg.StreamerInfo.services.GenreService;
import pl.edu.pg.StreamerInfo.services.StreamerService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

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
        // init genres
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
        var wow = Game.builder()
                .name("World of Warcraft")
                .abbreviation("wow")
                .genreReduced(mmorpg)
                .description("A mmorpg set in Warcraft lore which gives players various pvp and pve activities.")
                .build();
        gameService.create(wow);

        var tft = Game.builder()
                .name("Teamfight Tactics")
                .abbreviation("tft")
                .genreReduced(autoBattler)
                .description("Auto Battler with LoL characters.")
                .build();
        gameService.create(tft);

        var hs = Game.builder()
                .name("Hearthstone")
                .abbreviation("hs")
                .genreReduced(cardGame)
                .description("Card game where players create decks using cards from their collection with both pvp and pve modes.")
                .build();
        gameService.create(hs);

        var tesSkyrim = Game.builder()
                .name("The Elder Scrolls V: Skyrim")
                .abbreviation("skyrim")
                .genreReduced(rpg)
                .description("Another game in the series of The Elder Scrolls, this time placed in the province of Skyrim.")
                .build();
        gameService.create(tesSkyrim);

        var amongUs = Game.builder()
                .name("Among Us")
                .abbreviation("among")
                .genreReduced(socialDeductionGame)
                .description("Among the crew there are impostors, crewmates have to find out who they are and get rid of them or try to finish their task before all of them get murdered.")
                .build();
        gameService.create(amongUs);

        // init streamers
        var black = StreamerReduced.builder()
                .id(1L)
                .playedGames(new HashSet<>(Set.of(hs, tft)))
                .build();
        streamerService.create(black);

        var jdotb = StreamerReduced.builder()
                .id(2L)
                .playedGames(new HashSet<>(Set.of(wow)))
                .build();
        streamerService.create(jdotb);

        var sco = StreamerReduced.builder()
                .id(3L)
                .playedGames(new HashSet<>(Set.of(wow)))
                .build();
        streamerService.create(sco);

        var jorbs = StreamerReduced.builder()
                .id(4L)
                .playedGames(new HashSet<>(Set.of(amongUs, hs)))
                .build();
        streamerService.create(jorbs);
    }
}
