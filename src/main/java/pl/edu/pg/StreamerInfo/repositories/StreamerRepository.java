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
public interface StreamerRepository extends JpaRepository<Streamer, Long> {
    @Query("select s from Streamer s join s.playedGames g where g = :game")
    List<Streamer> findAllByGame(@Param("game") GameReduced gameReduced);

    @Query("select s from Streamer s join s.playedGames g where g.id = :gameId")
    List<Streamer> findAllByGame(@Param("gameId") Long gameId);

    @Query("select s from Streamer s join s.playedGames g where s.id = :streamerId and g.id = :gameId")
    Optional<Streamer> findAllByIdAndGameId(@Param("streamerId") Long streamerId, @Param("gameId") Long gameId);

    @Query("select s from Streamer s join s.playedGames g join g.genreReduced gen where gen.id = :genre")
    List<Streamer> findAllByGenreId(@Param("genre") Long genreId);

    @Query("select s from Streamer s join s.playedGames g join g.genreReduced gen where s.id = :streamerId and gen.id = :genre")
    Optional<Streamer> findAllByIdAndGenreId(@Param("streamerId") Long streamerId, @Param("genre") Long genreId);

    @Query("select s from Streamer s join s.playedGames g join g.genreReduced gen where gen = :genre")
    List<Streamer> findAllByGenre(@Param("genre") GenreReduced genre);
}
