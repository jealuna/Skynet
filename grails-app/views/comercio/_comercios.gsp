<div id="board">
    <g:each var="comercio" in="${comercios}">
        <br/>
        <p>${comercio?.nombre}</p>
            <g:if test="${accedio == true && admin == true}">
                <table class="main_table" onclick="goTo('${createLink(action:'editarComercio', controller:'administrador', params: [id:comercio?.id], absolute:"true")}')">
            </g:if>
            <g:else>
                <table class="main_table" onclick="goTo('${createLink(action:'comercio', controller:'comercio', params: [id:comercio?.id], absolute:"true")}')"> 
            </g:else>
                    <tr><td class="table_inf">Servicios(comer/llevar): </td><td class="table_inf_input" style="color:grey"><g:if test="${comercio?.comedor == true}"> Comer y llevar </g:if><g:else>Llevar</g:else></td></tr>
                    <tr><td class="table_inf">Baño: </td><td class="table_inf_input" style="color:grey"> <g:if test="${comercio?.bano == true}"> Si tiene </g:if><g:else>No tiene</g:else></td></tr>
                    <tr><td class="table_inf">Direccion: </td><td class="table_inf_input" style="color:grey">${comercio?.direccion}</td></tr>
                    <tr><td class="table_inf">Pagina web: </td><td class="table_inf_input"><a style="color:grey">${comercio?.pagina}</a></td></tr>
                    <tr><td class="table_inf">Calificacion: </td><td class="table_inf_input"><g:each in="[1,2,3,4,5]" var="i"> <g:if test="${i <= comercio?.calificacion}"><div class="rating_star_on"><div></div></div></g:if><g:else><div class="rating_star_off"><div></div></div></g:else></g:each></td></tr>
                </table>
            <br/>
            <hr/>
    </g:each>
</div>
<div class="pagination">
    <g:paginate total="${total ?: 0}" />
</div>