package com.normancoloma.management.domain.model.team;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface TeamRepository {
    void save(Team team);

    void saveAll(Collection<Team> teams);

    Optional<Team> fetch(UUID teamId);

    boolean existsWithNameAndCountry(String name, String country);

    Collection<Team> findAll();
}
