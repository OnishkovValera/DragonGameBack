package org.onishkoff.itmo.IS1.service;

import lombok.RequiredArgsConstructor;
import org.onishkoff.itmo.IS1.model.UploadHistory;
import org.onishkoff.itmo.IS1.model.enums.Role;
import org.onishkoff.itmo.IS1.repository.UploadHistoryRepository;
import org.onishkoff.itmo.IS1.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UploadHistoryService {

    private final UploadHistoryRepository uploadHistoryRepository;
    private final SecurityUtil securityUtil;

    public List<UploadHistory> getUserHistory() {
        if(securityUtil.getUserFromContext().getRole() == Role.ADMIN) {
            return uploadHistoryRepository.findAll();
        }else{
            return uploadHistoryRepository.findAllByUser(securityUtil.getUserFromContext());
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addBadUpload(UploadHistory uploadHistory) {
        uploadHistoryRepository.save(uploadHistory);

    }


    public void addNewUpload(UploadHistory uploadHistory) {
        uploadHistoryRepository.save(uploadHistory);
    }


    public UploadHistory createEmptyUpload() {
        return uploadHistoryRepository.save(UploadHistory.builder()
                .user(securityUtil.getUserFromContext())
                .isSuccess(false)
                .objectUrl("")
                .build());
    }
}
