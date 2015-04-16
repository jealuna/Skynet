/**
 * Define las funciones necesarias para el correcto funcionamiento
 * de la edicion del studento de skynet.
 * @author Mijail Gutiérrez Valdez
 * @version 1.0, febrero 2014
 */

/*
 * Al cargar el documento se inserta la función para el manejo
 * de funciones al editar a un postulante
 */
$(document).ready(function() {
	$("#login_form").submit(function(event) {
		event.preventDefault();//se detiene el envió de la forma
		login(this);
	});
})

/*
 * Envía los datos necesarios de la forma form 
 * para editar un studento
 * @para {DOMElement} form
 * @return {undefined}
 */
 function login(form) {
        var formData = new FormData();
 	formData.append('nombreUsuario', form.nombreUsuario.value);
        var contrasena = CryptoJS.SHA3(form.contrasena.value, { outputLength: 512 });
 	formData.append('contrasena', contrasena);
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
        	 	goTo(home);
        	 }
        });
        envio.fail (function (object, estate, message) {
        	if(object.status && object.status == 404) {
        		goTo(home + "error/notFound");
        	}
        	if(object.status && object.status == 500) {
        		goTo(home + 'error/internalError');
        	}
        	if(object.status && object.status == 405) {
   				alert('Error metodo no encontrado');
   				return;
        	}
        	alert('Error:\n' + estate + '\n' + message);
        });
}