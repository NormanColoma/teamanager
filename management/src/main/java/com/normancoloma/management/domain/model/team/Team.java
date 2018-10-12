package com.normancoloma.management.domain.model.team;

import com.normancoloma.management.domain.exception.PlayerAlreadyBelongsToTeamException;
import com.normancoloma.management.domain.exception.PlayerDoesNotBelongToGivenTeam;
import com.normancoloma.management.domain.exception.PlayerDoesNotExist;
import com.normancoloma.management.domain.exception.ShirtNumberIsAlreadyTakenException;
import com.normancoloma.management.domain.model.team.player.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class Team {
    private UUID id;
    private String name;
    private String country;
    @Builder.Default
    private Set<Player> players = new HashSet<>();
    private Fund fund;

    public void releasePlayer(Player player) {
        if (hasPlayerAlready(player)) {
            throw new PlayerAlreadyBelongsToTeamException(String.format("Player %s already belongs to team %s", player.getId(), id));
        }
        players.add(player);
    }

    public boolean isSameTeam(String name, String country) {
        return Objects.equals(this.name, name) && Objects.equals(this.country, country);
    }

    public void changePlayerShirtNumber(UUID playerId, byte shirtNumber) {
        boolean isShirtNumberAlreadyTaken = players.stream()
                .anyMatch(player -> player.hasShirtNumber(shirtNumber));

        if (isShirtNumberAlreadyTaken) {
            throw new ShirtNumberIsAlreadyTakenException(String.format("Shirt with number %s is already taken in team %s", shirtNumber, id));
        }
        Player playerFound = getPlayer(playerId);

        playerFound.setShirtNumber(shirtNumber);
    }


    public Player getPlayer(UUID playerId) {
        return players.stream()
                .filter(player -> player.isSame(playerId))
                .findFirst()
                .orElseThrow(() -> new PlayerDoesNotExist(String.format("There is no player with id %s", playerId)));
    }

    public void terminatePlayer(Player player) {
        if (!players.contains(player)) {
            throw new PlayerDoesNotBelongToGivenTeam(String.format("Player %s does not belong to team %s", player.getId(), id));
        }
        players.remove(player);
    }

    private boolean hasPlayerAlready(Player player) {
        return players.stream()
                .anyMatch(playerFound -> playerFound.isSame(player.getName(), player.getYears()));
    }
}
