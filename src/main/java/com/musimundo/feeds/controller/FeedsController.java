package com.musimundo.feeds.controller;

import com.musimundo.feeds.beans.*;
import com.musimundo.feeds.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class FeedsController {

    @Autowired
    ProductService productService;

    @Autowired
    PriceService priceService;

    @Autowired
    StockService stockService;

    @Autowired
    MediaService mediaService;

    @Autowired
    MerchandiseService merchandiseService;

    @Autowired
    ClassificationService classificationService;

    @Autowired
    AuditService auditService;

    @RequestMapping(value = {"/listaproductos"})
    public String getProductList(ModelMap model){

        List<Product> productList = productService.findAll();
        ProductReport productReport = productService.getReport();

        model.addAttribute("productList", productList);
        model.addAttribute("productReport", productReport);

        return "productlist";
    }

    @RequestMapping(value = {"/listaprecios"})
    public String getPriceList(ModelMap model){
        List<Price> priceList = priceService.findAll();
        PriceReport priceReport = priceService.getReport();

        model.addAttribute("priceList", priceList);
        model.addAttribute("priceReport", priceReport);

        return "pricelist";
    }

    @RequestMapping(value = {"/listastock"})
    public String getStockList(ModelMap model){
        List<Stock> stockList = stockService.findAll();
        StockReport stockReport = stockService.getReport();

        model.addAttribute("stockList", stockList);
        model.addAttribute("stockReport", stockReport);

        return "stocklist";
    }

    @RequestMapping(value = {"/listamedia"})
    public String getMediaList(ModelMap model){
        List<Media> mediaList = mediaService.findAll();
        MediaReport mediaReport = mediaService.getReport();

        model.addAttribute("mediaList", mediaList);
        model.addAttribute("mediaReport", mediaReport);

        return "medialist";
    }

    @RequestMapping(value = {"/listamerchandise"})
    public String getMerchandiseList(ModelMap model){
        List<Merchandise> merchandiseList = merchandiseService.findAll();
        MerchandiseReport merchandiseReport= merchandiseService.getReport();

        model.addAttribute("merchandiseList", merchandiseList);
        model.addAttribute("merchandiseReport", merchandiseReport);

        return "merchandiselist";
    }

    @RequestMapping(value = {"/listaclasificacion"})
    public String getCLassificationList(ModelMap model){
        List<Classification> classificationList = classificationService.findAll();
        ClassificationReport classificationReport= classificationService.getReport();

        model.addAttribute("classificationList", classificationList);
        model.addAttribute("classificationReport", classificationReport);

        return "classificationlist";
    }

    @RequestMapping(value = {"/listaauditoria"})
    public String getAuditList(ModelMap model)
    {
        List<Audit> auditList = auditService.findAll();
        model.addAttribute("auditList", auditList);

        return "auditlist";
    }
}
