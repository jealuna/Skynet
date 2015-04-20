package skynet;

/*
 * Clase que representa un usuario en Skynet
 * @author Mijail Gutiérrez Valdez
 * @version 1.0
 */
public class Comida {

    /**
     * Nombre de la comida.
     */
    private String nombre;

    /**
     * Precio de la comida.
     */
    private int precio;

    /**
     * Tipo de comida.
     */
    private String tipo;

    /**
     * Tipo de comercio.
     */
    private Comercio comercio;

    /*
     * Metodo getter de nombre
     */
    public String getNombre() {
        return this.nombre;
    }

    /*
     * Metodo getter de precio
     */
    public int getPrecio() {
        return this.precio;
    }

    /*
     * Metodo getter de tipo
     */
    public String getTipo() {
        return this.tipo;
    }

    /*
     * Metodo getter de comercio
     */
    public Comercio getComercio() {
        return this.comercio;
    }

    /*
     * Metodo setter de nombre
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /*
     * Metodo setter de precio
     * @param precio
     */
    public int setPrecio(int precio) {
        this.precio = precio;
    }

    /*
     * Metodo setter de tipo
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        nombre size: 1..100, blank: false;
        tipo size: 1..200;
    }

    /*
     * Relacion 1 a 1 con comida
     */
    static belongsTo = Comercio;
}
