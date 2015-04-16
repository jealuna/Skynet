/**
 * Define las funciones necesarias para el correcto funcionamiento
 * del skynet.
 * @author Mijail Gutiérrez Valdez
 * @version 1.0, febrero 2014
 */
var marker = null;
var comercioLatitud = 0;
var comercioLongitud = 0;

/*
 * Al cargar el documento se inserta la función para el manejo
 * errores al llenar la forma para registra a un usuario
 */
$(document).ready(function() {
	$("#editar_comercio").submit(function(event) {
		event.preventDefault();//se detiene el envió de la forma
		registrar(this);
	});	
})

/**
 * Obtiene el mapa de google maps
 * @param {Integer} latitud
 * @param {Integer} longitud
 */
function obtenerMapa(latitud, longitud) {
    var pos = new google.maps.LatLng(latitud, longitud);
    var mapOptions = {
        zoom: 13,
        center: pos
    };
    var map = new google.maps.Map(document.getElementById('map'), mapOptions);
    if(latitud !== 19.3142052 && longitud !== -99.1835216) {
        marker = new google.maps.Marker({
            position: pos,
            map: map,
            title: 'Comercio'
        });
    }
    google.maps.event.addListener(map, 'click', function(event) {
        if(marker != null) {
            marker.setMap(null);
        }
        marker = new google.maps.Marker({position: event.latLng, map: map});
        comercioLatitud = event.latLng.lat();
        comercioLongitud = event.latLng.lng();
    });
}
//<tr><td class="table_inf">${comida?.nombre}</td><td class="table_inf" style="color:grey">${comida?.tipo}</td><td class="table_inf_input"><input type="button" class="eliminar_comida" value="eliminar" onclick="eliminarComida(${comida?.id})"/></td></tr>
function agregarComida() {
    var table = document.getElementById("comida");
    var length = table.rows.length;
    var row = table.insertRow(length);
    var cell1 = row.insertCell(0);
    cell1.class="table_inf";
    var cell2 = row.insertCell(1);
    cell2.class="table_inf";
    var input = document.createElement("input");
    input.type = "text";
    input.class = "clase";
    input.placeholder = "Nombre comida";
    cell2.appendChild(input);
    var cell3 = row.insertCell(2); 
}

function agregarEstacion(){ 
    var table = document.getElementById("estacion");
    var length = table.rows.length;
    var row = table.insertRow(length);
    var cell1 = row.insertCell(0);
    cell1.class="table_inf";
    var cell2 = row.insertCell(1);
    cell2.class="table_inf";
    var input = document.createElement("input");
    input.type = "text";
    input.class = "clase";
    input.placeholder = "Nombre estacion";
    cell2.appendChild(input);
    var cell3 = row.insertCell(2);
}

function registrar(form) {
    var formData = new FormData();
    formData.append('nombre', form.nombre.value);
    formData.append('nombre', form.nombre.value);
    formData.append('recomendada', form.recomendada.value);
    formData.append('recomendadaTipo', form.recomendadaTipo.value);
    formData.append('menorPrecio', form.menorPrecio.value);
    formData.append('mayorPrecio', form.mayorPrecio.value);
    formData.append('latitud', comercioLatitud);
    formData.append('longitud', comercioLongitud);
    formData.append('direccion', form.direccion);
    formData.append('pagina', form.pagina);
    formData.append('bano', form.bano.checked);
    formData.append('comedor', form.comedor.checked);
    var comida = document.getElementById("comida_comercio");
    var totalComida = comida.elements.length;
    var transporte = document.getElementById("transporte_comercio");
    var totalTransporte = transporte.elements.length;
    for (i = 0; i < totalComida; i++) { 
        formData.append('comida' + i, comida.elements.item(i).value);
    }
    for (i = 0; i < totalTransporte; i++) { 
        formData.append('transporte' + i, transporte.elements.item(i).value);
    }
    var envio = $.ajax({
 	   						type: 'POST',
 	   						url: $(form).attr('action'),
 	   						xhr: function() {
            						var ownXhr = $.ajaxSettings.xhr();
            						if(ownXhr.upload){
                							ownXhr.upload.addEventListener('progress', function(e) {
                																	progressSend(e,null);
                															}, false);// false for asinchronous
           							}
           							return ownXhr;},
           					timeout: 5000,
 	   						data: formData,
 	   						cache: false,
 	   						contentType: false,
    						processData: false,
 	   						dataType: 'json'});
 	    envio.done (function (object) {
        	 if(object.error) {
        	 	showMessageS(null, 'Error:  <br />' + object.message, (5 * 1000), 0, 0, 'form-error', 'showError');
        	 	return;
        	 }
        	 if(object.success) {
        	 	alert("El comercio se ha creado satisfactoriamente");
        	 	goTo(home);
        	 }
        });
        envio.fail (function (object, estate, message) {
        	if(object.status && object.status === 404) {
        		goTo(home + "error/notFound");
        	}
        	if(object.status && object.status === 500) {
        		goTo(home + 'error/internalError');
        	}
        	alert('Error:\n' + estate + '\n' + message);
        });
}