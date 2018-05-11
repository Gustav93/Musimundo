package com.musimundo.carritos.beans;

import java.util.ArrayList;
import java.util.List;

public class Orders
{
    private List<Order> orders;

    public Orders() {
        this.orders = new ArrayList<Order>();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orders=" + orders +
                '}';
    }
}
