package com.normancoloma.management.application.transformer;

import com.normancoloma.management.domain.model.team.Team;

import java.util.Collection;

public interface TeamTransformer {
    Object transform(Team team);
    Collection<Object> transform(Collection<Team> team);
}
