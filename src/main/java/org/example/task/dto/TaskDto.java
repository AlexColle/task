package org.example.task.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class TaskDto {

    private int id;

    private String nome;

    private String descrizione;

    private String stato;
}
