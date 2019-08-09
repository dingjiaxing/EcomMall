package com.jackting.module_goods.bean.entity;


import java.io.Serializable;
import java.util.List;

/**
 * Created by ${guodandan} on 2017/8/23.
 */

public class CategoryEntity implements Serializable {

    /**
     * cat_id : 5
     * cat_app_name : 四位名称
     * type_img : images/201701/1484787387500244600.png
     * child : [{"cat_id":"182","cat_app_name":"四位名称","type_img":"images/201701/1484787387500244600.png"},{"cat_id":"179","cat_app_name":"四位名称","type_img":"images/201701/1484787387500244600.png"},{"cat_id":"181","cat_app_name":"四位名称","type_img":"images/201701/1484787387500244600.png"},{"cat_id":"180","cat_app_name":"四位名称","type_img":"images/201701/1484787387500244600.png"},{"cat_id":"178","cat_app_name":"四位名称","type_img":"images/201701/1484787387500244600.png"},{"cat_id":"367","cat_app_name":"四位名称","type_img":"images/201701/1484787387500244600.png"},{"cat_id":"371","cat_app_name":"四位名称","type_img":"images/201701/1484787387500244600.png"},{"cat_id":"471","cat_app_name":"四位名称","type_img":"images/201701/1484787387500244600.png"},{"cat_id":"497","cat_app_name":"四位名称","type_img":"images/201701/1484787387500244600.png"},{"cat_id":"537","cat_app_name":"四位名称","type_img":"images/201701/1484787387500244600.png"},{"cat_id":"498","cat_app_name":"四位名称","type_img":"images/201701/1484787387500244600.png"},{"cat_id":"491","cat_app_name":"四位名称","type_img":"images/201701/1484787387500244600.png"},{"cat_id":"454","cat_app_name":"四位名称","type_img":"images/201701/1484787387500244600.png"},{"cat_id":"615","cat_app_name":"四位名称","type_img":"images/201701/1484787387500244600.png"}]
     */

    public String catId;
    public String catAppName;
    public String typeImg;
    // 级别，1时为大类，2时为小类
    public int rank;


}
