package com.test.privat.currency.controllers;

import com.test.privat.currency.models.dtolayer.services.OperationDTOService;
import com.test.privat.currency.models.dtolayer.services.UserDetailsDTOService;
import com.test.privat.currency.models.dtolayer.services.WalletDTOService;
import com.test.privat.currency.models.dtolayer.wrappers.OperationWrapper;
import com.test.privat.currency.models.dtolayer.wrappers.PaginatedWrapper;
import com.test.privat.currency.models.dtolayer.wrappers.WalletDetailsWrapper;
import com.test.privat.currency.models.entities.User;
import com.test.privat.currency.models.entities.Wallet;
import com.test.privat.currency.models.exceptions.UserNotFoundException;
import com.test.privat.currency.models.services.AuthenticationService;
import com.test.privat.currency.models.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rest")
public class UserDetailsController {

    private final WalletDTOService walletDTOService;
    private final WalletService walletService;
    private final AuthenticationService authenticationService;
    private final OperationDTOService operationDTOService;

    @Autowired
    public UserDetailsController(WalletDTOService walletDTOService,
                                 WalletService walletService,
                                 AuthenticationService authenticationService,
                                 OperationDTOService operationDTOService) {
        this.walletDTOService = walletDTOService;
        this.walletService = walletService;
        this.authenticationService = authenticationService;
        this.operationDTOService = operationDTOService;
    }

    @GetMapping(value = "/wallets/get_remote",
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<WalletDetailsWrapper>> getCurrencies() throws UserNotFoundException {
        User remoteUser = authenticationService.getRemoteUser();

        try {
            return new ResponseEntity<>(walletDTOService.getWalletDetails(remoteUser), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/wallets/get_operation_history",
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaginatedWrapper<OperationWrapper>> getOperationHistory(
            @RequestParam(name = "walletKey") String walletKey,
            @RequestParam(name = "page") String page) throws UserNotFoundException {
        Wallet requestWallet = walletService.getByKey(walletKey);

        if (!authenticationService.isRemoteUser(requestWallet.getUser())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        try {
            return new ResponseEntity<>(operationDTOService.getPage(requestWallet, page), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
