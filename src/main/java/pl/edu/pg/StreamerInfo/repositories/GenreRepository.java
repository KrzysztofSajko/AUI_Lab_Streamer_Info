package pl.edu.pg.StreamerInfo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pg.StreamerInfo.models.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, String> { }
