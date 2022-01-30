package com.crud.crudstoreserver.repositories;

import com.crud.crudstoreserver.models.BankAccount;
import com.crud.crudstoreserver.models.CardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    Optional<BankAccount> findByCardNumber(String cardNumber);

    List<BankAccount> findAllByBank(String bank);

    List<BankAccount> findAllByCardType(CardType cardType);
}

