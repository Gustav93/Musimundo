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



//    @Override
//    public void process(FeedType feedType) {
//
//        if(feedType.equals(FeedType.PRODUCT))
//        {
//            List<Product> productList = productService.findNotProcessed();
//
//            for(Product product : productList)
//            {
//                List<Audit> auditList = auditService.findBy(product.getProductCode(),FeedType.PRODUCT, product.getImportOrigin());
//
//                if(auditList == null || auditList.size()== 0)
//                    continue;
//
//                Audit audit = auditList.get(0);
//
//                product.setProcessingDate(new Date());
//                product.setFeedStatus(getFeedStatus(audit));
//                product.setErrorDescription(audit.getDescription());
//                product.setProcessed(true);
//                product.setCompany(audit.getCompany());
//                audit.setProcessed(true);
//
//                auditService.save(audit);
//                productService.save(product);
//            }
//
//            productService.getCsv(productList, Filter.ALL_REGISTERS);
//            File file = productService.getCsv(productList, Filter.ONLY_NOT_OK);
//            List<ProductReport> reportList = productService.getReportList(productList);
//            Company company = productService.getCompany(productList);
//            mailService.sendProductMail(reportList, file, company);
//        }
//
//        else if(feedType.equals(FeedType.PRICE))
//        {
//            List<Price> priceList = priceService.findNotProcessed();
//
//            for(Price price : priceList)
//            {
//                List<Audit> auditList = auditService.findBy(price.getProductCode(),FeedType.PRICE, price.getImportOrigin());
//                price.setProcessed(true);
//
//                if(auditList == null || auditList.size()== 0)
//                {
//                    priceService.save(price);
//                    continue;
//                }
//
//                Audit audit = auditList.get(0);
//                audit.setProcessed(true);
//                price.setProcessingDate(new Date());
//                price.setFeedStatus(getFeedStatus(audit));
//                price.setErrorDescription(audit.getDescription());
//                price.setCompany(audit.getCompany());
//
//                auditService.save(audit);
//                priceService.save(price);
//            }
//
//            priceService.getCsv(priceList, Filter.ALL_REGISTERS);
//            File file = priceService.getCsv(priceList, Filter.ONLY_NOT_OK);
//            List<PriceReport> reportList = priceService.getReportList(priceList);
//            Company company = priceService.getCompany(priceList);
//            mailService.sendPriceMail(reportList, file, company);
//        }
//
//        else if(feedType.equals(FeedType.STOCK))
//        {
//            List<Stock> stockList = stockService.findNotProcessed();
//
//            for(Stock stock : stockList)
//            {
//                List<Audit> auditList = auditService.findBy(stock.getProductCode(),FeedType.STOCK, stock.getImportOrigin(), stock.getWarehouse());
//                stock.setProcessed(true);
//
//                if(auditList == null || auditList.size()== 0)
//                {
//                    stockService.update(stock);
//                    continue;
//                }
//
//                Audit audit = auditList.get(0);
//                audit.setProcessed(true);
//                stock.setProcessingDate(new Date());
//                stock.setFeedStatus(getFeedStatus(audit));
//                stock.setErrorDescription(audit.getDescription());
//                stock.setCompany(audit.getCompany());
//
//                auditService.update(audit);
//                stockService.update(stock);
//            }
//
//            stockService.getCsv(stockList, Filter.ALL_REGISTERS);
//            File file = stockService.getCsv(stockList, Filter.ONLY_NOT_OK);
//            List<StockReport> reportList = stockService.getReportList(stockList);
//            Company company = stockService.getCompany(stockList);
//            mailService.sendStockMail(reportList, file, company);
//        }
//
//        else if(feedType.equals(FeedType.MEDIA))
//        {
//            List<Media> mediaList = mediaService.findNotProcessed();
//
//            for(Media media : mediaList)
//            {
//                List<Audit> auditList = auditService.findBy(media.getProductCode(),FeedType.MEDIA, media.getImportOrigin());
//                media.setProcessed(true);
//
//                if(auditList == null || auditList.size()== 0)
//                {
//                    mediaService.save(media);
//                    continue;
//                }
//
//                Audit audit = auditList.get(0);
//                audit.setProcessed(true);
//                media.setProcessingDate(new Date());
//                media.setFeedStatus(getFeedStatus(audit));
//                media.setErrorDescription(audit.getDescription());
//                media.setCompany(audit.getCompany());
//
//                auditService.save(audit);
//                mediaService.save(media);
//            }
//            mediaService.getCsv(mediaList, Filter.ALL_REGISTERS);
//            File file = mediaService.getCsv(mediaList, Filter.ONLY_NOT_OK);
//            List<MediaReport> reportList = mediaService.getReportList(mediaList);
//            Company company = mediaService.getCompany(mediaList);
//            mailService.sendMediaMail(reportList, file, company);
//        }
//
//        else if(feedType.equals(FeedType.MERCHANDISE))
//        {
//            List<Merchandise> merchandiseList = merchandiseService.findNotProcessed();
//
//            for(Merchandise merchandise : merchandiseList)
//            {
//                List<Audit> auditList = auditService.findBy(merchandise.getSource(),FeedType.MERCHANDISE, merchandise.getImportOrigin());
//                merchandise.setProcessed(true);
//
//                if(auditList == null || auditList.size()== 0)
//                {
//                    merchandiseService.update(merchandise);
//                    continue;
//                }
//
//                Audit audit = auditList.get(0);
//                audit.setProcessed(true);
//                merchandise.setProcessingDate(new Date());
//                merchandise.setFeedStatus(getFeedStatus(audit));
//                merchandise.setErrorDescription(audit.getDescription());
//                merchandise.setCompany(audit.getCompany());
//
//                auditService.update(audit);
//                merchandiseService.update(merchandise);
//            }
//
//            merchandiseService.getCsv(merchandiseList, Filter.ALL_REGISTERS);
//            File file = merchandiseService.getCsv(merchandiseList, Filter.ONLY_NOT_OK);
//            List<MerchandiseReport> reportList = merchandiseService.getReportList(merchandiseList);
//            Company company = merchandiseService.getCompany(merchandiseList);
//            mailService.sendMerchandiseMail(reportList, file, company);
//        }
//
//        else if(feedType.equals(FeedType.CLASSIFICATION))
//        {
//            List<Classification> classificationList = classificationService.findNotProcessed();
//
//            for(Classification classification : classificationList)
//            {
//                List<Audit> auditList = auditService.findBy(classification.getProductCode(),FeedType.CLASSIFICATION, classification.getImportOrigin());
//                classification.setProcessed(true);
//
//                if(auditList == null || auditList.size()== 0)
//                {
//                    classificationService.save(classification);
//                    continue;
//                }
//
//                Audit audit = auditList.get(0);
//                audit.setProcessed(true);
//                classification.setProcessingDate(new Date());
//                classification.setFeedStatus(getFeedStatus(audit));
//                classification.setErrorDescription(audit.getDescription());
//                classification.setCompany(audit.getCompany());
//
//                auditService.save(audit);
//                classificationService.save(classification);
//            }
//            classificationService.getCsv(classificationList, Filter.ALL_REGISTERS);
//            File file = classificationService.getCsv(classificationList, Filter.ONLY_NOT_OK);
//            List<ClassificationReport> reportList = classificationService.getReportList(classificationList);
//            Company company = classificationService.getCompany(classificationList);
//            mailService.sendClassificationMail(reportList, file, company);
//        }
//    }

    @Override
    public List<Product> processProduct(){
        List<Product> productList = productService.findNotProcessed();
//        List<String> importOriginList = productService.getImportOrigin(productList);

//        List<Audit> auditList = auditService.findBy(FeedType.PRODUCT);
//        List<Product> productListNotOk = new ArrayList<>();

//        for(String importOrigin : importOriginList)
//            auditService.updateState(FeedType.PRODUCT, importOrigin);
//
//        for(Product product :productList)
//        {
//            for(Audit audit : auditList)
//            {
//                if(product.getProductCode().equals(audit.getProductCode()) && product.getImportOrigin().equals(audit))
//                {
//                    product.setProcessingDate(new Date());
//                    product.setFeedStatus(getFeedStatus(audit));
//                    product.setErrorDescription(audit.getDescription());
//                    product.setProcessed(true);
//                    product.setCompany(audit.getCompany());
//
//                    if(!audit.getErrorCode().contains("I"))
//                        productListNotOk.add(product);
//                }
//
//                else{
//                    audit.setProcessed(false);
//                }
//            }
//        }



        for(Product product : productList)
        {
            List<Audit> auditLista = auditService.findBy(product.getProductCode(),FeedType.PRODUCT, product.getImportOrigin());

            if(auditLista == null || auditLista.size()== 0)
                continue;

            Audit audit = auditLista.get(0);

            product.setProcessingDate(new Date());
            product.setFeedStatus(getFeedStatus(audit));
            product.setErrorDescription(audit.getDescription());
            product.setProcessed(true);
            product.setCompany(audit.getCompany());
            audit.setProcessed(true);

            auditService.save(audit);
            productService.save(product);
        }

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
        List<Price> priceList = priceService.findNotProcessed();

        for(Price price : priceList)
        {
            List<Audit> auditList = auditService.findBy(price.getProductCode(),FeedType.PRICE, price.getImportOrigin());
            price.setProcessed(true);

            if(auditList == null || auditList.size()== 0)
            {
                priceService.save(price);
                continue;
            }

            Audit audit = auditList.get(0);
            audit.setProcessed(true);
            price.setProcessingDate(new Date());
            price.setFeedStatus(getFeedStatus(audit));
            price.setErrorDescription(audit.getDescription());
            price.setCompany(audit.getCompany());

            auditService.save(audit);
            priceService.save(price);
        }

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
        List<Stock> stockList = stockService.findNotProcessed();

        for(Stock stock : stockList)
        {
            List<Audit> auditList = auditService.findBy(stock.getProductCode(),FeedType.STOCK, stock.getImportOrigin(), stock.getWarehouse());
            stock.setProcessed(true);

            if(auditList == null || auditList.size()== 0)
            {
                stockService.update(stock);
                continue;
            }

            Audit audit = auditList.get(0);
            audit.setProcessed(true);
            stock.setProcessingDate(new Date());
            stock.setFeedStatus(getFeedStatus(audit));
            stock.setErrorDescription(audit.getDescription());
            stock.setCompany(audit.getCompany());

            auditService.update(audit);
            stockService.update(stock);
        }

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
        List<Media> mediaList = mediaService.findNotProcessed();

        for(Media media : mediaList)
        {
            List<Audit> auditList = auditService.findBy(media.getProductCode(),FeedType.MEDIA, media.getImportOrigin());
            media.setProcessed(true);

            if(auditList == null || auditList.size()== 0)
            {
                mediaService.save(media);
                continue;
            }

            Audit audit = auditList.get(0);
            audit.setProcessed(true);
            media.setProcessingDate(new Date());
            media.setFeedStatus(getFeedStatus(audit));
            media.setErrorDescription(audit.getDescription());
            media.setCompany(audit.getCompany());

            auditService.save(audit);
            mediaService.save(media);
        }

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
        List<Merchandise> merchandiseList = merchandiseService.findNotProcessed();

        for(Merchandise merchandise : merchandiseList)
        {
            List<Audit> auditList = auditService.findBy(merchandise.getSource(),FeedType.MERCHANDISE, merchandise.getImportOrigin());
            merchandise.setProcessed(true);

            if(auditList == null || auditList.size()== 0)
            {
                merchandiseService.update(merchandise);
                continue;
            }

            Audit audit = auditList.get(0);
            audit.setProcessed(true);
            merchandise.setProcessingDate(new Date());
            merchandise.setFeedStatus(getFeedStatus(audit));
            merchandise.setErrorDescription(audit.getDescription());
            merchandise.setCompany(audit.getCompany());

            auditService.update(audit);
            merchandiseService.update(merchandise);
        }

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
        List<Classification> classificationList = classificationService.findNotProcessed();

        for(Classification classification : classificationList)
        {
            List<Audit> auditList = auditService.findBy(classification.getProductCode(),FeedType.CLASSIFICATION, classification.getImportOrigin());
            classification.setProcessed(true);

            if(auditList == null || auditList.size()== 0)
            {
                classificationService.save(classification);
                continue;
            }

            Audit audit = auditList.get(0);
            audit.setProcessed(true);
            classification.setProcessingDate(new Date());
            classification.setFeedStatus(getFeedStatus(audit));
            classification.setErrorDescription(audit.getDescription());
            classification.setCompany(audit.getCompany());

            auditService.save(audit);
            classificationService.save(classification);
        }
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