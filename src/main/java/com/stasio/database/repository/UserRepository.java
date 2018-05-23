package com.stasio.database.repository;

import com.stasio.database.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByName(String name);

    User getUserById(Long id);

    User findByName(String name);

//    boolean deleteUserById(Long id);



    @Override
    List<User> findAll();
}
