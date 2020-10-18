package pl.edu.pg.StreamerInfo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.pg.StreamerInfo.datastore.DataStore;

@Repository
public class GameRepository {
    private DataStore dataStore;

    @Autowired
    public GameRepository(DataStore dataStore){
        this.dataStore = dataStore;
    }
}
