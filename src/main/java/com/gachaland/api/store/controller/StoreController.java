package com.gachaland.api.store.controller;

import com.gachaland.api.store.controller.model.ItemBody;
import com.gachaland.api.store.dao.model.StoreItem;
import com.gachaland.api.store.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jhpark1220 on 2017. 6. 9..
 */
@RestController
@RequestMapping("/store")
@Api(description = "상품 정보", tags = "Store Info")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @ApiOperation(value = "상품 등록하기", notes = "상품 등록")
    @RequestMapping(method = RequestMethod.POST, value = "/item")
    public StoreItem registerGoods(
            @ApiParam(value = "상품정보", required = true) @RequestBody(required = true) ItemBody item) {

        return storeService.registerGoods(item);
    }

    @ApiOperation(value = "상품 조회 하기", notes = "상품 조회")
    @RequestMapping(method = RequestMethod.GET, value = "/item/{itemId}")
    public StoreItem registerGoods( @ApiParam(value = "itemId", required = true) @PathVariable long itemId) {
        return storeService.getItem(itemId);
    }

    @ApiOperation(value = "등록된 모든 상품 조회 하기", notes = "등록된 상품 조회")
    @RequestMapping(method = RequestMethod.GET, value = "/items")
    public List<StoreItem> registerGoods(
            @ApiParam(name="enable", required = false, value="0") @RequestParam(value = "enable", required = false, defaultValue = "") Boolean enable) {
        return storeService.getItems(enable);
    }

    @ApiOperation(value = "Item Type으로 상품 조회 하기", notes = "Type별 상품 조회")
    @RequestMapping(method = RequestMethod.GET, value = "/items/{type}")
    public List<StoreItem> registerGoods(
            @ApiParam(value = "type", required = true) @PathVariable String type) {
        return storeService.getEnableItems(type);
    }

}

