package org.onishkoff.itmo.IS1.controllers;

import lombok.RequiredArgsConstructor;
import org.onishkoff.itmo.IS1.dto.model.request.DragonDtoRequest;
import org.onishkoff.itmo.IS1.dto.model.response.DragonDto;
import org.onishkoff.itmo.IS1.service.DragonService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/dragon")
@RequiredArgsConstructor
public class DragonController {

    private final DragonService dragonService;

    @GetMapping("/{id}")
    public DragonDto getDragon(@PathVariable Integer id){
        return dragonService.getDragonDto(id);
    }

    @GetMapping
    public Page<DragonDto> getDragons(@RequestParam(defaultValue = "0") Integer pageNumber,
                                   @RequestParam(defaultValue = "10") Integer pageSize
                                      ){
        return dragonService.getDragons(pageNumber, pageSize);
    }

    @PostMapping
    public DragonDto createDragon(@RequestBody @Validated DragonDtoRequest dragonDto){
        return dragonService.createDragon(dragonDto);
    }

    @DeleteMapping
    @PreAuthorize("@securityUtil.hasAccess(@dragonService.getDragon(#id).getOwner().getId())")
    public void deleteDragon(@RequestParam(name = "id") Integer id){
        dragonService.delete(id);
    }

    @PutMapping
    @PreAuthorize("@securityUtil.hasAccess(@dragonService.getDragon(#dragonDto.getId()).getOwner().getId())")
    public DragonDto updateDragon(@RequestBody @Validated DragonDtoRequest dragonDto){
        return dragonService.update(dragonDto);
    }




}
