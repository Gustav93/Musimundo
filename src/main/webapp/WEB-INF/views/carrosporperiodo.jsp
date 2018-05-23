<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <title>Carritos</title>

    <!-- Bootstrap -->
    <link href="<c:url value='/static/assets/gentelella-master/vendors/bootstrap/dist/css/bootstrap.min.css'/>" rel="stylesheet"></link>
    <!-- Font Awesome -->
    <link href="<c:url value='/static/assets/gentelella-master/vendors/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet"></link>
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
	
	<script src="<c:url value='/static/assets/bootstrap/bootstrap-datepicker-1.6.4/js/bootstrap-datepicker.js'/>"></script>
	<script src="<c:url value='/static/assets/bootstrap/bootstrap-datepicker-1.6.4/locales/bootstrap-datepicker.es.min.js'/>"></script>
		
	<!-- Custom Theme Scripts -->
	<script src="<c:url value='/static/assets/gentelella-master/build/js/custom.js'/>"></script>
	<!--  <script src="<c:url value='/static/assets/js/Main.js'/>"></script>-->

</head>
<body class="nav-md">
<div class="container body">
    <div class="main_container">

       <div id="actualizarReporte" style="visibility: hidden;"></div>
        
       <%@include file="navbar.jsp" %>
        
        <div class="right_col" role="main">
         <div class="row">
        	<div class="col-md-12 col-sm-12 col-xs-12">
            	<div class="x_panel">
        			<div class="container">
	                    <form name="formularioHistorico" class="form-horizontal form-label-left" action="<c:url value='/getcarritosperiodo' />" method="get">
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label"></label>
	                            <div class="col-sm-6">
	                                Fecha de inicio:<br>
	                                <div class="input-group date calendarioDesde">
	                                    <input name="fechaDesde" type="text" class="form-control" ><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
	                                </div>
	                                Fecha de Fin:<br>
	                                <div class="input-group date calendarioHasta">
	                                    <input name="fechaHasta" type="text" class="form-control" ><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
	                                </div>
	                                <button type="submit" class="btn btn-primary">Buscar</button>
	                            </div>
	                            <label class="col-sm-3 control-label"></label>
	                        </div>
	                    </form>
	                </div>
	           	</div>
	      	</div>
      	</div>    
            <div class="">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h3>Totales del ${fechaDesde} al ${fechaHasta}</h3>
                                

                                <div class="row"></div>

                                <div class="row">
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                        <div class="tile-stats">
                                            <div id="totalCarros" class="count">${totalesCarritos.totalCarros}</div>
                                            <h3>Total Carros</h3>
                                        </div>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                        <div class="tile-stats">
                                            <div id="totalCarrosCarsa" class="count">${totalesCarritos.totalCarrosCarsa}</div>
                                            <h3>Total Carros Carsa</h3>
                                        </div>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                        <div class="tile-stats">
                                            <div id="totalCarrosEmsa" class="count">${totalesCarritos.totalCarrosEmsa}</div>
                                            <h3>Total Carros Emsa</h3>
                                        </div>
                                    </div>

                                </div>

                                <div class="row">
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                        <div class="tile-stats">
                                            <div id="totalRecaudado" class="count">${totalesCarritos.totalRecaudado}</div>
                                            <h3>Total Recaudado</h3>
                                        </div>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                        <div class="tile-stats">
                                            <div id="totalRecaudadoCarsa" class="count">${totalesCarritos.totalRecaudadoCarsa}</div>
                                            <h3>Total Recaudado Carsa</h3>
                                        </div>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                        <div class="tile-stats">
                                            <div id="totalRecaudadoEmsa" class="count">${totalesCarritos.totalRecaudadoEmsa}</div>
                                            <h3>Total Recaudado Emsa</h3>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                        <div class="tile-stats">
                                            <div id="totalProductos" class="count">${totalesCarritos.totalProductos}</div>
                                            <h3>Total Productos</h3>
                                        </div>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                        <div class="tile-stats">
                                            <div id="totalProductosCarsa" class="count">${totalesCarritos.totalProductosCarsa}</div>
                                            <h3>Total Productos Carsa</h3>
                                        </div>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                        <div class="tile-stats">
                                            <div id="totalProductosEmsa" class="count">${totalesCarritos.totalProductosEmsa}</div>
                                            <h3>Total Productos Emsa</h3>
                                        </div>
                                    </div>

                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                        <div class="tile-stats">
                                            <!--<div class="well">-->
                                                <div class=" display text-center">
                                                    Tiempo transcurrido desde el ultimo carrito: <span id="hor">00</span>:<span id="min">00</span>:<span id="seg">00</span>
                                                </div>
                                            <!--</div>-->
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h3>Carros desde ${fechaDesde} al ${fechaHasta}</h3>
                                <div class="row"></div>
                            </div>
                            <div class="x_content">
                                <!--<table id="datatable_carros" class="table table-striped table-bordered dataTable no-footer nowrap" role="grid" aria-describedby="datatable_info">-->
                                    <table id="datatable_carros" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline" cellspacing="0" width="100%" role="grid" aria-describedby="datatable-responsive_info">
                                    <thead>
                                    <tr role="row">
                                        <th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 155px;" aria-label="Position: activate to sort column ascending">Id</th>
                                        <th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 155px;" aria-label="Position: activate to sort column ascending">Fecha de Cierre</th>
                                        <th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 155px;" aria-label="Position: activate to sort column ascending">Usuario Invitado</th>
                                        <th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 155px;" aria-label="Position: activate to sort column ascending">Medio de Pago</th>
                                        <th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 155px;" aria-label="Position: activate to sort column ascending">Tarjeta</th>
                                        <th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 155px;" aria-label="Position: activate to sort column ascending">Cuotas</th>
                                        <th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 155px;" aria-label="Position: activate to sort column ascending">Empresa</th>
                                        <th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 155px;" aria-label="Position: activate to sort column ascending">Total</th>
                                    </thead>
                                    <tbody>
										<c:forEach items="${carritos}" var="carroCerrado">
											<tr>
												<td>${carroCerrado.id}</td>
												<td>${carroCerrado.fechaCierrePedido}</td>
												<td>${carroCerrado.usuarioInvitado}</td>
												<td>${carroCerrado.medioPago}</td>
												<td>${carroCerrado.tarjeta}</td>
												<td>${carroCerrado.cuotas}</td>
												<td>${carroCerrado.company}</td>
												<td>${carroCerrado.total}</td>
											</tr>
										</c:forEach>
							    	</tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

       
    </div>
</div>

<!-- Google Analytics -->
<script>
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
        (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
        m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

    ga('create', 'UA-23581568-13', 'auto');
    ga('send', 'pageview');

</script>

<script>
   
$('.calendarioDesde').datepicker({
    format: "dd-mm-yyyy",
    language: "es"
});

$('.calendarioHasta').datepicker({
    format: "dd-mm-yyyy",
    language: "es"
});
   
</script>
</body>
</html>