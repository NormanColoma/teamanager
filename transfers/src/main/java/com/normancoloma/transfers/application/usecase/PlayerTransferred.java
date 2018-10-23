package com.normancoloma.transfers.application.usecase;

import com.normancoloma.transfers.domain.model.transaction.Concept;
import com.normancoloma.transfers.domain.model.transaction.Transaction;
import com.normancoloma.transfers.domain.model.transaction.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@AllArgsConstructor
@Component
public class PlayerTransferred {
    private final TransactionRepository transactionRepository;

    public void execute(UUID playerId, UUID teamId, float quantity) {
        Transaction transaction = createTransactionWith(playerId, teamId, quantity);
        transactionRepository.save(transaction);
    }

    private Transaction createTransactionWith(UUID playerId, UUID teamId, float quantity) {
        return Transaction.builder()
                .id(UUID.randomUUID())
                .playerId(playerId)
                .teamId(teamId)
                .quantity(quantity)
                .concept(Concept.Sale)
                .build();
    }
}
