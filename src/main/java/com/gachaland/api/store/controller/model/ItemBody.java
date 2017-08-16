package com.gachaland.api.store.controller.model;

import com.gachaland.api.common.Enumerations;
import lombok.Data;

@Data
public class ItemBody {

    private String name;    // 상품명

    private int value;      // 금액

    private Enumerations.ItemPayType type;   // 타입

}
