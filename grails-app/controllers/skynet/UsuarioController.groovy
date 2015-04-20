package skynet;

import static org.springframework.http.HttpStatus.*;
import grails.transaction.Transactional;

public class UsuarioController {

    public Object usuarios(int max) {
        if(!session.usuario || !(session.usuario instanceof Administrador)) {
            render view: '/error', model: [status:405, exception: "Permiso denegado"];
            return;
        }
        params.max = max ?: 10;
        params.offset =  params.offset ?: 0;
        params.clase = "skynet.Usuario"
        Object listaUsuarios =  Usuario.executeQuery("""FROM Usuario as usuario WHERE\n\
                                                            usuario.class=?""",
                                                            [params.clase],
                                                            [max:params.max, offset:params.offset]);
        Object totalUsuarios = Usuario.count();
        return sesionIniciada((LinkedHashMap)[usuarios:listaUsuarios, total:totalUsuarios]);
    }

    public Object registrar(){}

    public Object nuevoRegistro() { 
        if (params == null ||
                                params.nombreUsuario == null ||
                                params.nombre == null ||
                                params.apellidoP == null ||
                                params.apellidoM == null ||
                                params.correo == null ||
                                params.contrasena == null) {
            redirect(controller:"error", action:"notFound")
            return
	}
        Usuario usuario = new Usuario(params);
        if(!usuario.save()) {
            render view: '/error', model: [status:500, exception:usuario.errors];
            return
        }
        response.setContentType("application/json");
        render '{"success":true,"message":"El usuario se ha registrado correctamente"}';
    }

    public Object existeUsuario() { 
        response.setContentType("application/json");
        if (params && params.nombreUsuario) {
            Usuario usuario = Usuario.findByNombreUsuario(params.nombreUsuario)
            if(usuario) {
                render '{"existe":true}';
                return;
            }
            render '{"existe":false}';
            return;
	}
        render '{"error":"email is null"}'
    }

    public Object editar() {
        if(!session.usuario) {
            render view: '/error', model: [status:405, exception: "Permiso denegado"];
            return;
        }
        Usuario usuario = Usuario.get(params.id);
        if(usuario) {
            return sesionIniciada((LinkedHashMap)[editar:usuario]);
        }
        render view: '/error', model: [status:500, exception: "Usuario no encontrado"];
    }

    public Object modificar() {
        if(!session.usuario) {
            render view: '/error', model: [status:405, exception: "Permiso denegado"];
            return;
        }
	Usuario usuario = Usuario.get(params.id);
        if(!usuario) {
            render view: '/error', model: [status:500, exception: "Usuario no encontrado"];
            return;
        }
        if(params.nombre) {
            usuario.setNombre(params.nombre);
        }
        if(params.apellidoP) {
            usuario.setApellidoP(params.apellidoP);
        }
	if(params.apellidoM) {
            usuario.setApellidoM(params.apellidoM);
        }
        if(params.correo) {
            usuario.setCorreo(params.correo);
        }
        if(params.contrasena) {
            usuario.setContrasena(params.contrasena);
        }
        if (!usuario.save(flush:true)) {
            StringBuilder sb = new StringBuilder();
            usuario.errors.each {
		sb.append(it.toString() + "<br />");
            }
            response.setContentType("application/json")
            render '{"error":true,"message":"' + sb.toString() + '"}'
            return
	}
	response.setContentType("application/json")
	render '{"success":true,"message":"El usuario se ha modificado satisfactoriamente"}'
    }

    public Object eliminar() {
        if(!session.usuario || !(session.usuario instanceof Administrador)) {
            render view: '/error', model: [status:405, exception: "Permiso denegado"];
            return;
        }
        Usuario usuario = Usuario.get(params.id);
        if(!usuario) {
            render view: '/error', model: [status:500, exception: "Usuario no encontrado"];
            return;
        }
	usuario.delete(flush:true);
	response.setContentType("application/json")
	render '{"success":true,"message":"El usuario se ha eliminado satisfactoriamente"}'
    }

    public Object sesionIniciada(LinkedHashMap parametros) {
        if(session.usuario) {
            parametros.put('accedio', true);
            parametros.put('usuario', session.usuario);
            if(session.usuario instanceof Administrador) {
                parametros.put('admin', true);
            }
            return parametros;
        }
        return parametros;
    }
}
			