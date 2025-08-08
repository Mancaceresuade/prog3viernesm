public class Jugador extends Persona implements IContratable {
    private String equipo;
    private int numeroCamiseta;

    public Jugador(int id, String nombre, String equipo, int numeroCamiseta) throws Exception  {
        if(nombre == null || nombre.isEmpty()) {
            throw new Exception("El nombre no puede ser nulo o vacio");
        }
        setId(id);
        setNombre(nombre);
        this.equipo = equipo;
        this.numeroCamiseta = numeroCamiseta;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public int getNumeroCamiseta() {
        return numeroCamiseta;
    }

    public void setNumeroCamiseta(int numeroCamiseta) {
        this.numeroCamiseta = numeroCamiseta;
    }

    @Override
    public void contratar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contratar'");
    }
    
}
