package com.jackting.module_order.adapter;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatCheckBox;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.jackting.common.CommonApplication;
import com.jackting.common.data.img.ImageLoader;
import com.jackting.common.widget.CountViewInCart;
import com.jackting.module_order.R;
import com.jackting.module_order.bean.entity.CartGoodEntity;
import com.jackting.module_order.bean.entity.CartShopEntity;
import com.jackting.module_order.bean.vo.GetCartListVO;

import java.util.List;

/**
 * Created by admin on 2017/2/14.
 */

public class CartExpandableRVAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public enum CART_TYPE {
        SHOP,
        GOOD,
        PACKAGE,
        PACKAGE_GOOD
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public CartExpandableRVAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(CART_TYPE.SHOP.ordinal(), R.layout.order_layout_cart_shop);
        addItemType(CART_TYPE.GOOD.ordinal(), R.layout.order_layout_cart_good);
        addItemType(CART_TYPE.PACKAGE.ordinal(),R.layout.order_layout_cart_package);
        addItemType(CART_TYPE.PACKAGE_GOOD.ordinal(),R.layout.order_layout_cart_package_good);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final MultiItemEntity item) {
        final int position = helper.getLayoutPosition();
//        final int position = helper.getAdapterPosition();

        if (item instanceof GetCartListVO.CartGoodsBean) {
            helper.getConvertView().setTag("cartGoodsBean");
        } else if (item instanceof CartShopEntity) {
            CartShopEntity cartShopModel=(CartShopEntity)item;
            helper.getConvertView().setTag(cartShopModel.getRecId());
        }
        if (helper.getItemViewType() == CART_TYPE.SHOP.ordinal()) {

            GetCartListVO.CartGoodsBean shop =
                    (GetCartListVO.CartGoodsBean) getData().get(position);

            // 数据显示
            helper.setChecked(R.id.accb_cart_shop_title, shop.isSelect())
                    .setText(R.id.accb_cart_shop_title, shop.getSupplierName());

            // 视图绑定
            AppCompatCheckBox accb = helper.getView(R.id.accb_cart_shop_title);
            accb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int realTimePosition = helper.getLayoutPosition();
                    boolean isChecked = ((AppCompatCheckBox) v).isChecked();
                    // 选择了店铺，该店铺所有的商品都应该全选
                    // 取消了店铺，该店铺所有的商品都应该取消
                    GetCartListVO.CartGoodsBean shop =
                            (GetCartListVO.CartGoodsBean) getData().get(realTimePosition);
                    shop.setSelect(isChecked);
                    List<CartShopEntity> testGoods
                            = shop.getSubItems();
                    for (int i = 0; i < testGoods.size(); i++) {
                        testGoods.get(i).setSelected(isChecked);
                    }
                    mOnAllChangeListener.onCheckChange();
//                    notifyDataSetChanged();
                    computeState();
                }
            });
        } else if (helper.getItemViewType() == CART_TYPE.GOOD.ordinal()) {
            final CartShopEntity testGood =
                    (CartShopEntity) getData().get(helper.getLayoutPosition());
            String activityName="";
//            if(testGood.getActivity().getName()!=null){
//                if(testGood.getActivity().getName().size()!=0){
//                    activityName=testGood.getActivity().getName().get(0);
//                }
//            }
            AppCompatCheckBox accb = helper.getView(R.id.accb_cart_good);
            accb.setOnCheckedChangeListener(null);

            helper.addOnClickListener(R.id.iv_cart_good_image);
            helper.addOnClickListener(R.id.ll_item_cart_good_right);
            // 数据显示
            helper.setChecked(R.id.accb_cart_good, testGood.isSelected())
                    .setText(R.id.tv_cart_good_title, testGood.getGoodsName())
//                    .setText(R.id.tv_cart_good_post_fee, testGood.get)
                    .setText(R.id.tv_cart_good_per_price, "￥" + testGood.getGoodsPrice())
                    .setText(R.id.tv_cart_good_spec,testGood.getGoodsAttr())
                    .setText(R.id.tv_cart_activity_name,activityName);
//                    .setText(R.id.tv_cart_good_count, "x" + testGood.getGoods_number());
            final CountViewInCart countView=helper.getView(R.id.cv_cart_good_count);
            EditText et=countView.getEt();
            et.setTag(testGood.getRecId());
            String id=testGood.getRecId();
            countView.setCount(Integer.parseInt(testGood.getGoodsNumber()));
            countView.setOnCountChangeListener(new CountViewInCart.OnCounChangeListener() {
                @Override
                public void requestChangeCount(final CountViewInCart countViewInCart, int oldCount, final int newCount) {

//                    ApiClient.getInstance().updateCart(testGood.getRecId(), newCount + "", new ResponseSubscriberTwo<CommonResponse>() {
//                        @Override
//                        protected void onRealSuccess(CommonResponse commonResponse) {
//                           countViewInCart.setCount(newCount);
//                            //暂时不了解这句话的作用
//                            testGood.setGoodsNumber(newCount+"");
//                            //这句代码的作用是重新计算总价钱，调接口
//                           mOnAllChangeListener.onCheckChange();
//                        }
//                        @Override
//                        protected void onOtherError(CommonResponse commonResponse) {
//                            super.onOtherError(commonResponse);
//                            ToastUtils.showToast("更新商品数量失败");
//                        }
//                        @Override
//                        public void onError(Throwable e) {
//                            super.onError(e);
//                        }
//                    });
                }
            });
            countView.setOnUpdateCartListener(new CountViewInCart.OnUpdateCartListener() {
                @Override
                public void updateCart(CountViewInCart countViews, final int oldCount, final int newCount) {
//                    ApiClient.getInstance().updateCart(testGood.getRecId(), newCount + "", new ResponseSubscriberTwo<CommonResponse>() {
//                        @Override
//                        protected void onRealSuccess(CommonResponse commonResponse) {
//                            countView.setCount(newCount);
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            super.onError(e);
//                            countView.setCount(oldCount);
//                        }
//
//                        @Override
//                        protected void onOtherError(CommonResponse commonResponse) {
//                            super.onOtherError(commonResponse);
//                            countView.setCount(oldCount);
//                        }
//                    });
                }
            });
            ImageLoader.with(CommonApplication.getContext()).load(testGood.getGoodsThumb()).into((ImageView) helper.getView(R.id.iv_cart_good_image));
            accb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int realTimePosition = helper.getLayoutPosition();
                    boolean isChecked = ((AppCompatCheckBox) v).isChecked();
                    testGood.setSelected(isChecked);
                    int shopPosition = getParentPosition(getItem(realTimePosition));
                    GetCartListVO.CartGoodsBean testShop =
                            (GetCartListVO.CartGoodsBean) getItem(shopPosition);
//                    Logger.d(helper.getLayoutPosition() + ": 改变了 " + isChecked);
                    // 如果点击之后是true
                    if (isChecked) {
                        List<CartShopEntity> testGoods
                                = testShop.getSubItems();
                        // 如果店名是 false
                        if (!testShop.isSelect()) {
                            Boolean shopSelected=true;
                            for (int i = 0; i < testGoods.size(); i++) {
                                if (!testGoods.get(i).isSelected()) {
                                    shopSelected=false;
                                }
                            }
                            if(shopSelected){
                                testShop.setSelect(true);
                                computeState(shopPosition);
                            }
//                            notifyItemChanged(shopPosition);
                        }
                    } else {
                        // 如果点击之后是 false
                        if (testShop.isSelect()) {
                            testShop.setSelect(false);
                            computeState(shopPosition);
//                            notifyItemChanged(shopPosition);
                        }
                    }
                    mOnAllChangeListener.onCheckChange();
                }
            });
        }else if(helper.getItemViewType()== CART_TYPE.PACKAGE.ordinal()){
            final CartShopEntity shopModel =
                    (CartShopEntity) getData().get(helper.getLayoutPosition());
            helper.setChecked(R.id.accb_cart_package_title,shopModel.isSelected());
            helper.setText(R.id.accb_cart_package_title,"");
            AppCompatCheckBox accb=helper.getView(R.id.accb_cart_package_title);
            accb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int realTimePosition = helper.getLayoutPosition();
                    boolean isChecked = ((AppCompatCheckBox) v).isChecked();
                    shopModel.setSelected(isChecked);
                    int shopPosition = getParentPosition(getItem(realTimePosition));
                    GetCartListVO.CartGoodsBean testShop =
                            (GetCartListVO.CartGoodsBean) getItem(shopPosition);
//                    Logger.d(helper.getLayoutPosition() + ": 改变了 " + isChecked);
                    // 如果点击之后是true
                    if (isChecked) {
                        List<CartShopEntity> testGoods
                                = testShop.getSubItems();
                        // 如果店名是 false
                        if (!testShop.isSelect()) {
                            Boolean shopSelected = true;
                            for (int i = 0; i < testGoods.size(); i++) {
                                if (!testGoods.get(i).isSelected()) {
                                    shopSelected = false;
                                }
                            }
                            if (shopSelected) {
                                testShop.setSelect(true);
                                computeState(shopPosition);
                            }
//                            notifyItemChanged(shopPosition);
                        }
                    } else {
                        // 如果点击之后是 false
                        if (testShop.isSelect()) {
                            testShop.setSelect(false);
                            computeState(shopPosition);
//                            notifyItemChanged(shopPosition);
                        }
                    }
                    mOnAllChangeListener.onCheckChange();
                }
            });
        }else if(helper.getItemViewType()== CART_TYPE.PACKAGE_GOOD.ordinal()){
            final CartGoodEntity goodModel =
                    (CartGoodEntity) getData().get(helper.getLayoutPosition());

            helper.addOnClickListener(R.id.iv_cart_good_image);
            helper.addOnClickListener(R.id.ll_item_cart_good_right);
            // 数据显示
            helper.setText(R.id.tv_cart_good_title, goodModel.getGoods_name())
                    .setText(R.id.tv_cart_good_per_price, "￥" + goodModel.getGoods_price())
                    .setText(R.id.tv_cart_good_spec,goodModel.getGoods_attr());
            final TextView countView=helper.getView(R.id.tv_cart_good_count);
            countView.setText("X"+ Integer.parseInt(goodModel.getGoods_number()));
            ImageLoader.with(CommonApplication.getContext()).load(goodModel.getGoods_thumb()).into((ImageView) helper.getView(R.id.iv_cart_good_image));
        }
    }

    public void computeState() {
        boolean all = compute();
        notifyDataSetChanged();
        mOnAllChangeListener.onAllChangeListener(all);
    }

    private boolean compute() {
        boolean all = true;
        List<MultiItemEntity> multiItems = getData();
        for (int i = 0; i < multiItems.size(); i++) {
            MultiItemEntity entity = multiItems.get(i);
            if (entity instanceof GetCartListVO.CartGoodsBean) {
                if (!((GetCartListVO.CartGoodsBean) entity).isSelect()) {
                    all = false;
                }
            }
        }
        return all;
    }

    private boolean computeHard() {
        boolean all = true;
        List<MultiItemEntity> multiItems = getData();
        for (int i = 0; i < multiItems.size(); i++) {
            MultiItemEntity entity = multiItems.get(i);
            if (entity instanceof CartShopEntity) {
                if (!((CartShopEntity) entity).isSelected()) {
                    all = false;
                }
            }
        }
        return all;
    }

    public void computeAll() {
        boolean all = computeHard();
        mOnAllChangeListener.onAllChangeListener(all);
    }

    public void computeShopAndAll() {
        computeShop();
        computeAll();
    }

    private void computeShop() {
        List<MultiItemEntity> multiItems = getData();
        for (int i = 0; i < multiItems.size(); i++) {
            MultiItemEntity entity = multiItems.get(i);
            if (entity instanceof GetCartListVO.CartGoodsBean) {
                // 如果每个 shop 里面的东西都 selected 那么该 shop 就必须要 selected
                List<CartShopEntity> testGoods =
                        ((GetCartListVO.CartGoodsBean) entity).getSubItems();
                boolean title = true;
                for (int j = 0; j < testGoods.size(); j++) {
                    if (!testGoods.get(j).isSelected()) {
                        title = false;
                    }
                }
                ((GetCartListVO.CartGoodsBean) entity).setSelect(title);
                notifyItemChanged(i);
            }
        }
    }

    public void computeState(int position) {
        boolean all = compute();
//        notifyDataSetChanged();
        notifyItemChanged(position);
        mOnAllChangeListener.onAllChangeListener(all);
    }

    public static interface OnAllChangeListener {
        void onAllChangeListener(boolean allBoolean);
        void onCheckChange();
    }

    private OnAllChangeListener mOnAllChangeListener;

    public void setOnAllChangeListener(OnAllChangeListener listener) {
        if (listener != null) {
            mOnAllChangeListener = listener;
        }
    }

}
