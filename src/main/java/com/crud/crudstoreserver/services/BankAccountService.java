package com.crud.crudstoreserver.services;

import com.crud.crudstoreserver.exceptions.BankAccountNotFoundException;
import com.crud.crudstoreserver.models.BankAccount;
import com.crud.crudstoreserver.models.CardType;
import com.crud.crudstoreserver.models.Users;

import java.util.List;

public interface BankAccountService {

    List<BankAccount> findAllBankAccounts();

    BankAccount findById(Long id) throws BankAccountNotFoundException;

    List<BankAccount> findByCardType(CardType cardType);

    BankAccount findByCardNumber(String cardNumber) throws BankAccountNotFoundException;

    List<BankAccount> findByBank(String bank);

    BankAccount createBankAccount(BankAccount bankAccount);

    void  updateBankAccount(BankAccount bankAccount) throws BankAccountNotFoundException;

    void deleteBankAccountById(Long id) throws BankAccountNotFoundException;

    void restoreBankAccountById(Long id) throws BankAccountNotFoundException;

    List<BankAccount> createBulkBankAccounts(List<BankAccount> bankAccounts);

    BankAccount getDefaultBankAccountByUser(Users user) throws BankAccountNotFoundException;
}
