package com.normancoloma.management.application.usecase.team;

import com.normancoloma.management.domain.exception.TeamDoesNotExistException;
import com.normancoloma.management.domain.model.team.Team;
import com.normancoloma.management.domain.model.team.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@AllArgsConstructor
@Component
public class ChangeShirtNumberOfAPlayer {
    private final TeamRepository teamRepository;

    public void execute(UUID teamId, UUID playerId, byte shirtNumber) {
        Team teamFound = teamRepository.fetch(teamId)
                .orElseThrow(() -> new TeamDoesNotExistException(String.format("There is no team with id %s", teamId)));

        teamFound.changePlayerShirtNumber(playerId, shirtNumber);

        teamRepository.save(teamFound);
    }
}
