package com.crud.crudstoreserver.controllers;

import com.crud.crudstoreserver.exceptions.BankAccountNotFoundException;
import com.crud.crudstoreserver.models.BankAccount;
import com.crud.crudstoreserver.models.CardType;
import com.crud.crudstoreserver.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bankAccount")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping
    public List<BankAccount> getAllBankAccount() {
        return bankAccountService.findAllBankAccounts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBankAccountById(@PathVariable Long id) throws BankAccountNotFoundException {
        bankAccountService.findById(id);

        try {
            bankAccountService.findById(id);
        } catch (BankAccountNotFoundException bankAccountNotFoundException) {
            return new ResponseEntity<>(bankAccountNotFoundException.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.FOUND);
    }

    @GetMapping("/{cardType}")
    public List<BankAccount> getBankAccountByCardType(@PathVariable CardType cardType) {
        return bankAccountService.findByCardType(cardType);
    }

    @GetMapping("/{cardNumber}")
    public ResponseEntity<?> getBankAccountByCardNumber(@PathVariable String cardNumber) throws BankAccountNotFoundException {
        bankAccountService.findByCardNumber(cardNumber);

        try {
            bankAccountService.findByCardNumber(cardNumber);
        } catch (BankAccountNotFoundException bankAccountNotFoundException) {
            return new ResponseEntity<>(bankAccountNotFoundException.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.FOUND);
    }

    @GetMapping("/{bank}")
    public List<BankAccount> getBankAccountByBank(@PathVariable String bank) {
        return bankAccountService.findByBank(bank);
    }

    @PostMapping
    public ResponseEntity<?> addBankAccount(@RequestBody @Valid BankAccount bankAccount) {
        bankAccountService.createBankAccount(bankAccount);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<BankAccount> updateBankAccount(@RequestBody @Valid BankAccount bankAccount) throws BankAccountNotFoundException {
        bankAccountService.updateBankAccount(bankAccount);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(bankAccount, headers, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteBankAccount(@PathVariable("id") Long id) throws BankAccountNotFoundException {
        bankAccountService.deleteBankAccountById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/restore/{id}")
    public ResponseEntity<?> restoreBankAccount(@PathVariable("id") Long id) throws BankAccountNotFoundException {
        bankAccountService.restoreBankAccountById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public List<BankAccount> createBulkBankAccounts(@RequestBody List<BankAccount> bankAccounts) {
        bankAccountService.createBulkBankAccounts(bankAccounts);
        return bankAccountService.createBulkBankAccounts(bankAccounts);
    }
}
