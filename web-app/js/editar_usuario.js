/**
 * Define las funciones necesarias para el correcto funcionamiento
 * del skynet.
 * @author Mijail Gutiérrez Valdez
 * @version 1.0, febrero 2014
 */

/*
 * Al cargar el documento se inserta la función para el manejo
 * errores al llenar la forma para registra a un postulante a profesor
 */
$(document).ready(function() {
    $("#editar_usuario").submit(function(event) {
        event.preventDefault();//se detiene el envió de la forma
	editar(this);
    });

    $("#eliminar_usuario").submit(function(event) {
        event.preventDefault();//se detiene el envió de la forma
	eliminar(this);
    });
})

/**
 * Válida que la cadena dada represente una dirección de correo electrónico
 * válida
 * @param {DOMElement} inputMail
 * @returns {Boolean}
 */
function validate_email(inputMail) {
	if((inputMail.value.indexOf('@') > 1) &&
       (inputMail.value.lastIndexOf('.') >= inputMail.value.indexOf('@') +1) &&
       (inputMail.value.lastIndexOf('.') +1 <= inputMail.value.length)) {
       return true;
    }
    showMessageS(inputMail, 'Email incorrecto', (5 * 1000), 15, 0, 'input-error', 'messageInputE');
    return false;
}//validate_email

/**
 * Válida que las contraseñas sean iguales, de
 * ser asi regresa true, false en caso contrario.
 * @param {DOMElement} inputPass1
 * @param {DOMElement} inputPass2
 * @returns {Boolean}
 */
function validate_pass(inputPass1, inputPass2) {
	if(inputPass1.value === inputPass2.value) {
		return true;
	}
	showMessageS(inputPass2, 'La contraseña no coincide', (5 * 1000), 15, 0, 'input-error', 'messageInputE');
	return false;
}//validate_pass

/*
 * Envía los datos necesarios de la forma form 
 * para crear un nuevo postulante a profesor
 * @para {DOMElement} form
 */
 function editar(form) {
    if(!confirm ("Quieres modifica el registro?")) {
        return false;
    }
    var formData = new FormData();
    if(form.correo.value !== "") {
        if(validate_email(form.correo)) {
            formData.append('correo', form.correo.value);
        } else {
            return false;
        }
    }
    if(form.contrasena.value !== "" || form.recontrasena.value !== "") {
        if(validate_pass(form.contrasena, form.recontrasena)) {
            var contrasena = CryptoJS.SHA3(form.recontrasena.value, { outputLength: 512 });
            formData.append('contrasena', contrasena);
        } else {
            return false;
        }
    }
    if(form.nombre.value !== "") {
        formData.append('nombre', form.nombre.value);
    }
    if(form.apellidoP.value !== "") {
        formData.append('apellidoP', form.apellidoP.value);
    }
    if(form.apellidoM.value !== "") {
        formData.append('apellidoM', form.apellidoM.value);
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
            return ownXhr;}, timeout: 5000,
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
            showMessageS(null, 'El registro se ha modificado satisfacoriamente', (5 * 1000), 0, 0, 'container', 'showSuccess');
        }
    });
    envio.fail (function (object, estate, message) {
        if(object.status && object.status == 404) {
            goTo(home + "error/notFound");
        }
        if(object.status && object.status == 500) {
            goTo(home + 'error/internalError/?pcontroller=professor&paction=register');
        }
        alert('Error:\n' + estate + '\n' + message);
    });
}

/*
 * Envía los datos para eliminar un registro de usuario
 * @para {DOMElement} form
 * @return {undefined}
 */
function eliminar(form) {
        if(! confirm("Desea eliminar el registro")) {
            return false;
        }
 	var envio = $.ajax({
 	   					type: 'POST',
 	   					url: $(form).attr('action'),
 	   					xhr: function() {
            					var ownXhr = $.ajaxSettings.xhr();
            					if(ownXhr.upload){
                						ownXhr.upload.addEventListener('progress', function(e) {
                																progressSend(e,null);
                														}, false);// no asincrono
           						}
           						return ownXhr;},
           				timeout: 5000,
 	   					data: null,
 	   					cache: false,
 	   					contentType: false,
    					processData: false,
 	   					dataType: 'json'});
    envio.done (function (object) {
    	if(object.error) {
        	showMessageS(null, 'Error:  <br/>' + object.message, (5 * 1000), 0, 0, 'form-error', 'showError');
        	return;
        }
        if(object.success) {
        	alert("El usuario se ha eliminado satisfactoriamente");
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