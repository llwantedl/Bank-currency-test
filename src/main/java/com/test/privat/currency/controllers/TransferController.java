package com.test.privat.currency.controllers;

import com.test.privat.currency.models.dtolayer.wrappers.TransferDetailsWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transfer")
public class TransferController {

    @PostMapping("/check_info")
    public ResponseEntity<TransferDetailsWrapper> checkInfo(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
