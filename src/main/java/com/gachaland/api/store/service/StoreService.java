package com.gachaland.api.store.service;

import com.gachaland.api.common.Enumerations;
import com.gachaland.api.store.controller.model.ItemBody;
import com.gachaland.api.store.dao.model.StoreItem;
import com.gachaland.api.store.dao.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jhpark1220 on 2017. 6. 9..
 */
@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public StoreItem registerGoods(ItemBody itemBody) {

        StoreItem storeItem = new StoreItem();
        storeItem.setName(itemBody.getName());
        storeItem.setValue(itemBody.getValue());
        storeItem.setItemPayType(itemBody.getType());
        storeItem.setEnable(false);

        storeRepository.save(storeItem);

        return storeItem;
    }

    public StoreItem getItem(long itemId) {
        return storeRepository.findOne(itemId);
    }

    public List<StoreItem> getItems(Boolean enable) {
        if (enable == null) {
            return storeRepository.findAll();
        }
        else {
            return storeRepository.findByEnable(enable);
        }
    }

    public List<StoreItem> getItems(String type) {
        try {
            Enumerations.ItemPayType itemPayType = Enumerations.ItemPayType.valueOf(type);
            return storeRepository.findByItemPayType(itemPayType);
        } catch (Exception ex) {
            return null;
        }
    }
}
