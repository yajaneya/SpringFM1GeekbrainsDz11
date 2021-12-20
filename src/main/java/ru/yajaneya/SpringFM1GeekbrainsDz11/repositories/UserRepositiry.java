package ru.yajaneya.SpringFM1GeekbrainsDz11.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import ru.yajaneya.SpringFM1GeekbrainsDz11.entities.User;

import java.util.Optional;

@Repository
public interface UserRepositiry extends CrudRepository<User, Long>{
    Optional<User> findByUsername(String username);
}
