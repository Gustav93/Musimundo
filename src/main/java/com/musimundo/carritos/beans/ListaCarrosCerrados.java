package com.musimundo.carritos.beans;

import java.util.ArrayList;
import java.util.List;

public class ListaCarrosCerrados
{
    private List<CarroCerrado> closedOrders;

    public ListaCarrosCerrados() {
        this.closedOrders = new ArrayList();
    }

    public List<CarroCerrado> getClosedOrders() {
        return closedOrders;
    }

    public void setClosedOrders(List<CarroCerrado> closedOrders) {
        this.closedOrders = closedOrders;
    }

    @Override
    public String toString() {
        return "ListaCarrosCerrados{" +
                "closedOrders=" + closedOrders +
                '}';
    }
}