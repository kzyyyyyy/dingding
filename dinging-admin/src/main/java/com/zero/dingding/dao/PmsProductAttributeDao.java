package com.zero.dingding.dao;

import com.zero.dingding.dto.ProductAttrInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品属性管理自定义Dao
 * Created by macro on 2018/5/23.
 */
public interface PmsProductAttributeDao {
    /**
     * 获取商品属性信息
     */
    List<ProductAttrInfo> getProductAttrInfo(@Param("id") Long productCategoryId);
}
