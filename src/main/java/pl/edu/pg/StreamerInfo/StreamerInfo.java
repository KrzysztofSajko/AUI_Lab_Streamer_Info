package pl.edu.pg.StreamerInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

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
                .route("games", r -> r.host(hostUrl)
                        .and()
                        .path("/api/games", "/api/games/**",
                                "/api/genres/{genreId}/games", "/api/genres/{genreId}/games/**",
                                "/api/streamers/{streamerId}/games", "/api/streamers/{streamerId}/games/**")
                        .uri(gamesUrl))
                .route("streamers", r -> r.host(hostUrl)
                        .and()
                        .path("/api/streamers", "/api/streamers/**",
                                "/api/genres/{genreId}/streamers", "/api/genres/{genreId}/streamers/**",
                                "/api/games/{gameId}/streamers", "/api/games/{gameId}/streamers/**")
                        .uri(streamersUrl))
                .build();
    }

    @Bean
    public CorsWebFilter corsWebFilter() {

        final CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Collections.singletonList("*"));
        corsConfig.setMaxAge(3600L);
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        corsConfig.addAllowedHeader("*");

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}
