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
<body class="nav-md">
<div class="container body">
    <div class="main_container">

       <div id="actualizarReporte" style="visibility: hidden;"></div>
        
        <%@include file="navbarservices.jsp" %>
		<div class="table-responsive">
			<table class="table table-striped table-hover">
								  <thead>
								    <tr>
								    <th>Estado</th>
								     <th>Nombre</th>
                                        <th>Url</th>
                                        <th>Tipo</th>
                                        <th>TimeOut</th>
                                        <th>Ultimo Check</th>
                                        <th>Fecha Creacion</th>
                                        <th>Usuario</th>
                                        <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
								        	<th></th>
								        </sec:authorize>
								        <sec:authorize access="hasRole('ADMIN')">
								        	<th></th>
								        </sec:authorize>
								    </tr>
								  </thead>
								  <tbody>
										<c:forEach items="${servicesToCheck}" var="servicesToCheck">
											<tr>
												<td><i class="fas fa-arrow-circle-up fa-3x green"></i></td>
												<td>${servicesToCheck.name}</td>
												<td>${servicesToCheck.url}</td>
												<td>${servicesToCheck.type}</td>
												<td>${servicesToCheck.timeOut}</td>
												<td>${servicesToCheck.lastCheckTime}</td>
												<td>${servicesToCheck.createTime}</td>
												<td>${servicesToCheck.user.ssoId}</td>
											    <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
													<td><a href="<c:url value='/edit-service-${servicesToCheck.id}' />" class="btn">edit</a></td>
										        </sec:authorize>
										        <sec:authorize access="hasRole('ADMIN')">
													<td><a href="<c:url value='/delete-service-${servicesToCheck.id}' />" class="btn">delete</a></td>
						        				</sec:authorize>
											</tr>
										</c:forEach>
							    	</tbody>
								</table>
							</div>
        <div class="right_col" role="main">
            <div class="">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h3>Estado de Servicios</h3>  
                                
                                
                                <div class="row">
	                                <c:forEach items="${servicesToCheck}" var="servicesToCheck">
		                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
		                                        <div class="tile-stats">
			                                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-6">
			                                           	<i class="fas fa-arrow-circle-up fa-5x"></i>
			                                        </div>		                                        
		                                            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-6">
		                                            	 <h3>${servicesToCheck.name}</h3>
		                                            </div>
		                                            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-6">
		                                            	<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
															<a href="<c:url value='/checkservice${servicesToCheck.id}' />">
																<div class="tile-stats bg-green">
												                	<h3 style="color:black;">Check</h3>
												                </div>
															</a>
														</sec:authorize>
		                                            </div>		                                            
		                                        </div>		                                       
		                                 </div>
	                                </c:forEach>
                                </div>                                
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
</body>
</html>