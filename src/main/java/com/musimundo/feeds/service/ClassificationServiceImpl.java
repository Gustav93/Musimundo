package com.musimundo.feeds.service;

import com.csvreader.CsvWriter;
import com.musimundo.feeds.beans.Classification;
import com.musimundo.feeds.beans.ClassificationReport;
import com.musimundo.feeds.dao.ClassificationDao;
import com.musimundo.utilities.Calendario;
import com.musimundo.utilities.Company;
import com.musimundo.utilities.FeedStatus;
import com.musimundo.utilities.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("classificacionService")
@Transactional
public class ClassificationServiceImpl implements ClassificationService {

    @Autowired
    ClassificationDao dao;

    @Override
    public Classification findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Classification> findByProductCode(String productCode) {
        return dao.findByProductCode(productCode);
    }

    @Override
    public List<Classification> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Classification> findNotProcessed() {
        return dao.findNotProcessed();
    }

    @Override
    public void save(Classification classification) {
        dao.save(classification);
    }

    @Override
    public void update(Classification classification) {
        Classification entity = dao.findById(classification.getId());

        if(entity != null)
        {
            entity.setProductCode(classification.getProductCode());
            entity.setAttCode(classification.getAttCode());
            entity.setCategoryCode(classification.getCategoryCode());
            entity.setAttValue(classification.getAttValue());
            entity.setImportOrigin(classification.getImportOrigin());
            entity.setProcessingDate(classification.getProcessingDate());
            entity.setFeedStatus(classification.getFeedStatus());
            entity.setErrorDescription(classification.getErrorDescription());
            entity.setCompany(classification.getCompany());
        }
    }

    @Override
    public ClassificationReport getReport() {
        ClassificationReport report = new ClassificationReport();
        report.setCountTotal(dao.countAll());
        report.setCountOk(dao.count(FeedStatus.OK));
        report.setCountWarning(dao.count(FeedStatus.WARNING));
        report.setCountError(dao.count(FeedStatus.ERROR));
        report.setCountNotProcessed(dao.count(FeedStatus.NOT_PROCESSED));

        return report;
    }
    
    @Override
    public ClassificationReport getReportByDate(Date fechaDesde, Date fechaHasta) {
    	ClassificationReport report = new ClassificationReport();
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
    public ClassificationReport getReportByCode(String code) {
    	ClassificationReport report = new ClassificationReport();
        report.setCountTotal(dao.countAllByCode(code));
        report.setCountOk(dao.countByCode(code, FeedStatus.OK));
        report.setCountWarning(dao.countByCode(code, FeedStatus.WARNING));
        report.setCountError(dao.countByCode(code, FeedStatus.ERROR));
        report.setCountNotProcessed(dao.countByCode(code, FeedStatus.NOT_PROCESSED));

        return report;
    }

	@Override
	public List<Classification> findClassificationByDate(Date desde, Date hasta) {
		//control si fechahasta es nula o igual a fecha desde
        if(hasta == null || hasta.equals(desde)) {
        	Calendar dateAfter = Calendar.getInstance(); 
        	dateAfter.setTime(desde); 
        	dateAfter.add(Calendar.DATE, 1);
        	hasta = dateAfter.getTime();
        }
		return dao.findClassificationByDate(desde, hasta);
	}

    @Override
    public File getCsv(List<Classification> classificationList, Filter filter) {
        File file = null;
        String fileName;
        CsvWriter writer = null;

        if(filter.equals(Filter.ALL_REGISTERS))
            fileName = nombreArchivoProcesadoClasificacion();

        else
            fileName = nombreArchivoNoProcesadoCorrectamenteClasificacion();

        try
        {

            file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file, true);
            writer = new CsvWriter(fileWriter, ',');

            writer.write("Product Code");
            writer.write("Att Code");
            writer.write("Category Code");
            writer.write("Att Value");
            writer.write("Import Origin");
            writer.write("Processing Date");
            writer.write("Feed Status");
            writer.write("Error Description");
            writer.write("Empresa");
//            writer.write("processed");
            writer.endRecord();

            if(filter.equals(Filter.ONLY_NOT_OK))
            {
                for (Classification classification : classificationList)
                {
                    FeedStatus status = classification.getFeedStatus();
                    if(status.equals(FeedStatus.WARNING) || status.equals(FeedStatus.ERROR) || status.equals(FeedStatus.NOT_PROCESSED))
                    {
                        writer.write(classification.getProductCode());
                        writer.write(classification.getAttCode());
                        writer.write(classification.getCategoryCode());
                        writer.write(classification.getAttValue());
                        writer.write(classification.getImportOrigin());
                        writer.write(dateToString(classification.getProcessingDate()));
                        writer.write(classification.getFeedStatus().name());
                        writer.write(classification.getErrorDescription());
                        writer.write(classification.getCompany());
                        writer.endRecord();
                    }
                }
            }

            else if(filter.equals(Filter.ALL_REGISTERS))
            {
                for (Classification classification : classificationList)
                {
                    writer.write(classification.getProductCode());
                    writer.write(classification.getAttCode());
                    writer.write(classification.getCategoryCode());
                    writer.write(classification.getAttValue());
                    writer.write(classification.getImportOrigin());
                    writer.write(dateToString(classification.getProcessingDate()));
                    writer.write(classification.getFeedStatus().name());
                    writer.write(classification.getErrorDescription());
                    writer.write(classification.getCompany());
                    writer.endRecord();
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
    public ClassificationReport getReport(List<Classification> classificationList, String importOrigin) {
        int all = 0;
        int ok = 0;
        int warning = 0;
        int error = 0;
        int notProcessed = 0;

        for(Classification classification : classificationList)
        {
            if(classification.getImportOrigin().equals(importOrigin))
            {
                all++;

                if(classification.getFeedStatus().equals(FeedStatus.OK))
                    ok++;

                else if(classification.getFeedStatus().equals(FeedStatus.WARNING))
                    warning++;

                else if(classification.getFeedStatus().equals(FeedStatus.ERROR))
                    error++;

                else if(classification.getFeedStatus().equals(FeedStatus.NOT_PROCESSED))
                    notProcessed++;
            }
        }

        ClassificationReport classificationReport = new ClassificationReport();

        classificationReport.setImportOrigin(importOrigin);
        classificationReport.setCountTotal(Long.valueOf(all));
        classificationReport.setCountOk(Long.valueOf(ok));
        classificationReport.setCountWarning(Long.valueOf(warning));
        classificationReport.setCountError(Long.valueOf(error));
        classificationReport.setCountNotProcessed(Long.valueOf(notProcessed));

        return classificationReport;
    }

    @Override
    public ClassificationReport getReport(List<Classification> classificationList) {
        int all = 0;
        int ok = 0;
        int warning = 0;
        int error = 0;
        int notProcessed = 0;

        for(Classification classification : classificationList)
        {
            all++;

            if(classification.getFeedStatus().equals(FeedStatus.OK))
                ok++;

            else if(classification.getFeedStatus().equals(FeedStatus.WARNING))
                warning++;

            else if(classification.getFeedStatus().equals(FeedStatus.ERROR))
                error++;

            else if(classification.getFeedStatus().equals(FeedStatus.NOT_PROCESSED))
                notProcessed++;
        }

        ClassificationReport classificationReport = new ClassificationReport();

        classificationReport.setCountTotal(Long.valueOf(all));
        classificationReport.setCountOk(Long.valueOf(ok));
        classificationReport.setCountWarning(Long.valueOf(warning));
        classificationReport.setCountError(Long.valueOf(error));
        classificationReport.setCountNotProcessed(Long.valueOf(notProcessed));

        return classificationReport;
    }

    @Override
    public List<ClassificationReport> getReportList(List<Classification> classificationList) {
        List<String> importOriginList = getImportOrigin(classificationList);
        List<ClassificationReport> classificationReportList = new ArrayList<>();

        for(String importOrigin : importOriginList)
        {
            ClassificationReport classificationReport = getReport(classificationList, importOrigin);
            classificationReportList.add(classificationReport);
        }

        return classificationReportList;
    }

    @Override
    public List<String> getImportOrigin(List<Classification> classificationList) {
        List<String> importOriginList = new ArrayList<>();

        if(classificationList == null)
            return importOriginList;

        for(Classification classification : classificationList)
        {
            String importOrigin = classification.getImportOrigin();
            if(!importOriginList.contains(importOrigin))
                importOriginList.add(importOrigin);
        }

        return importOriginList;
    }

    @Override
    public Company getCompany(List<Classification> classificationList) {
        int carsa = 0;
        int emsa = 0;
        Company res;

        for(Classification classification : classificationList)
        {
            if(classification.getCompany() == null)
                continue;

            if(classification.getCompany().equals("C"))
                carsa++;

            else if(classification.getCompany().equals("E"))
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
    public List<Classification> cloneClassificationList(List<Classification> classificationList) {
        List<Classification> res = new ArrayList<>();

        for(Classification classification : classificationList)
        {
            Classification classificationCopy = new Classification();

            classificationCopy.setId(classification.getId());
            classificationCopy.setProductCode(classification.getProductCode());
            classificationCopy.setAttCode(classification.getAttCode());
            classificationCopy.setCategoryCode(classification.getCategoryCode());
            classificationCopy.setAttValue(classification.getAttValue());
            classificationCopy.setImportOrigin(classification.getImportOrigin());
            classificationCopy.setProcessingDate(classification.getProcessingDate());
            classificationCopy.setFeedStatus(classification.getFeedStatus());
            classificationCopy.setErrorDescription(classification.getErrorDescription());
            classificationCopy.setCompany(classification.getCompany());

            res.add(classificationCopy);
        }

        return res;
    }

    @Override
    public boolean updateStateByTypeAndImport(FeedStatus feedStatus, String errorDescription, String company) {
        return dao.updateState(feedStatus, errorDescription, company);
    }

    public static String nombreArchivoProcesadoClasificacion()
    {
        Calendario calendario = new Calendario();

        return "clasificacion-procesado" + calendario.getFechaYHora() + ".csv";
    }

    public static String nombreArchivoNoProcesadoCorrectamenteClasificacion()
    {
        Calendario calendario = new Calendario();
        return "clasificacion-no-procesado-correctamente-" + calendario.getFechaYHora() + ".csv";
    }

    private String dateToString(Date date)
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        if(date == null)
            return "";

        return dateFormat.format(date);
    }
    
    @Override
    public void insertValues(List<Classification> clasificaciones) throws ParseException {
    	if(clasificaciones.size()<25000) {
    		String insert = makeInsert(clasificaciones);
    		boolean insertOk = dao.insertClassificationlist(insert);
    	}else{    		
    		List<List<Classification>> arreglosclasificaciones = new ArrayList();
    		int cantArreglos = (clasificaciones.size()/25000) + 1;
    		int cantidadPorArreglo = clasificaciones.size()/cantArreglos;
    		int inicioSubArreglo = 0;
    		int finSubArreglo = cantidadPorArreglo;

    		for(int arreglos=0;arreglos<cantArreglos; arreglos++) {
    			arreglosclasificaciones.add(clasificaciones.subList(inicioSubArreglo, finSubArreglo));
                inicioSubArreglo+=cantidadPorArreglo;
                finSubArreglo+=cantidadPorArreglo;
    		}

            if((cantArreglos*cantidadPorArreglo)%2 != 0)
            {
                List<Classification> lastClassification = new ArrayList<>();
                lastClassification.add(clasificaciones.get(clasificaciones.size()-1));
                arreglosclasificaciones.add(lastClassification);
            }

    		for(List<Classification> arreglo:arreglosclasificaciones) {
    			String insert = makeInsert(arreglo);
        		boolean insertOk = dao.insertClassificationlist(insert);
    		}    		
    	}
    }
    
    public String makeInsert(List<Classification> clasificaciones) {
    	String insert = "INSERT INTO musimundo.classification " + 
    			"(PRODUCT_CODE, ATT_CODE, CATEGORY_CODE, ATT_VALUE, IMPORT_ORIGIN, "
    			+ "PROCESSING_DATE, FEED_STATUS, ERROR_DESCRIPTION, COMPANY, PROCESSED)" + 
    			"VALUES ";
		int cont = 0;
		for(Classification classificacion:clasificaciones) {
			if(cont==0) {
				insert += classificacion.toInsert();
			}else {
				insert +=","+ classificacion.toInsert();
			}
			cont++;
		}
		return insert;
    }
}
