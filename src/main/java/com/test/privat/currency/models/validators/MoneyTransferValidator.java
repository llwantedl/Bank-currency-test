package com.test.privat.currency.models.validators;

import com.test.privat.currency.models.dtolayer.wrappers.TransferForm;
import com.test.privat.currency.models.entities.User;
import com.test.privat.currency.models.entities.Wallet;
import com.test.privat.currency.models.services.AuthenticationService;
import com.test.privat.currency.models.services.WalletService;
import com.test.privat.currency.models.services.system.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.util.Objects;

@Component
public class MoneyTransferValidator implements Validator {

    private final WalletService walletService;
    private final AuthenticationService authenticationService;
    private final ConfigurationService configurationService;

    @Autowired
    public MoneyTransferValidator(WalletService walletService,
                                  AuthenticationService authenticationService,
                                  ConfigurationService configurationService) {
        this.walletService = walletService;
        this.authenticationService = authenticationService;
        this.configurationService = configurationService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return TransferForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        TransferForm transferForm = (TransferForm) o;

        String sourceWalletKey = transferForm.getSourceWalletKey();
        String destinationWalletKey = transferForm.getDestinationWalletKey();

        Wallet sourceWallet = walletService.getByKey(sourceWalletKey);
        try {
            User sourceUser = sourceWallet.getUser();

            if (!authenticationService.isRemoteUser(sourceUser)) {
                errors.rejectValue("sourceWalletKey", "transfer-form.source-wallet.not-remote-user-wallet");
            }
        } catch (Exception e) {
            errors.rejectValue("sourceWalletKey", "transfer-form.source-wallet.not-remote-user-wallet");
        }

        Wallet destinationWallet = walletService.getByKey(destinationWalletKey);

        if (Objects.isNull(destinationWallet)) {
            errors.rejectValue("destinationWalletKey", "transfer-form.destination-wallet.wallet-not-exist");
        }

        //IS THE DESTINATION WALLET IS THE SOURCE WALLET
        if(destinationWalletKey.equals(sourceWalletKey)){
            errors.rejectValue("destinationWalletKey", "transfer-form.destination-wallet.same-wallets");
        }

        BigDecimal balance = sourceWallet.getBalance();

        BigDecimal commissionPercentage = configurationService.getCommissionPercentage();
        BigDecimal commissionValue = configurationService.getCommissionValue();

        if (balance.subtract(balance.multiply(commissionPercentage)).subtract(commissionValue).compareTo(BigDecimal.ZERO) < 0) {
            errors.rejectValue("amount", "transfer-form.amount.not-enough-money");
        }
    }
}
