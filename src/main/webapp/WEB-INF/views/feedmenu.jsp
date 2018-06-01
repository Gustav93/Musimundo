<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Procesar</title>

    <!-- Bootstrap -->
    <link href="assets/gentelella-master/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="assets/gentelella-master/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="assets/gentelella-master/vendors/nprogress/nprogress.css" rel="stylesheet">
    <!-- iCheck -->
    <link href="assets/gentelella-master/vendors/iCheck/skins/flat/green.css" rel="stylesheet">
    <!-- Datatables -->
    <link href="assets/gentelella-master/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link href="assets/gentelella-master/vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
    <link href="assets/gentelella-master/vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
    <link href="assets/gentelella-master/vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css" rel="stylesheet">
    <link href="assets/gentelella-master/vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="assets/gentelella-master/build/css/custom.css" rel="stylesheet">
</head>

<style>
    input[type=submit]
    {
        background-color: rgb(47, 67, 86);
        color: white;
    }
</style>

<body class="nav-md">
<div class="container body">
    <div class="main_container">

        <!--top navigation-->
        <div class="top_nav">
            <div class="nav_menu">
                <nav>
                    <div class="nav toggle">
                        <a id="menu_toggle" href="Main.html"><i class="fa fa-arrow-left"></i></a>
                    </div>

                    <div style="float: left;">
                        <h1>Procesar</h1>
                    </div>
                </nav>
            </div>
        </div>

        <!-- page content -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <!--<h1>Stock</h1>-->
                    </div>
                </div>

                <div class="clearfix"></div>
                <div class="x_content">

                    <div class="container">
                        <form name="form" class="form-horizontal form-label-left" method="post" enctype="multipart/form-data">
                            <!--<div class="form-group">-->
                            <!--<div class="col-sm-3 control-label"></div>-->
                            <!--<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">-->
                            <!--<div class="col-sm-12 input-group">-->
                            <!--Seleccione el archivo: <input class = "form-control" type="file" name="archivo" onchange="subirArchivo(this)" accept="text/csv" multiple>-->
                            <!--<input type="hidden" name="nombreArchivo" value="">-->
                            <!--</div>-->
                            <!--</div>-->
                            <!--<div class="col-sm-3 control-label"></div>-->
                            <!--</div>-->

                            <div class="form-group">
                                <label class="col-sm-3 control-label"></label>
                                <div class="col-sm-6 col-xs-12">
                                    <div class="col-sm-12 col-xs-12 input-group">
                                        Seleccione el archivo: <input class = "form-control" type="file" name="archivo" onchange="subirArchivo(this)" accept="text/csv" multiple>
                                        <input type="hidden" name="nombreArchivo" value="">
                                        <input class = "form-control" type="submit" value="Subir Archivo" onclick="validarExtension(this.form.archivo.value)">
                                    </div>
                                </div>
                                <label class="col-sm-3 control-label"></label>
                            </div>
                        </form>
                    </div>

                    <div class="container">
                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"></div>
                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 btn-group-vertical">
                            <button class="btn btn-default" type="button" onclick="window.location.href ='Precios.html'">Mostrar Precios</button>
                            <button class="btn btn-default" type="button" onclick="window.location.href ='Productos.html'">Mostrar Productos</button>
                            <button class="btn btn-default" type="button" onclick="window.location.href ='Stock.html'">Mostrar Stock</button>
                            <button class="btn btn-default" type="button" onclick="window.location.href ='Media.html'">Mostar Media</button>
                            <button class="btn btn-default" type="button" onclick="window.location.href ='Merchandise.html'">Mostar Merchandise</button>
                            <button class="btn btn-default" type="button" onclick="window.location.href ='Clasificacion.html'">Mostar Clasificacion</button>
                            <button class="btn btn-default" type="button" onclick="window.location.href ='Auditoria.html'">Mostrar Auditoria</button>
                            <br>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"></div>
                    </div>

                    <div class="container">
                        <form name="procesarDatos" class="form-horizontal form-label-left" method="post">
                            <div class="form-group">
                                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"></div>
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                    <form name="procesarDatos" method="post">
                                        Seleccionar el Feed a Procesar: <select class = "form-control" name="feed">
                                        <option value="Precios">Precios</option>
                                        <option value="Productos">Productos</option>
                                        <option value="Stock">Stock</option>
                                        <option value="Media">Media</option>
                                        <option value="Merchandise">Merchandise</option>
                                        <option value="Clasificacion">Clasificacion</option>
                                    </select>
                                        <input class = "form-control" type="submit" value="Procesar" onclick="goServlet('procesar')">
                                    </form>
                                </div>
                                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"></div>
                            </div>
                        </form>
                    </div>

                    <div class="container">
                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"></div>
                        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                            <form method="get" action="limpiar_tabla_auditoria">
                                <input class = "form-control" type="submit" value="Limpiar Tabla Auditoria">
                            </form>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- footer content -->
<footer>
    <div class="pull-right">
        Gentelella - Bootstrap Admin Template by <a href="https://colorlib.com">Colorlib</a>
    </div>
    <div class="clearfix"></div>
</footer>

<!-- jQuery -->
<!--<script data-cfasync="false" src="/cdn-cgi/scripts/af2821b0/cloudflare-static/email-decode.min.js"></script>-->
<script src="assets/gentelella-master/vendors/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="assets/gentelella-master/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="assets/gentelella-master/vendors/fastclick/lib/fastclick.js"></script>
<!-- NProgress -->
<script src="assets/gentelella-master/vendors/nprogress/nprogress.js"></script>
<!-- iCheck -->
<script src="assets/gentelella-master/vendors/iCheck/icheck.min.js"></script>
<!-- Datatables -->
<script src="assets/gentelella-master/vendors/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="assets/gentelella-master/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<script src="assets/gentelella-master/vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
<script src="assets/gentelella-master/vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
<script src="assets/gentelella-master/vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
<script src="assets/gentelella-master/vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
<script src="assets/gentelella-master/vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
<script src="assets/gentelella-master/vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
<script src="assets/gentelella-master/vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
<script src="assets/gentelella-master/vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
<script src="assets/gentelella-master/vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
<script src="assets/gentelella-master/vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
<script src="assets/gentelella-master/vendors/jszip/dist/jszip.min.js"></script>
<script src="assets/gentelella-master/vendors/pdfmake/build/pdfmake.min.js"></script>
<script src="assets/gentelella-master/vendors/pdfmake/build/vfs_fonts.js"></script>

<!-- Custom Theme Scripts -->
<script src="assets/gentelella-master/build/js/custom.js"></script>
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
                // alert("Se empezará a subir el archivo");
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