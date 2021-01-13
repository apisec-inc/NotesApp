package com.notes.demo.rest;

import com.notes.demo.entities.Note;
import com.notes.demo.entities.User;
import com.notes.demo.services.BaseService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/notes")
public class NoteController  {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    BaseService<Note> service = new BaseService<>();

    @PostMapping()
    public Note create(@RequestBody Note e) {
        logger.info("Create [{}]", e);
        e.setId(RandomStringUtils.randomNumeric(4));
        return this.service.save(e.getId(), e);
    }

    @PutMapping()
    public Note update(@RequestBody Note e) {
        logger.info("Save [{}]", e);
        return this.service.save(e.getId(), e);
    }

    @GetMapping(value = "/{id}")
    public Note findById(@PathVariable("id") String id) {
        logger.info("Find by ID [{}]", id);
        return this.service.findById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable String id) {
        logger.info("Deleting by id [{}]", id);
        this.service.delete(id);
    }

    @GetMapping()
    public Collection<Note> findAll(Pageable pageable) {
        logger.info("Find all");
        return this.service.findAll(pageable);
    }
}
