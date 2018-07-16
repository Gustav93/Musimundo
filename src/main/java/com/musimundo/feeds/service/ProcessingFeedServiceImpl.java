package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.*;
import com.musimundo.utilities.Company;
import com.musimundo.utilities.ErrorType;
import com.musimundo.utilities.FeedStatus;
import com.musimundo.utilities.FeedType;
import com.musimundo.utilities.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("processingFeedService")
@Transactional
public class ProcessingFeedServiceImpl implements ProcessingFeedService {

    @Autowired
    AuditService auditService;

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
    MailService mailService;

    @Override
    public List<Product> processProduct(){
        List<Product> productList = productService.cloneProductList(productService.findNotProcessed());
        List<String> importOriginList = productService.getImportOrigin(productList);
        List<Audit> auditList = auditService.cloneAuditList(auditService.findBy(FeedType.PRODUCT));
        List<Product> productListNotOk = new ArrayList<>();
        boolean existeproducto;


        for(Product product :productList)
        {
            existeproducto = false;
            for(Audit audit : auditList)
            {
                if(product.getProductCode().equals(audit.getProductCode()) && product.getImportOrigin().equals(audit.getImportOrigin()) && product.getProcessed() == false && audit.getProcessed() == false)
                {
                    existeproducto = true;
                    product.setProcessingDate(new Date());
                    product.setFeedStatus(getFeedStatus(audit));
                    product.setErrorDescription(audit.getDescription());
                    product.setProcessed(true);
                    product.setCompany(audit.getCompany());

                    if(!audit.getErrorCode().contains("I"))
                        productListNotOk.add(product);
                }
            }

            if(!existeproducto)
                productListNotOk.add(product);
        }

        String empresa = productList.get(0).getCompany();
        productService.updateStateByTypeAndImport(FeedStatus.OK,"PRODUCT - La novedad se cargó exitosamente", empresa);

        for(String importOrigin : importOriginList)
            auditService.updateState(FeedType.PRODUCT, importOrigin);

        for(Product productNotOk : productListNotOk)
            productService.update(productNotOk);

//        for(Product product : productList)
//        {
//            List<Audit> auditLista = auditService.findBy(product.getProductCode(),FeedType.PRODUCT, product.getImportOrigin());
//
//            if(auditLista == null || auditLista.size()== 0)
//                continue;
//
//            Audit audit = auditLista.get(0);
//
//            product.setProcessingDate(new Date());
//            product.setFeedStatus(getFeedStatus(audit));
//            product.setErrorDescription(audit.getDescription());
//            product.setProcessed(true);
//            product.setCompany(audit.getCompany());
//            audit.setProcessed(true);
//
//            auditService.save(audit);
//            productService.save(product);
//        }
//
        File fileAll = productService.getCsv(productList, Filter.ALL_REGISTERS);
        File fileNotOk = productService.getCsv(productList, Filter.ONLY_NOT_OK);
        List<ProductReport> reportList = productService.getReportList(productList);
        Company company = productService.getCompany(productList);
        mailService.sendProductMail(reportList, fileNotOk, company);
        mailService.sendProductMail(reportList, fileAll, Company.UNDEFINED);

        return productList;
    }

    @Override
    public List<Price> processPrice() {
        List<Price> priceList = priceService.clonePriceList(priceService.findNotProcessed());
        List<String> importOriginList = priceService.getImportOrigin(priceList);
        List<Audit> auditList = auditService.cloneAuditList(auditService.findBy(FeedType.PRICE));
        List<Price> priceListNotOk = new ArrayList<>();
        boolean existeprecio;


        for(Price price :priceList)
        {
            existeprecio = false;
            for(Audit audit : auditList)
            {
                if(price.getProductCode().equals(audit.getProductCode()) && price.getImportOrigin().equals(audit.getImportOrigin()) && price.getProcessed() == false && audit.getProcessed() == false)
                {
                    existeprecio = true;
                    price.setProcessingDate(new Date());
                    price.setFeedStatus(getFeedStatus(audit));
                    price.setErrorDescription(audit.getDescription());
                    price.setProcessed(true);
                    price.setCompany(audit.getCompany());

                    if(!audit.getErrorCode().contains("I"))
                        priceListNotOk.add(price);
                }
            }

            if(!existeprecio)
                priceListNotOk.add(price);
        }

        String empresa = priceList.get(0).getCompany();
        priceService.updateStateByTypeAndImport(FeedStatus.OK,"PRICE - La novedad se cargó exitosamente", empresa);

        for(String importOrigin : importOriginList)
            auditService.updateState(FeedType.PRICE, importOrigin);

        for(Price priceNotOk : priceListNotOk)
            priceService.update(priceNotOk);


//        for(Price price : priceList)
//        {
//            List<Audit> auditList = auditService.findBy(price.getProductCode(),FeedType.PRICE, price.getImportOrigin());
//            price.setProcessed(true);
//
//            if(auditList == null || auditList.size()== 0)
//            {
//                priceService.save(price);
//                continue;
//            }
//
//            Audit audit = auditList.get(0);
//            audit.setProcessed(true);
//            price.setProcessingDate(new Date());
//            price.setFeedStatus(getFeedStatus(audit));
//            price.setErrorDescription(audit.getDescription());
//            price.setCompany(audit.getCompany());
//
//            auditService.save(audit);
//            priceService.save(price);
//        }

        File fileAll = priceService.getCsv(priceList, Filter.ALL_REGISTERS);
        File fileNotOk = priceService.getCsv(priceList, Filter.ONLY_NOT_OK);
        List<PriceReport> reportList = priceService.getReportList(priceList);
        Company company = priceService.getCompany(priceList);
        mailService.sendPriceMail(reportList, fileNotOk, company);
        mailService.sendPriceMail(reportList, fileAll, Company.UNDEFINED);

        return priceList;
    }

    @Override
    public List<Stock> processStock() {
        List<Stock> stockList = stockService.cloneStockList(stockService.findNotProcessed());
        List<Audit> auditList = auditService.cloneAuditList(auditService.findBy(FeedType.STOCK));
        List<Stock> stockListNotOk = new ArrayList<>();
        List<String> importOriginList = stockService.getImportOrigin(stockList);



        boolean existeStock;

        for(Stock stock : stockList)
        {
            existeStock = false;

            for(Audit audit : auditList)
            {
                if(stock.getProductCode().equals(audit.getProductCode()) && stock.getImportOrigin().equals(audit.getImportOrigin()) && stock.getProcessed() == false && audit.getProcessed() == false)
                {
                    existeStock = true;
                    audit.setProcessed(true);
                    stock.setProcessingDate(new Date());
                    stock.setFeedStatus(getFeedStatus(audit));
                    stock.setErrorDescription(audit.getDescription());
                    stock.setProcessed(true);
                    stock.setCompany(audit.getCompany());

                    if(!audit.getErrorCode().contains("I"))
                        stockListNotOk.add(stock);
                }
            }

            if(!existeStock)
                stockListNotOk.add(stock);
        }

        String empresa = stockList.get(0).getCompany();
        stockService.updateStateByTypeAndImport(FeedStatus.OK,"STOCK - La novedad se cargó exitosamente", empresa);

        for(String importOrigin: importOriginList)
            auditService.updateState(FeedType.STOCK, importOrigin);

        for(Stock stockNotOk : stockListNotOk)
            stockService.update(stockNotOk);

//        for(Stock stock : stockList)
//        {
//            List<Audit> auditList = auditService.findBy(stock.getProductCode(),FeedType.STOCK, stock.getImportOrigin(), stock.getWarehouse());
//            stock.setProcessed(true);
//
//            if(auditList == null || auditList.size()== 0)
//            {
////                stockService.update(stock);
//                continue;
//            }
//
//            Audit audit = auditList.get(0);
////            audit.setProcessed(true);
//            stock.setProcessingDate(new Date());
//            stock.setFeedStatus(getFeedStatus(audit));
//            stock.setErrorDescription(audit.getDescription());
//            stock.setCompany(audit.getCompany());

//            auditService.update(audit);
//            stockService.update(stock);
//        }

        File fileAll = stockService.getCsv(stockList, Filter.ALL_REGISTERS);
        File fileNotOk = stockService.getCsv(stockList, Filter.ONLY_NOT_OK);
        List<StockReport> reportList = stockService.getReportList(stockList);
        Company company = stockService.getCompany(stockList);
        mailService.sendStockMail(reportList, fileNotOk, company);
        mailService.sendStockMail(reportList, fileAll, Company.UNDEFINED);

        fileAll.delete();
        fileNotOk.delete();

        return stockList;
    }

    @Override
    public List<Media> processMedia() {
        List<Media> mediaList = mediaService.cloneMediaList(mediaService.findNotProcessed());
        List<Audit> auditList = auditService.cloneAuditList(auditService.findBy(FeedType.MEDIA));
        List<Media> mediaListNotOk = new ArrayList<>();
        List<String> importOriginList = mediaService.getImportOrigin(mediaList);



        boolean existeMedia;

        for(Media media : mediaList)
        {
            existeMedia = false;

            for(Audit audit : auditList)
            {
                if(media.getProductCode().equals(audit.getProductCode()) && media.getImportOrigin().equals(audit.getImportOrigin()) && media.getProcessed() == false && audit.getProcessed() == false)
                {
                    existeMedia = true;
                    audit.setProcessed(true);
                    media.setProcessingDate(new Date());
                    media.setFeedStatus(getFeedStatus(audit));
                    media.setErrorDescription(audit.getDescription());
                    media.setProcessed(true);
                    media.setCompany(audit.getCompany());

                    if(!audit.getErrorCode().contains("I"))
                        mediaListNotOk.add(media);
                }
            }

            if(!existeMedia)
                mediaListNotOk.add(media);
        }

        String empresa = mediaList.get(0).getCompany();
        mediaService.updateStateByTypeAndImport(FeedStatus.OK,"MEDIA - La novedad se cargó exitosamente", empresa);

        for(String importOrigin: importOriginList)
            auditService.updateState(FeedType.MEDIA, importOrigin);

        for(Media mediaNotOk : mediaListNotOk)
            mediaService.update(mediaNotOk);

//        for(Media media : mediaList)
//        {
//            List<Audit> auditList = auditService.findBy(media.getProductCode(),FeedType.MEDIA, media.getImportOrigin());
//            media.setProcessed(true);
//
//            if(auditList == null || auditList.size()== 0)
//            {
//                mediaService.save(media);
//                continue;
//            }
//
//            Audit audit = auditList.get(0);
//            audit.setProcessed(true);
//            media.setProcessingDate(new Date());
//            media.setFeedStatus(getFeedStatus(audit));
//            media.setErrorDescription(audit.getDescription());
//            media.setCompany(audit.getCompany());
//
//            auditService.save(audit);
//            mediaService.save(media);
//        }

        File fileAll = mediaService.getCsv(mediaList, Filter.ALL_REGISTERS);
        File fileNotOk = mediaService.getCsv(mediaList, Filter.ONLY_NOT_OK);
        List<MediaReport> reportList = mediaService.getReportList(mediaList);
        Company company = mediaService.getCompany(mediaList);
        mailService.sendMediaMail(reportList, fileNotOk, company);
        mailService.sendMediaMail(reportList, fileAll, Company.UNDEFINED);

        fileAll.delete();
        fileNotOk.delete();

        return mediaList;
    }

    @Override
    public List<Merchandise> processMerchandise() {
        List<Merchandise> merchandiseList = merchandiseService.cloneMerchandiseList(merchandiseService.findNotProcessed());
        List<Audit> auditList = auditService.cloneAuditList(auditService.findBy(FeedType.MERCHANDISE));
        List<Merchandise> merchandiseListNotOk = new ArrayList<>();
        List<String> importOriginList = merchandiseService.getImportOrigin(merchandiseList);



        boolean existeMerchandise;

        for(Merchandise merchandise : merchandiseList)
        {
            existeMerchandise = false;

            for(Audit audit : auditList)
            {
                if(merchandise.getSource().equals(audit.getProductCode()) && merchandise.getImportOrigin().equals(audit.getImportOrigin()) && merchandise.getProcessed() == false && audit.getProcessed() == false)
                {
                    existeMerchandise = true;
                    audit.setProcessed(true);
                    merchandise.setProcessingDate(new Date());
                    merchandise.setFeedStatus(getFeedStatus(audit));
                    merchandise.setErrorDescription(audit.getDescription());
                    merchandise.setProcessed(true);
                    merchandise.setCompany(audit.getCompany());

                    if(!audit.getErrorCode().contains("I"))
                        merchandiseListNotOk.add(merchandise);
                }
            }

            if(!existeMerchandise)
                merchandiseListNotOk.add(merchandise);
        }

        String empresa = merchandiseList.get(0).getCompany();
        merchandiseService.updateStateByTypeAndImport(FeedStatus.OK,"MERCHANDISE - La novedad se cargó exitosamente", empresa);

        for(String importOrigin: importOriginList)
            auditService.updateState(FeedType.MERCHANDISE, importOrigin);

        for(Merchandise merchandiseNotOk : merchandiseListNotOk)
            merchandiseService.update(merchandiseNotOk);

//        for(Merchandise merchandise : merchandiseList)
//        {
//            List<Audit> auditList = auditService.findBy(merchandise.getSource(),FeedType.MERCHANDISE, merchandise.getImportOrigin());
//            merchandise.setProcessed(true);
//
//            if(auditList == null || auditList.size()== 0)
//            {
//                merchandiseService.update(merchandise);
//                continue;
//            }
//
//            Audit audit = auditList.get(0);
//            audit.setProcessed(true);
//            merchandise.setProcessingDate(new Date());
//            merchandise.setFeedStatus(getFeedStatus(audit));
//            merchandise.setErrorDescription(audit.getDescription());
//            merchandise.setCompany(audit.getCompany());
//
//            auditService.update(audit);
//            merchandiseService.update(merchandise);
//        }

        File fileAll = merchandiseService.getCsv(merchandiseList, Filter.ALL_REGISTERS);
        File fileNotOk = merchandiseService.getCsv(merchandiseList, Filter.ONLY_NOT_OK);
        List<MerchandiseReport> reportList = merchandiseService.getReportList(merchandiseList);
        Company company = merchandiseService.getCompany(merchandiseList);
        mailService.sendMerchandiseMail(reportList, fileNotOk, company);
        mailService.sendMerchandiseMail(reportList, fileAll, Company.UNDEFINED);

        return merchandiseList;
    }

    @Override
    public List<Classification> processClassification() {
        List<Classification> classificationList = classificationService.cloneClassificationList(classificationService.findNotProcessed());
        List<Audit> auditList = auditService.cloneAuditList(auditService.findBy(FeedType.CLASSIFICATION));
        List<Classification> classificationListNotOk = new ArrayList<>();
        List<String> importOriginList = classificationService.getImportOrigin(classificationList);

        boolean existeClassification;

        for(Classification classification : classificationList)
        {
            existeClassification = false;

            for(Audit audit : auditList)
            {
                if(classification.getProductCode().equals(audit.getProductCode()) && classification.getImportOrigin().equals(audit.getImportOrigin()) && classification.getProcessed() == false && audit.getProcessed() == false)
                {
                    existeClassification = true;
                    audit.setProcessed(true);
                    classification.setProcessingDate(new Date());
                    classification.setFeedStatus(getFeedStatus(audit));
                    classification.setErrorDescription(audit.getDescription());
                    classification.setProcessed(true);
                    classification.setCompany(audit.getCompany());

                    if(!audit.getErrorCode().contains("I"))
                        classificationListNotOk.add(classification);
                }
            }

            if(!existeClassification)
                classificationListNotOk.add(classification);
        }

        String empresa = classificationList.get(0).getCompany();
        classificationService.updateStateByTypeAndImport(FeedStatus.OK,"CLASSIFICATION - La novedad se cargó exitosamente", empresa);

        for(String importOrigin: importOriginList)
            auditService.updateState(FeedType.CLASSIFICATION, importOrigin);

        for(Classification classificationNotOk : classificationListNotOk)
            classificationService.update(classificationNotOk);


//        for(Classification classification : classificationList)
//        {
//            List<Audit> auditList = auditService.findBy(classification.getProductCode(),FeedType.CLASSIFICATION, classification.getImportOrigin());
//            classification.setProcessed(true);
//
//            if(auditList == null || auditList.size()== 0)
//            {
//                classificationService.save(classification);
//                continue;
//            }
//
//            Audit audit = auditList.get(0);
//            audit.setProcessed(true);
//            classification.setProcessingDate(new Date());
//            classification.setFeedStatus(getFeedStatus(audit));
//            classification.setErrorDescription(audit.getDescription());
//            classification.setCompany(audit.getCompany());
//
//            auditService.save(audit);
//            classificationService.save(classification);
//        }

        File fileAll = classificationService.getCsv(classificationList, Filter.ALL_REGISTERS);
        File fileNotOk = classificationService.getCsv(classificationList, Filter.ONLY_NOT_OK);
        List<ClassificationReport> reportList = classificationService.getReportList(classificationList);
        Company company = classificationService.getCompany(classificationList);
        mailService.sendClassificationMail(reportList, fileNotOk, company);
        mailService.sendClassificationMail(reportList, fileAll, Company.UNDEFINED);

        fileAll.delete();
        fileNotOk.delete();

        return classificationList;
    }


    private FeedStatus getFeedStatus(Audit audit)
    {
        if(auditService.getErrorType(audit).equals(ErrorType.SUCCESS))
            return FeedStatus.OK;

        else if(auditService.getErrorType(audit).equals(ErrorType.WARNING))
            return FeedStatus.WARNING;

        else if(auditService.getErrorType(audit).equals(ErrorType.ERROR))
           return FeedStatus.ERROR;

        else
            return FeedStatus.NOT_PROCESSED;
    }
}