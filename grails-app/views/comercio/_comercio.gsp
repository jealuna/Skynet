<div id="board">
    <div>
        <p>${comercio?.nombre}</p>
    </div>
    <br/>
      <!--<img src="${createLink(action:'imagen', controller:'comercio', params: [id:comercio?.id], absolute:"true")}" alt="${comercio?.nombre}" height="50" width="50">-->
    <table id="main_table">
        <tr><td class="table_inf" colspan="2">Comidas: </td></tr>
        <g:each var="comida" in="${comercio?.comidas}">
            <tr><td class="table_inf">${comida?.nombre}</td><td class="table_inf_input" style="color:grey">${comida?.precio}</td></tr>
        </g:each>
        <tr><td class="table_inf" colspan="2">Recomendada: </td></tr>
        <tr><td class="table_inf">${comercio?.recomendada?.nombre}</td><td class="table_inf_input" style="color:grey">${comercio?.recomendada?.precio}</td></tr>
        <tr><td class="table_inf">Rango precios: </td><td class="table_inf_input">${comercio?.mayorPrecio} - ${comercio?.menorPrecio}</td></tr>
        <tr><td class="table_inf">Servicios(comer/llevar): </td><td class="table_inf_input" style="color:grey"><g:if test="${comercio?.comedor == true}"> Comer y llevar </g:if><g:else>Llevar</g:else></td></tr>
        <tr><td class="table_inf">Baño: </td><td class="table_inf_input" style="color:grey"> <g:if test="${comercio?.bano == true}"> Si tiene </g:if><g:else>No tiene</g:else></td></tr>
        <tr><td class="table_inf">Direccion: </td><td class="table_inf_input" style="color:grey">${comercio?.direccion}</td></tr>
        <tr><td class="table_inf">Pagina web: </td><td class="table_inf_input"><a style="color:grey">${comercio?.pagina}</a></td></tr>
        <tr><td class="table_inf" colspan="2">Ubicacion: </td></tr>
        <tr><td colspan="2"><div id="map" style="width:500px;height:400px"></div></td></tr>
        <tr><td class="table_inf" colspan="2">Transporte cercano: </td></tr>
        <g:each var="estacion" in="${comercio?.estaciones}">
            <tr><td class="table_inf"></td><td class="table_inf_input" style="color:grey">${estacion?.nombre}</td></tr>
        </g:each>
        <g:if test="${accedio}">
            <g:form id="calificar" controller="comercio" action="calificar" params="[id:'${comida?.id}', usuario:'${usuario?.id}']">
                <tr><td class="table_inf">Calificacion: </td><td class="table_inf_input"><g:each in="[1,2,3,4,5]" var="i"> <g:if test="${i <= comercio?.calificacion}"><div class="rating_star_on"><div id="rating_start_${i}" onclick="calificar(this, ${i})"></div></div></g:if><g:else><div class="rating_star_off"><div id="rating_start_${i}" onclick="calificar(this, ${i})"></div></div></g:else></g:each></td></tr>
            </g:form>
            <tr><td class="table_inf">Agregar Comentario:</td></tr>
            <g:form id="comentar" controller="comercio" action="comentar" params="[id:'${comida?.id}', usuario:'${usuario?.id}']">
                <table width = "500" height = "400" ><tr><td colspan="3"><textarea class="ckeditor" id="agregar_comentario" rows="4" cols="50" form="comentar"></textarea></td></tr>
                <tr><td></td><td colspan="2"><input type="submit" name="new_wannabie" value="Comentar" id="new_wannabie" /></td></tr>
            </g:form>
        </g:if>
        <g:else>
            <tr><td class="table_inf">Calificacion: </td><td class="table_inf_input"><g:each in="[1,2,3,4,5]" var="i"> <g:if test="${i <= comercio?.calificacion}"><div class="rating_star_on"><div></div></div></g:if><g:else><div class="rating_star_off"><div></div></div></g:else></g:each></td></tr>
        </g:else>
    </table>
    <script type="text/javascript">
        <g:if test="${comercio?.latitud && comercio?.longitud}">
            obtenerMapa(${comercio?.latitud},${comercio?.longitud});
        </g:if>
        <g:else>
            obtenerMapa(19.3142052, -99.1835216);
        </g:else>
    </script>
    <br/>
    <div id="comparte">
        <p> Compartir </p>
        <ul>
            <script type="text/javascript" src="${createLinkTo(dir:'js',file:'share.js')}" ></script>
            <li><div class="facebook" onclick="shareOnFacebook()">facebook</div></li>
            <li><div class="twitter" onclick="shareOnTwitter()">twitter</div></li>
        </ul>
    </div>
    <br/>
    <div id="comment_users">
        <p> Comentarios </p>
        <g:each var="comentario" in="${comentarios}">
            <div class="comentr_user_name"> ${comentario?.usuario?.nombre} <g:formatDate format="yyyy-MM-dd" date="${comentario?.fecha}"/></div>
            <div class="coment_user_text">${comentario?.comentario}</div>
        </g:each>
    </div>
</div>