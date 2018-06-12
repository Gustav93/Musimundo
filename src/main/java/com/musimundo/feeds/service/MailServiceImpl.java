package com.musimundo.feeds.service;

import com.musimundo.feeds.beans.*;
import com.musimundo.utilities.Company;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.List;
import java.util.Properties;

@Service("mailService")
public class MailServiceImpl implements MailService {

    private final String EMAILSENDER = "gsanchez@musi.com.ar";
    private final String PWD = "fff0303456fff";
    private String[] destinatarios = {"gsanchez@musi.com.ar"};
    private String[] destinatariosCARSA = {"gsanchez@musi.com.ar"};
    private String[] destinatariosEMSA = {"gsanchez@musi.com.ar"};
//    private String[] destinatariosCARSA = {"gsanchez@musi.com.ar", "cbaez@musi.com.ar", "jbasombr@musi.com.ar", "marcelo.hassan@grupocarsa.com", "nestor.gatter@grupocarsa.com", "federico.henchoz@grupocarsa.com"};
//    private String[] destinatariosEMSA = {"gsanchez@musi.com.ar", "cbaez@musi.com.ar", "jbasombr@musi.com.ar", "alejandro.brun@emusimundo.com", "amartin@conmega.com.ar", "cbuffa@emusimundo.com"};

    @Override
    public void sendProductMail(List<ProductReport> productReportList, File attachment, Company company) {
        String asuntoMail;

        if(company.equals(Company.CARSA))
        {
            asuntoMail = "Productos Procesados (CARSA)";
            destinatarios = destinatariosCARSA;
        }


        else if (company.equals(Company.EMSA))
        {
            asuntoMail = "Productos Procesados (EMSA)";
            destinatarios = destinatariosEMSA;
        }

        else
            asuntoMail = "Productos Procesados";

        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");

        // Nombre del host de correo, es smtp.gmail.com
        props.setProperty("mail.smtp.host", "smtp.gmail.com");

        // TLS si está disponible
        props.setProperty("mail.smtp.starttls.enable", "true");

        // Puerto de gmail para envio de correos
        props.setProperty("mail.smtp.port","587");

        // Nombre del usuario
        props.setProperty("mail.smtp.user", "gsanchez@musi.com.ar");

        // Si requiere o no usuario y password para conectarse.
        props.setProperty("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

//        File file = writer.getCsvProductosNoProcesadosCorrectamente();
        String fileName = attachment.getName();

        Transport t;
        try
        {
            MimeMultipart multipart = new MimeMultipart();

            if(!isEmpty(attachment))
            {
                BodyPart attachedFile = new MimeBodyPart();
                attachedFile.setDataHandler(new DataHandler(new FileDataSource(fileName)));
                attachedFile.setFileName(fileName);
                multipart.addBodyPart(attachedFile);
            }

//            Agrego el cuerpo del mail
            BodyPart texto = new MimeBodyPart();
            StringBuilder sb = new StringBuilder();

            for(ProductReport reporte : productReportList)
                sb.append(reporte.toString() + "\n\n");

//            sb.append(productReportList.toString());
            texto.setText(sb.toString());
            multipart.addBodyPart(texto);

            MimeMessage message = new MimeMessage(session);

            // Quien envia el correo
            message.setFrom(new InternetAddress(EMAILSENDER));

            // A quien va dirigido
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.addRecipients(Message.RecipientType.TO, agregarDestinatarios());

//            message.setSubject("Productos No Procesados Correctamente");
            message.setSubject(asuntoMail);

            message.setContent(multipart);

            t = session.getTransport("smtp");
            t.connect(EMAILSENDER, PWD);

            t.sendMessage(message,message.getAllRecipients());

            t.close();
//            attachment.delete();
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void sendPriceMail(List<PriceReport> priceReportList, File attachment, Company company) {
        String asuntoMail;

        if(company.equals(Company.CARSA))
        {
            asuntoMail = "Precios Procesados (CARSA)";
            destinatarios = destinatariosCARSA;
        }


        else if (company.equals(Company.EMSA))
        {
            asuntoMail = "Precios Procesados (EMSA)";
            destinatarios = destinatariosEMSA;
        }

        else
            asuntoMail = "Precios Procesados";

        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");

        // Nombre del host de correo, es smtp.gmail.com
        props.setProperty("mail.smtp.host", "smtp.gmail.com");

        // TLS si está disponible
        props.setProperty("mail.smtp.starttls.enable", "true");

        // Puerto de gmail para envio de correos
        props.setProperty("mail.smtp.port","587");

        // Nombre del usuario
        props.setProperty("mail.smtp.user", "gsanchez@musi.com.ar");

        // Si requiere o no usuario y password para conectarse.
        props.setProperty("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

//        File file = writer.getCsvProductosNoProcesadosCorrectamente();
        String fileName = attachment.getName();

        Transport t;
        try
        {
            MimeMultipart multipart = new MimeMultipart();

            if(!isEmpty(attachment))
            {
                BodyPart attachedFile = new MimeBodyPart();
                attachedFile.setDataHandler(new DataHandler(new FileDataSource(fileName)));
                attachedFile.setFileName(fileName);
                multipart.addBodyPart(attachedFile);
            }

//            Agrego el cuerpo del mail
            BodyPart texto = new MimeBodyPart();
            StringBuilder sb = new StringBuilder();

            for(PriceReport report : priceReportList)
                sb.append(report.toString() + "\n\n");

//            sb.append(productReportList.toString());
            texto.setText(sb.toString());
            multipart.addBodyPart(texto);

            MimeMessage message = new MimeMessage(session);

            // Quien envia el correo
            message.setFrom(new InternetAddress(EMAILSENDER));

            // A quien va dirigido
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.addRecipients(Message.RecipientType.TO, agregarDestinatarios());

//            message.setSubject("Productos No Procesados Correctamente");
            message.setSubject(asuntoMail);

            message.setContent(multipart);

            t = session.getTransport("smtp");
            t.connect(EMAILSENDER, PWD);

            t.sendMessage(message,message.getAllRecipients());

            t.close();
//            attachment.delete();
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void sendStockMail(List<StockReport> stockReportList, File attachment, Company company) {
        String asuntoMail;

        if(company.equals(Company.CARSA))
        {
            asuntoMail = "Stock Procesado (CARSA)";
            destinatarios = destinatariosCARSA;
        }


        else if (company.equals(Company.EMSA))
        {
            asuntoMail = "Stock Procesado (EMSA)";
            destinatarios = destinatariosEMSA;
        }

        else
            asuntoMail = "Stock Procesado";

        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");

        // Nombre del host de correo, es smtp.gmail.com
        props.setProperty("mail.smtp.host", "smtp.gmail.com");

        // TLS si está disponible
        props.setProperty("mail.smtp.starttls.enable", "true");

        // Puerto de gmail para envio de correos
        props.setProperty("mail.smtp.port","587");

        // Nombre del usuario
        props.setProperty("mail.smtp.user", "gsanchez@musi.com.ar");

        // Si requiere o no usuario y password para conectarse.
        props.setProperty("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

//        File file = writer.getCsvProductosNoProcesadosCorrectamente();
        String fileName = attachment.getName();

        Transport t;
        try
        {
            MimeMultipart multipart = new MimeMultipart();

            if(!isEmpty(attachment))
            {
                BodyPart attachedFile = new MimeBodyPart();
                attachedFile.setDataHandler(new DataHandler(new FileDataSource(fileName)));
                attachedFile.setFileName(fileName);
                multipart.addBodyPart(attachedFile);
            }

//            Agrego el cuerpo del mail
            BodyPart texto = new MimeBodyPart();
            StringBuilder sb = new StringBuilder();

            for(StockReport report : stockReportList)
                sb.append(report.toString() + "\n\n");

//            sb.append(productReportList.toString());
            texto.setText(sb.toString());
            multipart.addBodyPart(texto);

            MimeMessage message = new MimeMessage(session);

            // Quien envia el correo
            message.setFrom(new InternetAddress(EMAILSENDER));

            // A quien va dirigido
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.addRecipients(Message.RecipientType.TO, agregarDestinatarios());

//            message.setSubject("Productos No Procesados Correctamente");
            message.setSubject(asuntoMail);

            message.setContent(multipart);

            t = session.getTransport("smtp");
            t.connect(EMAILSENDER, PWD);

            t.sendMessage(message,message.getAllRecipients());

            t.close();
//            attachment.delete();
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMediaMail(List<MediaReport> mediaReportList, File attachment, Company company) {
        String asuntoMail;

        if(company.equals(Company.CARSA))
        {
            asuntoMail = "Media Procesado (CARSA)";
            destinatarios = destinatariosCARSA;
        }


        else if (company.equals(Company.EMSA))
        {
            asuntoMail = "Media Procesado (EMSA)";
            destinatarios = destinatariosEMSA;
        }

        else
            asuntoMail = "Media Procesado";

        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");

        // Nombre del host de correo, es smtp.gmail.com
        props.setProperty("mail.smtp.host", "smtp.gmail.com");

        // TLS si está disponible
        props.setProperty("mail.smtp.starttls.enable", "true");

        // Puerto de gmail para envio de correos
        props.setProperty("mail.smtp.port","587");

        // Nombre del usuario
        props.setProperty("mail.smtp.user", "gsanchez@musi.com.ar");

        // Si requiere o no usuario y password para conectarse.
        props.setProperty("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

//        File file = writer.getCsvProductosNoProcesadosCorrectamente();
        String fileName = attachment.getName();

        Transport t;
        try
        {
            MimeMultipart multipart = new MimeMultipart();

            if(!isEmpty(attachment))
            {
                BodyPart attachedFile = new MimeBodyPart();
                attachedFile.setDataHandler(new DataHandler(new FileDataSource(fileName)));
                attachedFile.setFileName(fileName);
                multipart.addBodyPart(attachedFile);
            }

//            Agrego el cuerpo del mail
            BodyPart texto = new MimeBodyPart();
            StringBuilder sb = new StringBuilder();

            for(MediaReport report : mediaReportList)
                sb.append(report.toString() + "\n\n");

//            sb.append(productReportList.toString());
            texto.setText(sb.toString());
            multipart.addBodyPart(texto);

            MimeMessage message = new MimeMessage(session);

            // Quien envia el correo
            message.setFrom(new InternetAddress(EMAILSENDER));

            // A quien va dirigido
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.addRecipients(Message.RecipientType.TO, agregarDestinatarios());

//            message.setSubject("Productos No Procesados Correctamente");
            message.setSubject(asuntoMail);

            message.setContent(multipart);

            t = session.getTransport("smtp");
            t.connect(EMAILSENDER, PWD);

            t.sendMessage(message,message.getAllRecipients());

            t.close();
//            attachment.delete();
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMerchandiseMail(List<MerchandiseReport> merchandiseReportList, File attachment, Company company) {
        String asuntoMail;

        if(company.equals(Company.CARSA))
        {
            asuntoMail = "Merchandise Procesado (CARSA)";
            destinatarios = destinatariosCARSA;
        }


        else if (company.equals(Company.EMSA))
        {
            asuntoMail = "Merchandise Procesado (EMSA)";
            destinatarios = destinatariosEMSA;
        }

        else
            asuntoMail = "Merchandise Procesado";

        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");

        // Nombre del host de correo, es smtp.gmail.com
        props.setProperty("mail.smtp.host", "smtp.gmail.com");

        // TLS si está disponible
        props.setProperty("mail.smtp.starttls.enable", "true");

        // Puerto de gmail para envio de correos
        props.setProperty("mail.smtp.port","587");

        // Nombre del usuario
        props.setProperty("mail.smtp.user", "gsanchez@musi.com.ar");

        // Si requiere o no usuario y password para conectarse.
        props.setProperty("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

//        File file = writer.getCsvProductosNoProcesadosCorrectamente();
        String fileName = attachment.getName();

        Transport t;
        try
        {
            MimeMultipart multipart = new MimeMultipart();

            if(!isEmpty(attachment))
            {
                BodyPart attachedFile = new MimeBodyPart();
                attachedFile.setDataHandler(new DataHandler(new FileDataSource(fileName)));
                attachedFile.setFileName(fileName);
                multipart.addBodyPart(attachedFile);
            }

//            Agrego el cuerpo del mail
            BodyPart texto = new MimeBodyPart();
            StringBuilder sb = new StringBuilder();

            for(MerchandiseReport report : merchandiseReportList)
                sb.append(report.toString() + "\n\n");

//            sb.append(productReportList.toString());
            texto.setText(sb.toString());
            multipart.addBodyPart(texto);

            MimeMessage message = new MimeMessage(session);

            // Quien envia el correo
            message.setFrom(new InternetAddress(EMAILSENDER));

            // A quien va dirigido
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.addRecipients(Message.RecipientType.TO, agregarDestinatarios());

//            message.setSubject("Productos No Procesados Correctamente");
            message.setSubject(asuntoMail);

            message.setContent(multipart);

            t = session.getTransport("smtp");
            t.connect(EMAILSENDER, PWD);

            t.sendMessage(message,message.getAllRecipients());

            t.close();
//            attachment.delete();
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void sendClassificationMail(List<ClassificationReport> classificationReportList, File attachment, Company company) {
        String asuntoMail;

        if(company.equals(Company.CARSA))
        {
            asuntoMail = "Clasificacion Procesado (CARSA)";
            destinatarios = destinatariosCARSA;
        }


        else if (company.equals(Company.EMSA))
        {
            asuntoMail = "Clasificacion Procesado (EMSA)";
            destinatarios = destinatariosEMSA;
        }

        else
            asuntoMail = "Clasificacion Procesado";

        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");

        // Nombre del host de correo, es smtp.gmail.com
        props.setProperty("mail.smtp.host", "smtp.gmail.com");

        // TLS si está disponible
        props.setProperty("mail.smtp.starttls.enable", "true");

        // Puerto de gmail para envio de correos
        props.setProperty("mail.smtp.port","587");

        // Nombre del usuario
        props.setProperty("mail.smtp.user", "gsanchez@musi.com.ar");

        // Si requiere o no usuario y password para conectarse.
        props.setProperty("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

//        File file = writer.getCsvProductosNoProcesadosCorrectamente();
        String fileName = attachment.getName();

        Transport t;
        try
        {
            MimeMultipart multipart = new MimeMultipart();

            if(!isEmpty(attachment))
            {
                BodyPart attachedFile = new MimeBodyPart();
                attachedFile.setDataHandler(new DataHandler(new FileDataSource(fileName)));
                attachedFile.setFileName(fileName);
                multipart.addBodyPart(attachedFile);
            }

//            Agrego el cuerpo del mail
            BodyPart texto = new MimeBodyPart();
            StringBuilder sb = new StringBuilder();

            for(ClassificationReport report : classificationReportList)
                sb.append(report.toString() + "\n\n");

//            sb.append(productReportList.toString());
            texto.setText(sb.toString());
            multipart.addBodyPart(texto);

            MimeMessage message = new MimeMessage(session);

            // Quien envia el correo
            message.setFrom(new InternetAddress(EMAILSENDER));

            // A quien va dirigido
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.addRecipients(Message.RecipientType.TO, agregarDestinatarios());

//            message.setSubject("Productos No Procesados Correctamente");
            message.setSubject(asuntoMail);

            message.setContent(multipart);

            t = session.getTransport("smtp");
            t.connect(EMAILSENDER, PWD);

            t.sendMessage(message,message.getAllRecipients());

            t.close();
//            attachment.delete();
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
    }

    private boolean isEmpty(File csvFile) {
        FileReader fr;
        int lineNumber = 0;
        try {

            fr = new FileReader(csvFile);
            LineNumberReader lnr = new LineNumberReader(fr);

            while(lnr.readLine() != null)
                lineNumber++;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineNumber <= 1;
    }

    //transforma el arreglo de string con las direcciones de mail a un arreglo de Address para poder enviar los correos
    private Address[] agregarDestinatarios() throws AddressException
    {
        Address[] destinos = new Address[destinatarios.length];

        for(int i=0; i < destinatarios.length; i++)
            destinos[i] = new InternetAddress(destinatarios[i]);

        return destinos;
    }
}