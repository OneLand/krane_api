package com.gachaland.api.common.constants;

public enum ResultCode {
    OK(200),
    INVALID_INPUT(400),
    UNAUTHORIZED(401),
    NOT_ACCEPTABLE(406),
    INTERNAL_SERVER_ERROR(500),
    NOT_FOUND(404),
    DATA_FAIL_WITH_REMOTE_SERVER(601),      // 원격 서버와 통신하는데 오류가 발생한 경우

    ROOM_ALREADY_JOINED(1000),      // 이미 게임방에 접속중
    ROOM_NOT_JOINED(1001),          // 해당 방에 join되어있지 않음
    ROOM_NOT_EXIST(1002),           // 게임방이 존재하지 않음
    ROOM_USER_EXCEEDED(1003),     // 참여자 오버
    ROOM_JOINER_EXCEEDED(1004),     // 참여자 오버
    ROOM_VIEWER_EXCEEDED(1005),     // 관전자 오버
    ;

    private int code;

    public int getCode() {
        return code;
    }

    ResultCode(int code) {
        this.code = code;
    }

}
