package com.musimundo.feeds.service;

import com.csvreader.CsvWriter;
import com.musimundo.feeds.beans.*;
import com.musimundo.feeds.dao.MediaDao;
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
            entity.setProcessed(media.getProcessed());
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

    @Override
    public MediaReport getReport(List<Media> mediaList, String importOrigin) {
        int all = 0;
        int ok = 0;
        int warning = 0;
        int error = 0;
        int notProcessed = 0;

        for(Media media : mediaList)
        {
            if(media.getImportOrigin().equals(importOrigin))
            {
                all++;

                if(media.getFeedStatus().equals(FeedStatus.OK))
                    ok++;

                else if(media.getFeedStatus().equals(FeedStatus.WARNING))
                    warning++;

                else if(media.getFeedStatus().equals(FeedStatus.ERROR))
                    error++;

                else if(media.getFeedStatus().equals(FeedStatus.NOT_PROCESSED))
                    notProcessed++;
            }
        }

        MediaReport mediaReport = new MediaReport();

        mediaReport.setImportOrigin(importOrigin);
        mediaReport.setCountTotal(Long.valueOf(all));
        mediaReport.setCountOk(Long.valueOf(ok));
        mediaReport.setCountWarning(Long.valueOf(warning));
        mediaReport.setCountError(Long.valueOf(error));
        mediaReport.setCountNotProcessed(Long.valueOf(notProcessed));

        return mediaReport;
    }

    @Override
    public MediaReport getReport(List<Media> mediaList) {
        int all = 0;
        int ok = 0;
        int warning = 0;
        int error = 0;
        int notProcessed = 0;

        for(Media media : mediaList)
        {
            all++;

            if(media.getFeedStatus().equals(FeedStatus.OK))
                ok++;

            else if(media.getFeedStatus().equals(FeedStatus.WARNING))
                warning++;

            else if(media.getFeedStatus().equals(FeedStatus.ERROR))
                error++;

            else if(media.getFeedStatus().equals(FeedStatus.NOT_PROCESSED))
                notProcessed++;
        }

        MediaReport mediaReport = new MediaReport();

        mediaReport.setCountTotal(Long.valueOf(all));
        mediaReport.setCountOk(Long.valueOf(ok));
        mediaReport.setCountWarning(Long.valueOf(warning));
        mediaReport.setCountError(Long.valueOf(error));
        mediaReport.setCountNotProcessed(Long.valueOf(notProcessed));

        return mediaReport;
}

    @Override
    public List<MediaReport> getReportList(List<Media> mediaList) {
        List<String> importOriginList = getImportOrigin(mediaList);
        List<MediaReport> mediaReportList = new ArrayList<>();

        for(String importOrigin : importOriginList)
        {
            MediaReport mediaReport = getReport(mediaList, importOrigin);
            mediaReportList.add(mediaReport);
        }

        return mediaReportList;
    }

    @Override
    public List<String> getImportOrigin(List<Media> mediaList) {
        List<String> importOriginList = new ArrayList<>();

        if(mediaList == null)
            return importOriginList;

        for(Media media : mediaList)
        {
            String importOrigin = media.getImportOrigin();
            if(!importOriginList.contains(importOrigin))
                importOriginList.add(importOrigin);
        }

        return importOriginList;
    }

    @Override
    public Company getCompany(List<Media> mediaList) {
        int carsa = 0;
        int emsa = 0;
        Company res;

        for(Media media : mediaList)
        {
            if(media.getCompany() == null)
                continue;

            if(media.getCompany().equals("C"))
                carsa++;

            else if(media.getCompany().equals("E"))
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
    public List<Media> cloneMediaList(List<Media> mediaList) {
        List<Media> res = new ArrayList<>();

        for(Media media : mediaList)
        {
            Media mediaCopy = new Media();

            mediaCopy.setId(media.getId());
            mediaCopy.setProductCode(media.getProductCode());
            mediaCopy.setCodeMedia(media.getCodeMedia());
            mediaCopy.setIsDefault(media.getIsDefault());
            mediaCopy.setImportOrigin(media.getImportOrigin());
            mediaCopy.setProcessingDate(media.getProcessingDate());
            mediaCopy.setFeedStatus(media.getFeedStatus());
            mediaCopy.setErrorDescription(media.getErrorDescription());
            mediaCopy.setCompany(media.getCompany());
            mediaCopy.setProcessed(media.getProcessed());

            res.add(mediaCopy);
        }

        return res;
    }

    @Override
    public boolean updateStateByTypeAndImport(FeedStatus status, String errorDescription, String company) {
        return dao.updateStateByTypeAndImport(status,errorDescription,company);
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
    
    @Override
    public void insertValues(List<Media> medias) throws ParseException {
    	if(medias.size()<20000) {
    		String insert = makeInsert(medias);
    		boolean insertOk = dao.insertMedialist(insert);
    	}else{    		
    		List<List<Media>> arreglosMedias = new ArrayList();
    		int cantArreglos = (medias.size()/20000) + 1;
    		int cantidadPorArreglo = medias.size()/cantArreglos;
    		int inicioSubArreglo = 0;
    		int finSubArreglo = cantidadPorArreglo;

    		for(int arreglos=0;arreglos<cantArreglos; arreglos++) {
    			arreglosMedias.add(medias.subList(inicioSubArreglo, finSubArreglo)); 
    			inicioSubArreglo+=cantidadPorArreglo;
    			finSubArreglo+=cantidadPorArreglo;
    		}

            if((cantArreglos*cantidadPorArreglo)%2 != 0)
            {
                List<Media> lastMedia = new ArrayList<>();
                lastMedia.add(medias.get(medias.size()-1));
                arreglosMedias.add(lastMedia);
            }

    		for(List<Media> arreglo:arreglosMedias) {
    			String insert = makeInsert(arreglo);
        		boolean insertOk = dao.insertMedialist(insert);
    		}    		
    	}
    }
    
    public String makeInsert(List<Media> medias) {
    	String insert = "INSERT INTO musimundo.media " + 
    			"(PRODUCT_CODE, CODE_MEDIA, IS_DEFAULT, IMPORT_ORIGIN, PROCESSING_DATE, "
    			+ "FEED_STATUS, ERROR_DESCRIPTION, COMPANY, PROCESSED) VALUES ";
		int cont = 0;
		for(Media media:medias) {
			if(cont==0) {
				insert += media.toInsert();
			}else {
				insert +=","+ media.toInsert();
			}
			cont++;
		}
		return insert;
    }
}
