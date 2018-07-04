package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.Audit;
import com.musimundo.feeds.dao.AuditDao;
import com.musimundo.utilities.ErrorType;
import com.musimundo.utilities.FeedStatus;
import com.musimundo.utilities.FeedType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("auditService")
@Transactional
public class AuditServiceImpl implements AuditService {

    @Autowired
    AuditDao dao;

    @Override
    public Audit findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Audit> findByProductCode(String productCode) {
        return dao.findBy(productCode);
    }

    @Override
    public List<Audit> findBy(FeedType feedType) {
        return dao.findBy(feedType);
    }

    @Override
    public List<Audit> findBy(String productCode, FeedType feedType) {
        return dao.findBy(productCode, feedType);
    }

    @Override
    public List<Audit> findBy(String productCode, FeedType feedType, String importOrigin, String warehouse) {
        return dao.findBy(productCode, feedType, importOrigin, warehouse);
    }

    @Override
    public List<Audit> findBy(String productCode, FeedType feedType, String importOrigin) {
        return dao.findBy(productCode, feedType, importOrigin);
    }

    @Override
    public List<Audit> findAll() {
        return dao.findAll();
    }

    @Override
    public void save(Audit audit) {
        dao.save(audit);
    }

    @Override
    public ErrorType getErrorType(Audit audit)
    {
        String errorCode = audit.getErrorCode();

        if(errorCode.contains("E"))
            return ErrorType.ERROR;

        else if(errorCode.contains("W"))
            return ErrorType.WARNING;

        else if(errorCode.contains("I"))
            return  ErrorType.SUCCESS;

        else
            throw new IllegalArgumentException("illegal error type");
    }

    @Override
    public void setWarehouseStock(Audit audit) {

        if(!audit.getFeedType().equals(FeedType.STOCK))
            return;

        String res = null;
        Pattern pattern = Pattern.compile("(carsa_\\w{1,4})|(emsa_\\w{1,4})");
        Matcher matcher = pattern.matcher(audit.getDescription());

        if(matcher.find())
            res = matcher.group();

        audit.setWarehouseStock(res);
//        return res;
    }

    @Override
    public void update(Audit audit) {
        Audit entity = dao.findById(audit.getId());

        entity.setAuditLevel(audit.getAuditLevel());
        entity.setAuditType(audit.getAuditType());
        entity.setAuditDate(audit.getAuditDate());
        entity.setErrorCode(audit.getErrorCode());
        entity.setDescription(audit.getDescription());
        entity.setCompany(audit.getCompany());
        entity.setProductCode(audit.getProductCode());
        entity.setImportOrigin(audit.getImportOrigin());
        entity.setFeedType(audit.getFeedType());
        entity.setProcessed(audit.getProcessed());
    }
    
    @Override
    public void insertValues(List<Audit> auditorias) throws ParseException {
    	if(auditorias.size()<20000) {
    		String insert = makeInsert(auditorias);
    		boolean insertOk = dao.insertAuditlist(insert);
    	}else{    		
    		List<List<Audit>> arreglosAuditorias = new ArrayList();
    		int cantArreglos = (auditorias.size()/20000) + 1;
    		int cantidadPorArreglo = auditorias.size()/cantArreglos;
    		int inicioSubArreglo = 0;
    		int finSubArreglo = cantidadPorArreglo;

    		for(int arreglos=0;arreglos<cantArreglos; arreglos++) {
    			arreglosAuditorias.add(auditorias.subList(inicioSubArreglo, finSubArreglo)); 
    			inicioSubArreglo=finSubArreglo;
    			finSubArreglo+=cantidadPorArreglo;
    		}

            if((cantArreglos*cantidadPorArreglo)%2 != 0)
            {
                List<Audit> lastAudit = new ArrayList<>();
                lastAudit.add(auditorias.get(auditorias.size()-1));
                arreglosAuditorias.add(lastAudit);
            }

    		for(List<Audit> arreglo:arreglosAuditorias) {
    			String insert = makeInsert(arreglo);
        		boolean insertOk = dao.insertAuditlist(insert);
    		}    		
    	}
    }
    
    public String makeInsert(List<Audit> auditorias) {
    	String insert = "INSERT INTO musimundo.audit(AUDIT_LEVEL, AUDIT_TYPE, AUDIT_DATE, "
    			+ "ERROR_CODE, DESCRIPTION, COMPANY, PRODUCT_CODE, IMPORT_ORIGIN, FEED_TYPE, "
    			+ "PROCESSED, WAREHOUSE_STOCK) VALUES ";
		int cont = 0;
		for(Audit a:auditorias) {
			if(cont==0) {
				insert += a.toInsert();
			}else {
				insert +=","+ a.toInsert();
			}
			cont++;
		}
		return insert;
    }

    @Override
    public void updateState(FeedType feedType, String importOrigin){
        dao.updateStateByTypeAndImport(feedType, importOrigin);
    }
}
