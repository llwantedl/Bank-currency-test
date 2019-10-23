package com.test.privat.currency.models.services;

import com.test.privat.currency.models.entities.Operation;
import com.test.privat.currency.models.entities.Wallet;
import com.test.privat.currency.models.repositories.OperationsRepository;
import com.test.privat.currency.models.services.system.ConfigurationService;
import com.test.privat.currency.models.wrappers.OperationWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OperationServiceImpl implements OperationService {

    private static final int OPERATION_ENTITIES_ON_PAGE = 5;
    private final OperationsRepository operationsRepository;
    private final ConfigurationService configurationService;

    @Autowired
    public OperationServiceImpl(OperationsRepository operationsRepository,
                                ConfigurationService configurationService) {
        this.operationsRepository = operationsRepository;
        this.configurationService = configurationService;
    }

    @Override
    public List<Operation> getPage(Wallet wallet, int page) {
        return operationsRepository.getAllByWallet(wallet,
                PageRequest.of(page-1, OPERATION_ENTITIES_ON_PAGE,
                        Sort.Direction.DESC, "transferDate"));
    }

    @Override
    public long getPageCount(Wallet wallet) {
         return 1 + ((count(wallet) - 1)/OPERATION_ENTITIES_ON_PAGE);
    }

    @Override
    public long count(Wallet wallet) {
        return operationsRepository.countByWallet(wallet);
    }

    @Override
    public Operation getByKey(String key) {
        return operationsRepository.getByKey(key);
    }

    @Override
    public Operation create(OperationWrapper operationWrapper) {
        Operation operation = new Operation();

        operation.setAmount(operationWrapper.getAmount());
        operation.setCommission(operationWrapper.getCommission());
        operation.setDestinationWallet(operationWrapper.getDestinationWallet());
        operation.setSourceWallet(operationWrapper.getSourceWallet());
        operation.setKey(UUID.randomUUID().toString());
        operation.setRate(operationWrapper.getRate());
        operation.setTransferDate(new Date());

        return operationsRepository.save(operation);
    }
}
