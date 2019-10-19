package com.test.privat.currency.models.services;

import com.test.privat.currency.models.entities.Operation;
import com.test.privat.currency.models.entities.Wallet;
import com.test.privat.currency.models.repositories.OperationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    private static final int OPERATION_ENTITIES_ON_PAGE = 5;
    private final OperationsRepository operationsRepository;

    @Autowired
    public OperationServiceImpl(OperationsRepository operationsRepository) {
        this.operationsRepository = operationsRepository;
    }

    @Override
    public List<Operation> getPage(Wallet wallet, int page) {
        return operationsRepository.getAllByWallet(wallet,
                PageRequest.of(page, OPERATION_ENTITIES_ON_PAGE,
                        Sort.Direction.DESC, "transferDate"));
    }

    @Override
    public long count(Wallet wallet) {
        return operationsRepository.countByWallet(wallet);
    }

    @Override
    public Operation getByKey(String key) {
        return operationsRepository.getByKey(key);
    }
}
