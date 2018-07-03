<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Seleccion de Aplicacion</title>
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
	<!--  <script src="<c:url value='/static/assets/js/Main.js'/>"></script>-->
</head>

<body>
	<%@include file="authheader.jsp" %>
	<div class="right_col" role="main">
            <div class="">
                <div class="row">
                	<div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h3>Seleccione Aplicacion</h3>  
                                <div class="row">
                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                        <sec:authorize access="hasRole('ADMIN') or hasRole('DBA') or hasRole('USER')">
                                        	<a href="<c:url value='/indexcarritos' />">                                        	
                                        		<div class="tile-stats">
                                        			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
                                        				<i class="fas fa-shopping-cart fa-5x green"></i>
                                        			</div>
                                        			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
                                            		<h3>Carritos</h3>
                                            		</div>
                                        		</div>
                                        	</a>
										</sec:authorize>
                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                        <sec:authorize access="hasRole('ADMIN') or hasRole('DBA') or hasRole('USER')">
                                        	<a href="<c:url value='/feedmenu' />">
                                        		<div class="tile-stats">
                                        		<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
                                        				<i class="fas fa-book fa-5x grey"></i>
                                        			</div>
                                        			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
                                            		<h3>Auditoria</h3>
                                            		</div>
                                        		</div>
                                        	</a>
										</sec:authorize>
                                    </div>                                    
                                </div>
                                <div class="row">
	                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
	                                        <sec:authorize access="hasRole('ADMIN') or hasRole('DBA') or hasRole('USER')">
	                                        	<a href="<c:url value='/list' />">
	                                        		<div class="tile-stats">
	                                        		<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
                                        				<i class="fas fa-users fa-5x blue"></i>
                                        			</div>
                                        			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
	                                            		<h3>Usuarios</h3>
	                                            	</div>
	                                        		</div>
	                                        	</a>
											</sec:authorize>
	                                    </div>
	                                    <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
	                                        <sec:authorize access="hasRole('ADMIN') or hasRole('DBA') or hasRole('USER')">
	                                        	<a href="<c:url value='/servicesmonitor' />">
	                                        		<div class="tile-stats">
	                                        		<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
                                        				<i class="fas fa-sliders-h fa-5x black"></i>
                                        			</div>
                                        			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
	                                            		<h3>Servicios</h3>
	                                            	</div>
	                                        		</div>
	                                        	</a>
											</sec:authorize>
	                                    </div>
	                             </div>                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
	</div>
</body>
</html>