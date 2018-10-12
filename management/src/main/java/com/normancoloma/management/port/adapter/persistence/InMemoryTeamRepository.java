package com.normancoloma.management.port.adapter.persistence;

import com.normancoloma.management.domain.model.team.Currency;
import com.normancoloma.management.domain.model.team.Fund;
import com.normancoloma.management.domain.model.team.Team;
import com.normancoloma.management.domain.model.team.TeamRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.singletonList;

@Repository
public class InMemoryTeamRepository implements TeamRepository {
    private List<Team> teams = new ArrayList<>(
            singletonList(
                    new Team(UUID.fromString("efa39763-9b1f-4547-aa17-591c811583a1"),
                            "F.C Barcelona", "Spain", new HashSet<>(),
                            Fund.builder().currency(Currency.EUROS).build())
            )
    );

    @Override
    public void save(Team team) {
        if (teams.contains(team)) {
            teams.set(teams.indexOf(team), team);
        } else {
            teams.add(team);
        }
    }

    @Override
    public void saveAll(Collection<Team> teams) {
        teams.forEach(this::save);
    }

    @Override
    public Optional<Team> fetch(UUID teamId) {
        return teams.stream()
                .filter(team -> Objects.equals(team.getId(), teamId))
                .findFirst();
    }

    @Override
    public boolean existsWithNameAndCountry(String name, String country) {
        return teams.stream()
                .anyMatch(team -> team.isSameTeam(name, country));
    }

    @Override
    public Collection<Team> findAll() {
        return teams;
    }
}
