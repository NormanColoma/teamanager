package com.normancoloma.management.domain.model.team;

import com.normancoloma.management.domain.exception.TeamCannotAffordMoreExpenses;
import com.normancoloma.management.domain.model.team.player.Player;
import com.normancoloma.management.domain.model.team.player.Salary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MassSalaryReviewer {
    private final TeamRepository teamRepository;

    public void changePlayerSalary(Team team, Player player, Salary newPlayerSalary) {
        if (newPlayerSalary.getQuantity() > team.getFunds().getQuantity() * 0.2) {
            throw new TeamCannotAffordMoreExpenses(String.format("Team %s cannot afford more expenses", team.getId().toString()));
        }
        player.changeSalary(newPlayerSalary.getQuantity());
        teamRepository.save(team);
    }
}
