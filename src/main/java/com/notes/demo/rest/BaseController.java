package com.notes.demo.rest;

import com.notes.demo.entities.BaseEntity;
import com.notes.demo.services.BaseService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.converters.models.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

//@RestController
//@RequestMapping("/api/users")
public class BaseController<E extends BaseEntity> {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    BaseService<E> service = new BaseService<>();

    @PostMapping()
    public E create(@RequestBody E e) {
        logger.info("Create [{}]", e);
        e.setId(RandomStringUtils.randomNumeric(4));
        return this.service.save(e.getId(), e);
    }

    @PutMapping()
    public E update(@RequestBody E e) {
        logger.info("Save [{}]", e);
        return this.service.save(e.getId(), e);
    }

    @GetMapping(value = "/{id}")
    public E findById(@PathVariable("id") String id) {
        logger.info("Find by ID [{}]", id);
        return this.service.findById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable String id) {
        logger.info("Deleting by id [{}]", id);
        this.service.delete(id);
    }

    @GetMapping()
    public Collection<E> findAll(Pageable pageable) {
        logger.info("Find all");
        return this.service.findAll(pageable);
    }
}
