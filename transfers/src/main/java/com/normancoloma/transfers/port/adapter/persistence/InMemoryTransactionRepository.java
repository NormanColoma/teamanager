package com.normancoloma.transfers.port.adapter.persistence;

import com.normancoloma.transfers.domain.model.transaction.Transaction;
import com.normancoloma.transfers.domain.model.transaction.TransactionRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryTransactionRepository implements TransactionRepository {

    private List<Transaction> transactions = new ArrayList<>();

    @Override
    public void save(Transaction transaction) {
        if (transactions.contains(transaction)) {
            int indexOfTransaction = transactions.indexOf(transaction);
            transactions.set(indexOfTransaction, transaction);
        } else {
            transactions.add(transaction);
        }
    }
}
