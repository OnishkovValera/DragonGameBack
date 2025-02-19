package org.onishkoff.itmo.IS1.util;

import org.onishkoff.itmo.IS1.dto.login.RegisterResponseDto;
import org.onishkoff.itmo.IS1.dto.model.request.*;
import org.onishkoff.itmo.IS1.dto.model.response.*;
import org.onishkoff.itmo.IS1.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class Mapper {

    public DragonDto toDragonDto(Dragon dragon) {
        return DragonDto.builder()
                .id(dragon.getId())
                .name(dragon.getName())
                .age(dragon.getAge())
                .dragonCave(dragon.getCave())
                .dragonHead(dragon.getHead())
                .person(toPersonDto(dragon.getPerson()))
                .coordinates(dragon.getCoordinates())
                .description(dragon.getDescription())
                .dragonCharacter(dragon.getCharacter())
                .owner(dragon.getOwner())
                .creationDateTime(dragon.getDataTime())
                .speaking(dragon.getSpeaking())
                .build();
    }

    public Dragon toDragon(DragonDtoRequest dragonDtoRequest) {
        return Dragon.builder()
                .name(dragonDtoRequest.getName())
                .age(dragonDtoRequest.getAge())
                .owner(dragonDtoRequest.getOwner())
                .person(toPerson(dragonDtoRequest.getPerson()))
                .cave(toDragonCave(dragonDtoRequest.getDragonCave()))
                .head(toDragonHead(dragonDtoRequest.getDragonHead()))
                .coordinates(toCoordinates(dragonDtoRequest.getCoordinates()))
                .description(dragonDtoRequest.getDescription())
                .character(dragonDtoRequest.getDragonCharacter())
                .speaking(dragonDtoRequest.getSpeaking())
                .build();

    }

    public RegisterResponseDto toRegisterResponseDto(User user) {
        return RegisterResponseDto.builder()
                .password(user.getPassword())
                .role(user.getRole())
                .id(user.getId())
                .name(user.getName())
                .login(user.getLogin())
                .build();
    }




    public Person toPerson(PersonDtoRequest person) {
        return Person.builder()
                .name(person.getName())
                .eyeColor(person.getEyeColor())
                .hairColor(person.getHairColor())
                .nationality(person.getNationality())
                .weight(person.getWeight())
                .location(toLocation(person.getLocation()))
                .build();
    }

    public PersonDto toPersonDto(Person person) {
        if(person == null) return null;
        return PersonDto.builder()
                .eyeColor(person.getEyeColor())
                .hairColor(person.getHairColor())
                .nationality(person.getNationality())
                .weight(person.getWeight())
                .location(person.getLocation())
                .id(person.getId())
                .name(person.getName())
                .owner(toUserDto(person.getOwner()))
                .createdAt(person.getCreatedAt())
                .build();

    }


    public Location toLocation(LocationDtoRequest locationDtoRequest) {
        if (locationDtoRequest == null) {
            return null;
        }
        return Location.builder()
                .id(locationDtoRequest.getId())
                .x(locationDtoRequest.getX())
                .y(locationDtoRequest.getY())
                .z(locationDtoRequest.getZ())
                .build();
    }


    public DragonCave toDragonCave(CaveDtoRequest caveDtoRequest) {
        if (caveDtoRequest == null) {
            return null;
        }

        return DragonCave.builder()
                .depth(caveDtoRequest.getDepth())
                .numberOfTreasures(caveDtoRequest.getNumberOfTreasures())
                .id(caveDtoRequest.getId())
                .build();
    }


    public CaveDto toCaveDto(DragonCave dragonCave) {
        if (dragonCave == null) {
            return null;
        }
        return CaveDto.builder()
                .id(dragonCave.getId())
                .depth(dragonCave.getDepth())
                .numberOfTreasures(dragonCave.getNumberOfTreasures())
                .build();
    }

    public DragonHead toDragonHead(DragonHeadDtoRequest dragonHeadDtoRequest) {
        if (dragonHeadDtoRequest == null) {
            return null;
        }
        return DragonHead.builder()
                .toothCount(dragonHeadDtoRequest.getToothCount())
                .id(dragonHeadDtoRequest.getId())
                .build();

    }


    public Coordinates toCoordinates(CoordinatesDtoRequest coordinatesDtoRequest) {

        if (coordinatesDtoRequest == null) {
            return null;
        }
        return Coordinates.builder()
                .id(coordinatesDtoRequest.getId())
                .x(coordinatesDtoRequest.getX())
                .y(coordinatesDtoRequest.getY())
                .build();
    }


    public UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .login(user.getLogin())
                .role(user.getRole())
                .build();


    }

    public User toUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        return User.builder()
                .id(userDto.getId())
                .login(userDto.getLogin())
                .role(userDto.getRole())
                .name(userDto.getName())
                .build();
    }

    public TeamDto toTeamDto(Team team) {
        return TeamDto.builder()
                .id(team.getId())
                .name(team.getName())
                .cave(toCaveDto(team.getCave()))
                .owner(toUserDto(team.getOwner()))
                .members(team.getMembers() != null ? (ArrayList<PersonDto>) team.getMembers().stream().map(this::toPersonDto).collect(Collectors.toList()) : null)
                .build();

    }

    public Team toTeam(TeamDtoRequest teamDtoRequest) {
        if (teamDtoRequest == null) {
            return null;
        }
        return Team.builder()
                .id(teamDtoRequest.getId())
                .name(teamDtoRequest.getName())
                .build();

    }

    public RequestDto toAdminRequest(AdminRequests adminRequest) {
        return RequestDto.builder()
                .id(adminRequest.getId())
                .adminProcessedId(toUserDto(adminRequest.getAdminProcessedId()))
                .status(adminRequest.getStatus())
                .requestDate(adminRequest.getRequestDate())
                .processedDate(adminRequest.getProcessedDate())
                .user(toUserDto(adminRequest.getUser()))
                .comment(adminRequest.getComment())
                .build();
    }

}
