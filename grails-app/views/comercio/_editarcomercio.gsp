<br/>
<!--<img src="${createLink(action:'imagen', controller:'comercio', params: [id:comercio?.id], absolute:"true")}" alt="${comercio?.nombre}" height="50" width="50">-->
<table class="main_table">
    <g:form name="editar_comercio" controller="comercio" action="registrar" params="[nuevo:true]">
        <tr><td class="table_inf" colspan="2">Nombre: </td><td class="table_inf_input"><input type="text" name="nombre" maxlength="100" placeholder="Nombre" value="${comercio?.nombre}"/></td></tr>
        <tr><td class="table_inf" colspan="3">Recomendada: </td></tr>
        <tr><td class="table_inf"></td><td class="table_inf">Comida</td><td class="table_inf_input"><input type="text" name="recomendada" maxlength="50" placeholder="recomendada" value="${comercio?.recomendada?.nombre}"/></td></tr>
        <tr><td class="table_inf"></td><td class="table_inf">Tipo</td><td class="table_inf"><input type="text" name="recomendadaTipo" maxlength="50" placeholder="tipo" value="${comercio?.recomendada?.tipo}"/></td></tr>
        <tr><td class="table_inf" colspan="3">Rango precios: </td></tr>
        <tr><td class="table_inf"></td><td class="table_inf_input"><input type="number" name="menorPrecio" maxlength="20" placeholder="menor precio" value="${comercio?.menorPrecio}"/></td><td class="table_inf"></td></tr>
        <tr><td class="table_inf"></td><td class="table_inf"> A </td><td class="table_inf"></td></tr>
        <tr><td class="table_inf"></td><td class="table_inf_input"><input type="number" name="mayorPrecio" maxlength="20" placeholder="mayor precio" value="${comercio?.mayorPrecio}"/></td><td class="table_inf"></td></tr>
        <tr><td class="table_inf" colspan="2">Comedor: </td><td class="table_inf_input" style="color:grey"><g:if test="${comercio?.comedor == true}"><input type="checkbox" name="comedor" checked></g:if><g:else><input type="checkbox" name="comedor"></g:else></td></tr>
        <tr><td class="table_inf" colspan="2">Ba&#241o: </td><td class="table_inf_input" style="color:grey"><g:if test="${comercio?.bano == true}"><input type="checkbox" name="bano" checked></g:if><g:else><input type="checkbox" name="bano"></g:else></td></tr>
        <tr><td class="table_inf" colspan="2">Direccion: </td><td class="table_inf_input" style="color:grey"><input type="text" name="direccion" maxlength="100" placeholder="direccion" value="${comercio?.direccion}"/></td></tr>
        <tr><td class="table_inf" colspan="2">Pagina web: </td><td class="table_inf_input"><input type="text" name="pagina" maxlength="100" placeholder="pagina" value="${comercio?.pagina}"/></td></tr>
        <tr><td class="table_inf" colspan="3">Ubicacion: </td></tr>
        <tr><td colspan="3"><div id="map" style="width:500px;height:400px"></div></td></tr>
    </g:form>
</table>
<script type="text/javascript">
    <g:if test="${comercio?.latitud && comercio?.longitud}">
        obtenerMapa(${comercio?.latitud},${comercio?.longitud});
    </g:if>
    <g:else>
        obtenerMapa(19.3142052, -99.1835216);
    </g:else>
</script>

<p> Agregar comida:</p>
<g:form name="comida_comercio">
    <table id="comida">        
    </table>
</g:form>
<div id="agregar_comida">
    <input type="button" class="agregar_comida" value="Agregar" onclick="agregarComida()"/>
</div>

<p> Agregar transporte:</p>
<g:form name="transporte_comercio">
    <table id="estacion">
    </table>
</g:form>
<div id="agregar_transporte">
    <input type="button" class="agregar_transporte" value="Agregar" onclick="agregarEstacion()"/>
</div>
<br/>
<br/>
<button type="submit" form="editar_comercio" value="Registrar" style="margin-left:50%;margin-bottom:10%">Registrar</button>