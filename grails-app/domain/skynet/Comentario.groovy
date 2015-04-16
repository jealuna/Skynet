package skynet;

/*
 * Clase que representa un comentario que
 * se otorga a un comercio de Skynet
 * @author Mijail Gutiérrez Valdez
 * @version 1.0
 */
public class Comentario {
    /*
     * comentario otorgada
     */
    public String comentario;

    /*
     * fecha cuando se hizo el comentario
     */
    public Date fecha;

    /*
     * Metodo getter de comentario
     */
    public String getComentario() {
        return this.tipo;
    }

    /*
     * Metodo getter de usuario
     */
    public int getUsuario() {
        return this.usuario;
    }

    /*
     * Metodo getter de comercio
     */
    public int getComercio() {
        return this.comercio;
    }

    /*
     * Metodo getter de fecha
     */
    public Date getFecha() {
        return this.fecha;
    }

    /*
     * Metodo setter de fecha
     * @param fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /*
     * Metodo setter de comentario
     * @param comentario
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

        /*
     * Metodo setter de usuario
     * @param usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    /*
     * Metodo setter de comercio
     * @param comercio
     */
    public void setComercio(Comercio comercio) {
        this.comercio = comercio;
    }

     /*
     * Restricciones del sistema en la base
     * de datos, closure de groovy.
     */
    public static constraints = {
        comentario size: 1..500, blank: false;
    }
}