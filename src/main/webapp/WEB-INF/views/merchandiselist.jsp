<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <title>Merchandise</title>

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
    <!--  <script src="<c:url value='/static/assets/js/Main.js'/>"></script>-->

</head>
<body class="nav-md">
<div class="container body">
    <div class="main_container">

        <div id="actualizarReporte" style="visibility: hidden;"></div>

        <%@include file="navbarfeeds.jsp" %>

        <div class="right_col" role="main">
            <div class="">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h3>Merchandise</h3>


                                <div class="row"></div>
                                <div class="row">
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                        <div class="tile-stats">
                                            <div id="total" class="count">${merchandiseReport.countTotal}</div>
                                            <h3>Procesados Correctamente</h3>
                                        </div>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                        <div class="tile-stats">
                                            <div id="procesados" class="count">${merchandiseReport.countOk}</div>
                                            <h3>Procesados Correctamente</h3>
                                        </div>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                        <div class="tile-stats">
                                            <div id="procesadosConWarning" class="count">${merchandiseReport.countWarning}</div>
                                            <h3>Procesados con Warning</h3>
                                        </div>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                        <div class="tile-stats">
                                            <div id="procesadosConError" class="count">${merchandiseReport.countError}</div>
                                            <h3>Procesados con Error</h3>
                                        </div>
                                    </div>
                                    <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                                        <div class="tile-stats">
                                            <div id="noProcesados" class="count">${merchandiseReport.countNotProcessed}</div>
                                            <h3>No Procesados</h3>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <h3>Carros de Día</h3>
                                <div class="row"></div>
                            </div>
                            <div class="x_content">
                                <!--<table id="datatable_carros" class="table table-striped table-bordered dataTable no-footer nowrap" role="grid" aria-describedby="datatable_info">-->
                                <table id="datatable_carros" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline" cellspacing="0" width="100%" role="grid" aria-describedby="datatable-responsive_info">
                                    <!--<table id="datatable" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline" cellspacing="0" width="100%" role="grid" aria-describedby="datatable-responsive_info" style="width: 100%;">-->
                                    <thead>
                                    <tr role="row">
                                        <th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 155px;" aria-label="Position: activate to sort column ascending">Source</th>
                                        <th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 155px;" aria-label="Position: activate to sort column ascending">Ref Type</th>
                                        <th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 155px;" aria-label="Position: activate to sort column ascending">Target</th>
                                        <th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 155px;" aria-label="Position: activate to sort column ascending">Relationship</th>
                                        <th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 155px;" aria-label="Position: activate to sort column ascending">Qualifier</th>
                                        <th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 155px;" aria-label="Position: activate to sort column ascending">Preselected</th>
                                        <th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 155px;" aria-label="Position: activate to sort column ascending">Import Origin</th>
                                        <th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 155px;" aria-label="Position: activate to sort column ascending">Processing Date</th>
                                        <th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 155px;" aria-label="Position: activate to sort column ascending">Feed Status</th>
                                        <th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 155px;" aria-label="Position: activate to sort column ascending">Error Description</th>
                                        <th class="sorting" tabindex="0" aria-controls="datatable-responsive" rowspan="1" colspan="1" style="width: 155px;" aria-label="Position: activate to sort column ascending">Company</th>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${merchandiseList}" var="merchandise">
                                        <tr role="row">
                                            <td>${merchandise.source}</td>
                                            <td>${merchandise.refType}</td>
                                            <td>${merchandise.target}</td>
                                            <td>${merchandise.relationship}</td>
                                            <td>${merchandise.qualifier}</td>
                                            <td>${merchandise.preselected}</td>
                                            <td>${merchandise.importOrigin}</td>
                                            <td>${merchandise.processingDate}</td>
                                            <td>${merchandise.feedStatus}</td>
                                            <td>${merchandise.errorDescription}</td>
                                            <td>${merchandise.company}</td>
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
</body>
</html>