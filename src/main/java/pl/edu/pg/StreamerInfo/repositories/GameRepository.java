package pl.edu.pg.StreamerInfo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.StreamerInfo.models.Game;
import pl.edu.pg.StreamerInfo.models.Genre;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface GameRepository extends JpaRepository<Game, String> {
    List<Game> findAllByGenre(Genre genre);
}
