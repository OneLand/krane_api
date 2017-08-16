package com.gachaland.api.store.dto.model;

import com.gachaland.api.common.Enumerations;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreItemDTO {

    private long itemId;

    private String itemName;        // 루비 10개  / 우선선택권      // 무료사용권 / 우선선택권

    private int itemValue;          // 1000(원)  / 10,000(원)    // 0코인     / 100,000코인

    private Enumerations.ItemPayType itemPayType;     // 구매타입 : 루비, 코인, 무료지급

    private boolean enable;     // 아이템 활성 여부

}
