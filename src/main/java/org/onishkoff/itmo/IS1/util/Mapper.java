package org.onishkoff.itmo.IS1.util;

import org.onishkoff.itmo.IS1.dto.login.RegisterResponseDto;
import org.onishkoff.itmo.IS1.dto.model.request.*;
import org.onishkoff.itmo.IS1.dto.model.response.DragonDto;
import org.onishkoff.itmo.IS1.dto.model.response.PersonDto;
import org.onishkoff.itmo.IS1.dto.model.response.UserDto;
import org.onishkoff.itmo.IS1.model.*;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public DragonDto toDragonDto(Dragon dragon) {
        return DragonDto.builder()
                .id(dragon.getId())
                .name(dragon.getName())
                .age(dragon.getAge())
                .dragonCave(dragon.getCave())
                .dragonHead(dragon.getHead())
                .person(dragon.getPerson())
                .coordinates(dragon.getCoordinates())
                .description(dragon.getDescription())
                .dragonCharacter(dragon.getCharacter())
                .creationDateTime(dragon.getDataTime())
                .speaking(dragon.getSpeaking())
                .build();
    }

    public Dragon toDragon(DragonDtoRequest dragonDtoRequest){
        return Dragon.builder()
                .name(dragonDtoRequest.getName())
                .age(dragonDtoRequest.getAge())
                .owner(dragonDtoRequest.getOwner())
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


    public Person toPerson(PersonDtoRequest person){
        return Person.builder()
                .name(person.getName())
                .eyeColor(person.getEyeColor())
                .hairColor(person.getHairColor())
                .nationality(person.getNationality())
                .weight(person.getWeight())
                .location(toLocation(person.getLocation()))
                .build();
    }

    public PersonDto toPersonDto(Person person){
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


    public Location toLocation(LocationDtoRequest locationDtoRequest){
        if(locationDtoRequest == null){
            return null;
        }
        return Location.builder()
                .x(locationDtoRequest.getX())
                .y(locationDtoRequest.getY())
                .z(locationDtoRequest.getZ())
                .build();
    }


    public DragonCave toDragonCave(CaveDtoRequest caveDtoRequest){
        if(caveDtoRequest == null){
            return null;
        }

        return DragonCave.builder()
                .depth(caveDtoRequest.getDepth())
                .numberOfTreasures(caveDtoRequest.getNumberOfTreasures())
                .id(caveDtoRequest.getId())
                .build();
    }


    public DragonHead toDragonHead(DragonHeadDtoRequest dragonHeadDtoRequest){
        if(dragonHeadDtoRequest == null){
            return null;
        }
        return DragonHead.builder()
                .toothCount(dragonHeadDtoRequest.getToothCount())
                .id(dragonHeadDtoRequest.getId())
                .build();

    }


    public Coordinates toCoordinates(CoordinatesDtoRequest coordinatesDtoRequest){

        if(coordinatesDtoRequest == null){
            return null;
        }
        return Coordinates.builder()
                .id(coordinatesDtoRequest.getId())
                .x(coordinatesDtoRequest.getX())
                .y(coordinatesDtoRequest.getY())
                .build();
    }


    public UserDto toUserDto(User user){
        if(user == null){
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .login(user.getLogin())
                .role(user.getRole())
                .build();


    }

}
