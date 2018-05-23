package com.stasio.database.controller;

import com.stasio.database.model.Film;
import com.stasio.database.model.Transaction;
import com.stasio.database.service.FilmService;
import com.stasio.database.service.TransactionService;
import com.stasio.database.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class TestController {


    private final UserService userService;
    private final FilmService filmService;
    private final TransactionService transactionService;


    @Autowired
    public TestController(UserService userService, FilmService filmService, TransactionService transactionService) {
        this.userService = userService;
        this.filmService = filmService;
        this.transactionService = transactionService;

    }

//    @RequestMapping(value = "/name/{name}/details", method = RequestMethod.GET)
//    public ResponseEntity<User> getUserByName(@PathVariable String name) {
//        User user = userService.getUserByName(name);
//        return new ResponseEntity<>(user, new HttpHeaders(), HttpStatus.OK);
//    }

//    @RequestMapping(value = "/{userId}/details", method = RequestMethod.GET)
//    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
//        User user = userService.getUserById(userId);
//        return new ResponseEntity<>(user, new HttpHeaders(), HttpStatus.OK);
//    }

//    @RequestMapping(value = "name/{id}/video", method = RequestMethod.GET)
//    public ResponseEntity<Set<Film>> getUsersFilms(@PathVariable Long id) {
//        User user = userService.getUserById(id);
//        Set<Film> films = filmService.getAllByUser(user);
//        return new ResponseEntity<>(films, new HttpHeaders(), HttpStatus.OK);
//    }


    @RequestMapping(value = "/transaction/{id}/details")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Transaction transaction = transactionService.getTransactionById(id);
        return new ResponseEntity<>(transaction, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/allFilms")
    public ResponseEntity<List<Film>> getAllFilms() {
        List<Film> films = filmService.getAll();
        return new ResponseEntity<>(films, new HttpHeaders(), HttpStatus.OK);
    }

//    @RequestMapping(value = "/add/{userName}",method = RequestMethod.GET)
//    public ResponseEntity<?> add(@PathVariable String userName) {
//        User user=new User(userName);
//        try{
//            userService.addUser(user);
//            System.err.println(user.toString());
//            return ResponseEntity.created(URI.create(userName)).build();
//        }catch (Exception e){
//            System.err.println("ten user jest już w bazie: ");
//            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.BAD_REQUEST);
//        }
//
//    }

    private void validateUser(String userId) {
//       this.userService.findByUsername(userId).
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    class UserNotFoundException extends RuntimeException {

        public UserNotFoundException(String userId) {
            super("could not find user '" + userId + "'.");
        }
    }
}
