package org.onishkoff.itmo.IS1.util;

import org.onishkoff.itmo.IS1.dto.login.RegisterDto;
import org.onishkoff.itmo.IS1.dto.login.RegisterResponseDto;
import org.onishkoff.itmo.IS1.dto.model.request.DragonDtoRequest;
import org.onishkoff.itmo.IS1.dto.model.response.DragonDto;
import org.onishkoff.itmo.IS1.model.*;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public DragonDto toDragonDto(Dragon dragon) {
        return DragonDto.builder()
                .id(dragon.getId())
                .name(dragon.getName())
                .age(dragon.getAge())
                .cave(dragon.getCave())
                .head(dragon.getHead())
                .person(dragon.getPerson())
                .coordinates(dragon.getCoordinates())
                .description(dragon.getDescription())
                .character(dragon.getCharacter())
                .dataTime(dragon.getDataTime())
                .speaking(dragon.getSpeaking())
                .build();
    }

    public Dragon toDragon(DragonDtoRequest dragonDtoRequest,
                           Coordinates coordinates,
                           DragonCave dragonCave,
                           Person person,
                           DragonHead dragonHead){
        return Dragon.builder()
                .name(dragonDtoRequest.getName())
                .age(dragonDtoRequest.getAge())
                .cave(dragonCave)
                .head(dragonHead)
                .person(person)
                .coordinates(coordinates)
                .description(dragonDtoRequest.getDescription())
                .character(dragonDtoRequest.getCharacter())
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


}
