package com.jackting.module_goods.adapter.delegate;

import com.jackting.module_goods.R;
import com.jackting.module_goods.bean.entity.CategoryEntity;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by Administrator on 2017/6/7.
 */

public class CategoryTitleDelegate implements ItemViewDelegate<CategoryEntity> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.goods_item_category_right_title;
    }

    @Override
    public boolean isForViewType(CategoryEntity item, int position) {
        return item.rank==1;
    }

    @Override
    public void convert(ViewHolder holder, CategoryEntity categoryEntity, int position) {
        holder.setText(R.id.tv_goods_item_category_right_title,categoryEntity.catAppName);
    }

}
