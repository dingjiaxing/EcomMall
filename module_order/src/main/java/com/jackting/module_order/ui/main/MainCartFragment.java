package com.jackting.module_order.ui.main;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.jackting.common.base.BaseFragment;
import com.jackting.common.data.config.ConfigDataEngine;
import com.jackting.common.util.PriceUtil;
import com.jackting.common.util.ToastUtil;
import com.jackting.common.widget.CountViewInCart;
import com.jackting.lib_router.module.user.UserIntent;
import com.jackting.lib_router.provider.IGoodsProvider;
import com.jackting.lib_router.provider.IOrderProvider;
import com.jackting.lib_router.provider.IUserProvider;
import com.jackting.lib_router.router.ServiceManager;
import com.jackting.module_order.R;
import com.jackting.module_order.R2;
import com.jackting.module_order.adapter.CartExpandableRVAdapter;
import com.jackting.module_order.bean.entity.CartGoodEntity;
import com.jackting.module_order.bean.entity.CartShopEntity;
import com.jackting.module_order.bean.vo.GetCartListVO;
import com.jackting.module_order.data.config.OrderConfigKeys;
import com.jackting.module_order.di.DaggerOrderComponent;
import com.jackting.module_order.ui.order_ensure.OrderEnsureActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jackting.common.CommonApplication.getAppComponent;

@Route(path = IOrderProvider.ORDER_CART_FRAGMENT)
public class MainCartFragment extends BaseFragment<MainCartPresenter> implements MainCartContract.View {


    MenuItem editItem;
    ViewGroup rootView;
    @BindView(R2.id.tv_cart_title)
    TextView tvCartTitle;
    @BindView(R2.id.tb_cart)
    Toolbar tbCart;
    @BindView(R2.id.rv_cart)
    RecyclerView rvCart;
    @BindView(R2.id.accb_cart_all)
    AppCompatCheckBox accbCartAll;
    @BindView(R2.id.btn_cart_calculate)
    Button btnCartCalculate;
    @BindView(R2.id.tv_cart_sum_price)
    TextView tvCartSumPrice;
    @BindView(R2.id.rl_fragment_cart_login)
    LinearLayout rlFragmentCartLogin;
    @BindView(R2.id.ll_fragment_cart_unlogin)
    LinearLayout llFragmentCartUnlogin;
    @BindView(R2.id.tv_cart_price_ship_alert)
    TextView tvShipAlert;
    @BindView(R2.id.tv_cart_edit)
    TextView tvEdit;
    @BindView(R2.id.rl_caculate)
    RelativeLayout rlCalculate;
    @BindView(R2.id.btn_fragment_cart_unlogin)
    Button toLogin;
    @BindView(R2.id.order_fragment_cart_status_bar)
    View statusView;

    private CartExpandableRVAdapter cartExpandableRVAdapter;
    private LinearLayoutManager linearLayoutManager;
    List<GetCartListVO.CartGoodsBean> goodsList;

    private ArrayList<MultiItemEntity> list = new ArrayList<>();

    private boolean showBack = false;

    GetCartListVO data;

//    @Inject
//    public MainOrderFragment() {
//    }

    public static MainCartFragment newInstance() {
        Bundle args = new Bundle();

        MainCartFragment fragment = new MainCartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.order_fragment_main_cart;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        showToolBar();
        initRv();

    }

    /**
     * 判断当前的登录状态
     */
    @Override
    public void onResume() {
        super.onResume();
        hideEditView();
        if(!ServiceManager.getInstance().getUserProvider().isLogin()){
            //未登录
            rlFragmentCartLogin.setVisibility(View.GONE);
            llFragmentCartUnlogin.setVisibility(View.VISIBLE);
        }else {
            //已登录
            rlFragmentCartLogin.setVisibility(View.VISIBLE);
            llFragmentCartUnlogin.setVisibility(View.GONE);
            presenter.getCartList();
        }
    }

    /**
     * 生命周期方法
     */
    @Override
    public void onPause() {
        super.onPause();
        hideEditView();
        saveSelectedStatus();
    }



    void initRv(){
        rvCart.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(getContext());
        rvCart.setLayoutManager(linearLayoutManager);
        cartExpandableRVAdapter = new CartExpandableRVAdapter(list);

        TextView textView = new TextView(getContext());
        Drawable drawable = getResources().getDrawable(R.drawable.order_img_no_red_package);


        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

        textView.setCompoundDrawables(null, drawable, null, null);
        textView.setCompoundDrawablePadding(20);
        textView.setPadding(50, 50, 0, 0);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setText("购物车为空，快去选商品吧~");

        cartExpandableRVAdapter.setEmptyView(textView);

        rvCart.addOnItemTouchListener(new OnItemLongClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildClick(adapter, view, position);
//                if (view.getId() == R.id.ll_item_cart_good_right || view.getId() == R.id.ll_item_cart_good_right) {
                int parentPosition = 0;
                Boolean isParent = false;
                parentPosition = cartExpandableRVAdapter.getParentPosition(cartExpandableRVAdapter.getItem(position));
                if (cartExpandableRVAdapter.getItem(parentPosition) instanceof GetCartListVO.CartGoodsBean) {
                    final GetCartListVO.CartGoodsBean testShop =
                            (GetCartListVO.CartGoodsBean) cartExpandableRVAdapter.getItem(parentPosition);
                    // 首先删除其中的子节点，再remove掉adapter中的，然后判断该父节点是否还有其他子节点了，没有就直接删掉父节点
                    if (cartExpandableRVAdapter.getItem(position) instanceof GetCartListVO.CartGoodsBean) {
                        //选中了供应商
                        isParent = true;
                        return;
                    } else if (cartExpandableRVAdapter.getItem(position) instanceof CartShopEntity) {
                        //选中了商品
                        final int childInParentPosition = testShop.getSubItemPosition((CartShopEntity) cartExpandableRVAdapter.getItem(position));
//                        ARouter.getInstance().build(IGoodsProvider.GOODS_DETAIL_ACTIVITY)
//                                .withString(GoodDetailKey.GOOD_ID_KEY, testShop.getGoodsList().get(childInParentPosition).getGoodsId())
//                                .navigation();
                    }
                } else if (cartExpandableRVAdapter.getItem(parentPosition) instanceof CartShopEntity) {
                    CartShopEntity cartShopModel = (CartShopEntity) cartExpandableRVAdapter.getItem(parentPosition);

                    //普通商品
//                    ARouter.getInstance().build(IGoodsProvider.GOODS_DETAIL_ACTIVITY)
//                            .withString(GoodDetailKey.GOOD_ID_KEY, cartShopModel.getGoodsId())
//                            .navigation();
                }


            }
//            }

            @Override
            public void onSimpleItemLongClick(BaseQuickAdapter adapter, View view, final int position) {
                int parentPosition = 0;
                Boolean isParent = false;
                parentPosition = cartExpandableRVAdapter.getParentPosition(cartExpandableRVAdapter.getItem(position));
                // 首先删除其中的子节点，再remove掉adapter中的，然后判断该父节点是否还有其他子节点了，没有就直接删掉父节点
                if (cartExpandableRVAdapter.getItem(position) instanceof GetCartListVO.CartGoodsBean) {
                    //选中了供应商
                    isParent = true;
                    return;
                } else if (cartExpandableRVAdapter.getItem(position) instanceof CartShopEntity) {
                    //选中了商品或者礼包
                    final CartShopEntity cartShopModel = (CartShopEntity) cartExpandableRVAdapter.getItem(position);
                    MaterialDialog materialDialog = new MaterialDialog.Builder(getContext())
                            .content("确定要删除吗？")
                            .positiveText("删除")
                            .negativeText("取消")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick( MaterialDialog dialog, DialogAction which) {
                                    String recId = cartShopModel.getRecId();
//                                    ApiClient.getInstance().deleteCart(recId, new ResponseSubscriberTwo<CommonResponse>() {
//                                        @Override
//                                        protected void onRealSuccess(CommonResponse commonResponse) {
//                                            judgeAndDeleteGood(position);
//                                        }
//                                    });
                                    judgeAndDeleteGood(position);
                                }
                            })
                            .show();
                } else if (cartExpandableRVAdapter.getItem(position) instanceof CartGoodEntity) {
                    //点击了礼包下面的商品
                    CartGoodEntity cartGoodModel = (CartGoodEntity) cartExpandableRVAdapter.getItem(position);
                    final int packagePosition = cartExpandableRVAdapter.getParentPosition(cartGoodModel);
                    final CartShopEntity cartShopModel = (CartShopEntity) cartExpandableRVAdapter.getItem(packagePosition);
                    final String recId = cartShopModel.getRecId();
                    MaterialDialog materialDialog = new MaterialDialog.Builder(getContext())
                            .content("确定要删除吗？")
                            .positiveText("删除")
                            .negativeText("取消")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog dialog,  DialogAction which) {
//                                    ApiClient.getInstance().deleteCart(recId, new ResponseSubscriberTwo<CommonResponse>() {
//                                        @Override
//                                        protected void onRealSuccess(CommonResponse commonResponse) {
//                                            judgeAndDeleteGood(packagePosition);
//                                        }
//                                    });
                                    judgeAndDeleteGood(packagePosition);
                                }
                            })
                            .show();
                }
            }
        });

        rvCart.setAdapter(cartExpandableRVAdapter);

        cartExpandableRVAdapter.setOnAllChangeListener(new CartExpandableRVAdapter.OnAllChangeListener() {
            @Override
            public void onAllChangeListener(boolean allBoolean) {
                accbCartAll.setChecked(allBoolean);
            }

            @Override
            public void onCheckChange() {
                computeSumPrice();
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
//            mActivity.onBackPressed();
        } else if (item.getItemId() == R.id.menu_cart_fragment_edit) {
            if (editItem != null) {
                if (editItem.getTitle().toString().equals("编辑")) {
                    showEditView();
                    editItem.setTitle("完成");
                } else if (editItem.getTitle().toString().equals("完成")) {
                    hideEditView();
                    editItem.setTitle("编辑");
                }
            }
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 标题栏设置
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void showToolBar() {
        tbCart.setTitle("");
//        getActivity().setSupportActionBar(tbCart);
        if (showBack) {
//            getActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setHasOptionsMenu(showBack);
        }
    }

    /**
     * 编辑
     */
    public void showEditView() {
        tvShipAlert.setVisibility(View.GONE);
        tvCartSumPrice.setVisibility(View.GONE);
        btnCartCalculate.setText("删除");
    }

    /**
     * 结算
     */
    public void hideEditView() {
        tvShipAlert.setVisibility(View.VISIBLE);
        tvCartSumPrice.setVisibility(View.VISIBLE);
        tvEdit.setText("编辑");
        btnCartCalculate.setText("结算");
    }

    @OnClick({R2.id.accb_cart_all,R2.id.btn_cart_calculate,R2.id.btn_fragment_cart_unlogin,R2.id.tv_cart_edit})
    public void onClick(View v) {
        if (v.getId() == R.id.accb_cart_all) {
            //点击全选按钮
            boolean isChecked = accbCartAll.isChecked();

            List<MultiItemEntity> entities = cartExpandableRVAdapter.getData();
            if (entities != null) {
                if (entities.size() > 0) {
                    for (int i = 0; i < entities.size(); i++) {
                        MultiItemEntity entity = entities.get(i);
                        if (entity instanceof GetCartListVO.CartGoodsBean) {
                            ((GetCartListVO.CartGoodsBean) entity).setSelect(isChecked);
                        } else if (entity instanceof CartShopEntity) {
                            ((CartShopEntity) entity).setSelected(isChecked);
                        }
                    }
//                        cartExpandableRVAdapter.notifyDataSetChanged();
                    cartExpandableRVAdapter.computeState();
                    computeSumPrice();
                }
            }

        } else if (v.getId() == R.id.btn_cart_calculate) {

            if (goodsList == null) {
                ToastUtil.showToast("请选择需要购买的商品");
                return;
            }
            StringBuilder sb = new StringBuilder();
            Boolean isNeedIdentify = false;
            for (int i = 0; i < goodsList.size(); i++) {
                for (int j = 0; j < goodsList.get(i).getGoodsList().size(); j++) {
                    CartShopEntity good = goodsList.get(i).getGoodsList().get(j);
                    if (good.isSelected()) {
                        sb.append("," + good.getRecId());
                    }
                    if (good.getNeedRealAuth().equals("1")) {
                        isNeedIdentify = true;
                    }
                }
            }
            String recIds = sb.toString();
            if (btnCartCalculate.getText().equals("结算")) {
                if (recIds.equals("")) {
                    ToastUtil.showToast("请选择需要购买的商品");
                } else {
                    recIds = recIds.substring(1);
                    Intent intent = new Intent(getContext(), OrderEnsureActivity.class);
//                    intent.putExtra(OrderEnsureActivity.ORDER_GOODS_PT_MODEL, JSON.toJSONString(new OrderGroupBuyPayDTO(false, "", false)));
                    intent.putExtra(OrderEnsureActivity.ORDER_INFO_KEY, recIds);
                    startActivity(intent);
                }
            } else if (btnCartCalculate.getText().toString().equals("删除")) {
                if (recIds.equals("")) {
                    ToastUtil.showToast("请选择需要删除的商品");
                } else {
                    recIds = recIds.substring(1);
                    saveSelectedStatus();
                    presenter.deleteCart(recIds);
                }
            }
        } else if (v.getId() == R.id.btn_fragment_cart_unlogin) {
            UserIntent.startLoginActivity();
        } else if (v.getId() == R.id.tv_cart_edit) {

            if (tvEdit.getText().toString().equals("编辑")) {
                showEditView();
                tvEdit.setText("完成");
            } else if (tvEdit.getText().toString().equals("完成")) {
                RelativeLayout item = (RelativeLayout) rvCart.getFocusedChild();
                if (item != null) {
                    CountViewInCart countViewInCart = (CountViewInCart) item.getFocusedChild();
                    if (countViewInCart != null) {
                        EditText editText = (EditText) countViewInCart.getFocusedChild();
                        if (editText != null) {
                            editText.clearFocus();
                        }
                    }
                }
                hideEditView();
                tvEdit.setText("编辑");
            }

        }
    }

    /**
     * 保存选中状态
     */
    public void saveSelectedStatus() {
        HashMap<String, Boolean> selectedMap1 = new HashMap<>();
        HashMap<String, Boolean> selectedMap2 = new HashMap<>();
        if (data == null) {
            return;
        }
        if (data != null) {
            if (data.getCartGoods() == null) {
                return;
            }
            for (int i = 0; i < data.getCartGoods().size(); i++) {
                if (data.getCartGoods().get(i).isSelect()) {
                    selectedMap1.put(data.getCartGoods().get(i).getSupplierName(), true);
                }

                for (int j = 0; j < data.getCartGoods().get(i).getGoodsList().size(); j++) {
                    if (data.getCartGoods().get(i).getGoodsList().get(j).isSelected()) {
                        selectedMap2.put(data.getCartGoods().get(i).getGoodsList().get(j).getRecId(), true);
                    }
                }
            }
        }
        ConfigDataEngine.putObject(OrderConfigKeys.CART_SUPPLIER_SELECTED_KEY, selectedMap1);
        ConfigDataEngine.putObject(OrderConfigKeys.CART_GOOD_SELECTED_KEY, selectedMap2);
        ConfigDataEngine.putObject(OrderConfigKeys.CART_ALL_SELECT_STATUS, accbCartAll.isChecked());
    }

    /*计算被选中商品的总价钱*/
    void computeSumPrice() {

        double sumPrice = 0;
        StringBuilder sb = new StringBuilder();
        if (data != null) {
            if (data.getCartGoods() != null) {
                for (int i = 0; i < data.getCartGoods().size(); i++) {
                    GetCartListVO.CartGoodsBean supplier = data.getCartGoods().get(i);
                    if (supplier != null) {
                        for (int j = 0; j < supplier.getGoodsList().size(); j++) {
                            CartShopEntity good = supplier.getGoodsList().get(j);
                            if (good.isSelected()) {
                                sb.append("," + good.getRecId());
                            }

                        }
                    }
                }
            }
        }
//        showSumPrice(sumPrice);
//        */
        String recIds = sb.toString().trim();
        if (recIds.equals("")) {
            showSumPrice(0);
        } else {
            recIds = recIds.substring(1);
            presenter.getShippingFee(recIds);
        }
    }

    public void showSumPrice(double price) {

        tvCartSumPrice.setText("合计:￥" + PriceUtil.getShowPrice(price));
    }



    @Override
    public void showLoading() {

    }

    /**
     * 如果删除了这个商品之后，该商家没有商品了，那么商家也要 remove 掉
     *
     * @param position 此字段为 item在adapter中的position
     */
    private void judgeAndDeleteGood(int position) {
//        Logger.json(JSON.toJSONString(cartExpandableRVAdapter.getData())); parentPosition为supllier在adapter中的位置
        int parentPosition = cartExpandableRVAdapter.getParentPosition(cartExpandableRVAdapter.getItem(position));
        GetCartListVO.CartGoodsBean testShop =
                (GetCartListVO.CartGoodsBean) cartExpandableRVAdapter.getItem(parentPosition);
        if (cartExpandableRVAdapter.getItem(position) instanceof CartShopEntity) {
            CartShopEntity cartShopModel = (CartShopEntity) cartExpandableRVAdapter.getItem(position);
            int modelPositionInShop = testShop.getSubItemPosition(cartShopModel);

            //普通商品
            cartExpandableRVAdapter.remove(position);
            testShop.removeSubItem(modelPositionInShop);


//            if (cartShopModel.getModel_type().equals("2")) {
//                //首先，如果是礼包的话
////                cartExpandableRVAdapter.notifyDataSetChanged();
//
////                cartShopModel.removeAllSubItem();
////                testShop.getModelList().get()
//
////                goodsList.get(parentPosition).getModelList().remove(cartShopPosition);
//                testShop.removeSubItem(modelPositionInShop);
//                cartExpandableRVAdapter.notifyItemRangeRemoved(position, cartShopModel.getGoods_list().size() + 1);
//                cartExpandableRVAdapter.remove(position);
//                for (int i = 0; i < cartShopModel.getGoods_list().size(); i++) {
//                    cartExpandableRVAdapter.remove(position);
//                }
////                cartExpandableRVAdapter.notifyDataSetChanged();
////                cartExpandableRVAdapter.getData().get(parentPosition).getItemType();
//            } else {
//                //普通商品
//                cartExpandableRVAdapter.remove(position);
//                testShop.removeSubItem(modelPositionInShop);
////                data.getCart_goods().get(parentPosition).getModelList().remove(cartShopPosition);
////                goodsList.get(parentPosition).getModelList().remove(cartShopPosition);
//
////                cartExpandableRVAdapter.notifyDataSetChanged();
//            }
//            rvCart.notifyAll();
        } else {
            // 首先删除其中的子节点，再remove掉adapter中的，然后判断该父节点是否还有其他子节点了，没有就直接删掉父节点
            int childInParentPosition =
                    testShop.getSubItemPosition((CartShopEntity) cartExpandableRVAdapter.getItem(position));
//        Logger.d(childInParentPosition);

            cartExpandableRVAdapter.remove(position);
            testShop.removeSubItem(childInParentPosition);
        }
//        testShop.removeSubItem()
//        testShop.get
//        testShop.removeSubItem((TestGood) cartExpandableRVAdapter.getItem(position));
        if (testShop.getSubItems().size() == 0) {
            // 直接删掉父节点
            cartExpandableRVAdapter.remove(parentPosition);

            // 根据所有的 子项来勾全选
            cartExpandableRVAdapter.computeAll();
        } else {
            // 根据所有的子项来勾全选和父项
            cartExpandableRVAdapter.computeShopAndAll();
        }
        computeSumPrice();
        // 判断一下，有可能会出现这种情况，除了该项以外，所有的都是选中的，所以要将父项和全选选中
//        cartExpandableRVAdapter.computeState();
//        Logger.json(JSON.toJSONString(cartExpandableRVAdapter.getData()));
//        cartExpandableRVAdapter.getItem(position)

    }

    public void daggerInit() {
        DaggerOrderComponent.builder()
                .appComponent(getAppComponent())
                .build()
                .inject(this);
    }

}
