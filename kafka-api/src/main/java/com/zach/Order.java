package com.zach;

/**
 * @author 钟鹏 Zach
 * @version 1.0
 * @date 2021/1/27 2:16 下午
 */
public class Order {

    private Integer orderId;
    private Integer name;

    public Order(int orderId, int name) {
        this.orderId = orderId;
        this.name = name;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Integer getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
