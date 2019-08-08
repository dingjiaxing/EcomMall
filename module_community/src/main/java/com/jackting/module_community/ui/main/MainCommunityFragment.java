package com.jackting.module_community.ui.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jackting.common.base.BaseFragment;
import com.jackting.lib_router.provider.ICommunityProvider;
import com.jackting.lib_router.router.ServiceManager;
import com.jackting.module_community.R;
import com.jackting.module_community.R2;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = ICommunityProvider.MAIN_COMMUNITY_FRAGMENT)
public class MainCommunityFragment extends BaseFragment<MainCommunityPresenter> implements MainCommunityContract.View {


//    @BindView(R2.id.btn_login)
//    Button btnLogin;

//    @Inject
//    public MainCommunityFragment() {
//    }

    public static MainCommunityFragment newInstance() {
        Bundle args = new Bundle();

        MainCommunityFragment fragment = new MainCommunityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.community_fragment_main_community;
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
