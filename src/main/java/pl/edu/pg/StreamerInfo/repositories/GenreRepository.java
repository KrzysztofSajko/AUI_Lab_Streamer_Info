package pl.edu.pg.StreamerInfo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pg.StreamerInfo.datastore.DataStore;
import pl.edu.pg.StreamerInfo.models.Genre;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class GenreRepository implements Repository<Genre, String> {
    private DataStore dataStore;

    @Autowired
    public GenreRepository(DataStore dataStore){
        this.dataStore = dataStore;
    }

    @Override
    public List<Genre> findAll() {
        return dataStore.fetchGenres().collect(Collectors.toList());
    }

    @Override
    public Optional<Genre> findByKey(String id) {
        return dataStore
                .fetchGenres()
                .filter(genre -> genre
                        .getName()
                        .equals(id))
                .findFirst();
    }

    @Override
    public void add(Genre entity) {
        dataStore.addGenre(entity);
    }

    @Override
    public void update(Genre entity) {
        dataStore.updateGenre(entity);
    }

    @Override
    public void delete(Genre entity) {
        dataStore.deleteGenre(entity);
    }
}
