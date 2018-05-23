package com.stasio.database.service;

import com.stasio.database.model.Film;
import com.stasio.database.model.Tag;
import com.stasio.database.model.User;
import com.stasio.database.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service("filmService")
public class FilmService {

    private final FilmRepository filmRepository;

    @Autowired
    public FilmService(@Qualifier("filmRepository") FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Transactional
    public void addFilm(Film film) {
        filmRepository.save(film);
    }

    @Transactional
    public Film createIfNotExist(String title, String link, User user, List<Tag> tags) {
        Film film;
        try {
//            System.err.println("try");
            film = filmRepository.getFilmByLinkAndUser(link, user);
            System.err.println(film.toString());
            return film;

        } catch (NullPointerException e) {
//            System.err.println("catch film null:");
            film = new Film(title, link, user, tags);
            addFilm(film);
            System.err.println("created film:" + film.toString());
            film = filmRepository.getFilmByLinkAndUser(link, user);
            return film;
        }
    }

    @Transactional
    public Set<Film> getAllByUser(User user) {
        return filmRepository.getAllByUser(user);
    }

    @Transactional
    public Set<Film> getAllByTags(Tag tag) {
        return filmRepository.getAllByTags(tag);
    }

    @Transactional
    public Set<Film> getAllByUserAndTags(User user, Tag tag) {
        return filmRepository.getAllByUserAndTags(user, tag);
    }

    @Transactional
    public Film getFilmById(Long id) {
        return filmRepository.getFilmById(id);
    }


    @Transactional
    public List<Film> getAll() {
        return filmRepository.findAll();
    }

}
