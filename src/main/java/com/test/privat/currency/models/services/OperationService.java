package com.test.privat.currency.models.services;

import com.test.privat.currency.models.wrappers.OperationWrapper;
import com.test.privat.currency.models.entities.Operation;
import com.test.privat.currency.models.entities.Wallet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OperationService {
    List<Operation> getPage(Wallet wallet, int page);
    long getPageCount(Wallet wallet);
    long count(Wallet wallet);
    Operation getByKey(String key);
    Operation create(OperationWrapper operationWrapper);
}
