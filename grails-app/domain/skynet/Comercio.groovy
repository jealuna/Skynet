package skynet;

/*
 * Clase que representa un comercio que se
 * encuentra en Skynet
 * @author Mijail Gutiérrez Valdez
 * @version 1.0
 */
public class Comercio {

   /**
     * Nombre del comercio.
     */
    private String nombre;

    /**
     * Direccion del comercio.
     */
    private String direccion;

    /**
     * Pagina web.
     */
    private String pagina;

    /**
     * Ubicacion del local latitud.
     */
    private double latitud;

    /**
     * Ubicacion del local longitud.
     */
    private double longitud;

    /**
     * Ubicacion del local longitud.
     */
    private double calificacion;

    /**
     * Indica si hay comedor en el comercio.
     */
    private boolean comedor;

    /**
     * Indica si hay bano en el comercio.
     */
    private boolean bano;

    /**
     * Direccion del comercio.
     */
    private Comida recomendada;

    /**
     * Direccion del comercio.
     */
    private double menorPrecio;

    /**
     * Direccion del comercio.
     */
    private double mayorPrecio;

    /**
     * Imagen del comercio.
     */
    private byte[] foto;


    /*
     * Metodo getter de nombre
     */
    public String getNombre() {
        return this.nombre;
    }
  
    /*
     * Metodo getter de direccion
     */
    public String getDireccion() {
        return this.direccion;
    }

    /*
     * Metodo getter de pagina
     */
    public String getPagina() {
        return this.pagina;
    }

    /*
     * Metodo getter de latitud
     */
    public double getLatitud() {
        return this.latitud;
    }

    /*
     * Metodo getter de longitud
     */
    public double getLongitud() {
        return this.longitud;
    }

    /*
     * Metodo getter de si hay bano
     */
    public boolean getBano() {
        return this.bano;
    }
    
    /*
     * Metodo getter de si hay comedor
     */
    public boolean getComedor() {
        return this.bano;
    }

    /*
     * Metodo getter de comidad recomendada
     */
    public Comida getRecomendada() {
        return this.recomendada;
    }

    /*
     * Metodo getter la foto
     */
    public byte[] getFoto() {
        return this.foto;
    }

    /*
     * Metodo getter la calificacion
     */
    public double getCalificacion() {
        return this.calificacion;
    }

    /*
     * Metodo getter del menor precio
     */
    public double getMenorPrecio() {
        return this.menorPrecio;
    }

    /*
     * Metodo getter la calificacion
     */
    public double getMayorPrecio() {
        return this.mayorPrecio;
    }

    /*
     * Metodo setter de nombre
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
  
    /*
     * Metodo setter de direccion
     * @param direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /*
     * Metodo setter de pagina
     * @param pagina
     */
    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    /*
     * Metodo setter de latitud
     * @param latitud
     */
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    /*
     * Metodo setter de longitud
     * @param longitud
     */
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    /*
     * Metodo setter de si hay bano
     * @param bano
     */
    public void setBano(boolean bano) {
        this.bano = bano;
    }

    /*
     * Metodo setter de si hay comedor
     * @param comedor
     */
    public void setComedor(boolean comedor) {
        this.comedor = comedor;
    }

    /*
     * Metodo setter de comida recomendada
     * @param comida
     */
    public void setRecomendada(Comida recomendada) {
        this.comedor = comedor;
    }

    /*
     * Metodo setter de foto
     * @param foto
     */
    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    /*
     * Metodo setter de calificacion
     * @param calificacion
     */
    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    /*
     * Metodo setter de menor precio
     * @param menorPrecio
     */
    public void setMenorPrecio(double menorPrecio) {
        this.menorPrecio = menorPrecio;
    }

    /*
     * Metodo setter de mayor precio
     * @param mayorPrecio
     */
    public void setMayorPrecio(double mayorPrecio) {
        this.mayorPrecio = mayorPrecio;
    }

    /**
     * Relación 1 comercio a muchas comidas, muchos comentarios, muchas calificaciones, muchas estaciones
     */
    static hasMany = [comidas:Comida, comentarios:Comentario, calificaciones:Calificacion, estaciones:Estacion];

    /*
     * Restricciones del sistema en la base
     * de datos, closure de groovy.
     */
    public static constraints = {
        nombre size: 1..100, blank: false;
        direccion size: 1..300;
        bano blank: false;
        comedor blank: false;
        direccion size: 1..500, blank: false;
    }

    /**
     * Mapeo en la base de datos
     */
    public static mapping = {
        foto sqlType: 'longblob';
        comidas cascade: 'all-delete-orphan';
        comentarios cascade: 'all-delete-orphan', lazy:true;
        calificaciones cascade: 'all-delete-orphan';
    }
}
