package com.musimundo.feeds.service;

import com.csvreader.CsvWriter;
import com.musimundo.feeds.beans.Product;
import com.musimundo.feeds.beans.ProductReport;
import com.musimundo.feeds.dao.ProductDao;
import com.musimundo.utilities.Calendario;
import com.musimundo.utilities.FeedStatus;
import com.musimundo.utilities.Filter;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao dao;

    @Override
    public Product findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Product> findByProductCode(String productCode) {
        return dao.findByProductCode(productCode);
    }

    @Override
    public List<Product> findAll() {
//        Long r = dao.count(FeedStatus.NOT_PROCESSED);
//        System.out.println(r);
        return dao.findAll();
    }

    @Override
    public List<Product> findNotProcessed() {
        return dao.findNotProcessed();
    }

    @Override
    public void save(Product product) {
        dao.save(product);
    }

    public void update(Product product) {
        Product entity = dao.findById(product.getId());

        if (entity != null) {
            entity.setProductCode(product.getProductCode());
            entity.setEan(product.getEan());
            entity.setBrand(product.getBrand());
            entity.setName(product.getName());
            entity.setCategory(product.getCategory());
            entity.setWeight(product.getWeight());
            entity.setOnlineDateTime(product.getOnlineDateTime());
            entity.setOfflineDateTime(product.getOfflineDateTime());
            entity.setApprovalStatus(product.getApprovalStatus());
            entity.setDescription(product.getDescription());
            entity.setImportOrigin(product.getImportOrigin());
            entity.setProcessingDate(product.getProcessingDate());
            entity.setFeedStatus(product.getFeedStatus());
            entity.setErrorDescription(product.getErrorDescription());
            entity.setCompany(product.getCompany());
        }
    }

    @Override
    public ProductReport getReport() {
        ProductReport report = new ProductReport();
        report.setCountTotal(dao.countAll());
        report.setCountOk(dao.count(FeedStatus.OK));
        report.setCountWarning(dao.count(FeedStatus.WARNING));
        report.setCountError(dao.count(FeedStatus.ERROR));
        report.setCountNotProcessed(dao.count(FeedStatus.NOT_PROCESSED));

        return report;
    }

    @Override
    public ProductReport getReport(List<Product> productList) {

        ProductReport report = new ProductReport();

        int all = productList.size();
        int ok = 0;
        int warning = 0;
        int error = 0;
        int notProcessed = 0;

        for(Product product : productList)
        {
            if(product.getFeedStatus().equals(FeedStatus.OK))
                ok++;

            else if(product.getFeedStatus().equals(FeedStatus.WARNING))
                warning++;

            else if(product.getFeedStatus().equals(FeedStatus.ERROR))
                error++;

            else if(product.getFeedStatus().equals(FeedStatus.NOT_PROCESSED))
                notProcessed++;
        }

        report.setCountTotal(dao.countAll());
        report.setCountOk(dao.count(FeedStatus.OK));
        report.setCountWarning(dao.count(FeedStatus.WARNING));
        report.setCountError(dao.count(FeedStatus.ERROR));
        report.setCountNotProcessed(dao.count(FeedStatus.NOT_PROCESSED));

        return report;
    }

    @Override
    public ProductReport getReportByDate(Date fechaDesde, Date fechaHasta) {
        ProductReport report = new ProductReport();
        //control si fechahasta es nula o igual a fecha desde
        if (fechaHasta == null || fechaHasta.equals(fechaDesde)) {
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
    public ProductReport getReportByCode(String code) {
        ProductReport report = new ProductReport();
        report.setCountTotal(dao.countAllByCode(code));
        report.setCountOk(dao.countByCode(code, FeedStatus.OK));
        report.setCountWarning(dao.countByCode(code, FeedStatus.WARNING));
        report.setCountError(dao.countByCode(code, FeedStatus.ERROR));
        report.setCountNotProcessed(dao.countByCode(code, FeedStatus.NOT_PROCESSED));

        return report;
    }

    @Override
    public List<Product> findProductByDate(Date desde, Date hasta) {
        //control si fechahasta es nula o igual a fecha desde
        if (hasta == null || hasta.equals(desde)) {
            Calendar dateAfter = Calendar.getInstance();
            dateAfter.setTime(desde);
            dateAfter.add(Calendar.DATE, 1);
            hasta = dateAfter.getTime();
        }
        return dao.findProductByDate(desde, hasta);
    }

    @Override
    public File getCsv(List<Product> productList, Filter filter) {
        File file = null;
        String fileName;
        CsvWriter writer = null;

        if (filter.equals(Filter.ALL_REGISTERS))
            fileName = nombreArchivoProcesadoProducto();

        else
            fileName = nombreArchivoNoProcesadoCorrectamenteProducto();

        try {

            file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file, true);
            writer = new CsvWriter(fileWriter, ',');

            writer.write("Product Code");
            writer.write("Ean");
            writer.write("Brand");
            writer.write("Name");
            writer.write("Category");
            writer.write("Weight");
            writer.write("Online Date Time");
            writer.write("Offline Date Time");
            writer.write("Approval Status");
            writer.write("Description");
            writer.write("Import Origin");
            writer.write("Processing Date");
            writer.write("Feed Status");
            writer.write("Error Description");
            writer.write("Empresa");
//            writer.write("processed");
            writer.endRecord();

            if (filter.equals(Filter.ALL_REGISTERS)) {
                for (Product product : productList) {
                    writer.write(product.getProductCode());
                    writer.write(product.getEan());
                    writer.write(product.getBrand());
                    writer.write(product.getName());
                    writer.write(product.getCategory().toString());
                    writer.write(product.getWeight().toString());
                    writer.write(dateToString(product.getOnlineDateTime()));
                    writer.write(dateToString(product.getOfflineDateTime()));
                    writer.write(product.getApprovalStatus());
                    writer.write(product.getDescription());
                    writer.write(product.getImportOrigin());
                    writer.write(dateToString(product.getProcessingDate()));
                    writer.write(product.getFeedStatus().name());
                    writer.write(product.getErrorDescription());
                    writer.write(product.getCompany());
                    writer.endRecord();
                }
            } else if (filter.equals(Filter.ONLY_NOT_OK)) {
                for (Product product : productList) {
                    FeedStatus status = product.getFeedStatus();
                    if (status.equals(FeedStatus.WARNING) || status.equals(FeedStatus.ERROR) || status.equals(FeedStatus.NOT_PROCESSED)) {
                        writer.write(product.getProductCode());
                        writer.write(product.getEan());
                        writer.write(product.getBrand());
                        writer.write(product.getName());
                        writer.write(product.getCategory().toString());
                        writer.write(product.getWeight().toString());
                        writer.write(dateToString(product.getOnlineDateTime()));
                        writer.write(dateToString(product.getOfflineDateTime()));
                        writer.write(product.getApprovalStatus());
                        writer.write(product.getDescription());
                        writer.write(product.getImportOrigin());
                        writer.write(dateToString(product.getProcessingDate()));
                        writer.write(product.getFeedStatus().name());
                        writer.write(product.getErrorDescription());
                        writer.write(product.getCompany());
                        writer.endRecord();
                    }
                }
            }

            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }

        return file;


    }

    private String nombreArchivoProcesadoProducto() {
        Calendario calendario = new Calendario();

        return "producto-procesado-" + calendario.getFechaYHora() + ".csv";
    }

    private String nombreArchivoNoProcesadoCorrectamenteProducto() {
        Calendario calendario = new Calendario();

        return "producto-no-procesado-correctamente-" + calendario.getFechaYHora() + ".csv";
    }

    private String dateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        if (date == null)
            return "";

        return dateFormat.format(date);
    }
}
