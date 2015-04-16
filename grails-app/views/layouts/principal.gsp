<!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">
        <head>
            <meta http-equiv="Content-type" content="text/html;charset=UTF-8"/>
            <meta name="author" content="Mijail Guti&amp;eacute;rrez"/>
            <meta name="author" content="Skynet"/>
            <title><g:layoutTitle default="Skynet"/></title>
            <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'icon.ico')}" />
            <link rel="stylesheet" type="text/css" href="${createLinkTo(dir:'css',file:'skynet.css')}" />
            <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true"></script>
            <script type="text/javascript" src="${createLinkTo(dir:'js',file:'jquery-1.11.0.min.js')}"></script>
            <script type="text/javascript" src="${createLinkTo(dir:'js',file:'skynet.js')}"></script>
            <script type="text/javascript">
			var home = "${createLink(uri: '/', absolute : 'true')}";
            </script>
            <g:layoutHead/>
	</head>
	<body>
            <div id="header" class="main">
                <div id="inf">
                    <p id="name" class="shadow">Skynet</p>
                    <g:if test="${accedio}">
                        <a id="close_user" href="${createLink(action:'cerrar', controller:'sesion', absolute="true")}">Cerrar Sesion</a>
                    </g:if>
                    <g:else>
                        <a id="access_user" href="${createLink(action:'iniciar', controller:'sesion', absolute="true")}">Iniciar Sesion</a>
                    </g:else>
                    <g:form controller="comercio" action="buscar">
                        <input type="text" name="buscar" placeholder="Buscar" value="" id="search_input" class="user_inf_input" />
                        <input type="submit" value="Enviar" style="display:none" />
                    </g:form>
                        <a id="advance_search" href="${createLink(action:'busquedaAvanzada', controller:'comercio', absolute="true")}">Busqueda avanzada</a>
                    <g:if test="${accedio}">
                    </g:if>
                    <g:else>
                        <a id="registrar" href="${createLink(action:'registrar', controller:'usuario', absolute="true")}">Registrar</a>
                    </g:else>
                </div>
            </div>
            <g:if test="${accedio}">
                <div id="sesion_start">
                    <div id="name_session">
                        <p>Bienvenido</p>
                        <p class="shadow">${usuario?.nombre}</p>
                    </div>
                    <br/>
                    <br/>
                    <g:if test="${admin == true}">
                        <div id="sesion_edit">
                            <a href="${createLink(action:'usuarios', controller:'usuario', absolute="true")}">Editar registro Usuario</a>
                        </div>
                        <br/>
                        <br/>
                        <div id="sesion_edit">
                            <a href="${createLink(action:'comercios', controller:'comercio', absolute="true")}">Editar registro establecimiento</a>
                        </div>
                        <br/>
                        <br/>
                        <div id="sesion_edit">
                            <a href="${createLink(action:'nuevo', controller:'comercio', absolute="true")}">Registrar comercio</a>
                        </div>
                    </g:if>
                    <g:else>
                        <div id="sesion_edit">
                            <a href="${createLink(action:'editar', controller:'usuario', params:[id:usuario?.id], absolute="true")}">Editar registro</a>
                        </div>
                        <br/>
                    </g:else>
                </div>
            </g:if>
            <div id="container">
                <g:layoutBody/>
            </div>
            <div id="footer" >
                <i>Skynet</i><br/>
                &#xa9; <i>ATO</i> - 2015<br/>
                <h1 id="optimized-for" >&Eacute;sta p&aacute;gina se visualiza mejor con<i>Google Chrome</i></h1>
            </div>
	</body>
    </html>