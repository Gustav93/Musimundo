package com.musimundo.feeds.service;

import com.csvreader.CsvWriter;
import com.musimundo.feeds.beans.Media;
import com.musimundo.feeds.beans.MediaReport;
import com.musimundo.feeds.beans.Stock;
import com.musimundo.feeds.dao.MediaDao;
import com.musimundo.utilities.Calendario;
import com.musimundo.utilities.FeedStatus;
import com.musimundo.utilities.Filter;
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

@Service("mediaService")
@Transactional
public class MediaServiceImpl implements MediaService
{
    @Autowired
    MediaDao dao;

    @Override
    public Media findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<Media> findByProductCode(String productCode) {
        return dao.findByProductCode(productCode);
    }

    @Override
    public List<Media> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Media> findNotProcessed() {
        return dao.findNotProcessed();
    }

    @Override
    public void save(Media media) {
        dao.save(media);
    }

    @Override
    public void update(Media media) {

        Media entity = dao.findById(media.getId());

        if(entity != null)
        {
            entity.setProductCode(media.getProductCode());
            entity.setCodeMedia(media.getCodeMedia());
            entity.setIsDefault(media.getIsDefault());
            entity.setImportOrigin(media.getImportOrigin());
            entity.setProcessingDate(media.getProcessingDate());
            entity.setFeedStatus(media.getFeedStatus());
            entity.setErrorDescription(media.getErrorDescription());
            entity.setCompany(media.getCompany());
        }
    }

    @Override
    public MediaReport getReport() {
        MediaReport report = new MediaReport();
        report.setCountTotal(dao.countAll());
        report.setCountOk(dao.count(FeedStatus.OK));
        report.setCountWarning(dao.count(FeedStatus.WARNING));
        report.setCountError(dao.count(FeedStatus.ERROR));
        report.setCountNotProcessed(dao.count(FeedStatus.NOT_PROCESSED));

        return report;
    }
    
    @Override
    public MediaReport getReportByDate(Date fechaDesde, Date fechaHasta) {
    	MediaReport report = new MediaReport();
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
    public MediaReport getReportByCode(String code) {
    	MediaReport report = new MediaReport();
        report.setCountTotal(dao.countAllByCode(code));
        report.setCountOk(dao.countByCode(code, FeedStatus.OK));
        report.setCountWarning(dao.countByCode(code, FeedStatus.WARNING));
        report.setCountError(dao.countByCode(code, FeedStatus.ERROR));
        report.setCountNotProcessed(dao.countByCode(code, FeedStatus.NOT_PROCESSED));

        return report;
    }

	@Override
	public List<Media> findMediaByDate(Date desde, Date hasta) {
		//control si fechahasta es nula o igual a fecha desde
        if(hasta == null || hasta.equals(desde)) {
        	Calendar dateAfter = Calendar.getInstance(); 
        	dateAfter.setTime(desde); 
        	dateAfter.add(Calendar.DATE, 1);
        	hasta = dateAfter.getTime();
        }
		return dao.findMediaByDate(desde, hasta);
	}

    @Override
    public File getCsv(List<Media> mediaList, Filter filter) {
        File file = null;
        String fileName;
        CsvWriter writer = null;

        if(filter.equals(Filter.ALL_REGISTERS))
            fileName = nombreArchivoProcesadoMedia();

        else
            fileName = nombreArchivoNoProcesadoCorrectamenteMedia();

        try
        {

            file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file, true);
            writer = new CsvWriter(fileWriter, ',');

            writer.write("Product Code");
            writer.write("Code Media");
            writer.write("Is Default");
            writer.write("Import Origin");
            writer.write("Processing Date");
            writer.write("Feed Status");
            writer.write("Error Description");
            writer.write("Empresa");
//            writer.write("processed");
            writer.endRecord();

            if(filter.equals(Filter.ALL_REGISTERS))
            {
                for (Media media : mediaList)
                {
                    writer.write(media.getProductCode());
                    writer.write(media.getCodeMedia());
                    writer.write(Boolean.toString(media.getIsDefault()));
                    writer.write(media.getImportOrigin());
                    writer.write(dateToString(media.getProcessingDate()));
                    writer.write(media.getFeedStatus().name());
                    writer.write(media.getErrorDescription());
                    writer.write(media.getCompany());
                    writer.endRecord();
                }
            }

            else if(filter.equals(Filter.ONLY_NOT_OK))
            {
                for (Media media : mediaList)
                {
                    FeedStatus status = media.getFeedStatus();
                    if(status.equals(FeedStatus.WARNING) || status.equals(FeedStatus.ERROR) || status.equals(FeedStatus.NOT_PROCESSED))
                    {
                        writer.write(media.getProductCode());
                        writer.write(media.getCodeMedia());
                        writer.write(Boolean.toString(media.getIsDefault()));
                        writer.write(media.getImportOrigin());
                        writer.write(dateToString(media.getProcessingDate()));
                        writer.write(media.getFeedStatus().name());
                        writer.write(media.getErrorDescription());
                        writer.write(media.getCompany());
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

    private String dateToString(Date date)
    {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        if(date == null)
            return "";

        return dateFormat.format(date);
    }

    public static String nombreArchivoNoProcesadoCorrectamenteMedia()
    {
        Calendario calendario = new Calendario();

        return "media-no-procesado-correctamente-" + calendario.getFechaYHora() + ".csv";
    }

    public static String nombreArchivoProcesadoMedia()
    {
        Calendario calendario = new Calendario();

        return "media-procesado-" + calendario.getFechaYHora() + ".csv";
    }
}
