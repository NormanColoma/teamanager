package com.normancoloma.management.application.usecase.team;

import com.normancoloma.management.domain.exception.TeamDoesNotExistException;
import com.normancoloma.management.domain.model.team.Currency;
import com.normancoloma.management.domain.model.team.MassSalaryReviewer;
import com.normancoloma.management.domain.model.team.Team;
import com.normancoloma.management.domain.model.team.TeamRepository;
import com.normancoloma.management.domain.model.team.player.Player;
import com.normancoloma.management.domain.model.team.player.Salary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@AllArgsConstructor
@Component
public class ChangePlayerSalary {
    private final TeamRepository teamRepository;
    private final MassSalaryReviewer massSalaryReviewer;

    public void execute(UUID teamId, UUID playerId, float salary) {
        Team team = teamRepository.fetch(teamId)
                .orElseThrow(() -> new TeamDoesNotExistException(String.format("There is no team with id %s", teamId)));
        Player player = team.getPlayer(playerId);
        Salary newPlayerSalary = Salary.builder()
                .currency(Currency.EUROS)
                .quantity(salary)
                .build();

        massSalaryReviewer.changePlayerSalary(team, player, newPlayerSalary);
    }
}
