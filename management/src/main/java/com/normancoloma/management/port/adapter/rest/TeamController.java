package com.normancoloma.management.port.adapter.rest;

import com.normancoloma.management.application.usecase.team.AddPlayerToTeam;
import com.normancoloma.management.application.usecase.team.ChangeShirtNumberOfAPlayer;
import com.normancoloma.management.application.usecase.team.CreateTeam;
import com.normancoloma.management.application.usecase.team.GetAllTeams;
import com.normancoloma.management.application.usecase.team.TransferPlayerToTeam;
import com.normancoloma.management.domain.model.team.Team;
import com.normancoloma.management.port.adapter.rest.request.ChangeShirtNumberRequest;
import com.normancoloma.management.port.adapter.rest.request.PlayerRequest;
import com.normancoloma.management.port.adapter.rest.request.TeamRequest;
import com.normancoloma.management.port.adapter.rest.request.TransferPlayerRequest;
import com.normancoloma.management.port.adapter.rest.response.PlayerResponse;
import com.normancoloma.management.port.adapter.rest.response.TeamResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class TeamController {
    private final AddPlayerToTeam addPlayerToTeam;
    private final CreateTeam createTeam;
    private final GetAllTeams getAllTeams;
    private final ChangeShirtNumberOfAPlayer changeShirtNumberOfAPlayer;
    private final TransferPlayerToTeam transferPlayerToTeam;

    @PostMapping(value = "teams/{teamId}/players")
    public TeamResponse addPlayerToTeam(@PathVariable("teamId") UUID teamId, @RequestBody PlayerRequest playerRequest) {
        Team teamWithPlayerAdded = addPlayerToTeam.execute(playerRequest.getName(), playerRequest.getYears(), playerRequest.getShirtNumber(), teamId);
        return toTeamResponse(teamWithPlayerAdded);
    }

    @PostMapping(value = "teams")
    public TeamResponse createTeam(@RequestBody TeamRequest teamRequest) {
        Team teamCreated = createTeam.execute(teamRequest.getName(), teamRequest.getCountry(), teamRequest.getFunds(), teamRequest.getCurrency());
        return toTeamResponse(teamCreated);
    }

    @GetMapping(value = "teams")
    public List<TeamResponse> getAllTeams() {
        List<Team> teamsFound = getAllTeams.execute();
        return teamsFound.stream()
                .map(this::toTeamResponse)
                .collect(Collectors.toList());
    }

    @PutMapping(value = "teams/{teamId}/players/{playerId}/shirtNumbers")
    public void changeShirtNumberForPlayer(@PathVariable("teamId") UUID teamId,
                                           @PathVariable("playerId") UUID playerId,
                                           @RequestBody ChangeShirtNumberRequest changeShirtNumberRequest) {

        changeShirtNumberOfAPlayer.execute(teamId, playerId, changeShirtNumberRequest.getNumber());
    }

    @PostMapping(value = "teams/{teamId}/transfers")
    public void transferPlayer(@PathVariable("teamId") UUID teamId,
                               @RequestBody TransferPlayerRequest transferPlayerRequest) {

        transferPlayerToTeam.execute(transferPlayerRequest.getPlayerToBeTransferred(), teamId, transferPlayerRequest.getTeamAcquiringPlayer());
    }

    private TeamResponse toTeamResponse(Team team) {
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
                .funds(team.getFund().toString())
                .build();
    }
}
