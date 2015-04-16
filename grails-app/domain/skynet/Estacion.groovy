package skynet;

/*
 * Clase que representa a un estacion cerca
 * de un comercio de Skynet
 * @author Mijail Gutiérrez Valdez
 * @version 1.0
 */
public class Estacion {
    /*
     * Nombre de la estacion
     */
    public String nombre;

    /*
     * Nombre de la estacion
     */
    public Transporte transporte;


    /*
     * Metodo getter de nombre
     */
    public String getNombre() {
        return this.nombre;
    }

   /*
     * Metodo getter de transporte
     */
    public String getTransporte() {
        return this.trasnporte;
    }

    /*
     * Metodo setter de nombre
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /*
     * Metodo setter de trasnporte
     * @param transporte
     */
    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }

    /*
     * Restricciones del sistema en la base
     * de datos, closure de groovy.
     */
    public static constraints = {
        nombre size: 1..100, blank: false;
    }

    /*
     * Restricciones del sistema en la base
     * de datos, closure de groovy.
     */
    //static belongsTo = Transporte;
}
