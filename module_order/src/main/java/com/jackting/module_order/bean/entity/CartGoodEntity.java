package com.jackting.module_order.bean.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.jackting.module_order.adapter.CartExpandableRVAdapter;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/9.
 */

public class CartGoodEntity implements Serializable, MultiItemEntity {
    private String goods_id;
    private String need_real_auth;
    private String goods_thumb;
    private String goods_price;
    private String goods_number;
    private String goods_name;
    private String goods_attr;

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getNeed_real_auth() {
        return need_real_auth;
    }

    public void setNeed_real_auth(String need_real_auth) {
        this.need_real_auth = need_real_auth;
    }

    public String getGoods_thumb() {
        return goods_thumb;
    }

    public void setGoods_thumb(String goods_thumb) {
        this.goods_thumb = goods_thumb;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getGoods_number() {
        return goods_number;
    }

    public void setGoods_number(String goods_number) {
        this.goods_number = goods_number;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_attr() {
        return goods_attr;
    }

    public void setGoods_attr(String goods_attr) {
        this.goods_attr = goods_attr;
    }

    @Override
    public int getItemType() {
        return CartExpandableRVAdapter.CART_TYPE.PACKAGE_GOOD.ordinal();
    }
}
