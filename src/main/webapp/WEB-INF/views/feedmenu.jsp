<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Procesar</title>
	    <!-- Bootstrap -->
    <link href="<c:url value='/static/assets/gentelella-master/vendors/bootstrap/dist/css/bootstrap.min.css'/>" rel="stylesheet"></link>
    <!-- Font Awesome -->
    <link href="<c:url value='/static/assets/gentelella-master/vendors/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet"></link>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.12/css/all.css" integrity="sha384-G0fIWCsCzJIMAVNQPfjH08cyYaUtMwjJwqiRKxxE/rx96Uroj1BtIQ6MLJuheaO9" crossorigin="anonymous">
    <!-- NProgress -->
    <link href="<c:url value='/static/assets/gentelella-master/vendors/nprogress/nprogress.css'/>" rel="stylesheet"></link>
    <!-- iCheck -->
    <link href="<c:url value='/static/assets/gentelella-master/vendors/iCheck/skins/flat/green.css'/>" rel="stylesheet"></link>
    <!-- Datatables -->
    <link href="<c:url value='/static/assets/gentelella-master/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css'/>" rel="stylesheet"></link>
    <link href="<c:url value='/static/assets/gentelella-master/vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css'/>" rel="stylesheet"></link>
    <link href="<c:url value='/static/assets/gentelella-master/vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css'/>" rel="stylesheet"></link>
    <link href="<c:url value='/static/assets/gentelella-master/vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css'/>" rel="stylesheet"></link>
    <link href="<c:url value='/static/assets/gentelella-master/vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css'/>" rel="stylesheet"></link>

    <!-- Custom Theme Style -->
    <link href="<c:url value='/static/assets/gentelella-master/build/css/custom.css'/>" rel="stylesheet"></link>
    
    <!-- jQuery -->
	<script src="<c:url value='/static/assets/gentelella-master/vendors/jquery/dist/jquery.min.js'/>"></script>
	<!-- Bootstrap -->
	<script src="<c:url value='/static/assets/gentelella-master/vendors/bootstrap/dist/js/bootstrap.min.js'/>"></script>
	<!-- FastClick -->
	<script src="<c:url value='/static/assets/gentelella-master/vendors/fastclick/lib/fastclick.js'/>"></script>
	<!-- NProgress -->
	<script src="<c:url value='/static/assets/gentelella-master/vendors/nprogress/nprogress.js'/>"></script>
	<!-- iCheck -->
	<script src="<c:url value='/static/assets/gentelella-master/vendors/iCheck/icheck.min.js'/>"></script>
	<!-- Datatables -->
	<script src="<c:url value='/static/assets/gentelella-master/vendors/datatables.net/js/jquery.dataTables.min.js'/>"></script>
	<script src="<c:url value='/static/assets/gentelella-master/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js'/>"></script>
	<script src="<c:url value='/static/assets/gentelella-master/vendors/datatables.net-buttons/js/dataTables.buttons.min.js'/>"></script>
	<script src="<c:url value='/static/assets/gentelella-master/vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js'/>"></script>
	<script src="<c:url value='/static/assets/gentelella-master/vendors/datatables.net-buttons/js/buttons.flash.min.js'/>"></script>
	<script src="<c:url value='/static/assets/gentelella-master/vendors/datatables.net-buttons/js/buttons.html5.min.js'/>"></script>
	<script src="<c:url value='/static/assets/gentelella-master/vendors/datatables.net-buttons/js/buttons.print.min.js'/>"></script>
	<script src="<c:url value='/static/assets/gentelella-master/vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js'/>"></script>
	<script src="<c:url value='/static/assets/gentelella-master/vendors/datatables.net-keytable/js/dataTables.keyTable.min.js'/>"></script>
	<script src="<c:url value='/static/assets/gentelella-master/vendors/datatables.net-responsive/js/dataTables.responsive.min.js'/>"></script>
	<script src="<c:url value='/static/assets/gentelella-master/vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js'/>"></script>
	<script src="<c:url value='/static/assets/gentelella-master/vendors/datatables.net-scroller/js/dataTables.scroller.min.js'/>"></script>
	<script src="<c:url value='/static/assets/gentelella-master/vendors/jszip/dist/jszip.min.js'/>"></script>
	<script src="<c:url value='/static/assets/gentelella-master/vendors/pdfmake/build/pdfmake.min.js'/>"></script>
	<script src="<c:url value='/static/assets/gentelella-master/vendors/pdfmake/build/vfs_fonts.js'/>"></script>
	
	<!-- Custom Theme Scripts -->
	<script src="<c:url value='/static/assets/gentelella-master/build/js/custom.js'/>"></script>
</head>

<style>
    input[type=submit]
    {
        background-color: rgb(47, 67, 86);
        color: white;
    }
</style>

<body>
<%@include file="navbarfeeds.jsp" %>
	<div class="right_col" role="main">
            <div class="">
                <div class="row">
                	<div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h3>Auditoria de feeds</h3>  
                            </div>
                            <div class="x_content">
			                    <div class="container">			                    	
                                        <sec:authorize access="hasRole('ADMIN') or hasRole('DBA') or hasRole('USER')">
                                        	                                        	
                                        		<%--<form name="form" class="form-horizontal form-label-left" method="post" enctype="multipart/form-data">--%>
							                            <%--<div class="form-group">--%>
							                                <%--<label class="col-sm-3 control-label"></label>--%>
							                                <%--<div class="col-sm-6 col-xs-12">--%>
							                                    <%--<div class="col-sm-12 col-xs-12 input-group">--%>
							                                        <%--Seleccione el archivo: <input class = "form-control" type="file" name="archivo" onchange="subirArchivo(this)" accept="text/csv" multiple>--%>
							                                        <%--<input type="hidden" name="nombreArchivo" value="">--%>
							                                        <%--<input class = "form-control" type="submit" value="Subir Archivo" onclick="validarExtension(this.form.archivo.value)">--%>
							                                    <%--</div>--%>
							                                <%--</div>--%>
							                                <%--<label class="col-sm-3 control-label"></label>--%>
							                            <%--</div>--%>
							                        <%--</form>--%>

											<%--<form:form  modelAttribute="multipartList" class="form-horizontal form-label-left"  method="POST" enctype="multipart/form-data" action="/upload?${_csrf.parameterName}=${_csrf.token}">--%>
											<form:form modelAttribute="multipartList" class="form-horizontal form-label-left"  method="POST" enctype="multipart/form-data" action="/upload?${_csrf.parameterName}=${_csrf.token}">
											<fieldset>
												<div class="form-group">
													<label class="col-sm-3 control-label"></label>
													<div class="col-sm-6 col-xs-12">
														<div class="col-sm-12 col-xs-12 input-group">
															Seleccione el archivo: <input class = "form-control" type="file" name="files" multiple="multiple" >
																<%--<input type="hidden" name="nombreArchivo" value="">--%>
															<input class = "form-control" type="submit" value="Upload">
														</div>
													</div>
													<label class="col-sm-3 control-label"></label>
												</div>
											</fieldset>

											</form:form>
                                        	
										</sec:authorize>		                        
			                    </div>
			
			                    <div class="container">
			                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"></div>
			                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 btn-group-vertical">
			                            <button class="btn btn-default" type="button" onclick="window.location.href ='/pricereport'">Mostrar Precios</button>
			                            <button class="btn btn-default" type="button" onclick="window.location.href ='/productreport'">Mostrar Productos</button>
			                            <button class="btn btn-default" type="button" onclick="window.location.href ='/stockreport'">Mostrar Stock</button>
			                            <button class="btn btn-default" type="button" onclick="window.location.href ='/mediareport'">Mostar Media</button>
			                            <button class="btn btn-default" type="button" onclick="window.location.href ='/merchandisereport'">Mostar Merchandise</button>
			                            <button class="btn btn-default" type="button" onclick="window.location.href ='/classificationreport'">Mostar Clasificacion</button>
			                            <%--<button class="btn btn-default" type="button" onclick="window.location.href =''">Mostrar Auditoria</button>--%>
			                            <br>
			                        </div>
			                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"></div>
			                    </div>
			
			                    <div class="container">
			                        <form:form name="procesarDatos" class="form-horizontal form-label-left" method="get" action="/process">
			                            <div class="form-group">
			                                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"></div>
			                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
			                                    <%--<form name="procesarDatos" method="post">--%>
			                                        Seleccionar el Feed a Procesar: <select class = "form-control" name="feed">
			                                        <option value="Precios">Precios</option>
			                                        <option value="Productos">Productos</option>
			                                        <option value="Stock">Stock</option>
			                                        <option value="Media">Media</option>
			                                        <option value="Merchandise">Merchandise</option>
			                                        <option value="Clasificacion">Clasificacion</option>
			                                    </select>
			                                        <input class = "form-control" type="submit" value="Procesar">
			                                    <%--</form>--%>
			                                </div>
			                                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"></div>
			                            </div>
			                        </form:form>
			                    </div>
			
			                    <%--<div class="container">--%>
			                        <%--<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"></div>--%>
			                        <%--<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">--%>
			                            <%--<form method="get" action="limpiar_tabla_auditoria">--%>
			                                <%--<input class = "form-control" type="submit" value="Limpiar Tabla Auditoria">--%>
			                            <%--</form>--%>
			                        <%--</div>--%>
			                        <%--<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"></div>--%>
			                    <%--</div>--%>
			                </div>
                        </div>
                    </div>
                </div>
            </div>
    </div>
<script>
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
        (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
        m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

    ga('create', 'UA-23581568-13', 'auto');
    ga('send', 'pageview');

</script>

<script>
    function goServlet(servlet)
    {
        document.procesarDatos.action = servlet;
        document.procesarDatos.submit();
    }

    function subirArchivo(elemento)
    {
        var files = elemento.files;
        var nombreArchivo = document.form.nombreArchivo;
        var nombreArchivos = [];

        for(var i=0; i<files.length; i++)
        {
            nombreArchivos.push(files[i].name);
        }

        nombreArchivo.value = nombreArchivos;
    }

    function validarExtension(archivo)
    {
        var extensionPermitida = ".csv";

        if(!archivo)
            alert("Primero selecciona un archivo");

        else
        {
            var extensionArchivo = archivo.substring(archivo.lastIndexOf(".")).toLowerCase();

            if(extensionPermitida == extensionArchivo)
            {
                // alert("Se empezar� a subir el archivo");
                document.form.action = "subir_archivo"
                document.form.submit();
            }

            else
            {
                alert("Extension del archivo invalida");
            }
        }
    }
</script>
</body>
</html>