package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Audit;
import com.musimundo.feeds.beans.Product;
import com.musimundo.utilities.ErrorType;
import com.musimundo.utilities.FeedStatus;
import com.musimundo.utilities.FeedType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("processingFeedService")
@Transactional
public class ProcessingFeedServiceImpl implements ProcessingFeedService {

    @Autowired
    AuditService auditService;

    @Autowired
    ProductService productService;

    @Override
    public void process(FeedType feedType) {

        if(feedType.equals(FeedType.PRODUCT))
        {
            List<Product> productNotProcessed = productService.findNotProcessed();

            for(Product product : productNotProcessed)
            {
                List<Audit> auditList = auditService.findBy(product.getProductCode(),FeedType.PRODUCT, product.getImportOrigin());

                for(Audit audit : auditList)
                {
                    product.setProcessingDate(new Date());

                    if(auditService.getErrorType(audit).equals(ErrorType.SUCCESS))
                        product.setFeedStatus(FeedStatus.OK);

                    product.setErrorDescription(audit.getDescription());
                    product.setProcessed(true);
                    product.setCompany(audit.getCompany());
                    audit.setProcessed(true);

                    auditService.save(audit);
                    productService.save(product);

                    break;
                }
            }

        }

    }
}
