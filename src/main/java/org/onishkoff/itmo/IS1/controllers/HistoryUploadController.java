package org.onishkoff.itmo.IS1.controllers;

import lombok.RequiredArgsConstructor;
import org.onishkoff.itmo.IS1.model.UploadHistory;
import org.onishkoff.itmo.IS1.service.UploadHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/files")
@RequiredArgsConstructor
public class HistoryUploadController {

    private final UploadHistoryService uploadHistoryService;

    @GetMapping
    public ResponseEntity<List<UploadHistory>> getHistory() {
        List<UploadHistory> history = uploadHistoryService.getUserHistory();
        return ResponseEntity.ok(history);
    }

}
