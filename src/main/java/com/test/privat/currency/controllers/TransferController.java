package com.test.privat.currency.controllers;

import com.test.privat.currency.models.dtolayer.wrappers.TransferForm;
import com.test.privat.currency.models.dtolayer.wrappers.TransferResult;
import com.test.privat.currency.models.dtolayer.wrappers.TransferResultWrapper;
import com.test.privat.currency.models.exceptions.InsufficientFundsException;
import com.test.privat.currency.models.exceptions.NoSuchCurrencyRate;
import com.test.privat.currency.models.exceptions.RatesNotReceivedException;
import com.test.privat.currency.models.services.TransferInitializationService;
import com.test.privat.currency.models.services.privatrates.CurrencyRateService;
import com.test.privat.currency.models.validators.MoneyTransferValidator;
import com.test.privat.currency.models.validators.ValidatorErrorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest")
public class TransferController {

    private final CurrencyRateService currencyRateService;

    private final MoneyTransferValidator moneyTransferValidator;

    private final ValidatorErrorBuilder validatorErrorBuilder;

    private final TransferInitializationService transferInitializationService;

    @Autowired
    public TransferController(CurrencyRateService currencyRateService,
                              MoneyTransferValidator moneyTransferValidator,
                              ValidatorErrorBuilder validatorErrorBuilder,
                              TransferInitializationService transferInitializationService) {
        this.currencyRateService = currencyRateService;
        this.moneyTransferValidator = moneyTransferValidator;
        this.validatorErrorBuilder = validatorErrorBuilder;
        this.transferInitializationService = transferInitializationService;
    }

    @PostMapping(value = "/transfer/check_info",
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity checkInfo(@ModelAttribute("transferForm") TransferForm transferForm,
            BindingResult bindingResult) {
        moneyTransferValidator.validate(transferForm, bindingResult);

        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(validatorErrorBuilder.getErrors(bindingResult));
        }

        try {
            return ResponseEntity.ok(currencyRateService.getDetails(transferForm));
        } catch (RatesNotReceivedException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        } catch (NoSuchCurrencyRate noSuchCurrencyRate) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value = "/transfer/perform_transfer",
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity performTransfer(
            @ModelAttribute("transferForm") TransferForm transferForm,
            BindingResult bindingResult) {
        moneyTransferValidator.validate(transferForm, bindingResult);

        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(
                    TransferResultWrapper.build(TransferResult.VALIDATION_ERROR.getCode(),
                            validatorErrorBuilder.getErrorsText(bindingResult)));
        }

        try {
            transferInitializationService.initializeTransfer(transferForm);
            return ResponseEntity.ok().body(
                    TransferResultWrapper.build(TransferResult.SUCCESS.getCode(), "Success"));
        } catch (InsufficientFundsException e) {
            return ResponseEntity.badRequest().body(
                    TransferResultWrapper.build(TransferResult.ERROR.getCode(), "Insufficient Funds"));
        } catch (RatesNotReceivedException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(
                    TransferResultWrapper.build(TransferResult.ERROR.getCode(), "Unable to get transfer rates"));
        } catch (NoSuchCurrencyRate e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    TransferResultWrapper.build(TransferResult.ERROR.getCode(), "Currency rate between wallets could not be found"));
        }
    }

}
