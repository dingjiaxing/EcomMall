package com.jackting.module_main.ui.main.home;

import android.os.Bundle;


import com.jackting.common.base.BaseFragment;
import com.jackting.module_main.R;

import javax.inject.Inject;

public class MainHomeFragment extends BaseFragment<MainHomePresenter> implements MainHomeContract.View {

//    @BindView(R.id.recyclerView)
//    RecyclerView recyclerView;

//    HomeArticleAdapter adapter;
//    List<Article> articleList=new ArrayList<>();

    @Inject
    public MainHomeFragment() {
    }


    @Override
    public int getContentViewResId() {
        return R.layout.main_fragment_main_home;
    }

    @Override
    public void init(Bundle savedInstanceState) {

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
}
