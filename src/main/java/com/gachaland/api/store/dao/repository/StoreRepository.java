package com.gachaland.api.store.dao.repository;

import com.gachaland.api.common.Enumerations;
import com.gachaland.api.store.dao.model.StoreItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 */
public interface StoreRepository extends JpaRepository<StoreItem, Long> {
    List<StoreItem> findByEnable(boolean enable);
    List<StoreItem> findByItemPayType(Enumerations.ItemPayType itemPayType);

    List<StoreItem> findByEnableAndItemPayType(boolean enable, Enumerations.ItemPayType itemPayType);
}
