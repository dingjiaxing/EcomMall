package com.jackting.module_goods.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jackting.module_goods.R;
import com.jackting.module_goods.bean.entity.CategoryEntity;

import java.util.List;

/**
 * Created by admin on 2017/2/10.
 */

public class CategoryLeftAdapter extends BaseQuickAdapter<CategoryEntity,BaseViewHolder> {

    private int selectedPosition = 0;

    public CategoryLeftAdapter(List<CategoryEntity> data) {
        super(R.layout.goods_item_category_left, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryEntity item) {
        helper.setText(R.id.tv_category_left_title, item.catAppName);

        int layoutPosition = helper.getLayoutPosition();
        
        helper.getConvertView().setSelected(selectedPosition == layoutPosition);

        View view=helper.getConvertView();
        TextView textView=(TextView) view.findViewById(R.id.tv_category_left_title);
        if(selectedPosition == layoutPosition) {
            textView.setSelected(true);
        }else{
            textView.setSelected(false);
        }
    }

    public void select(int position) {
        if (selectedPosition != position) {
//            Logger.d(position);
            selectedPosition = position;
            notifyDataSetChanged();
        }
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

}
