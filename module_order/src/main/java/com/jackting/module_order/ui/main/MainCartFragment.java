package com.jackting.module_order.ui.main;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jackting.common.base.BaseFragment;
import com.jackting.lib_router.provider.IOrderProvider;
import com.jackting.module_order.R;

@Route(path = IOrderProvider.ORDER_CART_FRAGMENT)
public class MainCartFragment extends BaseFragment<MainCartPresenter> implements MainCartContract.View {


//    @BindView(R2.id.btn_login)
//    Button btnLogin;

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
        initRv();

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ServiceManager.getInstance().getUserProvider().startLoginActivity();
//            }
//        });
    }

    void initRv(){
//        adapter=new HomeArticleAdapter(articleList);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity,RecyclerView.VERTICAL,false));
//
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(
//                        new Intent(mActivity,WebActivity.class)
//                                .putExtra(WebActivity.URL_KEY,articleList.get(position).link)
//                );
//            }
//        });
    }



    @Override
    public void showLoading() {

    }

//    @OnClick({R2.id.btn_login})
//    void doClick(View view){
//        if(view.getId()==R.id.btn_login){
//            ServiceManager.getInstance().getUserProvider().startLoginActivity();
////            ARouter.getInstance().build(IUserProvider.USER_PROFILE_LOGIN_ACTIVITY).navigation();
//        }
//    }
}
