package com.mirim.byeolukyee.global.constant.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저가 존재하지 않습니다."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "글이 존재하지 않습니다."),
    IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "이미지가 존재하지 않습니다."),
    CHATROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "채팅방이 존재하지 않습니다."),
    MESSAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "메세지가 존재하지 않습니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "이미 가입된 이메일입니다."),
    DUPLICATE_CHATROOM(HttpStatus.CONFLICT, "이미 존재하는 채팅방입니다."),
    INCORRECT_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 알맞지 않습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "토큰이 유효하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String errorMessage;
}
