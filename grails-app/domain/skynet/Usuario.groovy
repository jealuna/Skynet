package skynet;

/*
 * Clase que representa un usuario en Skynet
 * @author Mijail Gutiérrez Valdez
 * @version 1.0
 */
public class Usuario {

    /**
     * Nombre del usuario.
     */
    private String nombre;

    /**
     * Apellido paterno del usuario.
     */
    private String apellidoP;

    /**
     * Apellido materno del usuario.
     */
    private String apellidoM;

    /**
     * Nombre del usuario
     */
    private String nombreUsuario

     /**
      * Contraseña del usuario.
      */
    private String contrasena;

    /**
      * Correo electrónico del usuario, este es unico.
      */
    private String correo;

    /*
     * Metodo getter de nombre
     */
    public String getNombre() {
        return this.nombre;
    }
  
    /*
     * Metodo getter de apellidoP
     */
    public String getApellidoP() {
        return this.apellidoP;
    }

    /*
     * Metodo getter de apellidoM
     */
    public String getApellidoM() {
        return this.apellidoM;
    }

    /*
     * Metodo getter de nombreUsuario
     */
    public String getNombreUsuario() {
        return this.nombreUsuario;
    }

    /*
     * Metodo getter de contrasena
     */
    public String getContrasena() {
        return this.contrasena;
    }

    /*
     * Metodo getter de correo
     */
    public String getCorreo() {
        return this.correo;
    }

    /*
     * Metodo setter de nombre
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
  
    /*
     * Metodo setter de apellidoP
     * @param apellidoP
     */
    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    /*
     * Metodo setter de apellidoM
     * @param apellidoM
     */
    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    /*
     * Metodo setter de nombreUsuario
     * @param nombreUsuario
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /*
     * Metodo setter de contrasena
     * @param contrasena
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /*
     * Metodo setter de correo
     * @param correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Relación 1 usuario a muchos comentarios, muchas calificaciones
     */
    static hasMany = [calificaciones:Calificacion, comentarios:Comentario];

    /*
     * Restricciones del sistema en la base
     * de datos, closure de groovy.
     * @return closure
     */
    public static constraints = {
        nombre size: 1..100, blank: false;
        nombreUsuario size: 1..100, blank: false, unique: true;
        contrasena size: 1..300, blank: false;
        correo size: 3..100, blank: false;
    }


    /**
     * Mapeo en la base de datos
     */
    public static mapping = {
        comentarios cascade: 'all-delete-orphan', lazy: true;
        calificaciones cascade: 'all-delete-orphan', lazy: true;
    }
}
