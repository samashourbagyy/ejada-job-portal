package com.ejada.job_portal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "NOTIFICATIONS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @Column(name = "NOTIFICATION_ID")
    private Long notificationId;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "MESSAGE", nullable = false, length = 500)
    private String message;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATED_AT")
    private Date createdAt;

    @Column(name = "IS_READ", length = 1)
    private String isRead;
}