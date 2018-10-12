package com.normancoloma.management.application.usecase.team;

import com.normancoloma.management.domain.exception.TeamAlreadyExists;
import com.normancoloma.management.domain.model.team.Currency;
import com.normancoloma.management.domain.model.team.Fund;
import com.normancoloma.management.domain.model.team.Team;
import com.normancoloma.management.domain.model.team.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@AllArgsConstructor
@Component
public class CreateTeam {
    private final TeamRepository teamRepository;

    public Team execute(String name, String country, double funds, String currency) {
        if (teamRepository.existsWithNameAndCountry(name, country)) {
            throw new TeamAlreadyExists(String.format("Team with name %s already exists for given country %s", name, country));
        }

        Fund fundsOfTeam = createFunds(funds, currency);
        Team newTeam = createNewTeamWith(name, country, fundsOfTeam);
        teamRepository.save(newTeam);

        return newTeam;
    }

    private Team createNewTeamWith(String name, String country, Fund funds) {
        return Team.builder()
                .id(UUID.randomUUID())
                .name(name)
                .country(country)
                .fund(funds)
                .build();
    }

    private Fund createFunds(double funds, String currency) {
        return Fund.builder()
                .quantity(funds)
                .currency(Currency.valueOf(currency))
                .build();
    }
}
