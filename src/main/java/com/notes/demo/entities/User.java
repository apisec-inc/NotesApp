package com.notes.demo.entities;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class User implements Serializable {

    @Id
    private String id;
    private String username;
    private String password;
}
