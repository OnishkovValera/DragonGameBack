package org.onishkoff.itmo.IS1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.onishkoff.itmo.IS1.model.enums.Status;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "admin_requests")
public class AdminRequests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    User user;

    @NotNull
    @Enumerated(EnumType.STRING)
    Status status;

    Date requestDate;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "processed_by")
    User adminProcessedId;

    Date processedDate;

    String comment;






}
