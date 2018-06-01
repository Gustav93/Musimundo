package com.musimundo.feeds.controller;

import com.musimundo.feeds.beans.*;
import com.musimundo.feeds.dao.OldPriceDaoImpl;
import com.musimundo.feeds.dao.OldProductDaoImpl;
import com.musimundo.feeds.service.*;
import com.musimundo.utilities.FeedStatus;
import com.musimundo.utilities.FeedType;
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

//    @Autowired
//    OldProductDaoImpl oldProductDao;
//
//    @Autowired
//    OldPriceDaoImpl oldPriceDao;

    @Autowired
    FeedBuilderService feedBuilderService;

    @Autowired
    ProcessingFeedService processingFeedService;


    @RequestMapping(value = {"/listaproductos"})
    public String getProductList(ModelMap model){

//        List<OldProduct> res = oldProductDao.findAll();
//
//        OldProduct oldProduct = res.get(1);
//        Product product = new Product();
//
//        product.setProductCode(oldProduct.getCodigoProducto());
//        product.setEan(oldProduct.getEan());
//        product.setBrand(oldProduct.getBrand());
//        product.setName(oldProduct.getName());
//        product.setCategory(Integer.parseInt(oldProduct.getCategory()));
//        product.setWeight(Integer.parseInt(oldProduct.getWeight()));
//        product.setApprovalStatus(oldProduct.getApprovalStatus());
//        product.setDescription(oldProduct.getDescription());
//        product.setImportOrigin(oldProduct.getOrigenImportacion());
//        product.setErrorDescription(oldProduct.getDescription());
//        product.setFeedStatus(parseFeedStatus(oldProduct.getEstadoProcesamiento()));
//        product.setCompany(oldProduct.getEmpresa());
//        product.setProcessed(true);
//
//        productService.save(product);
//        System.out.println(res);


//        List<OldPrice> res = oldPriceDao.findAll();
//
//        System.out.println(res);
//
//        OldPrice oldPrice = res.get(0);
//        Price price = new Price();
//
//        price.setProductCode(oldPrice.getCodigoProducto());
//        price.setOnlinePrice(Double.parseDouble(oldPrice.getOnlinePrice()));
//        price.setCurrency(oldPrice.getCurrency());
//        price.setStorePrice(Double.parseDouble(oldPrice.getStorePrice()));
//        price.setHasPriority(Boolean.parseBoolean(oldPrice.getHasPriority()));
//        price.setImportOrigin(oldPrice.getOrigenImportacion());
//        price.setFeedStatus(parseFeedStatus(oldPrice.getEstadoProcesamiento()));
//        price.setErrorDescription(oldPrice.getDescripcionError());
//        price.setCompany(oldPrice.getEmpresa());
//        priceService.save(price);

//        feedBuilderService.createRegister("Producto-20180531 100000 al 20180601 090000_aud.csv");
//        feedBuilderService.createRegister("producto-1806010001.csv");

        processingFeedService.process(FeedType.PRODUCT);

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

    private FeedStatus parseFeedStatus(String status)
    {
        if(status.equals("Procesado"))
            return FeedStatus.OK;

        else if(status.equals("Procesado con error"))
            return FeedStatus.ERROR;

        else if(status.equals("Procesado con warning"))
            return FeedStatus.WARNING;

        else
            return FeedStatus.NOT_PROCESSED;

    }
}
