package org.onishkoff.itmo.IS1.controllers;

import lombok.RequiredArgsConstructor;
import org.onishkoff.itmo.IS1.dto.model.response.RequestDto;
import org.onishkoff.itmo.IS1.model.enums.Status;
import org.onishkoff.itmo.IS1.service.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/request")
public class AdminRequestController {

    private final RequestService requestService;

    @GetMapping
    @PreAuthorize("@securityUtil.isAdmin()")
    public List<RequestDto> getRequests(){
        return requestService.getAllRequests();
    }

    @PostMapping
    @PreAuthorize("!@securityUtil.isAdmin()")
    public ResponseEntity<Void> newRequest() {
        requestService.addNewRequest();
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}")
    @PreAuthorize("@securityUtil.isAdmin()")
    public ResponseEntity<Void> handleRequest(@PathVariable Long id, @RequestParam Status status) {
        requestService.handleRequest(id, status);
        return ResponseEntity.ok().build();

    }



}
