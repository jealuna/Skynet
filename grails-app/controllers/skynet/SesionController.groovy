package skynet;

import static org.springframework.http.HttpStatus.*;
import grails.transaction.Transactional;

class SesionController {

    public Object iniciar() {
        return params;
    }

    public Object autenticar() {
        log.info "nombre de usuario: ${params.nombreUsuario}";
        Usuario usuario = Usuario.findByNombreUsuarioAndContrasena(params.nombreUsuario, params.contrasena)
        if(usuario) {
            session.usuario = usuario;
            response.setContentType("application/json");
            render '{"success":true}';
            return;
        }
	response.setContentType("application/json");
	render '{"error":true, "message":"usuario o contraseña incorrectos"}';
    }

    public Object cerrar() {
        response.setContentType("application/json");
        if(session.usuario) {
            session.invalidate();
            render '{"success":true, "message":"sesion cerrada"}';
            return;
        }
	render '{"error":true, "message":"sesion no iniciada"}';
    }
}