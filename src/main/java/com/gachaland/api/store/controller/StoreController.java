package com.gachaland.api.store.controller;

import com.gachaland.api.common.api.StandardResponse;
import com.gachaland.api.common.constants.ResultCode;
import com.gachaland.api.store.controller.model.ItemBody;
import com.gachaland.api.store.dto.model.StoreItemDTO;
import com.gachaland.api.store.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
@Api(description = "상품 정보", tags = "Store Info")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @ApiOperation(value = "상품 등록하기", notes = "상품 등록")
    @RequestMapping(method = RequestMethod.POST, value = "/item")
    public StandardResponse registerGoods(
            @ApiParam(value = "상품정보", required = true) @RequestBody(required = true) ItemBody item) {

        StoreItemDTO itemDTO = storeService.registerStoreItem(item);
        return new StandardResponse(ResultCode.OK.getCode(), ResultCode.OK.name(), itemDTO);
    }

    @ApiOperation(value = "상품 조회 하기", notes = "상품 조회")
    @RequestMapping(method = RequestMethod.GET, value = "/item/{itemId}")
    public StandardResponse registerGoods( @ApiParam(value = "itemId", required = true) @PathVariable long itemId) {

        StoreItemDTO itemDTO = storeService.getItemDTO(itemId);
        return new StandardResponse(ResultCode.OK.getCode(), ResultCode.OK.name(), itemDTO);
    }

    @ApiOperation(value = "상품 활성/비활성 변경 하기", notes = "상품 변경")
    @RequestMapping(method = RequestMethod.POST, value = "/item/{itemId}/{enable}")
    public StandardResponse registerGoods( @ApiParam(value = "itemId", required = true) @PathVariable long itemId,
                                           @ApiParam(value = "enable", required = true) @PathVariable boolean enable) {

        StoreItemDTO itemDTO = storeService.updateItemStatus(itemId, enable);
        return new StandardResponse(ResultCode.OK.getCode(), ResultCode.OK.name(), itemDTO);
    }

    @ApiOperation(value = "등록된 모든 상품 조회 하기", notes = "등록된 상품 조회")
    @RequestMapping(method = RequestMethod.GET, value = "/items")
    public StandardResponse registerGoods(
            @ApiParam(name="enable", required = false, value="0") @RequestParam(value = "enable", required = false, defaultValue = "") Boolean enable) {

        List<StoreItemDTO> itemDTOs = storeService.getItemDTOs(enable);
        return new StandardResponse(ResultCode.OK.getCode(), ResultCode.OK.name(), itemDTOs);
    }

    @ApiOperation(value = "Item Type으로 상품 조회 하기", notes = "Type별 상품 조회")
    @RequestMapping(method = RequestMethod.GET, value = "/items/{type}")
    public StandardResponse registerGoods(
            @ApiParam(value = "type", required = true) @PathVariable String type) {

        List<StoreItemDTO> itemDTOs = storeService.getEnableItemDTOs(type);
        return new StandardResponse(ResultCode.OK.getCode(), ResultCode.OK.name(), itemDTOs);
    }

}

