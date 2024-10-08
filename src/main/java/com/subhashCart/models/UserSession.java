package com.subhashCart.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sessionId;
    @Column(unique = true)
    private String token;

    @Column(unique = true)
    private Integer userId;
    private String userType;

    private LocalDateTime sessionStartTime;

    private LocalDateTime sessionEndTime;

}
