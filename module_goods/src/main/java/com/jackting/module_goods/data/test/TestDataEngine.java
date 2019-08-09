package com.jackting.module_goods.data.test;

import android.content.Context;
import android.content.res.AssetManager;

import com.alibaba.fastjson.JSON;
import com.jackting.common.CommonApplication;
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

        String json = getJson("category.json", CommonApplication.getContext());

        categoryTestData = JSON.parseObject(json,CategoryTestData.class);

        return categoryTestData;
    }

    public static class CategoryTestData implements Serializable {
        public List<CategoryEntity> left;
        public List<CategoryEntity> right;
    }


    public static String getJson(String fileName, Context context) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }


}
