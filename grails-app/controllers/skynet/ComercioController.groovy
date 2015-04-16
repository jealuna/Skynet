package skynet;

class ComercioController {

    public Object index(int max) {
        params.max = max ?: 10;
        params.offset =  params.offset ?: 0;
        Object listaComercios =  Comercio.executeQuery("""FROM Comercio comercio ORDER BY
                                                          comercio.calificacion DESC""");
        Object totalComercios = Comercio.count();
        return sesionIniciada((LinkedHashMap)[comercios:listaComercios, total:totalComercios]);
    }

     public Object comercios(int max) {
        if(!session.usuario || !(session.usuario instanceof Administrador)) {
            render view: '/error', model: [status:405, exception: "Permiso denegado"];
            return;
        }
        params.max = max ?: 10;
        params.offset =  params.offset ?: 0;
        Object listaComercios =  Comercio.list(max:params.max, offset:params.offset);
        Object totalComercios = Comercio.count();
        return sesionIniciada((LinkedHashMap)[comercios:listaComercios, total:totalComercios]);
    }

    public Object nuevo() {
         if(!session.usuario || !(session.usuario instanceof Administrador)) {
            render view: '/error', model: [status:405, exception: "Permiso denegado"];
            return;
        }
        return sesionIniciada([:]);
    }

    public Object registrar(){
        if(!session.usuario || !(session.usuario instanceof Administrador)) {
            render view: '/error', model: [status:405, exception: "Permiso denegado"];
            return;
        }
        Comercio comercio = new Comercio(nombre: params.nombre,
                                         direccion: params.direccion,
                                         pagina: params.pagina,
                                         menorPrecio: params.menorPrecio,
                                         mayorPrecio: params.mayorPrecio,
                                         latitud: params.latitud,
                                         longitud: params.longitud,
                                         comedor: params.comedor,
                                         bano: params.bano,
                                         recomendada: new Comida (nombre: params.recomendada,
                                                                  tipo: params.recomendadaTipo));
        comercio.save(flush:true);
        response.setContentType("application/json")
	render '{"success":true,"message":"El usuario se ha modificado satisfactoriamente"}'
    }
 
    public Object buscar() {
        Object listaComercios = Comercio.findByNombreLike("%${params.buscar}%");
        return sesionIniciada((LinkedHashMap)[comercios:listaComercios]);
    }

    public Object busquedaAvanzada() {
        if(params && params.buscar) {
            Object resultado = [];
            if(params.nombre) {
                resultado += Comercio.findByNombreLike("%${params.buscar}%");
            }
            if(params.recomendada) {
                resultado += Comercio.executeQuery("""FROM Comercio as comercio WHERE
                                                      comercio.recomendada.nombre LIKE=%?%""",
                                                      [params.recomendada]);
            }
            if(params.menorprecio && params.mayorprecio) {
                resultado += Comercio.executeQuery("""FROM Comercio as comercio WHERE
                                                      comercio.menorPrecio < ? AND 
                                                      comercio.mayorPrecio > ?""",
                                                      [params.menorprecio, params.mayorprecio]);
            }
            if(params.estacion) {
                resultado += Comercio.executeQuery("""FROM Comercio as comercio WHERE
                                                      comercio.estaciones.nombre LIKE=%?%""",
                                                      [params.estacion]);
            }
            if(params.latitud && params.longitud) {
                resultado += Comercio.executeQuery("""FROM Comercio as comercio WHERE
                                                      SQRT(POWER(comercio.latitud-?,2)\n\
                                                     + POWER(comercio.longitud-?,2)) < 0.01 """,
                                                      [params.latitud, params.longitud]);
            }
            if(params.comida) {
                resultado += Comercio.executeQuery("""FROM Comercio as comercio WHERE
                                                      comercio.comidas.nombre LIKE=%?%""",
                                                      [params.comida]);
            }
            if(params.tipo) {
                resultado += Comercio.executeQuery("""FROM Comercio as comercio WHERE
                                                      comercio.comidas.tipo LIKE=%?%""",
                                                      [params.tipo]);
            }
            return sesionIniciada((LinkedHashMap)[comercios:resultado, busqueda:true]);
        }
        return resultado;
    }

    public Object comercio() {
        if(params && params.id) {
            int maximos = 10;//por omision se muestran los primeros 10 comentarios
            int inicio = 0;//por omision siempre se toma desde los primeros
            Object listaComentarios = Comentario.executeQuery("""FROM Comentario AS comentario WHERE
                                                                 comentario.comercio =?""",
                                                                [Integer.parseInt(params.id)] ,
                                                                [max:maximos, offset:inicio]);
            return sesionIniciada((LinkedHashMap)[comercio:Comercio.get(params.id), comentarios:listaComentarios]);
        }
        render view: '/error', model: [status:500, exception: "Error comercio incorrecto"];
    }

    public Object comentarios() {
        if(params && params.id) {
            params.max = params.max ?: 10;
            params.offset = params.offset ?: 0;
            Object listaComentarios = Comentario.executeQuery("""FROM Comentario AS comentario WHERE
                                                                 comentario.comercio =?""",
                                                                [Integer.parseInt(params.id)] ,
                                                                [max:params.max, offset:params.offset]);
            return [comentarios:listaComentarios];
        }
        render view: '/error', model: [status:500, exception: "Error comercio incorrecto"];
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
	if(params.apellidoP) {
            usuario.setApellidoP(params.apellidoP);
        }
        if(params.correo) {
            usuario.setApellidoM(params.correo);
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
	if (!usuario.delete(flush:true)) {
            StringBuilder sb = new StringBuilder();
            usuario.errors.each {
		sb.append(it.toString() + "<br />");
            }
            response.setContentType("application/json")
            render '{"error":true,"message":"' + sb.toString() + '"}'
            return
	}
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
