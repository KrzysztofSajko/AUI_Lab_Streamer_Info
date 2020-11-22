package pl.edu.pg.StreamerInfo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.StreamerInfo.models.Genre;
import pl.edu.pg.StreamerInfo.services.GenreService;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {
    private final GenreService genreService;

    @Autowired
    public DataInitializer(GenreService genreService){
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
    }
}
