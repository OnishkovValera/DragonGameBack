package org.onishkoff.itmo.IS1.service;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.onishkoff.itmo.IS1.dto.model.request.TeamDtoRequest;
import org.onishkoff.itmo.IS1.dto.model.response.TeamDto;
import org.onishkoff.itmo.IS1.exception.PersonNotFoundException;
import org.onishkoff.itmo.IS1.exception.TeamNotFoundException;
import org.onishkoff.itmo.IS1.model.Dragon;
import org.onishkoff.itmo.IS1.model.DragonCave;
import org.onishkoff.itmo.IS1.model.Person;
import org.onishkoff.itmo.IS1.model.Team;
import org.onishkoff.itmo.IS1.repository.TeamRepository;
import org.onishkoff.itmo.IS1.util.Mapper;
import org.onishkoff.itmo.IS1.util.SecurityUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final PersonService personService;
    private final SecurityUtil securityUtil;
    public final Mapper mapper;
    private final DragonCaveService dragonCaveService;
    private final UserService userService;

    public TeamDto getTeamById(Long id) {
        return mapper.toTeamDto(teamRepository.findById(id).orElseThrow(TeamNotFoundException::new));
    }


    public TeamDto createTeam(TeamDtoRequest teamDtoRequest) {
        ArrayList<Person> members = null;
        Team team = mapper.toTeam(teamDtoRequest);
        team.setId(null);
        if(teamDtoRequest.getMembers() != null) {
            members = new ArrayList<>(teamDtoRequest.getMembers().stream()
                    .map(personService::findPersonById)
                    .toList());
            members.forEach((member) -> {
                member.setTeam(team);
            });
        }

        team.setCave(updateTeamCave(teamDtoRequest));
        team.setMembers(members);
        team.setOwner(securityUtil.getUserFromContext());
        return mapper.toTeamDto(teamRepository.save(team));
    }

    public void deleteTeam(Long teamId) {
        Team team = teamRepository.findById(teamId).orElseThrow(TeamNotFoundException::new);
        List<Person> persons = team.getMembers();
        persons.forEach(person -> {
            person.setTeam(null);
            personService.save(person);
        });
        teamRepository.delete(team);
    }


    public TeamDto updateTeam(TeamDtoRequest teamDtoRequest) {
        Team team = teamRepository.findById(teamDtoRequest.getId()).orElseThrow(TeamNotFoundException::new);
        team.setName(teamDtoRequest.getName());
        team.setCave(updateTeamCave(teamDtoRequest));
        List<Person> membersToDelete = new ArrayList<>();
        List<Person> newMembers = teamDtoRequest.getMembers().stream().map(personService::findPersonById).toList();
        team.getMembers().forEach((member) -> {
            if (!newMembers.contains(member)) {
                member.setTeam(null);
                membersToDelete.add(member);
                personService.save(member);

            }
        });

        membersToDelete.forEach((member) -> team.getMembers().remove(member));

        newMembers.forEach((member) -> {
            if(!team.getMembers().contains(member)) {
                member.setTeam(team);
                team.getMembers().add(member);
            }
        });
        return mapper.toTeamDto(teamRepository.save(team));
    }

    public TeamDto addTeamMember(Long teamId, Long memberId) {
        Person person = personService.findPersonById(memberId);
        person.setTeam(teamRepository.findById(teamId).orElseThrow(TeamNotFoundException::new));
        personService.save(person);
        return getTeamById(teamId);
    }

    public TeamDto deleteTeamMember(Long teamId, Long memberId) {
        Person person = personService.findPersonById(memberId);
        person.setTeam(null);
        personService.save(person);
        return getTeamById(teamId);
    }

    public Page<TeamDto> getAllTeam(Integer page, Integer size, String sortColumn, String filter, String order, Boolean userPersonOnly) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(order), sortColumn));
        Specification<Team> specification = (root, query, criteriaBuilder) -> {
            Predicate userOnlyPredicate = criteriaBuilder.conjunction();
            Predicate filterPredicate = criteriaBuilder.conjunction();
            if(filter != null && !filter.isEmpty()){
                filterPredicate = criteriaBuilder.like(root.get("name"), "%" + filter + "%");
            }
            if(userPersonOnly){
                userOnlyPredicate = criteriaBuilder.equal(root.get("owner"), securityUtil.getUserFromContext());
            }
            return criteriaBuilder.and(filterPredicate, userOnlyPredicate);
        };
        return teamRepository.findAll(specification, pageable).map(mapper::toTeamDto);
    }


    private DragonCave updateTeamCave(TeamDtoRequest teamDtoRequest){
        if(teamDtoRequest.getCave() == null){
            return null;
        }
        if (teamDtoRequest.getCave().getId() == null) {
            return dragonCaveService.createNewCave(teamDtoRequest.getCave().getDepth(), teamDtoRequest.getCave().getNumberOfTreasures());
        }

        DragonCave oldDragonCave = dragonCaveService.findDragonCaveById(teamDtoRequest.getCave().getId());
        oldDragonCave.setNumberOfTreasures(teamDtoRequest.getCave().getNumberOfTreasures());
        oldDragonCave.setDepth(teamDtoRequest.getCave().getDepth());
        return oldDragonCave;
    }
}
