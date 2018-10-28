package com.normancoloma.management.application.usecase.team;

import com.normancoloma.management.domain.exception.TeamDoesNotExistException;
import com.normancoloma.management.domain.model.team.Currency;
import com.normancoloma.management.domain.model.team.Team;
import com.normancoloma.management.domain.model.team.TeamRepository;
import com.normancoloma.management.domain.model.team.player.Player;
import com.normancoloma.management.domain.model.team.player.Salary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@AllArgsConstructor
@Component
public class AddPlayerToTeam {
    private final TeamRepository teamRepository;

    public Team execute(String playerName, byte playerYears, byte playerShirtNumber, UUID teamId, float playerSalary) {
        Team teamFound = teamRepository.fetch(teamId)
                .orElseThrow(() -> new TeamDoesNotExistException(String.format("There is no team with id %s", teamId)));

        Player player = createPlayerWith(playerName, playerYears, playerShirtNumber, playerSalary, teamFound.getCurrency());
        teamFound.releasePlayer(player);

        teamRepository.save(teamFound);

        return teamFound;
    }

    private Player createPlayerWith(String name, byte years, byte shirtNumber, float salary, Currency teamCurrency) {
        Salary playerSalary = Salary.builder()
                .quantity(salary)
                .currency(teamCurrency)
                .build();

        return Player.builder()
                .id(UUID.randomUUID())
                .name(name)
                .shirtNumber(shirtNumber)
                .years(years)
                .salary(playerSalary)
                .build();
    }
}
