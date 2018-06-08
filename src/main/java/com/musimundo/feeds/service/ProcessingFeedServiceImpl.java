package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.*;
import com.musimundo.utilities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
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
    public void process(FeedType feedType) {

        if(feedType.equals(FeedType.PRODUCT))
        {
            List<Product> productList = productService.findNotProcessed();

            for(Product product : productList)
            {
                List<Audit> auditList = auditService.findBy(product.getProductCode(),FeedType.PRODUCT, product.getImportOrigin());

                if(auditList == null || auditList.size()== 0)
                    continue;

                Audit audit = auditList.get(0);

                product.setProcessingDate(new Date());
                product.setFeedStatus(getFeedStatus(audit));
                product.setErrorDescription(audit.getDescription());
                product.setProcessed(true);
                product.setCompany(audit.getCompany());
                audit.setProcessed(true);

                auditService.save(audit);
                productService.save(product);
            }

            productService.getCsv(productList, Filter.ALL_REGISTERS);
            File file = productService.getCsv(productList, Filter.ONLY_NOT_OK);
            ProductReport report = productService.getReport(productList);

            mailService.sendMail(report, file, Company.CARSA);

        }

        else if(feedType.equals(FeedType.PRICE))
        {
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
        }

        else if(feedType.equals(FeedType.STOCK))
        {
            List<Stock> stockNotProcessed = stockService.findNotProcessed();

            for(Stock stock : stockNotProcessed)
            {
                List<Audit> auditList = auditService.findBy(stock.getProductCode(),FeedType.STOCK, stock.getImportOrigin(), stock.getWarehouse());
                stock.setProcessed(true);

                if(auditList == null || auditList.size()== 0)
                {
                    stockService.save(stock);
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
        }

        else if(feedType.equals(FeedType.MEDIA))
        {
            List<Media> mediaNotProcessed = mediaService.findNotProcessed();

            for(Media media : mediaNotProcessed)
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
        }

        else if(feedType.equals(FeedType.MERCHANDISE))
        {
            List<Merchandise> merchandiseNotProcessed = merchandiseService.findNotProcessed();

            for(Merchandise merchandise : merchandiseNotProcessed)
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
        }

        else if(feedType.equals(FeedType.CLASSIFICATION))
        {
            List<Classification> classificationNotProcessed = classificationService.findNotProcessed();

            for(Classification classification : classificationNotProcessed)
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
        }
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
