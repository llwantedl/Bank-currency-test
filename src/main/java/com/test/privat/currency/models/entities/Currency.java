package com.test.privat.currency.models.entities;

import org.hibernate.annotations.Entity;

import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "CURRENCY")
public class Currency implements Serializable {
}
