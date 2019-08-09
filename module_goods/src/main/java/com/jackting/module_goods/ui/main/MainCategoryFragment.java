package com.jackting.module_goods.ui.main;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.jackting.common.base.BaseFragment;
import com.jackting.lib_router.provider.IGoodsProvider;
import com.jackting.module_goods.R;
import com.jackting.module_goods.R2;
import com.jackting.module_goods.adapter.CategoryLeftAdapter;
import com.jackting.module_goods.adapter.delegate.CategoryContentDelegate;
import com.jackting.module_goods.adapter.delegate.CategoryTitleDelegate;
import com.jackting.module_goods.bean.entity.CategoryEntity;
import com.jackting.module_goods.data.test.TestDataEngine;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = IGoodsProvider.GOODS_CATEGORY_FRAGMENT)
public class MainCategoryFragment extends BaseFragment<MainCategoryPresenter> implements MainCategoryContract.View {


    CategoryLeftAdapter leftAdapter;
    MultiItemTypeAdapter rightAdapter;
    @BindView(R2.id.rv_category_fragment_left)
    RecyclerView rvLeft;
    @BindView(R2.id.rv_category_fragment_right)
    RecyclerView rvRight;

    private List<CategoryEntity> leftList = new ArrayList<>();
    private List<CategoryEntity> rightList = new ArrayList<>();

    private GridLayoutManager gridLayoutManager;
    private LinearLayoutManager linearLayoutManager;
    private int mCurrentLeftPosition=0;

//    @Inject
//    public MainCommunityFragment() {
//    }

    public static MainCategoryFragment newInstance() {
        Bundle args = new Bundle();

        MainCategoryFragment fragment = new MainCategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.goods_fragment_main_category;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        initRv();
        initTestData();

    }

    void initRv(){

        /*left adapter*/
        rvLeft.setHasFixedSize(true);
        leftAdapter = new CategoryLeftAdapter(leftList);
        rvLeft.setAdapter(leftAdapter);
        linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
        rvLeft.setLayoutManager(linearLayoutManager);
//        rvLeft.addItemDecoration(
//                new HorizontalDividerItemDecoration.Builder(mContext)
//                        .color(getResources().getColor(R.color.goods_category_search_title_bg))
//                        .size(JackUtils.dip2px(0.5f))
//                        .margin(0, 0)
//                        .build());
        rvLeft.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                mCurrentLeftPosition = position;
                leftAdapter.select(position);
                if (position!=leftList.size()-1){
                    // 右边的跳到相应的地方
                    String catId = leftList.get(position).catId;
                    for (int i = 0; i < rightList.size(); i++) {
                        if (rightList.get(i) instanceof CategoryEntity) {

                            if (catId.equals((((CategoryEntity) rightList.get(i)).catId))) {
//                            categoryRightLayoutManager.smoothScrollToPosition();
                                gridLayoutManager.scrollToPositionWithOffset(i, 0);
                            }
                        }
                    }
                }else{
                    gridLayoutManager.scrollToPositionWithOffset(rightList.size()-1, 0);
                }


            }
        });

        /*right adapter*/
        rightAdapter = new MultiItemTypeAdapter(getContext(), rightList);
        rightAdapter.addItemViewDelegate(new CategoryTitleDelegate());
        rightAdapter.addItemViewDelegate(new CategoryContentDelegate(getActivity()));
        rvRight.setAdapter(rightAdapter);
//        rvRight.setLayoutManager(gridLayoutManager);
        gridLayoutManager = new GridLayoutManager(getContext(), 3);

//        rvRight.addItemDecoration(
//                new HorizontalDividerItemDecoration.Builder(mActivity)
//                        .color(getResources().getColor(R.color.windowBackground))
//                        .size(JackUtils.dip2px(1))
//                        .margin(JackUtils.dip2px(10),JackUtils.dip2px(5))
//                        .build());
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return rightList.get(position).rank==1  ? 3 : 1;
            }
        });
        rvRight.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // 找到第一个头
                String leftCatId = findFirstSection();
//                Log.d("test", leftCatId + ":");
                // 通过第一个头的 cat_id ，使左边的 rv select,而且如果看不到左边的item了，滑到能看到的地方
                if (leftCatId == null) {
                    return;
                }

                selectLeftAdapterByCatId(leftCatId);
            }
        });
        rvRight.setLayoutManager(gridLayoutManager);

    }

    private String findFirstSection() {
        int childCount = rvRight.getChildCount();
        int firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();
        for (int i = firstVisibleItemPosition; i < firstVisibleItemPosition + childCount; i++) {
            if (rightList.get(i) instanceof CategoryEntity) {
                return ((CategoryEntity) rightList.get(i)).catId;
            }
        }
        return null;
    }

    /**
     * 通过cartId定位左边的item
     *
     * @param catId
     */
    private void selectLeftAdapterByCatId(String catId) {
        for (int i = 0; i < leftList.size(); i++) {


            if (leftList.get(i).catId.equals(catId)) {

                int leftListSize=leftList.size();

                //当左边的item个数大于3的时候，对后三个item进行特殊操作。因为最后 一个item对应的页面和倒数第二个item对应的右边的recyclerView可能是同一个位置
                if (leftListSize>=3){
                    if (mCurrentLeftPosition==leftList.size()-1){
                        leftAdapter.select(mCurrentLeftPosition);
                        letLeftSelectedItemVisible(mCurrentLeftPosition);
                        mCurrentLeftPosition=0;
                        return;
                    }else if (mCurrentLeftPosition==leftList.size()-2){
                        leftAdapter.select(mCurrentLeftPosition);
                        letLeftSelectedItemVisible(mCurrentLeftPosition);
                        mCurrentLeftPosition=0;
                        return;
                    }else if (mCurrentLeftPosition==leftList.size()-3){
                        leftAdapter.select(mCurrentLeftPosition);
                        letLeftSelectedItemVisible(mCurrentLeftPosition);
                        mCurrentLeftPosition=0;
                        return;
                    }else{
                        leftAdapter.select(i);
                        letLeftSelectedItemVisible(i);
                        return;
                    }
                }else if (leftListSize==2){
                    if (mCurrentLeftPosition==leftList.size()-1){
                        leftAdapter.select(mCurrentLeftPosition);
                        letLeftSelectedItemVisible(mCurrentLeftPosition);
                        mCurrentLeftPosition=0;
                        return;
                    }else if (mCurrentLeftPosition==leftList.size()-2){
                        leftAdapter.select(mCurrentLeftPosition);
                        letLeftSelectedItemVisible(mCurrentLeftPosition);
                        mCurrentLeftPosition=0;
                        return;
                    }else{
                        leftAdapter.select(i);
                        letLeftSelectedItemVisible(i);
                        return;
                    }
                } else {
                    leftAdapter.select(i);
                    letLeftSelectedItemVisible(i);
                    return;
                }

//                if (mCurrentLeftPosition!=leftList.size()-1){
//                    leftAdapter.select(i);
//                    letLeftSelectedItemVisible(i);
//                    return;
//                }else{
//                    leftAdapter.select(mCurrentLeftPosition);
//                    letLeftSelectedItemVisible(mCurrentLeftPosition);
//                    mCurrentLeftPosition=0;
//                    return;
//                }

            }
        }
    }

    /**
     * @param position
     */
    private void letLeftSelectedItemVisible(int position) {
        // 如果能看到就不动了
        int firstPosition = gridLayoutManager.findFirstCompletelyVisibleItemPosition();
        int lastPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
        // 不能看到就移动到那一项
        if (firstPosition <= position && position <= lastPosition) {
            // 可以看到
            return;
        }
//        categoryLeftLayoutManager.smoothScrollToPosition();
        rvLeft.smoothScrollToPosition(position);
    }




    @Override
    public void showLoading() {

    }

    @OnClick({R2.id.rl_category_scan})
    void doScan(){

    }

    void initTestData(){
        TestDataEngine.CategoryTestData testData = TestDataEngine.getCategoryData();

        leftList.addAll(testData.left);

        rightList.addAll(testData.right);

        leftAdapter.notifyDataSetChanged();
        rightAdapter.notifyDataSetChanged();
    }
}
