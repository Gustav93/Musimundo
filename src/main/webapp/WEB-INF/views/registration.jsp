<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Usuario</title>
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
	
	<!-- Custom Theme Scripts -->
	<script src="<c:url value='/static/assets/gentelella-master/build/js/custom.js'/>"></script>
</head>

<body>
	<div class="generic-container">
		<%@include file="navbarusers.jsp" %>
		<div class="row">
        	<div class="col-md-12 col-sm-12 col-xs-12">
            	<div class="x_panel">
        			<div class="x_title">
                        <h3>Usuario</h3>
                     </div>
                     <div class="x_content">
                     	<form:form method="POST" modelAttribute="user" class="form-horizontal">
							<form:input type="hidden" path="id" id="id"/>							
							<div class="form-group">
								<label class="col-sm-3 control-label"></label>							
		                        <div class="col-sm-6">
		                        
								<label class="col-md-3 control-lable" for="firstName">Nombre</label>
									<div class="input-group">
										<form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>
										<div class="has-error">
											<form:errors path="firstName" class="help-inline"/>
										</div>
									</div>
						
								<label class="col-md-3 control-lable" for="lastName">Apellido</label>
									<div class="input-group">
										<form:input type="text" path="lastName" id="lastName" class="form-control input-sm" />
										<div class="has-error">
											<form:errors path="lastName" class="help-inline"/>
										</div>
									</div>					
								
									<label class="col-md-3 control-lable" for="ssoId">SSO ID</label>
										<div class="input-group">
											<c:choose>
												<c:when test="${edit}">
													<form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm" disabled="true"/>
												</c:when>
												<c:otherwise>
													<form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm" />
													<div class="has-error">
														<form:errors path="ssoId" class="help-inline"/>
													</div>
												</c:otherwise>
											</c:choose>
										</div>
						
										<label class="col-md-3 control-lable" for="password">Clave</label>
										<div class="input-group">
											<form:input type="password" path="password" id="password" class="form-control input-sm" />
											<div class="has-error">
												<form:errors path="password" class="help-inline"/>
											</div>
										</div>
						
										<label class="col-md-3 control-lable" for="email">Email</label>
										<div class="input-group">
											<form:input type="text" path="email" id="email" class="form-control input-sm" />
											<div class="has-error">
												<form:errors path="email" class="help-inline"/>
											</div>
										</div>
						
										<label class="col-md-3 control-lable" for="userProfiles">Roles</label>
										<div class="input-group">
											<form:select path="userProfiles" items="${roles}" multiple="true" itemValue="id" itemLabel="type" class="form-control input-sm" />
											<div class="has-error">
												<form:errors path="userProfiles" class="help-inline"/>
											</div>
										</div>					
								
										<c:choose>
											<c:when test="${edit}">
												<input type="submit" value="Guardar" class="btn btn-primary btn-sm"/>   <a href="<c:url value='/list' />">Cancelar</a>
											</c:when>
											<c:otherwise>
												<input type="submit" value="Crear" class="btn btn-primary btn-sm"/>   <a href="<c:url value='/list' />">Cancelar</a>
											</c:otherwise>
										</c:choose>
									</div>
							</div>
						</form:form>
                     </div>
        		</div>
        	</div>
        </div>
    </div>
</body>
</html>