package com.notes.demo.rest;

import com.notes.demo.entities.Note;
import com.notes.demo.entities.User;
import com.notes.demo.services.BaseService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/notes")
public class NoteController  {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected static final BaseService<Note> service = new BaseService<>();

    @PostMapping()
    public @ResponseBody Note create(@RequestBody Note e) {
        logger.info("Create [{}]", e);
        e.setId(RandomStringUtils.randomNumeric(4));
        return this.service.save(e.getId(), e);
    }

    @PutMapping()
    public @ResponseBody Note update(@RequestBody Note e) {
        logger.info("Save [{}]", e);
        return this.service.save(e.getId(), e);
    }

    @GetMapping(value = "/{id}")
    public @ResponseBody Note findById(@PathVariable("id") String id) {
        logger.info("Find by ID [{}]", id);
        return this.service.findById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable String id) {
        logger.info("Deleting by id [{}]", id);
        this.service.delete(id);
    }

    @GetMapping()
    public @ResponseBody Collection<Note> findAll(@RequestParam(required = false, defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        logger.info("Find all");
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.service.findAll(pageable);
    }
}
