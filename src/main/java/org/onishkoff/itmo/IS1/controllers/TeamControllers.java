package org.onishkoff.itmo.IS1.controllers;

import lombok.RequiredArgsConstructor;
import org.onishkoff.itmo.IS1.dto.model.request.TeamDtoRequest;
import org.onishkoff.itmo.IS1.dto.model.response.PersonDto;
import org.onishkoff.itmo.IS1.dto.model.response.TeamDto;
import org.onishkoff.itmo.IS1.model.Team;
import org.onishkoff.itmo.IS1.service.TeamService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/team")
public class TeamControllers {

    public final TeamService teamService;

    @GetMapping("/{id}")
    public ResponseEntity<TeamDto> getTeam(@PathVariable(name = "id") final Long teamId) {
        return ResponseEntity.ok().body(teamService.getTeamById(teamId));
    }

    @GetMapping
    public ResponseEntity<Page<TeamDto>> getTeams(@RequestParam(defaultValue = "0") Integer page,
                                                  @RequestParam(defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TeamDto> teamPages = teamService.getAllTeam(pageable);
        return ResponseEntity.ok().body(teamPages);
    }


    @PostMapping
    public ResponseEntity<TeamDto> createTeam(@RequestBody @Validated final TeamDtoRequest teamDtoRequest) {
        TeamDto newTeam = teamService.createTeam(teamDtoRequest);
        return ResponseEntity.ok().body(newTeam);
    }

    @DeleteMapping()
    @PreAuthorize("@securityUtil.hasAccess(@teamService.getTeamById(#teamId).getOwner().getId())")
    public ResponseEntity<Void> deleteTeam(@RequestParam(name = "id") final Long teamId) {
        teamService.deleteTeam(teamId);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @PreAuthorize("@securityUtil.hasAccess(@teamService.getTeamById(#teamDtoRequest.getId()).getOwner().getId())")
    public ResponseEntity<TeamDto> updateTeam(@RequestBody @Validated final TeamDtoRequest teamDtoRequest) {
        return ResponseEntity.ok(teamService.updateTeam(teamDtoRequest));
    }

    @PostMapping("/{id}/add")
    @PreAuthorize("@securityUtil.hasAccess(@teamService.getTeamById(#teamId).getOwner().getId()) && @securityUtil.hasAccess(@personService.getPerson(#memberId).owner.id)")
    public ResponseEntity<TeamDto> addTeamMember(@PathVariable(name = "id") final Long teamId,
                                                 @RequestParam final Long memberId) {
        return ResponseEntity.ok(teamService.addTeamMember(teamId, memberId));

    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("@securityUtil.hasAccess(@teamService.getTeamById(#teamId).getOwner().getId())")
    public ResponseEntity<TeamDto> deleteTeamMember(@PathVariable(name = "id") final Long teamId,
                                                    @RequestParam final Long memberId) {
        return ResponseEntity.ok(teamService.deleteTeamMember(teamId, memberId));
    }

}
