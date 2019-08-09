package com.jackting.module_main.ui.main.home;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jackting.common.base.BaseFragment;
import com.jackting.common.data.img.ImageLoader;
import com.jackting.lib_router.provider.IGoodsProvider;
import com.jackting.lib_router.provider.IMainProvider;
import com.jackting.lib_router.provider.IMsgProvider;
import com.jackting.lib_router.router.ModuleRouter;
import com.jackting.module_main.R;
import com.jackting.module_main.R2;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MainHomeFragment extends BaseFragment<MainHomePresenter> implements MainHomeContract.View {

//    @BindView(R.id.recyclerView)
//    RecyclerView recyclerView;

//    HomeArticleAdapter adapter;
//    List<Article> articleList=new ArrayList<>();
    @BindView(R2.id.banner)
    Banner banner;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;

    List<Object> bannerImgList = new ArrayList<>();
    List<String> bannerTitleList = new ArrayList<>();

    @Inject
    public MainHomeFragment() {
    }


    @Override
    public int getContentViewResId() {
        return R.layout.main_fragment_main_home;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        initTestData();
        initBanner();
        initRefreshLoadMore();
    }

    void initBanner(){
        banner.setImageLoader(new com.youth.banner.loader.ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                ImageLoader.with(context).load(path).into(imageView);
//                Glide.with(context).load(path).into(imageView);
            }
        });
        banner.setImages(bannerImgList);

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(bannerTitleList);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);

        banner.start();
    }

    void initTestData(){
//        bannerImgList.add("https://github.com/dingjiaxing/EcomMall/blob/master/doc/img/banner/main_test_banner_1.jpg");
//        bannerImgList.add("https://github.com/dingjiaxing/EcomMall/blob/master/doc/img/banner/main_test_banner_2.jpg");
//        bannerImgList.add("https://github.com/dingjiaxing/EcomMall/blob/master/doc/img/banner/main_test_banner_3.jpg");
        bannerImgList.add(R.drawable.main_test_banner_1);
        bannerImgList.add(R.drawable.main_test_banner_2);
        bannerImgList.add(R.drawable.main_test_banner_3);
        bannerImgList.add(R.drawable.main_test_banner_3);
        bannerImgList.add(R.drawable.main_test_banner_3);

        bannerTitleList.add("年终大促");
        bannerTitleList.add("积分兑换");
        bannerTitleList.add("日韩爆款");
    }

    void initRefreshLoadMore(){
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
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

    @OnClick(R2.id.ll_home_scan)
    public void clickScan(){
        ModuleRouter.newInstance(IMainProvider.SCAN_ACTIVITY).navigation();
    }
    @OnClick(R2.id.ll_home_msg)
    public void clickMsg(){
        ModuleRouter.newInstance(IMsgProvider.MSG_LIST_ACTIVITY).navigation();
    }
    @OnClick(R2.id.fl_home_top_search)
    public void clickSearch(){
        ModuleRouter.newInstance(IGoodsProvider.GOODS_SEARCH_ACTIVITY).navigation();
    }
}
