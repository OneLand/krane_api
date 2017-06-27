package com.gachaland.api.store.dto.mapper;

import com.gachaland.api.store.dao.model.StoreItem;
import com.gachaland.api.store.dto.model.StoreItemDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by chris.j3 on 2017. 6. 27..
 */
@Slf4j
@Service
public class StoreItemMapper {

    public StoreItemDTO mappingStoreItem(StoreItem item) {
        StoreItemDTO storeItemDTO = new StoreItemDTO();
        storeItemDTO.setItemId(item.getId());
        storeItemDTO.setItemName(item.getName());
        storeItemDTO.setItemValue(item.getValue());
        storeItemDTO.setItemPayType(item.getItemPayType());
        storeItemDTO.setEnable(item.isEnable());

        return storeItemDTO;
    }

}
