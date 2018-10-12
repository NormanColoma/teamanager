package com.normancoloma.management.application.usecase.team;

import com.normancoloma.management.domain.model.team.Team;
import com.normancoloma.management.domain.model.team.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class GetAllTeams {
    private final TeamRepository teamRepository;

    public List<Team> execute() {
        return new ArrayList<>(teamRepository.findAll());
    }
}
