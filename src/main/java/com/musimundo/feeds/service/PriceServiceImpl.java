package com.musimundo.feeds.service;

import com.csvreader.CsvWriter;
import com.musimundo.feeds.beans.Price;
import com.musimundo.feeds.beans.PriceReport;
import com.musimundo.feeds.dao.PriceDao;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("priceService")
@Transactional
public class PriceServiceImpl implements PriceService {

    @Autowired
    PriceDao dao;

    @Override
    public Price findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Price> findByProductCode(String productCode) {
        return dao.findByProductCode(productCode);
    }

    @Override
    public List<Price> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Price> findNotProcessed() {
        return dao.findNotProcessed();
    }

    @Override
    public void save(Price price) {
        dao.save(price);
    }

    @Override
    public void update(Price price) {

        Price entity = dao.findById(price.getId());

        if(entity != null)
        {
            entity.setProductCode(price.getProductCode());
            entity.setCurrency(price.getCurrency());
            entity.setOnlinePrice(price.getOnlinePrice());
            entity.setStorePrice(price.getStorePrice());
            entity.setHasPriority(price.getHasPriority());
            entity.setImportOrigin(price.getImportOrigin());
            entity.setProcessingDate(price.getProcessingDate());
            entity.setFeedStatus(price.getFeedStatus());
            entity.setErrorDescription(price.getErrorDescription());
            entity.setCompany(price.getCompany());
        }
    }

    @Override
    public PriceReport getReport() {
        PriceReport report = new PriceReport();
        report.setCountTotal(dao.countAll());
        report.setCountOk(dao.count(FeedStatus.OK));
        report.setCountWarning(dao.count(FeedStatus.WARNING));
        report.setCountError(dao.count(FeedStatus.ERROR));
        report.setCountNotProcessed(dao.count(FeedStatus.NOT_PROCESSED));

        return report;
    }

    @Override
    public PriceReport getReport(List<Price> priceList) {
        int all = 0;
        int ok = 0;
        int warning = 0;
        int error = 0;
        int notProcessed = 0;

        for(Price price : priceList)
        {
            all++;

            if(price.getFeedStatus().equals(FeedStatus.OK))
                ok++;

            else if(price.getFeedStatus().equals(FeedStatus.WARNING))
                warning++;

            else if(price.getFeedStatus().equals(FeedStatus.ERROR))
                error++;

            else if(price.getFeedStatus().equals(FeedStatus.NOT_PROCESSED))
                notProcessed++;
        }

        PriceReport priceReport = new PriceReport();

        priceReport.setCountTotal(Long.valueOf(all));
        priceReport.setCountOk(Long.valueOf(ok));
        priceReport.setCountWarning(Long.valueOf(warning));
        priceReport.setCountError(Long.valueOf(error));
        priceReport.setCountNotProcessed(Long.valueOf(notProcessed));

        return priceReport;
    }

    @Override
    public PriceReport getReportByDate(Date fechaDesde, Date fechaHasta) {
    	PriceReport report = new PriceReport();
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
    public PriceReport getReportByCode(String code) {
    	PriceReport report = new PriceReport();
        report.setCountTotal(dao.countAllByCode(code));
        report.setCountOk(dao.countByCode(code, FeedStatus.OK));
        report.setCountWarning(dao.countByCode(code, FeedStatus.WARNING));
        report.setCountError(dao.countByCode(code, FeedStatus.ERROR));
        report.setCountNotProcessed(dao.countByCode(code, FeedStatus.NOT_PROCESSED));

        return report;
    }

	@Override
	public List<Price> findPriceByDate(Date desde, Date hasta) {
		//control si fechahasta es nula o igual a fecha desde
        if(hasta == null || hasta.equals(desde)) {
        	Calendar dateAfter = Calendar.getInstance(); 
        	dateAfter.setTime(desde); 
        	dateAfter.add(Calendar.DATE, 1);
        	hasta = dateAfter.getTime();
        }
		return dao.findPriceByDate(desde, hasta);
	}

    @Override
    public File getCsv(List<Price> priceList, Filter filter) {
        File file = null;
        String fileName;
        CsvWriter writer = null;

        if(filter.equals(Filter.ALL_REGISTERS))
            fileName = nombreArchivoProcesadoPrecio();

        else
            fileName = nombreArchivoNoProcesadoCorrectamentePrecio();

        try
        {

            file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file, true);
            writer = new CsvWriter(fileWriter, ',');

            writer.write("Product Code");
            writer.write("Currency");
            writer.write("Online Price");
            writer.write("Store Price");
            writer.write("Has Priority");
            writer.write("Import Origin");
            writer.write("Processing Date");
            writer.write("Feed Status");
            writer.write("Error Description");
            writer.write("Empresa");
//            writer.write("processed");
            writer.endRecord();

            if(filter.equals(Filter.ALL_REGISTERS))
            {
                for (Price price : priceList)
                {
                    writer.write(price.getProductCode());
                    writer.write(price.getCurrency());
                    writer.write(price.getOnlinePrice().toString());
                    writer.write(price.getStorePrice().toString());
                    writer.write(Boolean.toString(price.getHasPriority()));
                    writer.write(price.getImportOrigin());
                    writer.write(dateToString(price.getProcessingDate()));
                    writer.write(price.getFeedStatus().name());
                    writer.write(price.getErrorDescription());
                    writer.write(price.getCompany());
                    writer.endRecord();
                }
            }

            else if(filter.equals(Filter.ONLY_NOT_OK))
            {
                for (Price price : priceList)
                {
                    FeedStatus status = price.getFeedStatus();
                    if(status.equals(FeedStatus.WARNING) || status.equals(FeedStatus.ERROR) || status.equals(FeedStatus.NOT_PROCESSED))
                    {
                        writer.write(price.getProductCode());
                        writer.write(price.getCurrency());
                        writer.write(price.getOnlinePrice().toString());
                        writer.write(price.getStorePrice().toString());
                        writer.write(Boolean.toString(price.getHasPriority()));
                        writer.write(price.getImportOrigin());
                        writer.write(dateToString(price.getProcessingDate()));
                        writer.write(price.getFeedStatus().name());
                        writer.write(price.getErrorDescription());
                        writer.write(price.getCompany());
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
    public PriceReport getReport(List<Price> priceList, String importOrigin) {
        int all = 0;
        int ok = 0;
        int warning = 0;
        int error = 0;
        int notProcessed = 0;

        for(Price price : priceList)
        {
            if(price.getImportOrigin().equals(importOrigin))
            {
                all++;

                if(price.getFeedStatus().equals(FeedStatus.OK))
                    ok++;

                else if(price.getFeedStatus().equals(FeedStatus.WARNING))
                    warning++;

                else if(price.getFeedStatus().equals(FeedStatus.ERROR))
                    error++;

                else if(price.getFeedStatus().equals(FeedStatus.NOT_PROCESSED))
                    notProcessed++;
            }
        }

        PriceReport priceReport = new PriceReport();

        priceReport.setImportOrigin(importOrigin);
        priceReport.setCountTotal(Long.valueOf(all));
        priceReport.setCountOk(Long.valueOf(ok));
        priceReport.setCountWarning(Long.valueOf(warning));
        priceReport.setCountError(Long.valueOf(error));
        priceReport.setCountNotProcessed(Long.valueOf(notProcessed));

        return priceReport;
    }

    @Override
    public List<PriceReport> getReportList(List<Price> priceList) {
        List<String> importOriginList = getImportOrigin(priceList);
        List<PriceReport> priceReportList = new ArrayList<>();

        for(String importOrigin : importOriginList)
        {
            PriceReport priceReport = getReport(priceList, importOrigin);
            priceReportList.add(priceReport);
        }

        return priceReportList;
    }

    @Override
    public List<String> getImportOrigin(List<Price> priceList) {
        List<String> importOriginList = new ArrayList<>();

        if(priceList == null)
            return importOriginList;

        for(Price price : priceList)
        {
            String importOrigin = price.getImportOrigin();
            if(!importOriginList.contains(importOrigin))
                importOriginList.add(importOrigin);
        }

        return importOriginList;
    }

    @Override
    public Company getCompany(List<Price> priceList) {
        int carsa = 0;
        int emsa = 0;
        Company res;

        for(Price price : priceList)
        {
            if(price.getCompany() == null)
                continue;

            if(price.getCompany().equals("C"))
                carsa++;

            else if(price.getCompany().equals("E"))
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

    private String nombreArchivoProcesadoPrecio()
    {
        Calendario calendario = new Calendario();

        return "precio-procesado-" + calendario.getFechaYHora() + ".csv";
    }

    private String nombreArchivoNoProcesadoCorrectamentePrecio()
    {
        Calendario calendario = new Calendario();

        return "precio-no-procesado-correctamente-" + calendario.getFechaYHora() + ".csv";
    }

    private String dateToString(Date date)
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        if(date == null)
            return "";

        return dateFormat.format(date);
    }
}
