package com.notes.demo.rest;

import com.notes.demo.services.BaseService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    BaseService<com.notes.demo.entities.User> service = new BaseService<>();

    @PostMapping()
    public com.notes.demo.entities.User create(@RequestBody com.notes.demo.entities.User e) {
        logger.info("Create [{}]", e);
        e.setId(RandomStringUtils.randomNumeric(4));
        return this.service.save(e.getId(), e);
    }

    @PutMapping()
    public com.notes.demo.entities.User update(@RequestBody com.notes.demo.entities.User e) {
        logger.info("Save [{}]", e);
        return this.service.save(e.getId(), e);
    }

    @GetMapping(value = "/{id}")
    public com.notes.demo.entities.User findById(@PathVariable("id") String id) {
        logger.info("Find by ID [{}]", id);
        return this.service.findById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable String id) {
        logger.info("Deleting by id [{}]", id);
        this.service.delete(id);
    }

    @GetMapping()
    public Collection<com.notes.demo.entities.User> findAll(Pageable pageable) {
        logger.info("Find all");
        return this.service.findAll(pageable);
    }
}
