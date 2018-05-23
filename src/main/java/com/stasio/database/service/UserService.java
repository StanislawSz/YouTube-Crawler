package com.stasio.database.service;

import com.stasio.database.model.User;
import com.stasio.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service("userService")
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(@Qualifier("userRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Transactional
    public User getUserById(Long id) {
        System.err.println(userRepository.getUserById(id).toString());
        return userRepository.getUserById(id);
    }

    @Transactional
    public User getUserByName(String name) {
//        System.err.println(userRepository.getUserByName(name).toString());
        return userRepository.getUserByName(name);
    }

    @Transactional
    public User findByUsername(String name) {
        return userRepository.findByName(name);
    }

    @Transactional
    public List<String> getAllUsernames() {
        List<String> names = new LinkedList<>();
        for (User u :
                userRepository.findAll()) {
            names.add(u.getName());
        }
        return names;
    }


    @Transactional
    public User createIfNotExist(String name) {
        User user;
        try {
            user = getUserByName(name);
            System.err.println(user.toString());
        } catch (NullPointerException e) {
            addUser(new User(name));
            user = getUserByName(name);
            System.err.println("Created user:" + user.toString());
        }
        return user;
    }

    public void delete(User user) {
        userRepository.deleteById(user.getId());
    }

    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
