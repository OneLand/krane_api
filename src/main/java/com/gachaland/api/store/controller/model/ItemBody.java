package com.gachaland.api.store.controller.model;

import com.gachaland.api.common.Enumerations;
import lombok.Data;

/**
 * Created by jhpark1220 on 2017. 6. 9..
 */
@Data
public class ItemBody {

    private String name;    // 상품명

    private int value;      // 금액

    private Enumerations.ItemPayType type;   // 타입

}
