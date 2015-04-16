/**
 * Define las funciones necesarias para el correcto funcionamiento
 * de la pagina index de skynet.
 * @author Mijail Gutiérrez Valdez
 * @version 1.0, febrero 2014
 */

/*
 * Al cargar el documento se inserta la función para el manejo
 * errores al llenar la forma para registra a un usuario
 */
$(document).ready(function() {
	$("#registro").submit(function(event) {
		event.preventDefault();//se detiene el envió de la forma
		createUser(this);
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
}

/**
 * Válida que el correo no este usado por otra persona.
 * @param {DOMElement} inputEmail
 * @param {Boolean} validate
 * @returns {Boolean}
 */
function validate_existUser(inputUser) {
	var object = null;
	var send = {'nombreUsuario': inputUser.value};
	var envio = $.ajax({
						type: 'POST',
 	   					url: "/Skynet/Usuario/existeUsuario",
 	   					timeout: 5000,
 	   					data: send,
 	   					async: false,
 	   					cache: false,
 	   					dataType: 'json'});
 	envio.done (function (obj) {
        	object = obj;
    });
    envio.fail (function (objeto, estado, mensaje) {
           showMessageS(inputUser, 'Error al verificar el usuario', (5 * 1000), 15, 0, 'input-error', 'messageInputE');
    });
    if(object && object.existe != null) {
    	if(object.existe) {
    		showMessageS(inputUser, 'El nombre de usuario ya existe', (5 * 1000), 15, 0, 'input-error', 'messageInputE');
    		return true;
    	} else {
    		return false;
    	}
    }
    return true;
}//validate_user_name

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
 * para crear un nuevo studento del curso elegido
 * @para {DOMElement} form
 * @return {Boolean}
 */
 function createUser(form) {
 	if(validate_email(form.correo) &&
 	   (! validate_existUser(form.nombreUsuario)) && 
 	   validate_pass(form.contrasena, form.recontrasena)) {
 	   var contrasena = CryptoJS.SHA3(form.recontrasena.value, { outputLength: 512 });
 	   var formData = new FormData();
 	   formData.append('nombreUsuario', form.nombreUsuario.value);
           formData.append('nombre', form.nombre.value);
 	   formData.append('correo', form.correo.value);
 	   formData.append('apellidoP', form.apellidoP.value);
           formData.append('apellidoM', form.apellidoM.value);
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
        	 	alert("El usuario ha sido inscrito correctamente");
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
}