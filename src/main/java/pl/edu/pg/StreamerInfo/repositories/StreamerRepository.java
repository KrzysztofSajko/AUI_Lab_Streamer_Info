package pl.edu.pg.StreamerInfo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.edu.pg.StreamerInfo.models.Game;
import pl.edu.pg.StreamerInfo.models.GenreReduced;
import pl.edu.pg.StreamerInfo.models.StreamerReduced;

import java.util.List;

@Repository
public interface StreamerRepository extends JpaRepository<StreamerReduced, Long> {
    @Query("select s from StreamerReduced s join s.playedGames g where g = :game")
    List<StreamerReduced> findAllByGame(@Param("game") Game game);

    @Query("select s from StreamerReduced s join s.playedGames g where g.genre = :genre")
    List<StreamerReduced> findAllByGenre(@Param("genre") GenreReduced genreReduced);
}
