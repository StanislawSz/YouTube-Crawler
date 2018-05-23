package com.stasio.database.controller;

import com.stasio.database.model.Film;
import com.stasio.database.model.Tag;
import com.stasio.database.model.User;
import com.stasio.database.model.Wallet;
import com.stasio.database.service.FilmService;
import com.stasio.database.service.TagService;
import com.stasio.database.service.UserService;
import com.stasio.database.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {

    private UserService userService;
    private FilmService filmService;
    private TagService tagService;
    private WalletService walletService;

    @Autowired
    public FilmController(UserService userService, FilmService filmService, TagService tagService, WalletService walletService) {
        this.userService = userService;
        this.filmService = filmService;
        this.tagService = tagService;
        this.walletService = walletService;
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public HttpStatus addFilm(@RequestParam String userName, @RequestParam String title, @RequestParam String link, @RequestParam List<String> tags, @RequestParam String walletNr) {
        User user = userService.createIfNotExist(userName);
        System.err.println("USER:" + user.toString());
        tagService.addTags(tags);

        List<Tag> tagList = tagService.getTagList(tags);
//        System.err.println("TAGS");


        Film film = filmService.createIfNotExist(title, link, user, tagList);

        Wallet wallet = walletService.createIfNotExist(walletNr, user, film);
//        System.err.println(wallet.toString());
        System.err.println("OK");
        return HttpStatus.OK;

    }


    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Film> getFilmById(@PathVariable Long id) {
        Film film = filmService.getFilmById(id);
        return new ResponseEntity<>(film, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public ResponseEntity<List<Film>> getAll() {
        List<Film> films = filmService.getAll();
        return new ResponseEntity<>(films, new HttpHeaders(), HttpStatus.OK);
    }
}

