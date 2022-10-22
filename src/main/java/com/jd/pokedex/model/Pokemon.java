package com.jd.pokedex.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "pokedex")
public class Pokemon {
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;
}
