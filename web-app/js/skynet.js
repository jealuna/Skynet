/**
 * Define las funciones necesarias para el correcto funcionamiento
 * de la pagina index de skynet.
 * @author Mijail Gutiérrez Valdez
 * @version 1.0, febrero 2014
 */

/*
 * Al cargar el documento se inserta la función para el manejo
 * de funciones al editar a un postulante
 */
$(document).ready(function() {
	$("#close_user").click(function(event) {
		event.preventDefault();//se detiene el envió de la forma
		cerrarSesion(this);
	});
})

/**
 * Redirecciona a un visitante a nivel de ingles que se desea,
 * atravez de la funcion  window.location.assign
 * @param pagina donde se dese ir
 * @returns {undefined}
 */
function goTo(page) {
    window.location.assign(page);
}

/**
 * Muestra un mensaje posicionado en left por posx y en top
 * por posy  respecto al objeto obj un a este sele agregara
 * la clase class, la clase y el mensaje msj duraran tiempo time
 * @param {DOMElement} obj
 * @param {String} msj
 * @param {Int} time
 * @param {Int} posx
 * @param {Int} posy
 * @param {String} class
 * @returns {undefined}
 */
function showMessageS(obj, msj, time, posx, posy, classObj, classMess) {
	var message = document.createElement('div');
	message.style.position= 'absolute';
	message.style.zIndex = '9999';//por defecto
	message.className = classMess;//esta clase debe de estar definida en css
	var pos = getPosition(obj);
	if(obj) {
		message.height = obj.offsetHeight + 'px';//por defecto
		message.height = obj.offsetHeight + 'px';//por defecto
		message.style.left = (pos.x + obj.offsetWidth + posx) + 'px';
		message.style.top = (pos.y + posy) + 'px';
		$(obj).addClass(classObj);
	}
	message.innerHTML = msj;
	message.style.display = 'none';
	document.body.appendChild(message);
	$("html, body").animate({ scrollTop: ((pos.y - 50) + 'px') });
	$(message).fadeIn('slow');
	setTimeout( function () {
		$(message).fadeOut('slow', function () {
			document.body.removeChild(message);
			if(obj) {
				$(obj).removeClass(classObj);
			}
		});
	}, time);
}

/**
 * Regresa la posicion x y del objeto DOM que es pasado
 * como parametro en la función
 * @param {DOMElement} obj
 * @returns {Object} {x,y}
 */
function getPosition(obj) {
	if(obj) {
		var left = obj.offsetLeft;
		var top = obj.offsetTop;
		var parentPos = getPosition(obj.offsetParent);
		left += parentPos.x;
		top += parentPos.y;
		return {x:left, y:top}
	}
	return {x:0, y:0}
}

/**
 * Funcion de progeso para el envio de la forma
 * como parametro en la función
 * @param {xhmr} progress
 * @param {DOMElement} obj
 * @returns {undefined}
 */
function progressSend(progress, obj) {	
    if(progress.lengthComputable) {
        console.log('xhr progress: ' + progress.loaded + '  max: ' + progress.total);
    }
}

/**
 * Cierra sesion.
 * @param {DOMElement} a
 * @param {Boolean} validate
 * @returns {Boolean}
 */
function cerrarSesion(a) {
        if(! confirm ("Cerrar sesion")) {
            return false;
        }
	var send = {'cerrar': 'sesion'};
	var envio = $.ajax({
						type: 'POST',
 	   					url: "/Skynet/sesion/cerrar",
 	   					timeout: 5000,
 	   					data: send,
 	   					async: false,
 	   					cache: false,
 	   					dataType: 'json'});
 	envio.done (function (object) {
            if(object && object.error != null) {
              	showMessageS(null, 'Error:  <br />' + object.message, (5 * 1000), 15, 0, 'input-error', 'showError');
                return true;
            } else {
                goTo(home);
                return true;
            }
    });
    envio.fail (function (objeto, estado, mensaje) {
           showMessageS(null, 'Error al cerrar sesion', (5 * 1000), 15, 0, 'input-error', 'showError');
    });
    return true;
}//validate_user_name