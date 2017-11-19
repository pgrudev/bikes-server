package pl.pgrudev.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.pgrudev.client.User;

import java.util.List;

public interface CustomerRepository extends MongoRepository<User, String> {
    public List<User> findByFirstName(String firstName);
    public List<User> findByLastName(String lastName);
}
