<html>
	<head>
		<meta name="layout" content="principal"/>
                <script src="http://crypto-js.googlecode.com/svn/tags/3.1.2/build/rollups/sha3.js"></script>
                <script type="text/javascript" src="${createLinkTo(dir:'js',file:'registro.js')}" ></script>
        </head>
	<body>
            <div id="des">
                <p>Registro usuarios</p>
            </div>
            <div id="board">
                <g:form name="registro" controller="usuario" action="nuevoRegistro">
                    <br/>
                    <p> Datos del Usuario: </p>
                    <hr/>
                    <table id="main_table"> 
                        <tr><td class="table_inf">Nombre de usuario: </td><td class="table_inf_input"><input type="text" name="nombreUsuario" maxlength="100" placeholder="Usuario" required="" value="" id="input_nombre_usuario" /></td></tr>
 			<tr><td class="table_inf">Nombre: </td><td class="table_inf_input"><input type="text" name="nombre" maxlength="100" placeholder="Nombre" required="" value="" id="input_nombre" /></td></tr>
                        <tr><td class="table_inf">Apellido paterno: </td><td class="table_inf_input"><input type="text" name="apellidoP" maxlength="100" placeholder="Apellido paterno" required="" value="" id="input_apellido_p" /></td></tr>
                        <tr><td class="table_inf">Apellido materno: </td><td class="table_inf_input"><input type="text" name="apellidoM" maxlength="100" placeholder="Apellido materno" required="" value="" id="input_apellido_m" /></td></tr>
 			<tr><td class="table_inf">Correo: </td><td class="table_inf_input"><input type="email" name="correo" placeholder="email@organizaci&oacute;n.com" maxlength="100" required="" value="" id="input_correo" /></td></tr>
			<tr><td class="table_inf">Contraseña: </td><td class="table_inf_input"><input type="password" name="contrasena" maxlength="100" placeholder="Contrase&ntilde;a" required="" value="" id="input_contrasena" /></td></tr>
			<tr><td class="table_inf">Repetir Contraseña: </td><td class="table_inf_input"><input type="password" name="recontrasena" maxlength="100" placeholder="Re-Contrase&ntilde;a" required="" value="" id="input_recontrasena" /></td></tr>
                    </table>
                    <table id ="send_record">
			<tr><td></td><td colspan="2"><input type="submit" name="send_record" value="Registrar" id="new_user" /></td></tr>
                    </table>
		</g:form>
            </div>
	</body>
</html>
