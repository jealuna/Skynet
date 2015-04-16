package skynet;

public class Sesion {

    /**
     * Nombre del usuario que inicio sesion
     */
    private Usuario usuario;

    /**
     * Hash del usuario
     */
    private String codigo;

    /**
     * fecha de inicio de sesion
     */
    private Date fecha;
 
    /*
     * Metodo getter de usuario
     */
    public Usuario getUsuario() {
        return this.usuario;
    }

    /*
     * Metodo getter de codigo
     */
    public String getCodigo() {
        return this.codigo;
    }

    /*
     * Metodo getter de correo
     */
    public Date getFecha() {
        return this.fecha;
    }

     /*
     * Metodo setter de usuario
     * @param usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

     /*
     * Metodo setter de codigo
     * @param codigo
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

     /*
     * Metodo setter de fecha
     * @param fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fechas;
    }

    
     /*
     * Restricciones del sistema en la base
     * de datos, closure de groovy.
     */
    public static constraints = {
        codigo size: 1..150, blank: false;
    }
}
