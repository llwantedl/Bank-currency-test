package com.test.privat.currency.models.dtolayer.services;

import com.test.privat.currency.models.dtolayer.wrappers.OperationWrapper;
import com.test.privat.currency.models.dtolayer.wrappers.PaginatedWrapper;
import com.test.privat.currency.models.entities.Operation;
import com.test.privat.currency.models.entities.Wallet;
import org.springframework.stereotype.Service;

@Service
public interface OperationDTOService extends DTOService<Operation, OperationWrapper> {
    PaginatedWrapper<OperationWrapper> getPage(Wallet wallet, int page);
    PaginatedWrapper<OperationWrapper> getPage(Wallet wallet, String page);
}
