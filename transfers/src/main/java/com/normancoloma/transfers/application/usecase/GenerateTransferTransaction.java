package com.normancoloma.transfers.application.usecase;

import com.normancoloma.transfers.domain.model.transaction.Concept;
import com.normancoloma.transfers.domain.model.transaction.Transaction;
import com.normancoloma.transfers.domain.model.transaction.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@AllArgsConstructor
@Component
public class GenerateTransferTransaction {
    private final TransactionRepository transactionRepository;

    public void execute(GenerateTransferTransactionCommand generateTransferTransactionCommand) {
        Transaction transaction = createTransactionWith(
                generateTransferTransactionCommand.getPlayerId(),
                generateTransferTransactionCommand.getTeamId(),
                generateTransferTransactionCommand.getQuantity()
        );
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
