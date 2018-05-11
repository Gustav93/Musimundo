	<!--  <div class="authbar">
		<span><strong>${loggedinuser}</strong>, Bienvenido!.</span> <span class="floatRight"><a href="<c:url value="/logout" />">Logout</a></span>
	</div>-->
	
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

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                	<div class="nav navbar-nav menu-list">
                		<span><strong>Usuario: ${loggedinuser}</strong></span>
                	</div>               	
                    <ul class="nav navbar-nav menu-list" style="float:right;">                    	
                    	<li><a href="<c:url value='/logout' />">Salir</a></li>
                    </ul>
                </div>
            </div>
        </nav>
