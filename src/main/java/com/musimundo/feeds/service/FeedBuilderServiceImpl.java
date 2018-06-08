package com.musimundo.feeds.service;

import com.csvreader.CsvReader;
import com.musimundo.feeds.beans.*;
import com.musimundo.feeds.dao.StockDao;
import com.musimundo.utilities.FeedType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    StockService stockService;

    @Autowired
    MediaService mediaService;

    @Autowired
    MerchandiseService merchandiseService;

    @Autowired
    ClassificationService classificationService;

    @Autowired
    AuditService auditService;

    @Autowired
    StockDao stockDao;

    private CsvReader reader;

    private String path;

    @Override
    public void createRegister(String path) {

        this.path = path;

        try {
            reader = new CsvReader(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if(isProduct())
            createProductRegister(reader);

        else if(isPrice())
            createPriceRegister(reader);

        else if(isStock())
            createStockRegister(reader);

        else if(isMedia())
            createMediaRegister(reader);

        else if(isMerchandise())
            createMerchandiseRegister(reader);

        else if(isClassification())
            createClassificationRegister(reader);

        else if(isAudit())
            createAuditRegister(reader);

        reader.close();
    }

    private boolean isProduct() {
        return this.path.contains("producto") && !this.path.contains("_aud");
    }

    private boolean isPrice() {
        return path.contains("precio") && !path.contains("_aud");
    }

    private boolean isStock() {
        return path.contains("stock") && !path.contains("_aud");
    }

    private boolean isMedia() {
        return path.contains("media") && !path.contains("_aud");
    }

    private boolean isMerchandise() {
        return path.contains("merchandise") && !path.contains("_aud");
    }

    private boolean isClassification() {
        return path.contains("clasificacion") && !path.contains("_aud");
    }

    private boolean isAudit() {
        return path.contains("_aud");
    }

    private Date parseDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm");
        Date res = null;

        if (dateString == null || dateString.equals(""))
            return null;

        try {
            res = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            res = null;
        }

        return res;
    }

    private String getImportOrigin(String path) {
        String patron = "";
        String importOrigin = "";

        if (isProduct())
            patron = "producto-\\d+\\.csv";

        else if (isPrice())
            patron = "precio-\\d+.csv";

        else if (isStock())
            patron = "stock-\\d+.csv";

        else if (isMedia())
            patron = "media-\\d+.csv";

        else if (isMerchandise())
            patron = "merchandise-\\d+.csv";

        else if (isClassification())
            patron = "clasificacion-\\d+.csv";

        else if (path.contains("_aud") && path.contains("stock"))
            patron = "stock-\\d+";

        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(path);

        if (matcher.find()) {
            if (path.contains("_aud") && path.contains("stock"))
                importOrigin = matcher.group() + ".csv";
            else
                importOrigin = matcher.group();
        }

        return importOrigin;
    }

    public static boolean checkFeedType(String feedType) {
        final String[] FEED_TYPE = {"classification", "product", "price", "merchandise", "media", "stock"};


        boolean res = false;
        for (String feed : FEED_TYPE) {
            if (feed.equals(feedType))
                res = true;
        }
        return res;
    }

    private void fixAudit(Audit audit) {
        fixCompany(audit);
        fixFeedType(audit);
        fixProductCode(audit);
    }

    //para corregir el feedType se utilizan expresiones regulares para buscar el tipo de feed en la descripcion del registro
    //ya que es unico el lugar de donde puedo sacar la informacion.
    private void fixFeedType(Audit audit) {
        String description = audit.getDescription(); //descripcion del registro
        Pattern pattern = Pattern.compile("classification"); //patron con el que vamos a buscar en la descripcion
        Matcher matcher = pattern.matcher(description); //se compila el patron con la descripcion

        //si encuentra el patron (en este caso es "classification") en el matcher, setea el feed en el registro
        if (matcher.find()) {
            audit.setFeedType(FeedType.CLASSIFICATION);
            return;
        }

        pattern = Pattern.compile("product");
        matcher = pattern.matcher(description);

        if (matcher.find()) {
            audit.setFeedType(FeedType.PRODUCT);
            return;
        }

        pattern = Pattern.compile("media");
        matcher = pattern.matcher(description);

        if (matcher.find()) {
            audit.setFeedType(FeedType.MEDIA);
            return;
        }

        pattern = Pattern.compile("price");
        matcher = pattern.matcher(description);

        if (matcher.find()) {
            audit.setFeedType(FeedType.PRICE);
            return;
        }

        pattern = Pattern.compile("stock");
        matcher = pattern.matcher(description);

        if (matcher.find()) {
            audit.setFeedType(FeedType.STOCK);
            return;
        }

    }

    //con el campo empresa es lo mismo que con el metodo anteriror
    private void fixCompany(Audit audit) {
        String description = audit.getDescription();
        String emsaPattern = "emsa";
        String carsaPattern = "carsa";
        Pattern pattern = Pattern.compile(emsaPattern);
        Matcher matcher = pattern.matcher(description);

        if (matcher.find()) {
            audit.setCompany("E");
            return;
        }

        pattern = Pattern.compile(carsaPattern);
        matcher = pattern.matcher(description);

        if (matcher.find()) {
            audit.setCompany("C");
            return;
        }
    }

    //en este caso el codigo de producto tiene diferentes errores dependiendo del feed
    private void fixProductCode(Audit audit) {
        //si el registro es un "E37", el patron va a ser una barra invertida (\), comillas ("), 3 o mas digitos,
        //barra invertida (\) y comillas. Aca se presupone que los codigo de productos tienen 3 o mas digitos.
        //Por ejemplo con \"123456\" matchea
        if (audit.getErrorCode().equals("E37")) {
            String description = audit.getDescription();
            String productCodePattern = "\\\\\"\\d{3,}\\\\\"";
            Pattern pattern = Pattern.compile(productCodePattern);
            Matcher matcher = pattern.matcher(description);

            if (matcher.find()) {
                String productCode = matcher.group();
                productCode = productCode.substring(2); //con esto me saco de encima (\")
                int index = 0;

                while (index < productCode.length()) //y con esto quito la parte final (\")
                {
                    if (productCode.charAt(index) == '\\')
                        break;

                    index++;
                }

                productCode = productCode.substring(0, index);
                audit.setProductCode(productCode);
            }
        }

        //En el caso de que el registro sea del feed "media", el codigo de producto esta segido del nombre de un
        // archivo jpg. Por ejemplo: 126698-126698_01.jpg. Mientras si es classification, el codigo de producto esta
        // seguido por categorias de la siguiente manera: 127381/HELADERAS/NO FROST
        else if (audit.getFeedType().equals(FeedType.CLASSIFICATION) || audit.getFeedType().equals(FeedType.MEDIA)) {
            String productCode = audit.getProductCode();

            for (int i = 0; i < productCode.length(); i++) {
                if (productCode.charAt(i) == '-' || productCode.charAt(i) == '/') {
                    productCode = productCode.substring(0, i);
                    break;
                }
            }
            audit.setProductCode(productCode);
        }
    }

    private FeedType parseFeedType(String feedType) {
        if (feedType.equals("product"))
            return FeedType.PRODUCT;

        else if (feedType.equals("price"))
            return FeedType.PRICE;

        else if (feedType.equals("stock"))
            return FeedType.STOCK;

        else if (feedType.equals("media"))
            return FeedType.MEDIA;

        else if (feedType.equals("merchandise"))
            return FeedType.MERCHANDISE;

        else if (feedType.equals("classification"))
            return FeedType.PRICE;

        else
            throw new IllegalArgumentException("invalid feed type");

    }

    private Integer parseStock(String stock) {
        try {
            return Integer.parseInt(stock);
        } catch (Exception e) {
            return null;
        }
    }

    private void createProductRegister(CsvReader reader) {
        try {
            while (reader.readRecord())
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createPriceRegister(CsvReader reader){
        try {
            while (reader.readRecord())
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createStockRegister(CsvReader reader){
        List<Stock> stockList = new ArrayList<>();

        try {
            while (reader.readRecord())
            {
                Stock stock = new Stock();

                stock.setProductCode(reader.get(0));
                stock.setStock(parseStock(reader.get(1)));
                stock.setWarehouse(reader.get(2));
                stock.setStatus(reader.get(3));
                stock.setImportOrigin(path);

                stockList.add(stock);
//                stockService.save(stock);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        for(Stock stock : stockList)
//            stockService.save(stock);



//        {
//            for(Stock stocks : stockList)
//                if(stock.equals(stocks)){
//                    System.out.println("1");
//                }
//        }

        stockDao.saveList(stockList);

        int a = 1;
    }

    private void createMediaRegister(CsvReader reader){
        try {
            while (reader.readRecord())
            {
                Media media = new Media();

                media.setProductCode(reader.get(0));
                media.setCodeMedia(reader.get(1));
                media.setIsDefault(Boolean.parseBoolean(reader.get(2)));
                media.setImportOrigin(getImportOrigin(path));

                mediaService.save(media);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createMerchandiseRegister(CsvReader reader){
        try {
            while (reader.readRecord())
            {
                Merchandise merchandise = new Merchandise();

                merchandise.setSource(reader.get(0));
                merchandise.setRefType(reader.get(1));
                merchandise.setTarget(reader.get(2));
                merchandise.setRelationship(reader.get(3));
                merchandise.setQualifier(reader.get(4));
                merchandise.setPreselected(reader.get(5));
                merchandise.setImportOrigin(getImportOrigin(path));

                merchandiseService.save(merchandise);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createClassificationRegister(CsvReader reader){
        try {
            while (reader.readRecord())
            {
                Classification classification = new Classification();

                classification.setProductCode(reader.get(0));
                classification.setAttCode(reader.get(1));
                classification.setCategoryCode(reader.get(2));
                classification.setAttValue(reader.get(3));
                classification.setImportOrigin(getImportOrigin(path));

                classificationService.save(classification);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createAuditRegister(CsvReader reader) {
        reader.setDelimiter(';');

        try {
            while (reader.readRecord())
            {
                Audit audit = new Audit();

                String errorCode = reader.get(3);
                String feedType = reader.get(8).toLowerCase();

                if (checkFeedType(feedType) || errorCode.contains("E37"))
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

                    if (importOrigin.contains("stock"))
                    {
                        importOrigin = getImportOrigin(path);
                        auditService.setWarehouseStock(audit);
                    }


                    else if (importOrigin.contains("classification"))
                        importOrigin.replaceAll("classification", "clasificacion");

                    audit.setImportOrigin(importOrigin);

                    if (errorCode.equals("E37")) {
                        fixAudit(audit);
                        if (audit.getProductCode().equals(""))
                            continue;
                    }

//                    String warehouse = auditService.setWarehouseStock(audit);
//                    audit.setWarehouseStock(warehouse);
                    auditService.save(audit);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
