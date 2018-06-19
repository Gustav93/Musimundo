package com.musimundo.feeds.controller;

import com.musimundo.feeds.beans.*;
import com.musimundo.feeds.dao.FileNameDao;
import com.musimundo.feeds.service.*;
import com.musimundo.utilities.FeedStatus;
import com.musimundo.utilities.FeedType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class FeedsController {

    @Autowired
    FileNameDao fileNameDao;

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

//    @Autowired
//    OldProductDaoImpl oldProductDao;
//
//    @Autowired
//    OldPriceDaoImpl oldPriceDao;

    @Autowired
    FeedBuilderService feedBuilderService;

    @Autowired
    ProcessingFeedService processingFeedService;

    @Autowired
    FileService fileService;


    @RequestMapping(value = {"/listaproductos"})
    public String getProductList(ModelMap model){

//        List<OldProduct> res = oldProductDao.findAll();
//
//        OldProduct oldProduct = res.get(1);
//        Product product = new Product();
//
//        product.setProductCode(oldProduct.getCodigoProducto());
//        product.setEan(oldProduct.getEan());
//        product.setBrand(oldProduct.getBrand());
//        product.setName(oldProduct.getName());
//        product.setCategory(Integer.parseInt(oldProduct.getCategory()));
//        product.setWeight(Integer.parseInt(oldProduct.getWeight()));
//        product.setApprovalStatus(oldProduct.getApprovalStatus());
//        product.setDescription(oldProduct.getDescription());
//        product.setImportOrigin(oldProduct.getOrigenImportacion());
//        product.setErrorDescription(oldProduct.getDescription());
//        product.setFeedStatus(parseFeedStatus(oldProduct.getEstadoProcesamiento()));
//        product.setCompany(oldProduct.getEmpresa());
//        product.setProcessed(true);
//
//        productService.save(product);
//        System.out.println(res);


//        List<OldPrice> res = oldPriceDao.findAll();
//
//        System.out.println(res);
//
//        OldPrice oldPrice = res.get(0);
//        Price price = new Price();
//
//        price.setProductCode(oldPrice.getCodigoProducto());
//        price.setOnlinePrice(Double.parseDouble(oldPrice.getOnlinePrice()));
//        price.setCurrency(oldPrice.getCurrency());
//        price.setStorePrice(Double.parseDouble(oldPrice.getStorePrice()));
//        price.setHasPriority(Boolean.parseBoolean(oldPrice.getHasPriority()));
//        price.setImportOrigin(oldPrice.getOrigenImportacion());
//        price.setFeedStatus(parseFeedStatus(oldPrice.getEstadoProcesamiento()));
//        price.setErrorDescription(oldPrice.getDescripcionError());
//        price.setCompany(oldPrice.getEmpresa());
//        priceService.save(price);

//        feedBuilderService.createRegister("Producto-20180531 100000 al 20180601 090000_aud.csv");
//        feedBuilderService.createRegister("Merchandise-20180601 090000 al 20180604 100000_aud.csv");
//        feedBuilderService.createRegister("merchandise-1806040001.csv");
//        feedBuilderService.createRegister("merchandise-1806020001.csv");
//        feedBuilderService.createRegister("merchandise-1806010004.csv");

//        feedBuilderService.createRegister("stock-1806030002_aud.csv");
//        feedBuilderService.createRegister("stock-1806030002.csv");
//        processingFeedService.process(FeedType.STOCK);

//        feedBuilderService.createRegister("Producto-20180611 090000 al 20180612 090000_aud.csv");
//        feedBuilderService.createRegister("producto-20180611521.csv");
//        feedBuilderService.createRegister("producto-1806110001.csv");
//        processingFeedService.process(FeedType.PRODUCT);

//        feedBuilderService.createRegister("Precio-20180611 090000 al 20180612 090000_aud.csv");
//        feedBuilderService.createRegister("precio-1806110001.csv");
//        feedBuilderService.createRegister("precio-1806110002.csv");
//        processingFeedService.process(FeedType.PRICE);

//        feedBuilderService.createRegister("Media-20180612 090000 al 20180613 090000_aud.csv");
//        feedBuilderService.createRegister("media-1806120001.csv");
//        processingFeedService.process(FeedType.MEDIA);

//        feedBuilderService.createRegister("Merchandise-20180612 090000 al 20180613 090000_aud.csv");
//        feedBuilderService.createRegister("merchandise-1806130001.csv");
//        processingFeedService.process(FeedType.MERCHANDISE);

//        feedBuilderService.createRegister("Clasificacion-20180612 090000 al 20180613 090000_aud.csv");
//        feedBuilderService.createRegister("clasificacion-1806120001.csv");
//        processingFeedService.process(FeedType.CLASSIFICATION);

        List<Product> productList = productService.findAll();
        ProductReport productReport = productService.getReport();

//        File f = productService.getCsv(Filter.ONLY_NOT_OK, "C");
//        System.out.println(f.getAbsolutePath());

        model.addAttribute("productList", productList);
        model.addAttribute("productReport", productReport);

        return "productlist";
    }    

    @RequestMapping(value = {"/precios"})
    public String getPrice(@RequestParam String code, ModelMap model){
        List<Price> priceList = priceService.findByProductCode(code);
        PriceReport priceReport = priceService.getReport();

        model.addAttribute("priceList", priceList);
        model.addAttribute("priceReport", priceReport);

        return "pricelist";
    }
    
    @RequestMapping(value = {"/pricebyfecha"})
    public String getPriceByFecha(@RequestParam String fechaDesde, @RequestParam String fechaHasta, ModelMap model){
		
		Date desde = new Date();
		Date hasta = new Date();		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar calDesde = Calendar.getInstance();
		Calendar calHasta = Calendar.getInstance();
		//mejorar este pasaje, quiza usando un metodo aparte
		try {
			if(fechaDesde != null && !fechaDesde.isEmpty()) {
				calDesde.setTime(dateFormat.parse(fechaDesde));
				desde = calDesde.getTime();
			}			
			if(fechaHasta != null && !fechaHasta.isEmpty()) {				
				calHasta.setTime(dateFormat.parse(fechaHasta));				
				hasta = calHasta.getTime();
			}
		}catch(ParseException ex) {
			System.out.println(ex.getMessage());
		}		
		
		List<Price> priceList = priceService.findPriceByDate(desde, hasta);
		PriceReport priceReport = priceService.getReportByDate(desde, hasta);

		 model.addAttribute("priceList", priceList);
	     model.addAttribute("priceReport", priceReport);

	     return "pricelist";
    }
    
    @RequestMapping(value = {"/listaprecios"})
    public String getPriceList(ModelMap model){
        List<Price> priceList = priceService.findAll();
        PriceReport priceReport = priceService.getReport();

        model.addAttribute("priceList", priceList);
        model.addAttribute("priceReport", priceReport);

        return "pricelist";
    }
    
    @RequestMapping(value = {"/stock"})
    public String getStock(@RequestParam String code, ModelMap model){
        List<Stock> stockList = stockService.findByProductCode(code);
        StockReport stockReport = stockService.getReport();

        model.addAttribute("stockList", stockList);
        model.addAttribute("stockReport", stockReport);

        return "stocklist";
    }
    
    @RequestMapping(value = {"/stockbyfecha"})
    public String getStockByFecha(@RequestParam String fechaDesde, @RequestParam String fechaHasta, ModelMap model){
		
		Date desde = new Date();
		Date hasta = new Date();		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar calDesde = Calendar.getInstance();
		Calendar calHasta = Calendar.getInstance();
		//mejorar este pasaje, quiza usando un metodo aparte
		try {
			if(fechaDesde != null && !fechaDesde.isEmpty()) {
				calDesde.setTime(dateFormat.parse(fechaDesde));
				desde = calDesde.getTime();
			}			
			if(fechaHasta != null && !fechaHasta.isEmpty()) {				
				calHasta.setTime(dateFormat.parse(fechaHasta));				
				hasta = calHasta.getTime();
			}
		}catch(ParseException ex) {
			System.out.println(ex.getMessage());
		}		
		
		 List<Stock> stockList = stockService.findStockByDate(desde, hasta);
	     StockReport stockReport = stockService.getReportByDate(desde, hasta);

		model.addAttribute("stockList", stockList);
        model.addAttribute("stockReport", stockReport);

        return "stocklist";
    }

    @RequestMapping(value = {"/listastock"})
    public String getStockList(ModelMap model){
        List<Stock> stockList = stockService.findAll();
        StockReport stockReport = stockService.getReport();

        model.addAttribute("stockList", stockList);
        model.addAttribute("stockReport", stockReport);

        return "stocklist";
    }

    @RequestMapping(value = {"/media"})
    public String getMedia(@RequestParam String code, ModelMap model){
        List<Media> mediaList = mediaService.findByProductCode(code);
        MediaReport mediaReport = mediaService.getReport();

        model.addAttribute("mediaList", mediaList);
        model.addAttribute("mediaReport", mediaReport);

        return "medialist";
    }
    
    @RequestMapping(value = {"/mediabyfecha"})
    public String getMediaByFecha(@RequestParam String fechaDesde, @RequestParam String fechaHasta, ModelMap model){
		
		Date desde = new Date();
		Date hasta = new Date();		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar calDesde = Calendar.getInstance();
		Calendar calHasta = Calendar.getInstance();
		//mejorar este pasaje, quiza usando un metodo aparte
		try {
			if(fechaDesde != null && !fechaDesde.isEmpty()) {
				calDesde.setTime(dateFormat.parse(fechaDesde));
				desde = calDesde.getTime();
			}			
			if(fechaHasta != null && !fechaHasta.isEmpty()) {				
				calHasta.setTime(dateFormat.parse(fechaHasta));				
				hasta = calHasta.getTime();
			}
		}catch(ParseException ex) {
			System.out.println(ex.getMessage());
		}		
		
		List<Media> mediaList = mediaService.findMediaByDate(desde, hasta);
		MediaReport mediaReport = mediaService.getReportByDate(desde, hasta);

		model.addAttribute("mediaList", mediaList);
        model.addAttribute("mediaReport", mediaReport);

        return "medialist";
    }
    
    @RequestMapping(value = {"/listamedia"})
    public String getMediaList(ModelMap model){
        List<Media> mediaList = mediaService.findAll();
        MediaReport mediaReport = mediaService.getReport();

        model.addAttribute("mediaList", mediaList);
        model.addAttribute("mediaReport", mediaReport);

        return "medialist";
    }

    @RequestMapping(value = {"/merchandise"})
    public String getMerchandise(@RequestParam String code, ModelMap model){
        List<Merchandise> merchandiseList = merchandiseService.findByProductCode(code);
        MerchandiseReport merchandiseReport= merchandiseService.getReport();

        model.addAttribute("merchandiseList", merchandiseList);
        model.addAttribute("merchandiseReport", merchandiseReport);

        return "merchandiselist";
    }
    
    @RequestMapping(value = {"/merchandisebyfecha"})
    public String getMerchandiseByFecha(@RequestParam String fechaDesde, @RequestParam String fechaHasta, ModelMap model){
		
		Date desde = new Date();
		Date hasta = new Date();		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar calDesde = Calendar.getInstance();
		Calendar calHasta = Calendar.getInstance();
		//mejorar este pasaje, quiza usando un metodo aparte
		try {
			if(fechaDesde != null && !fechaDesde.isEmpty()) {
				calDesde.setTime(dateFormat.parse(fechaDesde));
				desde = calDesde.getTime();
			}			
			if(fechaHasta != null && !fechaHasta.isEmpty()) {				
				calHasta.setTime(dateFormat.parse(fechaHasta));				
				hasta = calHasta.getTime();
			}
		}catch(ParseException ex) {
			System.out.println(ex.getMessage());
		}		
		
		List<Merchandise> merchandiseList = merchandiseService.findMerchandiseByDate(desde, hasta);
		MerchandiseReport merchandiseReport= merchandiseService.getReportByDate(desde, hasta);

		model.addAttribute("merchandiseList", merchandiseList);
        model.addAttribute("merchandiseReport", merchandiseReport);

        return "merchandiselist";
    }
    
    @RequestMapping(value = {"/listamerchandise"})
    public String getMerchandiseList(ModelMap model){
        List<Merchandise> merchandiseList = merchandiseService.findAll();
        MerchandiseReport merchandiseReport= merchandiseService.getReport();

        model.addAttribute("merchandiseList", merchandiseList);
        model.addAttribute("merchandiseReport", merchandiseReport);

        return "merchandiselist";
    }

    @RequestMapping(value = {"/clasificacion"})
    public String getCLassification(@RequestParam String code, ModelMap model){
        List<Classification> classificationList = classificationService.findByProductCode(code);
        ClassificationReport classificationReport= classificationService.getReport();

        model.addAttribute("classificationList", classificationList);
        model.addAttribute("classificationReport", classificationReport);

        return "classificationlist";
    }
    
    @RequestMapping(value = {"/clasificacionbyfecha"})
    public String getClasificacionByFecha(@RequestParam String fechaDesde, @RequestParam String fechaHasta, ModelMap model){
		
		Date desde = new Date();
		Date hasta = new Date();		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar calDesde = Calendar.getInstance();
		Calendar calHasta = Calendar.getInstance();
		//mejorar este pasaje, quiza usando un metodo aparte
		try {
			if(fechaDesde != null && !fechaDesde.isEmpty()) {
				calDesde.setTime(dateFormat.parse(fechaDesde));
				desde = calDesde.getTime();
			}			
			if(fechaHasta != null && !fechaHasta.isEmpty()) {				
				calHasta.setTime(dateFormat.parse(fechaHasta));				
				hasta = calHasta.getTime();
			}
		}catch(ParseException ex) {
			System.out.println(ex.getMessage());
		}		
		
		List<Classification> classificationList = classificationService.findClassificationByDate(desde, hasta);
		ClassificationReport classificationReport = classificationService.getReportByDate(desde, hasta);

        model.addAttribute("classificationList", classificationList);
        model.addAttribute("classificationReport", classificationReport);

        return "classificationlist";
    }
    
    @RequestMapping(value = {"/listaclasificacion"})
    public String getCLassificationList(ModelMap model){
        List<Classification> classificationList = classificationService.findAll();
        ClassificationReport classificationReport= classificationService.getReport();

        model.addAttribute("classificationList", classificationList);
        model.addAttribute("classificationReport", classificationReport);

        return "classificationlist";
    }

    @RequestMapping(value = {"/listaauditoria"})
    public String getAuditList(ModelMap model)
    {
        List<Audit> auditList = auditService.findAll();
        model.addAttribute("auditList", auditList);

        return "auditlist";
    }

    private FeedStatus parseFeedStatus(String status)
    {
        if(status.equals("Procesado"))
            return FeedStatus.OK;

        else if(status.equals("Procesado con error"))
            return FeedStatus.ERROR;

        else if(status.equals("Procesado con warning"))
            return FeedStatus.WARNING;

        else
            return FeedStatus.NOT_PROCESSED;

    }

    @RequestMapping(value = {"/feedmenu"})
    public String feedMenu(ModelMap model){
        MultipartList multipartList = new MultipartList();
        model.addAttribute("multipartList", multipartList);
        return "feedmenu";
    }

//    @RequestMapping(value = {"/upload"}, method = RequestMethod.POST)
//    public String handleFileUpload(@RequestParam("file") MultipartFile file,
//                                   RedirectAttributes redirectAttributes) {
//
////        storageService.store(file);
//
//        System.out.println(file.getOriginalFilename());
////        redirectAttributes.addFlashAttribute("message",
////                "You successfully uploaded " + file.getOriginalFilename() + "!");
//
//        return "redirect:/";
//    }

    @RequestMapping(value = {"/upload"}, method = RequestMethod.POST)
    public String handleFileUpload(@Valid MultipartList multipartList) {

//        storageService.store(file);
        List<MultipartFile> multiparts = multipartList.getFiles();
        for (MultipartFile multipart : multiparts)
            fileService.saveFile(multipart);
//            System.out.println(multipart.getOriginalFilename());
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded " + file.getOriginalFilename() + "!");

        List<String> fileNames = fileService.getFileNames();

        for(String fileName : fileNames)
            feedBuilderService.createRegister(fileName);

        fileService.deleteFiles();

        return "redirect:/feedmenu";
    }

    @RequestMapping(value = {"/process"}, method = RequestMethod.GET)
    public String processFeed(@RequestParam("feed") String feedType, ModelMap model){
        String page = "feedmenu";

        if(feedType.equals("Precios"))
        {
            List<Price> priceList = processingFeedService.processPrice();
            PriceReport priceReport = priceService.getReport(priceList);
            page = "pricelist";
            model.addAttribute("priceList", priceList);
            model.addAttribute("priceReport", priceReport);

        }

        else if(feedType.equals("Productos"))
        {
            List<Product> productList = processingFeedService.processProduct();
            ProductReport productReport = productService.getReport(productList);
            page = "productlist";
            model.addAttribute("productList", productList);
            model.addAttribute("productReport", productReport);
        }

        else if(feedType.equals("Stock"))
        {
            List<Stock> stockList = processingFeedService.processStock();
            StockReport stockReport = stockService.getReport(stockList);
            page = "stocklist";
            model.addAttribute("stockList", stockList);
            model.addAttribute("stockReport", stockReport);
        }

        else if(feedType.equals("Media"))
        {
            List<Media> mediaList = processingFeedService.processMedia();
            MediaReport mediaReport = mediaService.getReport(mediaList);
            page = "medialist";
            model.addAttribute("mediaList", mediaList);
            model.addAttribute("mediaReport", mediaReport);
        }

        else if(feedType.equals("Merchandise"))
        {
            List<Merchandise> merchandiseList = processingFeedService.processMerchandise();
            MerchandiseReport merchandiseReport = merchandiseService.getReport();
            page = "merchandiselist";
            model.addAttribute("merchandiseList", merchandiseList);
            model.addAttribute("merchandiseReport", merchandiseReport);
        }

        else if(feedType.equals("Clasificacion"))
        {
            List<Classification> classificationList = processingFeedService.processClassification();
            ClassificationReport classificationReport = classificationService.getReport(classificationList);
            page = "classificationlist";
            model.addAttribute("classificationList", classificationList);
            model.addAttribute("classificationReport", classificationReport);
        }
        return page;
    }
}