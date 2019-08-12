package com.jackting.module_order.bean.vo;

import com.chad.library.adapter.base.entity.IExpandable;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.jackting.module_order.adapter.CartExpandableRVAdapter;
import com.jackting.module_order.bean.entity.CartShopEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sakura on 2017/3/2.
 */
public class GetCartListVO {


    /**
     * cart_goods : [{"supplier_name":"德国精品海淘","goods_list":[{"rec_id":"4424","goods_name":"erdbar草莓熊德国 吸吸乐儿童辅食有机水果混合果泥 100G 买四送一","goods_thumb":"images/201610/thumb_img/1185_thumb_G_1476927759603.jpg","goods_price":"25.00","goods_number":"1","goods_attr":"口味:苹果、香蕉、覆盆子混合果泥 \n"}]},{"supplier_name":"网站自营","goods_list":[{"rec_id":"4426","goods_name":"新西兰Swisse 儿童复合维生素 120粒","goods_thumb":"images/201609/thumb_img/378_thumb_G_1475227340856.jpg","goods_price":"105.00","goods_number":"1","goods_attr":""},{"rec_id":"4425","goods_name":"意大利Trudi特鲁迪婴儿护理防护软膏100ml","goods_thumb":"images/201612/thumb_img/3196_thumb_G_1482099652035.jpg","goods_price":"70.00","goods_number":"1","goods_attr":""},{"rec_id":"4419","goods_name":"【直邮】Aussie袋鼠顺滑修护洗发水（果香型）400ml","goods_thumb":"images/201701/thumb_img/3658_thumb_G_1483662501058.jpg","goods_price":"75.00","goods_number":"1","goods_attr":""}]}]
     * total_fee : 275.00
     */

    private String totalFee;
    private List<CartGoodsBean> cartGoods;

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public List<CartGoodsBean> getCartGoods() {
        return cartGoods;
    }

    public void setCartGoods(List<CartGoodsBean> cartGoods) {
        this.cartGoods = cartGoods;
    }

    public static class CartGoodsBean implements MultiItemEntity, IExpandable<CartShopEntity> {

        private boolean isSelect=false;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        /**
         * supplier_name : 德国精品海淘
         * goods_list : [{"rec_id":"4424","goods_name":"erdbar草莓熊德国 吸吸乐儿童辅食有机水果混合果泥 100G 买四送一","goods_thumb":"images/201610/thumb_img/1185_thumb_G_1476927759603.jpg","goods_price":"25.00","goods_number":"1","goods_attr":"口味:苹果、香蕉、覆盆子混合果泥 \n"}]
         */

        private String supplierName;
        private List<CartShopEntity> goodsList;

        public String getSupplierName() {
            return supplierName;
        }

        public void setSupplierName(String supplierName) {
            this.supplierName = supplierName;
        }

        public List<CartShopEntity> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<CartShopEntity> goodsList) {
            this.goodsList = goodsList;
        }

        @Override
        public boolean isExpanded() {
            return false;
        }

        @Override
        public void setExpanded(boolean expanded) {

        }

        @Override
        public List<CartShopEntity> getSubItems() {
            return goodsList;
        }


        @Override
        public int getLevel() {
            return 0;
        }

        @Override
        public int getItemType() {
            return CartExpandableRVAdapter.CART_TYPE.SHOP.ordinal();
        }

        public int getSubItemPosition(CartShopEntity subItem) {
            return goodsList != null ? goodsList.indexOf(subItem) : -1;
        }

        public boolean removeSubItem(CartShopEntity subItem) {
            return goodsList != null && goodsList.remove(subItem);
        }

        public boolean removeSubItem(int position) {
            if (goodsList != null && position >= 0 && position < goodsList.size()) {
                goodsList.remove(position);
                return true;
            }
            return false;
        }

        public void addSubItem(CartShopEntity subItem) {
            if (goodsList == null) {
                goodsList = new ArrayList<>();
            }
            goodsList.add(subItem);
        }

        public void addSubItem(int position, CartShopEntity subItem) {
            if (goodsList != null && position >= 0 && position < goodsList.size()) {
                goodsList.add(position, subItem);
            } else {
                addSubItem(subItem);
            }
        }

    }
}
