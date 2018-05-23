package com.stasio.database.repository;

import com.stasio.database.model.Film;
import com.stasio.database.model.Tag;
import com.stasio.database.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository("filmRepository")
public interface FilmRepository extends JpaRepository<Film, Long> {
    Film getFilmByLinkAndUser(String link, User user);

    Set<Film> getAllByUser(User user);

    Set<Film> getAllByTags(Tag tag);

    Set<Film> getAllByUserAndTags(User user, Tag tag);

    Film getFilmById(Long id);


    @Override
    List<Film> findAll();



}
