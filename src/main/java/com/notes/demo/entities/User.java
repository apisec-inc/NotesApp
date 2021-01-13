package com.notes.demo.entities;
import com.notes.demo.entities.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User extends BaseEntity {

    private String username;
    private String password;
}
