package com.jackting.module_user.ui.profile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jackting.common.CommonApplication;
import com.jackting.common.base.BaseFragment;
import com.jackting.lib_router.module.user.UserIntent;
import com.jackting.lib_router.provider.IUserProvider;
import com.jackting.lib_router.router.ServiceManager;
import com.jackting.module_user.R;
import com.jackting.module_user.R2;
import com.jackting.module_user.di.component.DaggerUserComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = IUserProvider.USER_PROFILE_FRAGMENT)
public class MainProfileFragment extends BaseFragment<MainProfilePresenter> implements MainProfileContract.View {

//    @BindView(R.id.recyclerView)
//    RecyclerView recyclerView;

//    HomeArticleAdapter adapter;
//    List<Article> articleList=new ArrayList<>();
//    @BindView(R2.id.btn_login)
//    Button btnLogin;

    @Inject
    public MainProfileFragment() {
    }

    public static MainProfileFragment newInstance() {

        Bundle args = new Bundle();

        MainProfileFragment fragment = new MainProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getContentViewResId() {
        return R.layout.user_fragment_main_profile;
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

    @Override
    public void daggerInit() {
        DaggerUserComponent.builder()
                .appComponent(CommonApplication.getAppComponent())
                .build()
                .inject(this);
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
//            UserIntent.startLoginActivity();
////            ARouter.getInstance().build(IUserProvider.USER_PROFILE_LOGIN_ACTIVITY).navigation();
//        }
//    }
}
