package com.notes.demo.entities;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Note implements Serializable {

    @Id
    private String id;
    private String name;
    private String body;
    private String type;
}
