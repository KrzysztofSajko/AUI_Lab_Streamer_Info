package pl.edu.pg.StreamerInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StreamerInfo {

    public static void main(String[] args) {
        SpringApplication.run(StreamerInfo.class, args);
    }

    @Autowired
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder,
                                           @Value("${host.url}") String hostUrl,
                                           @Value("${sinfo.genres.url}") String genresUrl,
                                           @Value("${sinfo.games.url}") String gamesUrl,
                                           @Value("${sinfo.streamers.url}") String streamersUrl) {

        return builder.routes()
                .route("genres", r -> r.host(hostUrl)
                        .and()
                        .path("/api/genres", "/api/genres/{id}")
                        .uri(genresUrl))
                .route("games", r -> r.host("localhost:8080")
                        .and()
                        .path("/api/games", "/api/game/**",
                                "/api/genres/{id}/games", "/api/genres/{id}/games/**",
                                "/api/streamers/{id}/games", "/api/streamers/{id}/games/**")
                        .uri(gamesUrl))
                .route("streamers", r -> r.host("localhost:8080")
                        .and()
                        .path("/api/streamers", "/api/streamers/**",
                                "/api/genres/{id}/streamers", "/api/genres/{id}/streamers/**",
                                "/api/games/{id}/streamers", "/api/games/{id}/streamers/**")
                        .uri(streamersUrl))
                .build();
    }
}
