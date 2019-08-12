package com.jackting.common.widget;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jackting.common.R;

import java.io.Serializable;


/**
 * Created by ${guodandan} on 2017/9/28.
 */

public class CountViewInCart extends LinearLayout {
    private float mDensity;
    private int mCount;
    private int mMin;
    private ImageView mtvSub;
    private ImageView mtvAdd;
    private EditText mtvCount;
    private static UpdateCartMessage updateCartMessage = new UpdateCartMessage();
    OnCounChangeListener listener;
    OnUpdateCartListener updateListener;

    public CountViewInCart(Context context) {
        this(context, (AttributeSet) null);
    }

    public CountViewInCart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountViewInCart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mCount = 1;
        this.mMin = 1;
        this.init();
    }

    public void setMin(int min) {
        this.mMin = min;
    }

    public void setCount(int count) {
        this.mCount = count;
        mtvCount.setText(mCount + "");
    }

    public int getCount() {
        return this.mCount;
    }

    public EditText getEt() {
        return mtvCount;
    }

    ;

    private void init() {
//        com.gaohuiyu.lib.R.drawable.count_view_sub_back=======count_view_sub
        this.mDensity = this.getResources().getDisplayMetrics().density;
        this.setGravity(16);
        this.mtvSub = new ImageView(this.getContext());
        this.mtvSub.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.common_count_view_sub_back));
        this.mtvSub.setMinimumWidth((int) (this.mDensity * 27.0F));
        this.mtvSub.setMinimumHeight((int) (this.mDensity * 27.0F));
        this.mtvSub.setImageResource(R.drawable.common_count_view_sub);
        this.mtvSub.setPadding((int) this.mDensity * 8, (int) this.mDensity * 8, (int) this.mDensity * 8, (int) this.mDensity * 8);
        this.mtvSub.setEnabled(true);
        this.mtvSub.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (TextUtils.isEmpty(CountViewInCart.this.mtvCount.getText())) {
                    return;
                }
                if (mCount > mMin) {
//                    CountViewInCart.this.mtvCount.setText(String.valueOf(--mCount));
                    if (listener != null) {

                        listener.requestChangeCount(CountViewInCart.this, mCount, mCount-1);
                        CountViewInCart.this.mtvSub.setEnabled(true);
                    }
                } else {
                    CountViewInCart.this.mtvSub.setEnabled(false);
                }
//                mtvCount.setText(String.valueOf(--mCount));
//                if(CountViewInCart.this.mCount <= CountViewInCart.this.mMin) {
//                    CountViewInCart.this.mtvSub.setEnabled(false);
//                }

            }
        });
        InputFilter[] filters = {new InputFilter.LengthFilter(6)};
        this.mtvCount = new EditText(this.getContext());
        this.mtvCount.setFilters(filters);
        this.mtvCount.setText(String.valueOf(this.mCount));
        this.mtvCount.setTextSize(13.0F);
        this.mtvCount.setWidth((int) this.mDensity * 34);
        this.mtvCount.setHeight((int) (this.mDensity * 27.0F));

        this.mtvCount.setTextColor(Color.parseColor("#4D4D4D"));
        this.mtvCount.setBackgroundColor(Color.parseColor("#F5F5F5"));
        this.mtvCount.setGravity(17);
        this.mtvCount.setPadding(0, 0, 0, 0);
        this.mtvCount.setInputType(InputType.TYPE_CLASS_NUMBER);
        this.mtvCount.setSelection(1);
        this.mtvCount.setFocusableInTouchMode(true);
        final LayoutParams lpCount = new LayoutParams(-2, -2);
        lpCount.setMargins((int) this.mDensity, 0, (int) this.mDensity, 0);
        this.mtvCount.setLayoutParams(lpCount);
        this.mtvAdd = new ImageView(this.getContext());
        this.mtvAdd.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.common_count_view_sub_back));
        this.mtvAdd.setMinimumHeight((int) (this.mDensity * 27.0F));
        this.mtvAdd.setImageResource(R.drawable.common_count_view_add);
        this.mtvAdd.setPadding((int) this.mDensity * 8, (int) this.mDensity * 8, (int) this.mDensity * 8, (int) this.mDensity * 8);
        this.mtvAdd.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (TextUtils.isEmpty(CountViewInCart.this.mtvCount.getText())) {
                    return;
                }
//                CountViewInCart.this.mtvCount.setText(String.valueOf(++mCount));
                if (listener != null) {

                    listener.requestChangeCount(CountViewInCart.this, mCount, mCount+1);
                    CountViewInCart.this.mtvSub.setEnabled(true);
                }
//                mtvCount.setText(String.valueOf(++mCount));
//                if(CountViewInCart.this.mCount > CountViewInCart.this.mMin) {
//                    CountViewInCart.this.mtvSub.setEnabled(true);
//                }
            }
        });
        this.mtvCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                String tag = (String) CountViewInCart.this.mtvCount.getTag();
                String texts = editable.toString();

                if (!TextUtils.isEmpty(texts)) {
                    if ("0".equals(texts)) {
                        CountViewInCart.this.mtvCount.setText("1");
                    }
                    mCount = Integer.parseInt(texts);
                    CountViewInCart.this.mtvSub.setEnabled(true);
                    CountViewInCart.this.mtvCount.setSelection(texts.length());

                    updateCartMessage.setRec_id(tag);
                    updateCartMessage.setCount(texts);
//                    Prefs.putObject("updateCartMessage", updateCartMessage);

                    if (mCount > mMin) {
                        if (listener != null) {
//                            listener.requestChangeCount(CountViewInCart.this, mCount, mCount);
                            CountViewInCart.this.mtvSub.setEnabled(true);
                        }
                    } else {
                        CountViewInCart.this.mtvSub.setEnabled(false);
                    }
                }

            }
        });
        this.mtvCount.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                EditText editText = (EditText) view;
                if (!b) {
//                            ToastUtils.showToast(view.getTag()+"对应的输入框失去焦点了");
                    if (updateListener != null) {

                        updateListener.updateCart(CountViewInCart.this, Integer.parseInt(updateCartMessage.getOldCount()), mCount);
                    }
                } else {
//                            ToastUtils.showToast(view.getTag()+"对应的输入框得到焦点了");
                    updateCartMessage.setOldCount(mtvCount.getText().toString());

                }
            }
        });
        ViewTreeObserver vto = this.mtvCount.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mtvCount.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int heights = mtvSub.getMeasuredHeight();
                lpCount.height = heights;
                CountViewInCart.this.mtvCount.setLayoutParams(lpCount);
            }
        });
        this.addView(this.mtvSub);
        this.addView(this.mtvCount);
        this.addView(this.mtvAdd);


    }

    public void setOnCountChangeListener(OnCounChangeListener listener) {
        this.listener = listener;
    }

    public void setOnUpdateCartListener(OnUpdateCartListener listener) {
        this.updateListener = listener;
    }

    public interface OnCounChangeListener {
        void requestChangeCount(CountViewInCart countView, int oldCount, int newCount);
    }

    public interface OnUpdateCartListener {
        void updateCart(CountViewInCart countViewInchat, int oldCount, int newCount);
    }

    public static class UpdateCartMessage implements Serializable {
        private String rec_id;
        private String count;
        private String oldCount;

        public String getOldCount() {
            return oldCount;
        }

        public void setOldCount(String oldCount) {
            this.oldCount = oldCount;
        }

        public String getRec_id() {
            return rec_id;
        }

        public void setRec_id(String rec_id) {
            this.rec_id = rec_id;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }
    }

}

