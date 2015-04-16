<div id="board">
    <g:each var="usuario" in="${usuarios}">
        <br/>
        <table class="table_table" onclick="goTo('${createLink(action:'editar', controller:'usuario', params: [id:usuario?.id])}')">
            <tr><td class="table_inf">Nombre usuario: </td><td class="table_inf_input" style="color:grey">${usuario?.nombreUsuario}</td></tr>
            <tr><td class="table_inf">Nombre: </td><td class="table_inf_input" style="color:grey">${usuario?.nombre}</td></tr>
            <tr><td class="table_inf">Correo: </td><td class="table_inf_input" style="color:grey">${usuario?.correo}</td></tr>
        </table>
        <br/>
        <hr>
    </g:each>
</div>
<div class="pagination">
    <g:paginate total="${total ?: 0}" />
</div>
