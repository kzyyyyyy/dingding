package com.zero.dingding.dto;

import com.zero.dingding.model.PmsProduct;
import com.zero.dingding.model.SmsFlashPromotionProductRelation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 限时购商品信息封装
 * Created by macro on 2018/11/16.
 */
public class SmsFlashPromotionProduct extends SmsFlashPromotionProductRelation{
    @Getter
    @Setter
    @ApiModelProperty("关联商品")
    private PmsProduct product;
}
