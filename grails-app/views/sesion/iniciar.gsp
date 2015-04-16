<html>
	<head>
		<meta name="layout" content="principal"/>
                <script src="http://crypto-js.googlecode.com/svn/tags/3.1.2/build/rollups/sha3.js"></script>
                <script type="text/javascript" src="${createLinkTo(dir:'js',file:'sesion.js')}" ></script>
        </head>
	<body>
            <div id="des">
                <p>Acceso a usuarios</p>
            </div>
                <div id="login">
                    <g:form name="login_form" controller="sesion" action="autenticar">
                        <table id="login_table">
                            <tr><td class="user_inf">Nombre usuario: </td><td class="user_inf_input"><input type="text" name="nombreUsuario" placeholder="Nombre de usuario" value="" id="login_nuser_input" required="" /></td></tr>
                            <tr><td class="user_inf">Contraseña: </td><td class="user_inf_input"><input type="password" name="contrasena" maxlength="100" value="" placeholder="Contrase&ntilde;a usuario" id="login_puser_input" required="" /></td></tr>
                            <tr><td colspan="2"><input type="submit" name="access_login" value="Acceder" id="access_login" /></td></tr>
                        </table>
                    </g:form>
                </div>
	</body>
</html>
