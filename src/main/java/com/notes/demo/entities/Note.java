package com.notes.demo.entities;
import com.notes.demo.entities.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Note extends BaseEntity {

    private String name;
    private String body;
    private String type;
}
