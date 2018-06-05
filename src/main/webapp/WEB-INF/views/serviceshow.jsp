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
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.12/css/all.css">
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
	<script src="<c:url value='/static/assets/js/textFit.js'/>"></script>
</head>

<body>
	<div class="generic-container">
		<%@include file="navbarservices.jsp" %>
		<div class="row">
        	<div class="col-md-12 col-sm-12 col-xs-12">
            	<div class="x_panel">
        			<div class="container">
        				<!-- item -->
		                <div class="col-md-6 text-center x_content">
		                    <div class="panel ${classActive} panel-service">
		                        <div class="panel-heading">
		                            <c:if test="${service.state == 'OK'}">
										<i class="fas fa-arrow-circle-up fa-4x green"></i>
									</c:if>
									<c:if test="${service.state == 'DOWN'}">
										<i class="fas fa-arrow-circle-down fa-4x red"></i>
									</c:if>
									<c:if test="${service.state == 'LATE'}">
										<i class="fas fa-arrow-circle-right fa-4x yellow"></i>
									</c:if>
		                            <h3>${service.name}</h3>
		                        </div>
		                        <div class="panel-body" id="urlService">
		                            <p><strong>Url: ${service.url}</strong></p>
		                        </div>
		                        <ul class="list-group text-center">
		                            <li class="list-group-item"><i class="far fa-clock"></i> Time Out: ${service.timeOut} Seg</li>
		                            <li class="list-group-item"><i class="fas fa-code"></i> Tipo de servicio: ${service.type}</li>
		                            <li class="list-group-item"><i class="fa fa-check"></i> Ultimo check: ${service.lastCheckTime}</li>
		                            <li class="list-group-item"><i class="fas fa-stopwatch"></i> Tiempo de Respuesta ${service.elapsedTime}</li>
		                        </ul>
		                        <div class="panel-footer">
		                            <p><strong><i class="fas fa-edit"></i>Creacion: ${service.createTime}</strong></p>
        							<p><strong><i class="fas fa-user"></i>Usuario: ${service.user.ssoId}</strong></p>
        							<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
										<a href="<c:url value='/edit-service-${service.id}' />" placeholder="Editar">											
											<i class="far fa-edit fa-4x blue" placeholder="Editar"></i>											
										</a>
									</sec:authorize>
		                        </div>
		                    </div>
		                </div>
	                </div>
	           	</div>
	      	</div>
      	</div>
      	<div class="row">
                    
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h3>Ultimos Checks</h3>
                                <div class="row"></div>
                            </div>
                            <div class="x_content">
                                <table id="datatable_checks" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline" cellspacing="0" width="100%" role="grid" aria-describedby="datatable-responsive_info">
                                    <thead>
                                    <tr role="row">
                                        <th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 155px;" aria-label="Position: activate to sort column ascending">Name</th>
                                        <th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 155px;" aria-label="Position: activate to sort column ascending">Activo</th>
                                        <th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 155px;" aria-label="Position: activate to sort column ascending">Hora del Check</th>
                                        <th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 155px;" aria-label="Position: activate to sort column ascending">Codigo de Respuesta</th>
                                        <th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 155px;" aria-label="Position: activate to sort column ascending">Tiempo de Respuesta</th>
                                    </thead>
                                    <tbody>
										<c:forEach items="${listChecks}" var="check">
											<tr>
												<td>${check.name}</td>
												<c:if test="${check.codeResponse == '200'}">
													<td>Activo</td>
												</c:if>
												<c:if test="${check.codeResponse != '200'}">
													<td>No Activo</td>
												</c:if>											
												<td>${check.checkTime}</td>
												<td>${check.codeResponse}</td>
												<td>${check.elapsedString}</td>
											</tr>
										</c:forEach>
							    	</tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                 </div> 
   	</div> 
   	<script type="text/javascript">
   		$('#datatable_checks').DataTable();
   	</script>
</body>
</html>