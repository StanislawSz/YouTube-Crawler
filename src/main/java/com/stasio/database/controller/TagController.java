package com.stasio.database.controller;

import com.stasio.database.model.Tag;
import com.stasio.database.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }


    @RequestMapping(value = "/all")
    public ResponseEntity<List<Tag>> getAllFilms() {
        List<Tag> tags = tagService.getAll();
        return new ResponseEntity<>(tags, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public HttpStatus add(@RequestParam List<String> tags) {
//        System.err.println(tags);
        tagService.addTags(tags);
        return HttpStatus.OK;
    }
}
