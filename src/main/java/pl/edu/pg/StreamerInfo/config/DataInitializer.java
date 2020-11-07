package pl.edu.pg.StreamerInfo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.StreamerInfo.models.Game;
import pl.edu.pg.StreamerInfo.models.Genre;
import pl.edu.pg.StreamerInfo.models.Streamer;
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
        var mmorpg = Genre.builder()
                .name("MMORPG")
                .description("Massively multiplayer online games where players can create, progress and role play their characters.")
                .build();
        genreService.create(mmorpg);

        var moba = Genre.builder()
                .name("MOBA")
                .description("Players in teams control one character each, goal is to destroy opposite team's hub while protecting your own.")
                .build();
        genreService.create(moba);

        var autoBattler = Genre.builder()
                .name("Auto Battler")
                .description("Strategy game where players choose and place their units during prepare phase, after which comes battle phase where units of opposing players fight each other without further inputs of players.")
                .build();
        genreService.create(autoBattler);

        var roguelike = Genre.builder()
                .name("Roguelike")
                .description("2D dungeon crawler with randomly generated structures, where player has to reach the end of a run, acquiring mulitple upgrades along the way.")
                .build();
        genreService.create(roguelike);

        var rpg = Genre.builder()
                .name("RPG")
                .description("Single player game where player create their character and progress them through the game's storyline.")
                .build();
        genreService.create(rpg);

        var fps = Genre.builder()
                .name("FPS")
                .description("Games where players get shoot any kind of ranged weapons in first person perspective.")
                .build();
        genreService.create(fps);

        var cardGame = Genre.builder()
                .name("Card Game")
                .description("Game where player has a deck of cards which determines the possible set of actions for a player.")
                .build();
        genreService.create(cardGame);

        var socialDeductionGame = Genre.builder()
                .name("Social Deduction Game")
                .description("Game where players are in a group, some of those players are chosen to actively sabotage the rest. Players try to figure out who is sabotaging before its too late.")
                .build();
        genreService.create(socialDeductionGame);

        // init games
        var wow = Game.builder()
                .name("World of Warcraft")
                .abbreviation("wow")
                .genre(mmorpg)
                .description("A mmorpg set in Warcraft lore which gives players various pvp and pve activities.")
                .build();
        gameService.create(wow);

        var tft = Game.builder()
                .name("Teamfight Tactics")
                .abbreviation("tft")
                .genre(autoBattler)
                .description("Auto Battler with LoL characters.")
                .build();
        gameService.create(tft);

        var hs = Game.builder()
                .name("Hearthstone")
                .abbreviation("hs")
                .genre(cardGame)
                .description("Card game where players create decks using cards from their collection with both pvp and pve modes.")
                .build();
        gameService.create(hs);

        var tesSkyrim = Game.builder()
                .name("The Elder Scrolls V: Skyrim")
                .abbreviation("skyrim")
                .genre(rpg)
                .description("Another game in the series of The Elder Scrolls, this time placed in the province of Skyrim.")
                .build();
        gameService.create(tesSkyrim);

        var amongUs = Game.builder()
                .name("Among Us")
                .abbreviation("among")
                .genre(socialDeductionGame)
                .description("Among the crew there are impostors, crewmates have to find out who they are and get rid of them or try to finish their task before all of them get murdered.")
                .build();
        gameService.create(amongUs);

        // init streamers
        var black = Streamer.builder()
                .name("BlackFireIce")
                .description("Mostly strategy oriented games, e-sports commentator.")
                .playedGames(new HashSet<>(Set.of(hs, tft)))
                .build();
        streamerService.create(black);

        var jdotb = Streamer.builder()
                .name("jdotb")
                .description("World class wow m+ healer, known for showing his chest hair on streams to his audience content.")
                .playedGames(new HashSet<>(Set.of(wow)))
                .build();
        streamerService.create(jdotb);

        var sco = Streamer.builder()
                .name("Sco")
                .description("World class tank, multiple R2WF winner, gm of Method guild and founder of Method gaming org.")
                .playedGames(new HashSet<>(Set.of(wow)))
                .build();
        streamerService.create(sco);

        var jorbs = Streamer.builder()
                .name("Jorbs")
                .description("Big brain dude, 200 iq plays are a norm for him")
                .playedGames(new HashSet<>(Set.of(amongUs, hs)))
                .build();
        streamerService.create(jorbs);
    }
}
