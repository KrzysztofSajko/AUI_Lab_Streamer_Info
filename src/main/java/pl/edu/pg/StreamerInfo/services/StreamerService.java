package pl.edu.pg.StreamerInfo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.StreamerInfo.repositories.StreamerRepository;

@Service
public class StreamerService {
    private StreamerRepository repository;

    @Autowired
    public StreamerService(StreamerRepository repository){
        this.repository = repository;
    }
}
