package com.notes.demo.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BaseService<E> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private Map<String, E> repository = new ConcurrentHashMap<>();


    public E save(String id, E e) {
        //e.setId(RandomStringUtils.randomNumeric(4));
        this.repository.put(id, e);
        return e;
    }

    public E findById(String id) {
        return this.repository.get(id);
    }

    public Collection<E> findAll(Pageable pageable) {
        return this.repository.values();
    }

    public void delete(String id) {
        this.repository.remove(id);
    }
}
