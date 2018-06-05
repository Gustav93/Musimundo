<nav class="navbar navbar-default">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>

            
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav menu-list">
                    	<li id=liBifurcador><a href="<c:url value='/bifurcacion' />">Seleccion de aplicacion</span></a></li>
                    	<li id=liRegistroHistorico><a href="<c:url value='/busquedafeed' />">Registro Historico</a></li>
                    	<li id=liProcesarFeed><a href="<c:url value='/feedMenu' />">Procesar Archivos</a></li>                      	             		
                    </ul>
                    <ul class="nav navbar-nav menu-list" style="float:right;">
                    	<li><a href="<c:url value='/logout' />">Salir</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <script>
        	var liActivo = ${liActivo};
        	if( liActivo !=null){
        		$('li').removeClass();
        		$("ul").find(liActivo).addClass("active");
        	}
        </script>