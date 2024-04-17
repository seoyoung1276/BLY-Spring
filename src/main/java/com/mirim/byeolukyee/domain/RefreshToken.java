package com.mirim.byeolukyee.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long userId;

    @Column(nullable = false)
    private String refreshToken;

   private RefreshToken(Long userId, String refreshToken) {
       this.userId = userId;
       this.refreshToken = refreshToken;
   }

   private RefreshToken update(String newRefreshToken) {
       this.refreshToken = newRefreshToken;
       return this;
   }
}
