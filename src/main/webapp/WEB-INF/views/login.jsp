<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Login page</title>
		<link href="<c:url value='/static/assets/gentelella-master/vendors/bootstrap/dist/css/bootstrap.min.css'/>" rel="stylesheet"></link>
    	<!-- Font Awesome -->
    	<link href="<c:url value='/static/assets/gentelella-master/vendors/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet"></link>
	    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.12/css/all.css">
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
		<!-- Custom Theme Scripts -->
		<script src="<c:url value='/static/assets/gentelella-master/build/js/custom.js'/>"></script>
	</head>

	<body>
	<div class="generic-container" style="text-align: center;">
		<div class="row">		
					<div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel col-md-6 col-sm-6 col-xs-6">
                            <div class="x_title">
                                <h3>Login</h3>
                                <div class="row">                                
                                </div>
                            </div>
                            <div class="x_content">
                            	<div class="login-form">
									<c:url var="loginUrl" value="/login" />
									<form action="${loginUrl}" method="post" class="form-horizontal">
										<c:if test="${param.error != null}">
											<div class="alert alert-danger">
												<p>Usuario y/o contraseña invalido.</p>
											</div>
										</c:if>
										<c:if test="${param.logout != null}">
											<div class="alert alert-success">
												<p>Salida exitosa</p>
											</div>
										</c:if>
										<div class="input-group input-sm">
											<label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
											<input type="text" class="form-control" id="username" name="ssoId" placeholder="Ingresar Usuario" required>
										</div>
										<div class="input-group input-sm">
											<label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> 
											<input type="password" class="form-control" id="password" name="password" placeholder="Ingresar Contraseña" required>
										</div>
										<div class="input-group input-sm">
			                              <div class="checkbox">
			                                <label><input type="checkbox" id="rememberme" name="remember-me"> Recordar</label>  
			                              </div>
			                            </div>
										<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
											
										<div class="form-actions">
											<input type="submit"
												class="btn btn-block btn-primary btn-default" value="Log in">
										</div>
									</form>
								</div>
                            </div>
                         </div>
                    </div>
        </div>
	</div>
	</body>
</html>