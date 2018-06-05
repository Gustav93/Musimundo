<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Confirmacion</title>
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
<body>
	<div class="generic-container">
		<%@include file="navbarservices.jsp" %>
		<div class="row">
			<div class="alert alert-success lead">
		    	${success}
			</div>
		</div>
	</div>
</body>

</html>