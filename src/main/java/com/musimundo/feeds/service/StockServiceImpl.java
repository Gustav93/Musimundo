package com.musimundo.feeds.service;

import com.csvreader.CsvWriter;
import com.musimundo.feeds.beans.Stock;
import com.musimundo.feeds.beans.StockReport;
import com.musimundo.feeds.dao.StockDao;
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

@Service("stockService")
@Transactional
public class StockServiceImpl implements StockService {

    @Autowired
    StockDao dao;

    @Override
    public Stock findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Stock> findByProductCode(String productCode) {
        return dao.findByProductCode(productCode);
    }

    @Override
    public List<Stock> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Stock> findNotProcessed() {
        return dao.findNotProcessed();
    }

    @Override
    public void save(Stock stock) {
        dao.save(stock);
    }

    @Override
    public void update(Stock stock) {
        Stock entity = dao.findById(stock.getId());

        if(entity != null)
        {
            entity.setProductCode(stock.getProductCode());
            entity.setStock(stock.getStock());
            entity.setWarehouse(stock.getWarehouse());
            entity.setStatus(stock.getStatus());
            entity.setImportOrigin(stock.getImportOrigin());
            entity.setProcessingDate(stock.getProcessingDate());
            entity.setFeedStatus(stock.getFeedStatus());
            entity.setErrorDescription(stock.getErrorDescription());
            entity.setCompany(stock.getCompany());
        }
    }

    @Override
    public StockReport getReport() {
        StockReport report = new StockReport();
        report.setCountTotal(dao.countAll());
        report.setCountOk(dao.count(FeedStatus.OK));
        report.setCountWarning(dao.count(FeedStatus.WARNING));
        report.setCountError(dao.count(FeedStatus.ERROR));
        report.setCountNotProcessed(dao.count(FeedStatus.NOT_PROCESSED));

        return report;
    }

    @Override
    public StockReport getReportByDate(Date fechaDesde, Date fechaHasta) {
    	StockReport report = new StockReport();
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
    public File getCsv(List<Stock> stockList, Filter filter) {
        File file = null;
        String fileName;
        CsvWriter writer = null;

        if(filter.equals(Filter.ALL_REGISTERS))
            fileName = nombreArchivoProcesadoStock();

        else
            fileName = nombreArchivoNoProcesadoCorrectamenteStock();

        try
        {

            file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file, true);
            writer = new CsvWriter(fileWriter, ',');

            writer.write("Product Code");
            writer.write("Stock");
            writer.write("Warehouse");
            writer.write("Status");
            writer.write("Import Origin");
            writer.write("Processing Date");
            writer.write("Feed Status");
            writer.write("Error Description");
            writer.write("Empresa");
//            writer.write("processed");
            writer.endRecord();

            if(filter.equals(Filter.ALL_REGISTERS))
            {
                for (Stock stock : stockList)
                {
                    writer.write(stock.getProductCode());
                    writer.write(integerToString(stock.getStock()));
                    writer.write(stock.getWarehouse());
                    writer.write(stock.getStatus());
                    writer.write(stock.getImportOrigin());
                    writer.write(dateToString(stock.getProcessingDate()));
                    writer.write(stock.getFeedStatus().name());
                    writer.write(stock.getErrorDescription());
                    writer.write(stock.getCompany());
                    writer.endRecord();
                }
            }

            else if(filter.equals(Filter.ONLY_NOT_OK))
            {
                for (Stock stock : stockList)
                {
                    FeedStatus status = stock.getFeedStatus();
                    if(status.equals(FeedStatus.WARNING) || status.equals(FeedStatus.ERROR) || status.equals(FeedStatus.NOT_PROCESSED))
                    {
                        writer.write(stock.getProductCode());
                        writer.write(integerToString(stock.getStock()));
                        writer.write(stock.getWarehouse());
                        writer.write(stock.getStatus());
                        writer.write(stock.getImportOrigin());
                        writer.write(dateToString(stock.getProcessingDate()));
                        writer.write(stock.getFeedStatus().name());
                        writer.write(stock.getErrorDescription());
                        writer.write(stock.getCompany());
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
    public StockReport getReport(List<Stock> stockList) {
        int all = 0;
        int ok = 0;
        int warning = 0;
        int error = 0;
        int notProcessed = 0;

        for(Stock product : stockList)
        {
            all++;

            if(product.getFeedStatus().equals(FeedStatus.OK))
                ok++;

            else if(product.getFeedStatus().equals(FeedStatus.WARNING))
                warning++;

            else if(product.getFeedStatus().equals(FeedStatus.ERROR))
                error++;

            else if(product.getFeedStatus().equals(FeedStatus.NOT_PROCESSED))
                notProcessed++;
            }

        StockReport stockReport = new StockReport();

        stockReport.setCountTotal(Long.valueOf(all));
        stockReport.setCountOk(Long.valueOf(ok));
        stockReport.setCountWarning(Long.valueOf(warning));
        stockReport.setCountError(Long.valueOf(error));
        stockReport.setCountNotProcessed(Long.valueOf(notProcessed));

        return stockReport;
    }

    @Override
    public StockReport getReport(List<Stock> stockList, String importOrigin) {
        int all = 0;
        int ok = 0;
        int warning = 0;
        int error = 0;
        int notProcessed = 0;

        for(Stock product : stockList)
        {
            if(product.getImportOrigin().equals(importOrigin))
            {
                all++;

                if(product.getFeedStatus().equals(FeedStatus.OK))
                    ok++;

                else if(product.getFeedStatus().equals(FeedStatus.WARNING))
                    warning++;

                else if(product.getFeedStatus().equals(FeedStatus.ERROR))
                    error++;

                else if(product.getFeedStatus().equals(FeedStatus.NOT_PROCESSED))
                    notProcessed++;
            }
        }

        StockReport stockReport = new StockReport();

        stockReport.setImportOrigin(importOrigin);
        stockReport.setCountTotal(Long.valueOf(all));
        stockReport.setCountOk(Long.valueOf(ok));
        stockReport.setCountWarning(Long.valueOf(warning));
        stockReport.setCountError(Long.valueOf(error));
        stockReport.setCountNotProcessed(Long.valueOf(notProcessed));

        return stockReport;
    }

    @Override
    public List<StockReport> getReportList(List<Stock> stockList) {
        List<String> importOriginList = getImportOrigin(stockList);
        List<StockReport> stockReportList = new ArrayList<>();

        for(String importOrigin : importOriginList)
        {
            StockReport stockReport = getReport(stockList, importOrigin);
            stockReportList.add(stockReport);
        }

        return stockReportList;
    }

    @Override
    public List<String> getImportOrigin(List<Stock> stockList) {
        List<String> importOriginList = new ArrayList<>();

        if(stockList == null)
            return importOriginList;

        for(Stock stock : stockList)
        {
            String importOrigin = stock.getImportOrigin();
            if(!importOriginList.contains(importOrigin))
                importOriginList.add(importOrigin);
        }

        return importOriginList;
    }

    @Override
    public Company getCompany(List<Stock> stockList) {
        int carsa = 0;
        int emsa = 0;
        Company res;

        for(Stock stock : stockList)
        {
            if(stock.getCompany() == null)
                continue;

            if(stock.getCompany().equals("C"))
                carsa++;

            else if(stock.getCompany().equals("E"))
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
    public StockReport getReportByCode(String code) {
    	StockReport report = new StockReport();
        report.setCountTotal(dao.countAllByCode(code));
        report.setCountOk(dao.countByCode(code, FeedStatus.OK));
        report.setCountWarning(dao.countByCode(code, FeedStatus.WARNING));
        report.setCountError(dao.countByCode(code, FeedStatus.ERROR));
        report.setCountNotProcessed(dao.countByCode(code, FeedStatus.NOT_PROCESSED));

        return report;
    }

	@Override
	public List<Stock> findStockByDate(Date desde, Date hasta) {
		//control si fechahasta es nula o igual a fecha desde
        if(hasta == null || hasta.equals(desde)) {
        	Calendar dateAfter = Calendar.getInstance(); 
        	dateAfter.setTime(desde); 
        	dateAfter.add(Calendar.DATE, 1);
        	hasta = dateAfter.getTime();
        }
		return dao.findStockByDate(desde, hasta);
	}

    private String nombreArchivoProcesadoStock()
    {
        Calendario calendario = new Calendario();

        return "stock-procesado-" + calendario.getFechaYHora() + ".csv";
    }

    private String nombreArchivoNoProcesadoCorrectamenteStock()
    {
        Calendario calendario = new Calendario();

        return "stock-no-procesado-correctamente-" + calendario.getFechaYHora() + ".csv";
    }

    private String dateToString(Date date)
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        if(date == null)
            return "";

        return dateFormat.format(date);
    }

    private String integerToString(Integer integer)
    {
        if(integer == null)
            return "";

        return integer.toString();
    }
	
	    @Override
    public void insertValues(List<Stock> stocks) throws ParseException {
    	if(stocks.size()<25000) {
    		String insert = makeInsert(stocks);
    		boolean insertOk = dao.insertStocklist(insert);
    	}else{    		
    		List<List<Stock>> arreglosStock = new ArrayList<List<Stock>>();
    		int cantArreglos = stocks.size()/25000;
    		int cantidadPorArreglo = stocks.size()/cantArreglos;
    		int inicioSubArreglo = 0;
    		int finSubArreglo = cantidadPorArreglo;
    		for(int arreglos=0;arreglos<cantArreglos; arreglos++) {
    			arreglosStock.add(stocks.subList(inicioSubArreglo, finSubArreglo)); 
    			inicioSubArreglo+=finSubArreglo;
    			finSubArreglo+=finSubArreglo;
    		}
    		for(List<Stock> arreglo:arreglosStock) {
    			String insert = makeInsert(arreglo);
        		boolean insertOk = dao.insertStocklist(insert);
    		}    		
    	}
    }
    
    public String makeInsert(List<Stock> stocks) {
    	String insert = "INSERT INTO musimundo.stock " + 
    			"(PRODUCT_CODE, STOCK, WAREHOUSE, STATUS, IMPORT_ORIGIN, PROCESSING_DATE, "
    			+ "FEED_STATUS, ERROR_DESCRIPTION, COMPANY, PROCESSED) VALUES";
		int cont = 0;
		for(Stock stock:stocks) {
			if(cont==0) {
				insert += stock.toInsert();
			}else {
				insert +=","+ stock.toInsert();
			}
			cont++;
		}
		return insert;
    }
}
