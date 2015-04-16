package skynet;

/*
 * Clase que representa a un transporte
 * que pasa por un comercio de Skynet
 * @author Mijail Gutiérrez Valdez
 * @version 1.0
 */
public class Transporte {

    /*
     * Nombre del transporte
     */
    public String nombre;

    /*
     * Metodo getter de nombre
     */
    public String getNombre() {
        return this.nombre;
    }

    /*
     * Metodo setter de nombre
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Relación 1 trasporte a muchas estaciones
     */
    static hasMany = [estaciones:Estacion];

    /*
     * Las restricciones del objeto
     */
    public static constraints = {
        nombre blank: false, unique: true;
    }
}
