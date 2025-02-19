package org.onishkoff.itmo.IS1.repository;

import org.onishkoff.itmo.IS1.model.UploadHistory;
import org.onishkoff.itmo.IS1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UploadHistoryRepository extends JpaRepository<UploadHistory, Integer> {
    List<UploadHistory> findAllByUser(User user);
}
