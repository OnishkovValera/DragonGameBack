package org.onishkoff.itmo.IS1.controllers;

import lombok.RequiredArgsConstructor;
import org.onishkoff.itmo.IS1.dto.model.request.DragonDtoRequest;
import org.onishkoff.itmo.IS1.dto.model.response.DragonDto;
import org.onishkoff.itmo.IS1.model.UploadHistory;
import org.onishkoff.itmo.IS1.service.DragonService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


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
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      @RequestParam(defaultValue = "id") String sortColumn,
                                      @RequestParam(defaultValue = "") String filter,
                                      @RequestParam(defaultValue = "asc") String order,
                                      @RequestParam(defaultValue = "true") Boolean userPersonOnly
                                      ){
        return dragonService.getDragons(pageNumber, pageSize, sortColumn, filter, order, userPersonOnly);
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


    @PostMapping(path = "/file_upload")
    public ResponseEntity<UploadHistory> massiveUploadDragons(@RequestPart(value = "file") MultipartFile file){
        return ResponseEntity.ok(dragonService.fileUpload(file));
    }

    @GetMapping("/group_by_killer")
    public ResponseEntity<Map<String, Integer>> groupByKiller(){
        return ResponseEntity.ok(dragonService.groupDragonsByKiller());
    }

    @GetMapping("/filter_by_tooth")
    public ResponseEntity<List<DragonDto>> filterByTooth(@RequestParam(name = "toothCount", defaultValue = "0") Integer toothCount){
        if(toothCount == null){
            toothCount = 0;
        }
        return ResponseEntity.ok(dragonService.filterByTooth(toothCount));
    }

    @GetMapping("/get_by_speaking")
    public ResponseEntity<List<DragonDto>> getBySpeaking(@RequestParam(value = "speaking") Boolean speaking){
        return ResponseEntity.ok(dragonService.getBySpeaking(speaking));
    }

}
