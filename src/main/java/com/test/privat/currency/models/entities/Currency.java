package com.test.privat.currency.models.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CURRENCY")
public class Currency implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "key", unique = true)
    private String key;

    public Currency() {
    }

    public Currency(String key) {
        this.key = key;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
