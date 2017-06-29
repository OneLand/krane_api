package com.gachaland.api.common;

/**
 * Created by chris.j3 on 2017. 5. 26..
 */
public class Enumerations {

    public enum MemberType {
        GUEST, MEMBER, GOOGLE, FACEBOOK
    }

    public enum MemberStatus {
        UNREGISTERED, REGISTERED, ABUSER
    }

    public enum DirectionType {
        UP, DOWN, LEFT, RIGHT, PICK
    }

    public enum ItemPayType {
        INAPP, RUBY, COIN, FREE        // 루비로 구매 / 코인으로 구매 / 무료
    }

    public enum ItemStatus {
        ABLE, DISABLE        // 아이템 활성 / 비활성
    }

    public enum StoreGoodsType {
        RUBY, COIN, COUPON      // 루비 (inapp) / 코인 (ingame money) / 이용권 (무료,,,)
    }

    public enum MemberHistoryStatus{
        LOGIN,      // 접속
        START,      // 게임시작
        PURCHASE,   // 구매
        EXCHANGE,   // 교환
        ROOM_JOIN,  // 게임방 입장
        ROOM_EXIT,   // 게임방 퇴장
        END
    }

    public enum GameRoomType {
        RUBY_ROOM,      // 루비방
        GOLD_ROOM,      // 골드방
        FREE_ROOM,      // 무료방
        AD_ROOM,        // 광고방
    }

}
