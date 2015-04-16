package skynet;

/*
 * Clase que representa una calificacion
 * que se otroca a un comercio de Skynet
 * @author Mijail Gutiérrez Valdez
 * @version 1.0
 */
public class Calificacion {

    /*
     * calificación otorgada
     */
    public int calificacion;

    /*
     * usuario que la otorgo
     */
    public Usuario usuario;

    /*
     * comercio que se le asigno
     */
    public Comercio comercio;

    /*
     * Metodo getter de calificación
     */
    public int getCalificacion() {
        return this.calificacion;
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
     * Metodo setter de calificación
     * @param calificacion
     */
    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
}
