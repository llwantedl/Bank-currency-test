package com.test.privat.currency.models.dtolayer.services;

import com.test.privat.currency.models.dtolayer.converter.DTOConverter;
import com.test.privat.currency.models.dtolayer.wrappers.WalletDetailsWrapper;
import com.test.privat.currency.models.entities.User;
import com.test.privat.currency.models.entities.Wallet;
import com.test.privat.currency.models.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WalletDTOServiceImpl implements WalletDTOService {

    private final DTOConverter<Wallet, WalletDetailsWrapper> walletConverter;
    private final WalletService walletService;

    @Autowired
    public WalletDTOServiceImpl(DTOConverter<Wallet, WalletDetailsWrapper> walletConverter,
                                WalletService walletService) {
        this.walletConverter = walletConverter;
        this.walletService = walletService;
    }

    @Override
    public List<WalletDetailsWrapper> getWalletDetails(User user) {
        return convertListToDTO(walletService.getByUser(user));
    }

    @Override
    public WalletDetailsWrapper getWalletDetails(Wallet wallet) {
        return walletConverter.backward(wallet);
    }

    @Override
    public List<WalletDetailsWrapper> convertListToDTO(List<Wallet> entities) {
        return entities
                .stream()
                .map(walletConverter::backward)
                .collect(Collectors.toList());
    }
}
