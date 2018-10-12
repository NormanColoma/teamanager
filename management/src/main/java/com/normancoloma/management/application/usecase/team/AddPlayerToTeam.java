package com.normancoloma.management.application.usecase.team;

import com.normancoloma.management.domain.exception.TeamDoesNotExistException;
import com.normancoloma.management.domain.model.team.Team;
import com.normancoloma.management.domain.model.team.TeamRepository;
import com.normancoloma.management.domain.model.team.player.Player;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@AllArgsConstructor
@Component
public class AddPlayerToTeam {
    private final TeamRepository teamRepository;

    public Team execute(String playerName, byte playerYears, byte playerShirtNumber, UUID teamId) {
        Team teamFound = teamRepository.fetch(teamId)
                .orElseThrow(() -> new TeamDoesNotExistException(String.format("There is no team with id %s", teamId)));
        Player player = new Player(UUID.randomUUID(), playerName, playerYears, playerShirtNumber);

        teamFound.releasePlayer(player);

        teamRepository.save(teamFound);

        return teamFound;
    }
}
