package pl.edu.pg.StreamerInfo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.pg.StreamerInfo.models.Game;
import pl.edu.pg.StreamerInfo.models.Genre;
import pl.edu.pg.StreamerInfo.models.Streamer;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findAllByGenre(Genre genre);
    Optional<Game> findByName(String name);

    @Query("select g from Game g join g.streamers s where s = :streamer")
    List<Game> findAllByStreamer(@Param("streamer") Streamer streamer);
}
