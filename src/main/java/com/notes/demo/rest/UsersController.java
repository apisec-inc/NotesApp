package com.notes.demo.rest;

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
@RequestMapping(value = "/api/users", consumes = "application/json", produces = "application/json")
public class UsersController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected static final BaseService<User> service = new BaseService<>();

    @PostMapping(headers = "content-type: application/json")
    public @ResponseBody User create(@RequestBody User e) {
        logger.info("Create [{}]", e);
        e.setId(RandomStringUtils.randomNumeric(4));
        e = this.service.save(e.getId(), e);
        return e;
    }

    @PutMapping()
    public @ResponseBody User update(@RequestBody User e) {
        logger.info("Save [{}]", e);
        return this.service.save(e.getId(), e);
    }

    @GetMapping(value = "/{id}")
    public @ResponseBody User findById(@PathVariable("id") String id) {
        logger.info("Find by ID [{}]", id);
        return this.service.findById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable String id) {
        logger.info("Deleting by id [{}]", id);
        this.service.delete(id);
    }

    @GetMapping()
    public @ResponseBody Collection<User> findAll(@RequestParam(required = false, defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        logger.info("Find all");
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.service.findAll(pageable);
    }
}
