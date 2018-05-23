package com.stasio.database.controller;

import com.stasio.database.model.User;
import com.stasio.database.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//import org.springframework.dao.DataAccessException;
//import org.springframework.dao.DataAccessException;
//import org.springframework.web.servlet.HandlerExceptionResolver;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/add/{userName}", method = RequestMethod.GET)
    public HttpStatus add(@PathVariable String userName) {
        User user = new User(userName);
        try {
            userService.addUser(user);
            System.err.println("created" + user.toString());
            return HttpStatus.OK;
        } catch (Exception e) {
            System.err.println("ten user jest już w bazie: ");

            return HttpStatus.NOT_MODIFIED;
        }

    }

    @RequestMapping(value = "/get/{userId}/details", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId) {
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/name/{name}/details", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByName(@PathVariable String name) {
        User user = userService.getUserByName(name);
        return new ResponseEntity<>(user, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/allusers")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userService.getAllUsers(), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/name/{userName}", method = RequestMethod.GET)
    public HttpStatus delete(@PathVariable String userName) {
        User user;
        try {
            user = userService.getUserByName(userName);
            userService.delete(user);
            return HttpStatus.OK;

        } catch (NullPointerException e) {
            return HttpStatus.NOT_MODIFIED;
        }
    }

}
