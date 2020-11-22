package pl.edu.pg.StreamerInfo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pl.edu.pg.StreamerInfo.dtos.event.CreateStreamerRequestEvent;
import pl.edu.pg.StreamerInfo.models.Streamer;

@Repository
public class StreamerEventRepository {
    private final RestTemplate restTemplate;

    @Autowired
    public StreamerEventRepository(@Value("${streamers-info.games.url}") String streamersUrl){
        this.restTemplate = new RestTemplateBuilder().rootUri(streamersUrl).build();
    }

    public void delete(Streamer streamer){
        restTemplate.delete("/streamers/{id}", streamer.getId());
    }

    public void create(Streamer streamer){
        restTemplate.postForLocation("/streamers", CreateStreamerRequestEvent.entityToDtoMapper().apply(streamer));
    }
}
