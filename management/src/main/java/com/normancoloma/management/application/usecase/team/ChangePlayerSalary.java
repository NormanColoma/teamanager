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
public class ChangePlayerSalary {
    private final TeamRepository teamRepository;

    public void execute(UUID teamId, UUID playerId, float salary) {
        Team team = teamRepository.fetch(teamId)
                .orElseThrow(() -> new TeamDoesNotExistException(String.format("There is no team with id %s", teamId)));
        Player player = team.getPlayer(playerId);


        player.changeSalary(salary);

        teamRepository.save(team);

    }
}
