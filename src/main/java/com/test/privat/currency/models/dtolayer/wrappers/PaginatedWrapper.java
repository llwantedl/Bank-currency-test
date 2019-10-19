package com.test.privat.currency.models.dtolayer.wrappers;

import java.util.List;

public class PaginatedWrapper<T> {

    private long entitiesCount;
    private List<T> entityPage;

    public PaginatedWrapper() {
    }

    public long getEntitiesCount() {
        return entitiesCount;
    }

    public void setEntitiesCount(long entitiesCount) {
        this.entitiesCount = entitiesCount;
    }

    public List<T> getEntityPage() {
        return entityPage;
    }

    public void setEntityPage(List<T> entityPage) {
        this.entityPage = entityPage;
    }
}
