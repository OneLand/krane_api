package com.gachaland.api.store.dao.model;

import com.gachaland.api.common.Enumerations;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by chris.j3 on 2017. 6. 7..
 */
@Data
@Entity
@Table(name = "LandStoreItem")
public class StoreItem {
    // 상점에서 판매할 상품들 목록
    @Id
    @GeneratedValue
    private long id;

    private String name;        // 루비 10개  / 우선선택권      // 무료사용권 / 우선선택권

    private int value;          // 1000(원)  / 10,000(원)    // 0코인     / 100,000코인
                                // INAPP     / INAPP         // COIN     / COIN

    //    @Column(columnDefinition = "VARCHAR(32) default 'ALL'")
    @Enumerated(EnumType.STRING)
    @Column(length = 64)
    private Enumerations.ItemPayType itemPayType;     // 구매타입 : 루비, 코인, 무료지급

    @Column(columnDefinition = "BOOLEAN default 0", nullable = false)
    private boolean enable;     // 아이템 활성 여부

}
