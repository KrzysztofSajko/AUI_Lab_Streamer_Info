package pl.edu.pg.StreamerInfo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.pg.StreamerInfo.models.Game;
import pl.edu.pg.StreamerInfo.models.GenreReduced;
import pl.edu.pg.StreamerInfo.models.StreamerReduced;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    @Query("select g from Game g where g.genreReduced = :genre")
    List<Game> findAllByGenre(@Param("genre")GenreReduced genreReduced);

    Optional<Game> findByName(String name);

    @Query("select g from Game g join g.streamersReduced s where s = :streamer")
    List<Game> findAllByStreamer(@Param("streamer") StreamerReduced streamerReduced);

    @Query("select g from Game g join g.streamersReduced s where g.id = :id and s.id = :streamerId")
    Optional<Game> findByIdAndStreamerId(@Param("id") Long id, @Param("streamerId") Long streamerId);

    @Query("select g from Game g where g.id = :id and g.genreReduced.id = :genreId")
    Optional<Game> findByIdAndGenreId(@Param("id") Long id, @Param("genreId") Long genreId);
}
