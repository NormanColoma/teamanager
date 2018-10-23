package com.normancoloma.management.port.adapter.rest;

import com.normancoloma.management.application.usecase.team.AddPlayerToTeam;
import com.normancoloma.management.application.usecase.team.ChangePlayerSalary;
import com.normancoloma.management.application.usecase.team.ChangeShirtNumberOfAPlayer;
import com.normancoloma.management.application.usecase.team.CreateTeam;
import com.normancoloma.management.application.usecase.team.GetAllTeams;
import com.normancoloma.management.application.usecase.team.TransferPlayerToTeam;
import com.normancoloma.management.domain.model.team.Team;
import com.normancoloma.management.port.adapter.rest.request.ChangePlayerSalaryRequest;
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

import java.util.ArrayList;
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
    private final ChangePlayerSalary changePlayerSalary;
    private final TeamJSONTransformer teamJSONTransformer;

    @GetMapping(value = "teams")
    public List<TeamResponse> getAllTeams() {
       return new ArrayList(teamJSONTransformer.transform(getAllTeams.execute()));
    }

    @PostMapping(value = "teams")
    public TeamResponse createTeam(@RequestBody TeamRequest teamRequest) {
        Team teamCreated = createTeam.execute(teamRequest.getName(), teamRequest.getCountry(), teamRequest.getFunds(), teamRequest.getCurrency());
        return teamJSONTransformer.transform(teamCreated);
    }

    @PostMapping(value = "teams/{teamId}/players")
    public TeamResponse addPlayerToTeam(@PathVariable("teamId") UUID teamId, @RequestBody PlayerRequest playerRequest) {
        Team teamWithPlayerAdded = addPlayerToTeam.execute(playerRequest.getName(), playerRequest.getYears(), playerRequest.getShirtNumber(), teamId, playerRequest.getSalary());
        return teamJSONTransformer.transform(teamWithPlayerAdded);
    }

    @PostMapping(value = "teams/{teamId}/transfers")
    public void transferPlayer(@PathVariable("teamId") UUID teamId,
                               @RequestBody TransferPlayerRequest transferPlayerRequest) {

        transferPlayerToTeam.execute(transferPlayerRequest.getPlayerToBeTransferred(), teamId, transferPlayerRequest.getTeamAcquiringPlayer());
    }

    @PutMapping(value = "teams/{teamId}/players/{playerId}/shirtNumbers")
    public void changeShirtNumberForPlayer(@PathVariable("teamId") UUID teamId,
                                           @PathVariable("playerId") UUID playerId,
                                           @RequestBody ChangeShirtNumberRequest changeShirtNumberRequest) {

        changeShirtNumberOfAPlayer.execute(teamId, playerId, changeShirtNumberRequest.getNumber());
    }

    @PutMapping(value = "teams/{teamId}/players/{playerId}/salary")
    public void changePlayerSalary(@PathVariable("teamId") UUID teamId,
                                   @PathVariable("playerId") UUID playerId,
                                   @RequestBody ChangePlayerSalaryRequest changePlayerSalaryRequest) {
        changePlayerSalary.execute(teamId, playerId, changePlayerSalaryRequest.getSalary());
    }
}
