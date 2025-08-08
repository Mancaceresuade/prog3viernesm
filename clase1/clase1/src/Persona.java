public abstract class Persona {
    private int id;
    private String nombre;
    private static final String saludo = "Hola, soy una persona";

    public static String getSaludo() {
        return saludo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
