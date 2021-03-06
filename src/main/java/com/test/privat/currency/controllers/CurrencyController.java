package com.test.privat.currency.controllers;

import com.test.privat.currency.models.dtolayer.services.CurrencyDTOService;
import com.test.privat.currency.models.dtolayer.wrappers.CurrencyWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest")
public class CurrencyController {

    private final CurrencyDTOService currencyDTOService;

    @Autowired
    public CurrencyController(CurrencyDTOService currencyDTOService) {
        this.currencyDTOService = currencyDTOService;
    }

    @GetMapping(value = "/data/currencies",
            produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<CurrencyWrapper>> getCurrencies() {
        try {
            return ResponseEntity.ok(currencyDTOService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
