package com.jackting.module_goods.data.test;

import android.content.Context;
import android.content.res.AssetManager;

import com.alibaba.fastjson.JSON;
import com.jackting.common.CommonApplication;
import com.jackting.common.util.AssetUtil;
import com.jackting.module_goods.bean.entity.CategoryEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.List;

public class TestDataEngine {

    String categoryJson="";

    public static CategoryTestData getCategoryData(){
        CategoryTestData categoryTestData = null;
//        List<CategoryEntity> list = new ArrayList<>();

        String json = AssetUtil.getJson("category.json", CommonApplication.getContext());

        categoryTestData = JSON.parseObject(json,CategoryTestData.class);

        return categoryTestData;
    }

    public static class CategoryTestData implements Serializable {
        public List<CategoryEntity> left;
        public List<CategoryEntity> right;
    }





}
