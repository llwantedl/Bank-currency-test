package com.test.privat.currency.models.dtolayer.services;

import java.util.List;

public interface DTOService<E, D> {
    List<D> convertToDTO(List<E> entities);
}
