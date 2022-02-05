package com.crud.crudstoreserver.services.implementations;

import com.crud.crudstoreserver.exceptions.BankAccountNotFoundException;
import com.crud.crudstoreserver.models.BankAccount;
import com.crud.crudstoreserver.models.CardType;
import com.crud.crudstoreserver.models.Person;
import com.crud.crudstoreserver.repositories.BankAccountRepository;
import com.crud.crudstoreserver.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
    public List<BankAccount> findAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    @Override
    public BankAccount findById(Long id) throws BankAccountNotFoundException {
        Optional<BankAccount> bankAccountOptional = bankAccountRepository.findById(id);

        if (bankAccountOptional.isEmpty()) {
            throw new BankAccountNotFoundException(id);
        }
        return bankAccountOptional.get();
    }

    @Override
    public List<BankAccount> findByCardType(CardType cardType) {
        return bankAccountRepository.findAllByCardType(cardType);

    }

    @Override
    public BankAccount findByCardNumber(String cardNumber) throws BankAccountNotFoundException {
        Optional<BankAccount> bankAccountOptional = bankAccountRepository.findByCardNumber(cardNumber);

        if (bankAccountOptional.isEmpty()) {
            throw new BankAccountNotFoundException(cardNumber);
        }
        return bankAccountOptional.get();
    }

    @Override
    public List<BankAccount> findByBank(String bank) {
        return bankAccountRepository.findAllByBank(bank);
    }

    @Override
    public BankAccount createBankAccount(BankAccount bankAccount) {
        bankAccount.setActive(true);
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public void updateBankAccount(BankAccount bankAccount) throws BankAccountNotFoundException {
        if (!bankAccountRepository.existsById(bankAccount.getId())) {
            throw new BankAccountNotFoundException(bankAccount.getId());
        }

        bankAccountRepository.saveAndFlush(bankAccount);
    }

    @Override
    public void deleteBankAccountById(Long id) throws BankAccountNotFoundException {
        Optional<BankAccount> bankAccountOptional = Optional.ofNullable(findById(id));

        if (bankAccountOptional.isEmpty()) {
            throw new BankAccountNotFoundException(id);
        } else {
            BankAccount bankAccount = bankAccountOptional.get();
            bankAccount.setActive(false);
            bankAccountRepository.saveAndFlush(bankAccount);
        }
    }

    @Override
    public void restoreBankAccountById(Long id) throws BankAccountNotFoundException {
        Optional<BankAccount> bankAccountOptional = Optional.ofNullable(findById(id));

        if (bankAccountOptional.isEmpty()) {
            throw new BankAccountNotFoundException(id);
        } else {
            BankAccount bankAccount = bankAccountOptional.get();
            bankAccount.setActive(true);
            bankAccountRepository.saveAndFlush(bankAccount);
        }

    }

    @Override
    public List<BankAccount> createBulkBankAccounts(List<BankAccount> bankAccounts) {
        List<BankAccount> createdBankAccount = new ArrayList<>();
        bankAccounts.forEach(bankAccount -> createdBankAccount.add(createBankAccount(bankAccount)));

        return createdBankAccount;
    }

    @Override
    public BankAccount getDefaultBankAccountByPerson(Person person) throws BankAccountNotFoundException {
        Optional<BankAccount> optionalBankAccount = person.getBankAccounts().stream()
                .filter(bankAccount -> bankAccount.isActive() && bankAccount.isDefault())
                .findFirst();

        if (optionalBankAccount.isEmpty()) {
            throw new BankAccountNotFoundException(person);
        }

        return optionalBankAccount.get();
    }
}
