<div id="des">
    <p>Editar perfil</p>
</div>
<div id="board">
    <g:form controller="usuario" action="modificar" name="editar_usuario" params="[id:editar?.id]">
        <br />
	<p> Editar datos del usuario: </p>
	<hr>
	<table id="main_table">
            <tr><td class="table_inf">Nombre de usuario: </td><td class="table_inf_input"><input type="text" name="nombreUsuario" maxlength="100" placeholder="nombre de usuario" value="${editar?.nombreUsuario}" disabled/></td></tr>
            <tr><td class="table_inf">Nombre: </td><td class="table_inf_input"><input type="text" name="nombre" maxlength="100" placeholder="${editar?.nombre}" value=""/></td></tr>
            <tr><td class="table_inf">Apellido paterno: </td><td class="table_inf_input"><input type="text" name="apellidoP" maxlength="100" placeholder="${editar?.apellidoP}" value=""/></td></tr>
            <tr><td class="table_inf">Apellido materno: </td><td class="table_inf_input"><input type="text" name="apellidoM" maxlength="100" placeholder="${editar?.apellidoM}" value=""/></td></tr>
            <tr><td class="table_inf">Correo: </td><td class="table_inf_input"><input type="email" name="correo" placeholder="${editar?.correo}" maxlength="100" value=""/></td></tr>
            <tr><td class="table_inf">Contraseña: </td><td class="table_inf_input"><input type="password" name="contrasena" maxlength="100" placeholder="Contrase&ntilde;a" value="" /></td></tr>
            <tr><td class="table_inf">Repetir Contraseña: </td><td class="table_inf_input"><input type="password" name="recontrasena" maxlength="100" placeholder="Re-Contrase&ntilde;a"  value="" id="repassword" /></td></tr>
        </table>
	<table id ="send_editar_form">
            <tr><td></td><td colspan="2"><input type="submit" name="send" value="Editar" /></td></tr>
	</table>
    </g:form>
    <g:if test="${accedio == true && admin == true}">
        <g:form controller="usuario" action="eliminar" name="eliminar_usuario" params="[id:editar?.id]">
            <table id ="send_delete_form">
                <tr><td></td><td colspan="2"><input type="submit" name="delete" value="Eliminar" /></td></tr>
            </table>
        </g:form>
    </g:if>
</div>
