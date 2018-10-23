package com.normancoloma.management.port.adapter.rest;

import com.normancoloma.management.application.transformer.TeamTransformer;
import com.normancoloma.management.domain.model.team.Team;
import com.normancoloma.management.port.adapter.rest.response.PlayerResponse;
import com.normancoloma.management.port.adapter.rest.response.TeamResponse;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TeamJSONTransformer implements TeamTransformer {
    @Override
    public TeamResponse transform(Team team) {
        Set<PlayerResponse> players = team.getPlayers().stream()
                .map(player -> PlayerResponse.builder()
                        .id(player.getId())
                        .name(player.getName())
                        .years(player.getYears())
                        .shirtNumber(player.getShirtNumber())
                        .build()
                )
                .collect(Collectors.toSet());

        return TeamResponse.builder()
                .id(team.getId())
                .name(team.getName())
                .country(team.getCountry())
                .players(players)
                .funds(team.getFunds().toString())
                .build();
    }

    @Override
    public Collection<Object> transform(Collection<Team> teams) {
        return teams.stream()
                .map(this::transform)
                .collect(Collectors.toList());
    }
}
