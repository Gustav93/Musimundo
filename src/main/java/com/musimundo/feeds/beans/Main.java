package com.musimundo.feeds.beans;

import com.musimundo.feeds.dao.ProductDao;
import com.musimundo.feeds.dao.ProductDaoImpl;

public class Main
{
    public static void main(String[] args) {

        Product p = new Product();

        p.setBrand("asd");
        p.setCodigoProducto("1234");

        ProductDao dao = new ProductDaoImpl();


//        dao.persist(p);
    }
}
