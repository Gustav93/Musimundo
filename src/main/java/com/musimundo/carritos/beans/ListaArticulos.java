package com.musimundo.carritos.beans;

import java.util.ArrayList;
import java.util.List;

public class ListaArticulos
{
    private List<Articulo> entries;

    public ListaArticulos() {
        this.entries = new ArrayList();
    }

    public List<Articulo> getEntries() {

        List<Articulo> res = new ArrayList<Articulo>();
        res.addAll(entries);
        return res;
    }

    @Override
    public String toString() {
        return "ListaArticulos{" +
                "entries=" + entries +
                '}';
    }

    public void setEntries(List<Articulo> entries) {
        this.entries = entries;
    }
}