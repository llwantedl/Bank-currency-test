package com.test.privat.currency.models.dtolayer.converter;

public interface Converter<E, D> {
    E forward(D dto);
    D backward(E entity);
}
