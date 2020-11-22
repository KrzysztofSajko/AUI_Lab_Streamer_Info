package pl.edu.pg.StreamerInfo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pl.edu.pg.StreamerInfo.dtos.event.CreateGameRequestEvent;
import pl.edu.pg.StreamerInfo.models.Game;

@Repository
public class GameEventRepository {
    private final RestTemplate restTemplate;

    @Autowired
    public GameEventRepository(@Value("${streamer-info.streamers.url}") String streamersUrl){
        this.restTemplate = new RestTemplateBuilder().rootUri(streamersUrl).build();
    }

    public void delete(Game game){
        restTemplate.delete("/games/{id}", game.getId());
    }

    public void create(Game game){
        restTemplate.postForLocation("/games", CreateGameRequestEvent.entityToDtoMapper().apply(game));
    }
}
