package com.stasio.database.repository;

import com.stasio.database.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    Tag getTagByName(String name);

    @Override
    List<Tag> findAll();
}
