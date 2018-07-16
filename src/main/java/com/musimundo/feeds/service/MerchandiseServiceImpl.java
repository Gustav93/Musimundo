package com.musimundo.feeds.service;

import com.csvreader.CsvWriter;
import com.musimundo.feeds.beans.Merchandise;
import com.musimundo.feeds.beans.MerchandiseReport;
import com.musimundo.feeds.dao.MerchandiseDao;
import com.musimundo.utilities.Calendario;
import com.musimundo.utilities.Company;
import com.musimundo.utilities.FeedStatus;
import com.musimundo.utilities.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("merchandiseService")
@Transactional
public class MerchandiseServiceImpl implements MerchandiseService {

    @Autowired
    MerchandiseDao dao;

    @Override
    public Merchandise findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Merchandise> findByProductCode(String productCode) {
        return dao.findByProductCode(productCode);
    }

    @Override
    public List<Merchandise> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Merchandise> findNotProcessed() {
        return dao.findNotProcessed();
    }

    @Override
    public void save(Merchandise merchandise) {
        dao.save(merchandise);
    }

    @Override
    public void update(Merchandise merchandise) {

        Merchandise entity = dao.findById(merchandise.getId());

        if(entity != null)
        {
            entity.setSource(merchandise.getSource());
            entity.setRefType(merchandise.getRefType());
            entity.setTarget(merchandise.getTarget());
            entity.setRelationship(merchandise.getRelationship());
            entity.setQualifier(merchandise.getQualifier());
            entity.setPreselected(merchandise.getPreselected());
            entity.setImportOrigin(merchandise.getImportOrigin());
            entity.setProcessingDate(merchandise.getProcessingDate());
            entity.setFeedStatus(merchandise.getFeedStatus());
            entity.setErrorDescription(merchandise.getErrorDescription());
            entity.setCompany(merchandise.getCompany());
            entity.setProcessed(merchandise.getProcessed());
        }
    }

    @Override
    public MerchandiseReport getReport() {
        MerchandiseReport report = new MerchandiseReport();

        report.setCountTotal(dao.countAll());
        report.setCountOk(dao.count(FeedStatus.OK));
        report.setCountWarning(dao.count(FeedStatus.WARNING));
        report.setCountError(dao.count(FeedStatus.ERROR));
        report.setCountNotProcessed(dao.count(FeedStatus.NOT_PROCESSED));

        return report;
    }
    
    @Override
    public MerchandiseReport getReportByDate(Date fechaDesde, Date fechaHasta) {
    	MerchandiseReport report = new MerchandiseReport();
        //control si fechahasta es nula o igual a fecha desde
        if(fechaHasta == null || fechaHasta.equals(fechaDesde)) {
        	Calendar dateAfter = Calendar.getInstance(); 
        	dateAfter.setTime(fechaDesde); 
        	dateAfter.add(Calendar.DATE, 1);
        	fechaHasta = dateAfter.getTime();
        }
        
        report.setCountTotal(dao.countAllByDate(fechaDesde, fechaHasta));
        report.setCountOk(dao.countByDate(fechaDesde, fechaHasta, FeedStatus.OK));
        report.setCountWarning(dao.countByDate(fechaDesde, fechaHasta, FeedStatus.WARNING));
        report.setCountError(dao.countByDate(fechaDesde, fechaHasta, FeedStatus.ERROR));
        report.setCountNotProcessed(dao.countByDate(fechaDesde, fechaHasta, FeedStatus.NOT_PROCESSED));

        return report;
    }

    @Override
    public MerchandiseReport getReportByCode(String code) {
    	MerchandiseReport report = new MerchandiseReport();
        report.setCountTotal(dao.countAllByCode(code));
        report.setCountOk(dao.countByCode(code, FeedStatus.OK));
        report.setCountWarning(dao.countByCode(code, FeedStatus.WARNING));
        report.setCountError(dao.countByCode(code, FeedStatus.ERROR));
        report.setCountNotProcessed(dao.countByCode(code, FeedStatus.NOT_PROCESSED));

        return report;
    }

	@Override
	public List<Merchandise> findMerchandiseByDate(Date desde, Date hasta) {
		//control si fechahasta es nula o igual a fecha desde
        if(hasta == null || hasta.equals(desde)) {
        	Calendar dateAfter = Calendar.getInstance(); 
        	dateAfter.setTime(desde); 
        	dateAfter.add(Calendar.DATE, 1);
        	hasta = dateAfter.getTime();
        }
		return dao.findMerchandiseByDate(desde, hasta);
	}

    @Override
    public File getCsv(List<Merchandise> merchandiseList, Filter filter) {
        File file = null;
        String fileName;
        CsvWriter writer = null;

        if(filter.equals(Filter.ALL_REGISTERS))
            fileName = nombreArchivoProcesadoMerchandise();

        else
            fileName = nombreArchivoNoProcesadoCorrectamenteMerchandise();

        try
        {

            file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file, true);
            writer = new CsvWriter(fileWriter, ',');

            writer.write("Source");
            writer.write("Ref Type");
            writer.write("Target");
            writer.write("Relationship");
            writer.write("Qualifier");
            writer.write("Preselected");
            writer.write("Import Origin");
            writer.write("Processing Date");
            writer.write("Feed Status");
            writer.write("Error Description");
            writer.write("Empresa");
//            writer.write("processed");
            writer.endRecord();

            if(filter.equals(Filter.ALL_REGISTERS))
            {
                for (Merchandise merchandise : merchandiseList)
                {
                    writer.write(merchandise.getSource());
                    writer.write(merchandise.getRefType());
                    writer.write(merchandise.getTarget());
                    writer.write(merchandise.getRelationship());
                    writer.write(merchandise.getQualifier());
                    writer.write(merchandise.getPreselected());
                    writer.write(merchandise.getImportOrigin());
                    writer.write(dateToString(merchandise.getProcessingDate()));
                    writer.write(merchandise.getFeedStatus().name());
                    writer.write(merchandise.getErrorDescription());
                    writer.write(merchandise.getCompany());
                    writer.endRecord();
                }
            }

            else if(filter.equals(Filter.ONLY_NOT_OK))
            {
                for (Merchandise merchandise : merchandiseList)
                {
                    FeedStatus status = merchandise.getFeedStatus();
                    if(status.equals(FeedStatus.WARNING) || status.equals(FeedStatus.ERROR) || status.equals(FeedStatus.NOT_PROCESSED))
                    {
                        writer.write(merchandise.getSource());
                        writer.write(merchandise.getRefType());
                        writer.write(merchandise.getTarget());
                        writer.write(merchandise.getRelationship());
                        writer.write(merchandise.getQualifier());
                        writer.write(merchandise.getPreselected());
                        writer.write(merchandise.getImportOrigin());
                        writer.write(dateToString(merchandise.getProcessingDate()));
                        writer.write(merchandise.getFeedStatus().name());
                        writer.write(merchandise.getErrorDescription());
                        writer.write(merchandise.getCompany());
                        writer.endRecord();
                    }
                }
            }

            fileWriter.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            writer.close();
        }

        return file;
    }

    @Override
    public MerchandiseReport getReport(List<Merchandise> merchandiseList) {
        int all = 0;
        int ok = 0;
        int warning = 0;
        int error = 0;
        int notProcessed = 0;

        for(Merchandise merchandise : merchandiseList)
        {
            all++;

            if(merchandise.getFeedStatus().equals(FeedStatus.OK))
                ok++;

            else if(merchandise.getFeedStatus().equals(FeedStatus.WARNING))
                warning++;

            else if(merchandise.getFeedStatus().equals(FeedStatus.ERROR))
                error++;

            else if(merchandise.getFeedStatus().equals(FeedStatus.NOT_PROCESSED))
                notProcessed++;
        }

        MerchandiseReport merchandiseReport = new MerchandiseReport();

        merchandiseReport.setCountTotal(Long.valueOf(all));
        merchandiseReport.setCountOk(Long.valueOf(ok));
        merchandiseReport.setCountWarning(Long.valueOf(warning));
        merchandiseReport.setCountError(Long.valueOf(error));
        merchandiseReport.setCountNotProcessed(Long.valueOf(notProcessed));

        return merchandiseReport;
    }

    @Override
    public MerchandiseReport getReport(List<Merchandise> merchandiseList, String importOrigin) {
        int all = 0;
        int ok = 0;
        int warning = 0;
        int error = 0;
        int notProcessed = 0;

        for(Merchandise merchandise : merchandiseList)
        {
            if(merchandise.getImportOrigin().equals(importOrigin))
            {
                all++;

                if(merchandise.getFeedStatus().equals(FeedStatus.OK))
                    ok++;

                else if(merchandise.getFeedStatus().equals(FeedStatus.WARNING))
                    warning++;

                else if(merchandise.getFeedStatus().equals(FeedStatus.ERROR))
                    error++;

                else if(merchandise.getFeedStatus().equals(FeedStatus.NOT_PROCESSED))
                    notProcessed++;
            }
        }

        MerchandiseReport merchandiseReport = new MerchandiseReport();

        merchandiseReport.setImportOrigin(importOrigin);
        merchandiseReport.setCountTotal(Long.valueOf(all));
        merchandiseReport.setCountOk(Long.valueOf(ok));
        merchandiseReport.setCountWarning(Long.valueOf(warning));
        merchandiseReport.setCountError(Long.valueOf(error));
        merchandiseReport.setCountNotProcessed(Long.valueOf(notProcessed));

        return merchandiseReport;
    }

    @Override
    public List<MerchandiseReport> getReportList(List<Merchandise> merchandiseList) {
        List<String> importOriginList = getImportOrigin(merchandiseList);
        List<MerchandiseReport> merchandiseReportList = new ArrayList<>();

        for(String importOrigin : importOriginList)
        {
            MerchandiseReport merchandiseReport = getReport(merchandiseList, importOrigin);
            merchandiseReportList.add(merchandiseReport);
        }

        return merchandiseReportList;
    }

    @Override
    public List<String> getImportOrigin(List<Merchandise> merchandiseList) {
        List<String> importOriginList = new ArrayList<>();

        if(merchandiseList == null)
            return importOriginList;

        for(Merchandise merchandise : merchandiseList)
        {
            String importOrigin = merchandise.getImportOrigin();
            if(!importOriginList.contains(importOrigin))
                importOriginList.add(importOrigin);
        }

        return importOriginList;
    }

    @Override
    public Company getCompany(List<Merchandise> merchandiseList) {
        int carsa = 0;
        int emsa = 0;
        Company res;

        for(Merchandise merchandise : merchandiseList)
        {
            if(merchandise.getCompany() == null)
                continue;

            if(merchandise.getCompany().equals("C"))
                carsa++;

            else if(merchandise.getCompany().equals("E"))
                emsa++;
        }

        if(carsa>0 && emsa<=0)
            res = Company.CARSA;

        else if(carsa<=0 && emsa > 0)
            res = Company.EMSA;

        else
            res = Company.UNDEFINED;

        return res;
    }

    @Override
    public List<Merchandise> cloneMerchandiseList(List<Merchandise> merchandiseList) {
        List<Merchandise> res = new ArrayList<>();

        for(Merchandise merchandise : merchandiseList)
        {
            Merchandise merchandiseCopy = new Merchandise();

            merchandiseCopy.setId(merchandise.getId());
            merchandiseCopy.setSource(merchandise.getSource());
            merchandiseCopy.setRefType(merchandise.getRefType());
            merchandiseCopy.setTarget(merchandise.getTarget());
            merchandiseCopy.setRelationship(merchandise.getRelationship());
            merchandiseCopy.setQualifier(merchandise.getQualifier());
            merchandiseCopy.setPreselected(merchandise.getPreselected());
            merchandiseCopy.setImportOrigin(merchandise.getImportOrigin());
            merchandiseCopy.setProcessingDate(merchandise.getProcessingDate());
            merchandiseCopy.setFeedStatus(merchandise.getFeedStatus());
            merchandiseCopy.setErrorDescription(merchandise.getErrorDescription());
            merchandiseCopy.setCompany(merchandise.getCompany());
            merchandiseCopy.setProcessed(merchandise.getProcessed());

            res.add(merchandiseCopy);
        }

        return res;
    }

    @Override
    public boolean updateStateByTypeAndImport(FeedStatus feedStatus, String errorDescription, String company) {
        return dao.updateStateByTypeAndImport(feedStatus, errorDescription, company);
    }

    private String nombreArchivoProcesadoMerchandise()
    {
        Calendario calendario = new Calendario();

        return "merchandise-procesado" + calendario.getFechaYHora() + ".csv";
    }

    private String nombreArchivoNoProcesadoCorrectamenteMerchandise()
    {
        Calendario calendario = new Calendario();
        return "merchandise-no-procesado-correctamente-" + calendario.getFechaYHora() + ".csv";
    }

    private String dateToString(Date date)
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        if(date == null)
            return "";

        return dateFormat.format(date);
    }
    
    @Override
    public void insertValues(List<Merchandise> merchandises) throws ParseException {
    	if(merchandises.size()<20000) {
    		String insert = makeInsert(merchandises);
    		boolean insertOk = dao.insertMerchandiselist(insert);
    	}else{    		
    		List<List<Merchandise>> arreglosMerchandise = new ArrayList();
    		int cantArreglos = (merchandises.size()/20000) + 1;
    		int cantidadPorArreglo = merchandises.size()/cantArreglos;
    		int inicioSubArreglo = 0;
    		int finSubArreglo = cantidadPorArreglo;

    		for(int arreglos=0;arreglos<cantArreglos; arreglos++) {
    			arreglosMerchandise.add(merchandises.subList(inicioSubArreglo, finSubArreglo));
                inicioSubArreglo=finSubArreglo;
                finSubArreglo+=cantidadPorArreglo;
    		}

            if((cantArreglos*cantidadPorArreglo)%2 != 0)
            {
                List<Merchandise> lastMerchandise = new ArrayList<>();
                lastMerchandise.add(merchandises.get(merchandises.size()-1));
                arreglosMerchandise.add(lastMerchandise);
            }

    		for(List<Merchandise> arreglo:arreglosMerchandise) {
    			String insert = makeInsert(arreglo);
        		boolean insertOk = dao.insertMerchandiselist(insert);
    		}    		
    	}
    }
    
    public String makeInsert(List<Merchandise> merchandises) {
    	String insert = "INSERT INTO musimundo.merchandise " + 
    			"(SOURCE, REF_TYPE, TARGET, RELATIONSHIP, QUALIFIER, PRESELECTED, IMPORT_ORIGIN, "
    			+ "PROCESSING_DATE, FEED_STATUS, ERROR_DESCRIPTION, COMPANY, PROCESSED) VALUES";
		int cont = 0;
		for(Merchandise merchandise:merchandises) {
			if(cont==0) {
				insert += merchandise.toInsert();
			}else {
				insert +=","+ merchandise.toInsert();
			}
			cont++;
		}
		return insert;
    }
}
