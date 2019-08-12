package com.jackting.module_order.bean.entity;

import com.chad.library.adapter.base.entity.IExpandable;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.jackting.module_order.adapter.CartExpandableRVAdapter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/4/9.
 */

public class CartShopEntity implements Serializable, MultiItemEntity, IExpandable<CartGoodEntity> {
    private String recId;
    /* 1 表示 普通商品，2表示礼包*/
//    private String model_type;
//    private String model_name;
    private String goodsPrice;
    private String needRealAuth;
    //    private List<CartGoodEntity> goods_list;
    private String goodsId;
    private String goodsName;
    private String goodsThumb;
    private String goodsNumber;
    private String goodsAttr;
    private boolean selected = false;

    private CartActivityEntity activity;

    public CartActivityEntity getActivity() {
        return activity;
    }

    public void setActivity(CartActivityEntity activity) {
        this.activity = activity;
    }


    public String getRecId() {
        return recId;
    }

    public void setRecId(String recId) {
        this.recId = recId;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getNeedRealAuth() {
        return needRealAuth;
    }

    public void setNeedRealAuth(String needRealAuth) {
        this.needRealAuth = needRealAuth;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsThumb() {
        return goodsThumb;
    }

    public void setGoodsThumb(String goodsThumb) {
        this.goodsThumb = goodsThumb;
    }

    public String getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getGoodsAttr() {
        return goodsAttr;
    }

    public void setGoodsAttr(String goodsAttr) {
        this.goodsAttr = goodsAttr;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public int getItemType() {
//        if(model_type.equals("1")){
//            return  CartExpandableRVAdapter.CART_TYPE.GOOD.ordinal();
//        }else if(model_type.equals("2")){
//            return  CartExpandableRVAdapter.CART_TYPE.PACKAGE.ordinal();
//        }else{
        return CartExpandableRVAdapter.CART_TYPE.GOOD.ordinal();
//        }
    }

    @Override
    public boolean isExpanded() {
        return true;
    }

    @Override
    public void setExpanded(boolean expanded) {

    }

//    public Boolean removeSubItem(CartGoodEntity CartGoodEntity){
//        return goods_list != null && goods_list.remove(CartGoodEntity);
//    }
//
//    public void removeAllSubItem(){
//        goods_list=new ArrayList<>();
//    }
//
    @Override
    public List<CartGoodEntity> getSubItems() {
        return  null;
    }

    @Override
    public int getLevel() {
        return 1;
    }
}
