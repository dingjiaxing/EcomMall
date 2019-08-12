package com.jackting.module_order.bean.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */

public class CartActivityEntity implements Serializable {
    private String discount;
    private List<String> name;

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }
}
