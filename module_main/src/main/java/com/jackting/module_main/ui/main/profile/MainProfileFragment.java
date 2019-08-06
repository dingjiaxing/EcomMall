package com.jackting.module_main.ui.main.profile;

import android.os.Bundle;

import com.jackting.common.base.BaseFragment;
import com.jackting.module_main.R;

import javax.inject.Inject;

public class MainProfileFragment extends BaseFragment<MainProfilePresenter> implements MainProfileContract.View {

//    @BindView(R.id.recyclerView)
//    RecyclerView recyclerView;

//    HomeArticleAdapter adapter;
//    List<Article> articleList=new ArrayList<>();

    @Inject
    public MainProfileFragment() {
    }


    @Override
    public int getContentViewResId() {
        return R.layout.fragment_main_profile;
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }

    protected void init() {
        initRv();
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
