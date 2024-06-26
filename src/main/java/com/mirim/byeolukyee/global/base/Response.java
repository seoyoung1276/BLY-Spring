package com.mirim.byeolukyee.global.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Response {
    private final Boolean isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDateTime updatedAt;
}
