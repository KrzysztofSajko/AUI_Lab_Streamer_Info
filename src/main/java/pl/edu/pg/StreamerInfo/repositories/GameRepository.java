package pl.edu.pg.StreamerInfo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.pg.StreamerInfo.models.GameReduced;
import pl.edu.pg.StreamerInfo.models.GenreReduced;
import pl.edu.pg.StreamerInfo.models.Streamer;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<GameReduced, Long> {
    @Query("select g from GameReduced g where g.genreReduced = :genre")
    List<GameReduced> findAllByGenre(@Param("genre")GenreReduced genreReduced);

    @Query("select g from GameReduced g join g.streamers s where s = :streamer")
    List<GameReduced> findAllByStreamer(@Param("streamer") Streamer streamer);
}
