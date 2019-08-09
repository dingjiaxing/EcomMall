package com.jackting.module_goods.adapter.delegate;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.jackting.common.data.img.ImageLoader;
import com.jackting.module_goods.R;
import com.jackting.module_goods.bean.entity.CategoryEntity;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by Administrator on 2017/6/8.
 */

public class CategoryContentDelegate implements ItemViewDelegate<CategoryEntity> {
    Activity mActivity;

    public CategoryContentDelegate(Activity activity) {
        this.mActivity = activity;
    }
    @Override
    public int getItemViewLayoutId() {
        return R.layout.goods_item_category_right_content;
    }

    @Override
    public boolean isForViewType(CategoryEntity item, int position) {
        return item.rank!=1;
    }

    @Override
    public void convert(ViewHolder holder, CategoryEntity item, int position) {
        ImageLoader.with(this.mActivity)
                .load(item.typeImg)
                .into((ImageView) holder.getView(R.id.iv_goods_category_right_content));
        holder.setText(R.id.tv_goods_category_right_content,
                item.catAppName);
        final ImageView iv=holder.getView(R.id.iv_goods_category_right_content);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                iv.getContext().startActivity(new Intent(iv.getContext(), SearchActivity.class).putExtra("cateId", Integer.valueOf(item.getCatId())));
            }
        });
    }


}
