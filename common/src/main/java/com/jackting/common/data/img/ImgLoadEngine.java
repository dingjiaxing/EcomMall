package com.jackting.common.data.img;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;

import com.bumptech.glide.Glide;

/**
 * 图片加载引擎
 * 此包装类的目的在于 可以灵活切换glide、picasso等而不需要大量修改代码，
 * 仅修改当前类的into方法即可
 */
public class ImgLoadEngine {

    public static RequestBuilder with(Context context){
        return new RequestBuilder(context);
    }

    public static class RequestBuilder{
        Context context;
        Object imgPath;
        @DrawableRes
        int placeHolderResId;
        Drawable placeHolderDrawable;

        public RequestBuilder(Context context) {
            this.context = context;
        }

        public  RequestBuilder load(Object imgPath){
            this.imgPath = imgPath;
            return this;
        }

        public  RequestBuilder placeholder(int resId){
            this.placeHolderResId = placeHolderResId;
            return this;
        }

        public  RequestBuilder placeholder(Drawable placeHolderDrawable){
            this.placeHolderDrawable = placeHolderDrawable;
            return this;
        }

        public  void into(ImageView imageView){
            Glide.with(context)
                    .load(imgPath)
                    .placeholder(placeHolderDrawable)
                    .placeholder(placeHolderResId)
                    .into(imageView);
        }

    }

}
