<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Servicio</title>
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
        	<div class="col-md-12 col-sm-12 col-xs-12">
            	<div class="x_panel">
        			<div class="container">
        			<form:form method="POST" modelAttribute="serviceToCheck" class="form-horizontal">
	                        <form:input type="hidden" path="id" id="id"/>
	                        <form:input type="hidden" path="state" id="state" value="OK"/>
	                        <form:input type="hidden" path="createTime" id="createTime"/>
	                        <form:input type="hidden" path="lastCheckTime" id="lastCheckTime"/>
	                        <form:input type="hidden" path="user" id="user" value="user ${loggedinuser}"/>
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label"></label>
	                            <div class="col-sm-6">
	                                Nombre:<br>
	                                <div class="input-group">
		                                <form:input type="text" path="name" name="name" id="name" class="form-control input-sm"/>
										<div class="has-error">
											<form:errors path="name" class="help-inline"/>
										</div>
	                                </div>
	                                URL:<br>
	                                <div class="input-group">
	                                	<form:input type="text" path="url" name="url" id="url" class="form-control input-sm"/>
	                                	<span class="input-group-addon"></span>
										<div class="has-error">
											<form:errors path="url" class="help-inline"/>
										</div>	                                    
	                                </div>
	                                 Tipo de Request<br>
	                                <div class="input-group">
	                               		<select class = "col-sm-12 form-control d-inline" name="type">
			                                <option value="application/json">application/json</option>
			                                <option value="application/xml">application/xml</option>
			                                <option value="application/text">application/text</option>
			                            </select>     
	                                </div>
	                                Time Out:<br>
	                                <div class="input-group">
	                                    <form:input type="text" path="timeOut" name="timeOut" id="timeOut" class="form-control input-sm"/>
	                                	<span class="input-group-addon">/Seg</span>
										<div class="has-error">
											<form:errors path="timeOut" class="help-inline"/>
										</div>	 
	                                </div>
									<c:choose>
										<c:when test="${edit}">
											<input type="submit" value="Guardar" class="btn btn-primary btn-sm"/>  <a href="<c:url value='/servicesmonitor' />">Cancelar</a>
										</c:when>
										<c:otherwise>
											<input type="submit" value="Crear" class="btn btn-primary btn-sm"/>  <a href="<c:url value='/servicesmonitor' />">Cancelar</a>
										</c:otherwise>
									</c:choose>
	                            </div>
	                            <label class="col-sm-3 control-label"></label>
	                        </div>
	                    </form:form>
	                </div>
	           	</div>
	      	</div>
      	</div> 
   	</div>
   	<script type="text/javascript">
	function getDate()
		{
		    var today = new Date();
		    var dd = today.getDate();
		    var mm = today.getMonth()+1; //January is 0!
		    var yyyy = today.getFullYear();
		    if(dd<10){dd='0'+dd} if(mm<10){mm='0'+mm}
		    //today = dd+"-"+mm+"-"+yyyy;
		
		    document.getElementById("lastCheckTime").value = today;
		    document.getElementById("createTime").value = today;
		}
		
		//call getDate() when loading the page
		getDate();
	</script>
</body>
</html>