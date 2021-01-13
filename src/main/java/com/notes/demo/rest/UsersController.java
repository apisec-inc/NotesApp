package com.notes.demo.rest;

import com.notes.demo.rest.BaseController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersController<User> extends BaseController {
}
