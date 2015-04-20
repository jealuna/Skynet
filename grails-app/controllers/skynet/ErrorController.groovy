package skynet;

import static org.springframework.http.HttpStatus.*;
import grails.transaction.Transactional;

/*
 * Controlador de errores en skynet
 */
public class ErrorController {

    public Object acessDenied() {
        return sesionIniciada([:]);
    }

    public Object notFound() {
        return sesionIniciada([:]);
    }

    public Object notAllowed() {
        return sesionIniciada([:]);
    }

    public Object internalError() {
        return sesionIniciada([:]);
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