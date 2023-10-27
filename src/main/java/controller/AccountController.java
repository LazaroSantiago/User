package controller;

import entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/verify/{id}")
    public ResponseEntity<?> verifyAccount(@PathVariable Long id) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(accountService.verifyAccount(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"No se encuentra la cuenta.\"}");
        }
    }

    @PutMapping("/pay/{fare}/account/{id}")
    public ResponseEntity<?> payTrip(@PathVariable Long id,@PathVariable Long fare){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(accountService.payTrip(id, fare));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"No se encuentra la cuenta.\"}");
        }
    }
}
