package com.test.privat.currency.models.dtolayer.converter;

import com.test.privat.currency.models.dtolayer.wrappers.OperationWrapper;
import com.test.privat.currency.models.entities.Currency;
import com.test.privat.currency.models.entities.Operation;
import com.test.privat.currency.models.entities.User;
import com.test.privat.currency.models.entities.Wallet;
import com.test.privat.currency.models.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OperationDTOConverter implements DTOConverter<Operation, OperationWrapper> {

    private final OperationService operationService;

    @Autowired
    public OperationDTOConverter(OperationService operationService) {
        this.operationService = operationService;
    }

    @Override
    public Operation forward(OperationWrapper dto) {
        return operationService.getByKey(dto.getKey());
    }

    @Override
    public OperationWrapper backward(Operation entity) {
        OperationWrapper operationWrapper = new OperationWrapper();

        Wallet sourceWallet = entity.getSourceWallet();
        Wallet destinationWallet = entity.getDestinationWallet();

        User sourceUser = sourceWallet.getUser();
        User destinationUser = destinationWallet.getUser();

        Currency sourceCurrency = sourceUser.getValueCurrency();
        Currency destinationCurrency = destinationUser.getValueCurrency();

        operationWrapper.setAmount(entity.getAmount().toPlainString());
        operationWrapper.setDestinationUser(destinationUser.getLogin());
        operationWrapper.setDestinationCurrencyKey(destinationCurrency.getKey());
        operationWrapper.setDestinationWalletKey(destinationWallet.getKey());
        operationWrapper.setSourceUser(sourceUser.getLogin());
        operationWrapper.setSourceCurrencyKey(sourceCurrency.getKey());
        operationWrapper.setSourceWalletKey(sourceWallet.getKey());
        operationWrapper.setRate(String.valueOf(entity.getRate()));
        operationWrapper.setKey(entity.getKey());
        operationWrapper.setTransferDate(entity.getTransferDate().toString());

        return operationWrapper;
    }
}
