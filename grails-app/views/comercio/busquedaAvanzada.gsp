<html>
	<head>
		<meta name="layout" content="principal"/>
                <script type="text/javascript" src="${createLinkTo(dir:'js',file:'busqueda.js')}" ></script>
        </head>
	<body>
            <g:if test="${busqueda}">
                <div id="des">
                    <p>Busqueda Avanzada <br/> resultados:</p>
		</div>
                <g:render template="comercios" collection="${comercios}" />
            </g:if>
            <g:else>
                <div id="des">
                    <p>Busqueda Avanzada</p>
		</div>
                <div id="board">
                    <g:form  controller="comercio" action="busquedaAvanzada">
                        <br/>
			<p>Buscar por: </p>
			<hr/>
			<table id="main_table">
                            <tr><td class="table_inf">Nombre: </td><td class="table_inf_input"><input type="text" name="name" maxlength="100" placeholder="Nombre del local" value="" id="nombre" /></td></tr>
                            <tr><td class="table_inf">Tipo de comida: </td><td class="table_inf_input"><input type="text" name="tipo" maxlength="100" placeholder="Tipo de comida" value="" id="tipo" /></td></tr>
                            <tr><td class="table_inf">Transporte cercano: </td><td class="table_inf_input"><input type="text" name="estacion" maxlength="100" placeholder="Estacion" value="" id="estacion" /></td></tr>
                            <tr><td class="table_inf">Recomendada </td><td class="table_inf_input"><input type="text" name="recomendada" maxlength="100" placeholder="Especialidad" value="" id="recomendada" /></td></tr>
                            <tr><td class="table_inf">Nombre de alguna comida: </td><td class="table_inf_input"><input type="text" name="comida" maxlength="100" placeholder="Comida" value="" id="comida" /></td></tr>
                            <tr><td colspan="2" class="table_inf">Precios de : </td></tr>
                            <tr><td class="table_inf"></td><td  class="table_inf_input"><input type="number" name="menorPrecio" placeholder="precio minimo" value="" id="menorPrecio" /></td></tr>
                            <tr><td colspan="2" class="table_inf"> A </td></tr>
                            <tr><td class="table_inf"></td><td class="table_inf_input"><input type="number" name="mayorPrecio" placeholder="precio maximo" value="" id="mayorPrecio" /></td>
                        </table>
			<table id ="send_adavance">
                            <tr><td></td><td colspan="2"><input type="submit" name="search" value="Buscar" id="new_searh" /></td></tr>
			</table>
                    </g:form>
                </div>
            </g:else>
	</body>
</html>
