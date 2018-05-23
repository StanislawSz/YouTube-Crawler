package com.stasio.database.service;

import com.stasio.database.model.Tag;
import com.stasio.database.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Transactional
    public void addIfNotExist(String name) {
        Tag tag;
        try {
            tag = tagRepository.getTagByName(name);
            System.err.println(tag.toString());
        } catch (NullPointerException e) {
            tag = new Tag(name);
            System.err.println("saved tag");
            tagRepository.save(tag);
        }
    }

    @Transactional
    public void addTags(List<String> tags) {
        for (String string : tags) {
            addIfNotExist(string);
        }
    }

    @Transactional
    public List<Tag> getTagList(List<String> tags) {
        Tag tag;
        List<Tag> tagList = new LinkedList<>();
        for (String s : tags) {
            tag = tagRepository.getTagByName(s);
            tagList.add(tag);
        }
//        System.err.println("taglist:" + tagList.toString());
        return tagList;
    }

    public List<Tag> getAll() {
        return tagRepository.findAll();
    }
}
