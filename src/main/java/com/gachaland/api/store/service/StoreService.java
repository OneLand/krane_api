package com.gachaland.api.store.service;

import com.gachaland.api.common.Enumerations;
import com.gachaland.api.store.controller.model.ItemBody;
import com.gachaland.api.store.dao.model.StoreItem;
import com.gachaland.api.store.dao.repository.StoreRepository;
import com.gachaland.api.store.dto.mapper.StoreItemMapper;
import com.gachaland.api.store.dto.model.StoreItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreItemMapper storeItemMapper;

    public StoreItemDTO registerStoreItem(ItemBody itemBody) {
        StoreItem item = registerItem(itemBody);
        return storeItemMapper.mappingStoreItem(item);
    }

    public StoreItemDTO getItemDTO(long itemId) {
        return storeItemMapper.mappingStoreItem(getItem(itemId));
    }

    public StoreItemDTO updateItemStatus(long itemId, boolean enable) {
        StoreItem item = getItem(itemId);
        if (item == null) {
            return null;
        }

        item.setEnable(enable);
        storeRepository.save(item);

        return storeItemMapper.mappingStoreItem(item);
    }

    public List<StoreItemDTO> getItemDTOs(Boolean enable) {
        List<StoreItemDTO> itemDTOs = getItems(enable).stream().map(item -> storeItemMapper.mappingStoreItem(item)).collect(Collectors.toList());
        return itemDTOs;
    }

    public List<StoreItemDTO> getEnableItemDTOs(String type) {
        List<StoreItemDTO> itemDTOs = getEnableItems(type).stream().map(item -> storeItemMapper.mappingStoreItem(item)).collect(Collectors.toList());
        return itemDTOs;
    }

    public StoreItem registerItem(ItemBody itemBody) {
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

    public List<StoreItem> getEnableItems(String type) {
        try {
            Enumerations.ItemPayType itemPayType = Enumerations.ItemPayType.valueOf(type);
            return storeRepository.findByEnableAndItemPayType(true, itemPayType);
//            return storeRepository.findByItemPayType(itemPayType);
        } catch (Exception ex) {
            return null;
        }
    }
}
