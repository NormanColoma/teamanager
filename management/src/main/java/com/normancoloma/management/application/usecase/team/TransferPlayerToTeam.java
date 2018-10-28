package com.normancoloma.management.application.usecase.team;

import com.normancoloma.management.domain.exception.TeamDoesNotExistException;
import com.normancoloma.management.domain.model.team.Team;
import com.normancoloma.management.domain.model.team.TeamRepository;
import com.normancoloma.management.domain.model.team.player.Player;
import com.normancoloma.management.domain.service.DomainEventEmitter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static java.util.Arrays.asList;

@AllArgsConstructor
@Component
public class TransferPlayerToTeam {
    private final TeamRepository teamRepository;
    private final DomainEventEmitter domainEventEmitter;

    public void execute(UUID playerId, UUID teamIdOfPlayer, UUID teamIdAcquiringPlayer) {
        Team currentTeamOfPlayer = teamRepository.fetch(teamIdOfPlayer)
                .orElseThrow(() -> new TeamDoesNotExistException(String.format("There is no team with id %s", teamIdOfPlayer)));
        Team newTeamOfPlayer = teamRepository.fetch(teamIdAcquiringPlayer)
                .orElseThrow(() -> new TeamDoesNotExistException(String.format("There is no team with id %s", teamIdAcquiringPlayer)));

        Player playerToBeTransferred = currentTeamOfPlayer.getPlayer(playerId);
        currentTeamOfPlayer.terminatePlayer(playerToBeTransferred);

        newTeamOfPlayer.releasePlayer(playerToBeTransferred);

        teamRepository.saveAll(asList(currentTeamOfPlayer, newTeamOfPlayer));
    }
}
