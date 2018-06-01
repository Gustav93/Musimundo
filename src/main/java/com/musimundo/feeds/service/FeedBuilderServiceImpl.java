package com.musimundo.feeds.service;

import com.csvreader.CsvReader;
import com.musimundo.feeds.beans.Audit;
import com.musimundo.feeds.beans.Price;
import com.musimundo.feeds.beans.Product;
import com.musimundo.utilities.FeedType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Transactional
@Service("feedBuilderService")
public class FeedBuilderServiceImpl implements FeedBuilderService {

    @Autowired
    ProductService productService;

    @Autowired
    PriceService priceService;

    @Autowired
    AuditService auditService;

    private CsvReader reader;

    @Override
    public void createRegister(String path) {

        try {
            reader = new CsvReader(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if(isAudit(path))
            reader.setDelimiter(';');

        try {
            while(reader.readRecord()) {

                if(isProduct(path))
                {
                    Product product = new Product();
                    product.setProductCode(reader.get(0));
                    product.setEan(reader.get(1));
                    product.setBrand(reader.get(2));
                    product.setName(reader.get(3));
                    product.setCategory(Integer.parseInt(reader.get(4)));
                    product.setWeight(Integer.parseInt(reader.get(5)));
                    product.setOnlineDateTime(parseDate(reader.get(6)));
                    product.setOfflineDateTime(parseDate(reader.get(7)));
                    product.setApprovalStatus(reader.get(8));
                    product.setDescription(reader.get(9));
                    product.setImportOrigin(getImportOrigin(path));

                    productService.save(product);
                }

                else if(isPrice(path))
                {
                    Price price = new Price();

                    price.setProductCode(reader.get(0));
                    price.setOnlinePrice(Double.parseDouble(reader.get(1)));
                    price.setCurrency(reader.get(2));
                    price.setStorePrice(Double.parseDouble(reader.get(3)));
                    price.setHasPriority(Boolean.parseBoolean(reader.get(4)));
                    price.setImportOrigin(getImportOrigin(path));

                    priceService.save(price);

                }

                else if(isAudit(path))
                {
                    Audit audit = new Audit();

                    String errorCode = reader.get(3);
                    String feedType = reader.get(8).toLowerCase();

                    if(checkFeedType(feedType) || errorCode.contains("E37"))
                    {
                        audit.setAuditLevel(reader.get(0));
                        audit.setAuditType(reader.get(1));
                        audit.setAuditDate(parseDate(reader.get(2)));
                        audit.setErrorCode(errorCode);
                        audit.setDescription(reader.get(4));
                        audit.setCompany(reader.get(5));
                        audit.setProductCode(reader.get(6));
                        audit.setFeedType(parseFeedType(feedType));

                        String importOrigin = reader.get(7).toLowerCase();

                        if(importOrigin.contains("stock"))
                            getImportOrigin(importOrigin);

                        else if(importOrigin.contains("classification"))
                            importOrigin.replaceAll("classification", "clasificacion");

                        audit.setImportOrigin(importOrigin);

                        if(errorCode.equals("E37"))
                        {
                            fixAudit(audit);
                            if(audit.getProductCode().equals(""))
                                continue;
                        }
                        auditService.save(audit);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private boolean isProduct(String path){
        return path.contains("producto") && !path.contains("_aud");
    }

    private boolean isPrice(String path)
    {
        return path.contains("precio") && !path.contains("_aud");
    }

    private boolean isAudit(String path){
        return path.contains("_aud");
    }

    private Date parseDate(String dateString)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm");
        Date res = null;

        if(dateString == null || dateString.equals(""))
            return null;

        try {
            res = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            res = null;
        }

        return res;
    }

    private String getImportOrigin(String path){
        String patron = "";
        String importOrigin = "";

        if(isProduct(path))
            patron = "producto-\\d+\\.csv";

        else if(isPrice(path))
            patron = "precio-\\d+.csv";

//        else if(feedType.equals("STOCK") && !path.contains("_aud"))
//            patron = "stock-\\d+.csv";
//
//        else if(feedType.equals("MEDIA"))
//            patron = "media-\\d+.csv";
//
//        else if(feedType.equals("MERCHANDISE"))
//            patron = "merchandise-\\d+.csv";
//
//        else if(feedType.equals("CLASSIFICATION"))
//            patron = "clasificacion-\\d+.csv";
//
//        else if(path.contains("_aud") && path.contains("stock"))
//            patron = "stock-\\d+";

        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(path);

        if(matcher.find())
        {
            if(path.contains("_aud") && path.contains("stock"))
                importOrigin = matcher.group() + ".csv";
            else
                importOrigin = matcher.group();
        }

        return importOrigin;
    }

    public static boolean checkFeedType(String feedType)
    {
        final String[] FEED_TYPE ={"classification", "product", "price", "merchandise", "media", "stock"};


        boolean res = false;
        for (String feed : FEED_TYPE)
        {
            if (feed.equals(feedType))
                res = true;
        }
        return res;
    }

    private void fixAudit(Audit audit)
    {
        fixCompany(audit);
        fixFeedType(audit);
        fixProductCode(audit);
    }

    //para corregir el feedType se utilizan expresiones regulares para buscar el tipo de feed en la descripcion del registro
    //ya que es unico el lugar de donde puedo sacar la informacion.
    private void fixFeedType(Audit audit)
    {
        String description = audit.getDescription(); //descripcion del registro
        Pattern pattern = Pattern.compile("classification"); //patron con el que vamos a buscar en la descripcion
        Matcher matcher = pattern.matcher(description); //se compila el patron con la descripcion

        //si encuentra el patron (en este caso es "classification") en el matcher, setea el feed en el registro
        if(matcher.find())
        {
            audit.setFeedType(FeedType.CLASSIFICATION);
            return;
        }

        pattern = Pattern.compile("product");
        matcher = pattern.matcher(description);

        if(matcher.find())
        {
            audit.setFeedType(FeedType.PRODUCT);
            return;
        }

        pattern = Pattern.compile("media");
        matcher = pattern.matcher(description);

        if(matcher.find())
        {
            audit.setFeedType(FeedType.MEDIA);
            return;
        }

        pattern = Pattern.compile("price");
        matcher = pattern.matcher(description);

        if(matcher.find())
        {
            audit.setFeedType(FeedType.PRICE);
            return;
        }

        pattern = Pattern.compile("stock");
        matcher = pattern.matcher(description);

        if(matcher.find())
        {
            audit.setFeedType(FeedType.STOCK);
            return;
        }

    }

    //con el campo empresa es lo mismo que con el metodo anteriror
    private void fixCompany(Audit audit)
    {
        String description = audit.getDescription();
        String emsaPattern = "emsa";
        String carsaPattern = "carsa";
        Pattern pattern = Pattern.compile(emsaPattern);
        Matcher matcher = pattern.matcher(description);

        if(matcher.find())
        {
            audit.setCompany("E");
            return;
        }

        pattern = Pattern.compile(carsaPattern);
        matcher = pattern.matcher(description);

        if(matcher.find())
        {
            audit.setCompany("C");
            return;
        }
    }

    //en este caso el codigo de producto tiene diferentes errores dependiendo del feed
    private void fixProductCode(Audit audit)
    {
        //si el registro es un "E37", el patron va a ser una barra invertida (\), comillas ("), 3 o mas digitos,
        //barra invertida (\) y comillas. Aca se presupone que los codigo de productos tienen 3 o mas digitos.
        //Por ejemplo con \"123456\" matchea
        if(audit.getErrorCode().equals("E37"))
        {
            String description = audit.getDescription();
            String productCodePattern = "\\\\\"\\d{3,}\\\\\"";
            Pattern pattern = Pattern.compile(productCodePattern);
            Matcher matcher = pattern.matcher(description);

            if(matcher.find())
            {
                String productCode = matcher.group();
                productCode = productCode.substring(2); //con esto me saco de encima (\")
                int index = 0;

                while(index < productCode.length()) //y con esto quito la parte final (\")
                {
                    if(productCode.charAt(index) == '\\')
                        break;

                    index++;
                }

                productCode = productCode.substring(0,index);
                audit.setProductCode(productCode);
            }
        }

        //En el caso de que el registro sea del feed "media", el codigo de producto esta segido del nombre de un
        // archivo jpg. Por ejemplo: 126698-126698_01.jpg. Mientras si es classification, el codigo de producto esta
        // seguido por categorias de la siguiente manera: 127381/HELADERAS/NO FROST
        else if (audit.getFeedType().equals(FeedType.CLASSIFICATION) || audit.getFeedType().equals(FeedType.MEDIA))
        {
            String productCode = audit.getProductCode();

            for(int i = 0; i<productCode.length(); i++)
            {
                if(productCode.charAt(i) == '-' || productCode.charAt(i) == '/')
                {
                    productCode = productCode.substring(0,i);
                    break;
                }
            }
            audit.setProductCode(productCode);
        }
    }

    private FeedType parseFeedType(String feedType){
        if(feedType.equals("product"))
            return FeedType.PRODUCT;

        else if(feedType.equals("price"))
            return FeedType.PRICE;

        else if(feedType.equals("stock"))
            return FeedType.STOCK;

        else if(feedType.equals("media"))
            return FeedType.MEDIA;

        else if(feedType.equals("merchandise"))
            return FeedType.MERCHANDISE;

        else if(feedType.equals("classification"))
            return FeedType.PRICE;

        else
            throw new IllegalArgumentException("invalid feed type");

    }
}
