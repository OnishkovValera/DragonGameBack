package org.onishkoff.itmo.IS1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "upload_history")
public class UploadHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "creation_date")
    private ZonedDateTime date;

    @Column(name = "is_success")
    private Boolean isSuccess;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "object_url")
    private String objectUrl;

    @PrePersist
    private void onCreate() {
        if (date == null) {
            date = ZonedDateTime.now();
        }
    }

}
