package com.test.privat.currency.models.dtolayer.services;

import com.test.privat.currency.models.dtolayer.converter.DTOConverter;
import com.test.privat.currency.models.dtolayer.wrappers.OperationWrapper;
import com.test.privat.currency.models.dtolayer.wrappers.PaginatedWrapper;
import com.test.privat.currency.models.entities.Operation;
import com.test.privat.currency.models.entities.Wallet;
import com.test.privat.currency.models.services.OperationService;
import com.test.privat.currency.models.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationDTOServiceImpl implements OperationDTOService {

    private final DTOConverter<Operation, OperationWrapper> dtoConverter;
    private final OperationService operationService;

    @Autowired
    public OperationDTOServiceImpl(DTOConverter<Operation, OperationWrapper> dtoConverter,
                                   OperationService operationService) {
        this.dtoConverter = dtoConverter;
        this.operationService = operationService;
    }

    @Override
    public PaginatedWrapper<OperationWrapper> getPage(Wallet wallet, int page) {
        PaginatedWrapper<OperationWrapper> operationWrapper = new PaginatedWrapper<>();

        List<Operation> operations = operationService.getPage(wallet, page);

        List<OperationWrapper> operationWrappers = convertListToDTO(operations);

        operationWrapper.setEntityPage(operationWrappers);
        operationWrapper.setEntitiesCount(operationService.getPageCount(wallet));

        return operationWrapper;
    }

    @Override
    public PaginatedWrapper<OperationWrapper> getPage(Wallet wallet, String page) {
        return getPage(wallet, PaginationUtils.formatPage(page));
    }

    @Override
    public List<OperationWrapper> convertListToDTO(List<Operation> entities) {
        return entities
                .stream()
                .map(dtoConverter::backward)
                .collect(Collectors.toList());
    }
}
