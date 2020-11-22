package pl.edu.pg.StreamerInfo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pl.edu.pg.StreamerInfo.dtos.event.CreateGenreRequestEvent;
import pl.edu.pg.StreamerInfo.models.Genre;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GenreEventRepository {

    private final List<RestTemplate> restTemplates = new ArrayList<>();

    @Autowired
    public GenreEventRepository(@Value("${streamer-info.streamers.url}") String streamersUrl,
                                @Value("${streamer-info.games.url}") String gamesUrl){
        restTemplates.add(new RestTemplateBuilder().rootUri(streamersUrl).build());
        restTemplates.add(new RestTemplateBuilder().rootUri(gamesUrl).build());
    }

    public void delete(Genre genre){
        restTemplates.forEach(restTemplate -> restTemplate.delete("/genres/{id}", genre.getId()));
    }

    public void create(Genre genre){
        restTemplates.forEach(restTemplate ->
                restTemplate.postForLocation("/genres",
                        CreateGenreRequestEvent
                                .entityToDtoMapper()
                                .apply(genre)));
    }
}
