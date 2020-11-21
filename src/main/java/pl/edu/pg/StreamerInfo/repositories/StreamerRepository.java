package pl.edu.pg.StreamerInfo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.edu.pg.StreamerInfo.models.Game;
import pl.edu.pg.StreamerInfo.models.Genre;
import pl.edu.pg.StreamerInfo.models.Streamer;

import java.util.List;

@Repository
public interface StreamerRepository extends JpaRepository<Streamer, Long> {
    @Query("select s from Streamer s join s.playedGames g where g = :game")
    List<Streamer> findAllByGame(@Param("game") Game game);

    @Query("select s from Streamer s join s.playedGames g where lower(g.name) = lower(:gameName)")
    List<Streamer> findAllByGame(@Param("gameName") String gameName);

    @Query("select s from Streamer s join s.playedGames g where g.id = :gameId")
    List<Streamer> findAllByGame(@Param("gameId") Long gameId);

    @Query("select s from Streamer s join s.playedGames g join g.genre gen where gen = :genre")
    List<Streamer> findAllByGenre(@Param("genre") Genre genre);

    @Query("select s from Streamer s join s.playedGames g join g.genre gen where lower(gen.name) = lower(:genreName)")
    List<Streamer> findAllByGenre(@Param("genreName") String genreName);
}
