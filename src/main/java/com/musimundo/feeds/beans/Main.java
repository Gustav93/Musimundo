package com.musimundo.feeds.beans;

import com.musimundo.feeds.dao.ProductoDao;
import com.musimundo.feeds.dao.ProductoDaoImpl;

public class Main
{
    public static void main(String[] args) {

        Producto p = new Producto();

        p.setBrand("asd");
        p.setCodigoProducto("1234");

        ProductoDao dao = new ProductoDaoImpl();


//        dao.persist(p);
    }
}
