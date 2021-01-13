package com.notes.demo.rest;

import com.notes.demo.rest.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notes")
public class NoteController<User> extends BaseController {
}
