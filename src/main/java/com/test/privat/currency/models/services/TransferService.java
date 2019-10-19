package com.test.privat.currency.models.services;

import com.test.privat.currency.models.dtolayer.wrappers.TransferForm;
import org.springframework.stereotype.Service;

@Service
public interface TransferService {
    void transferMoney(TransferForm transferWrapper);
}
