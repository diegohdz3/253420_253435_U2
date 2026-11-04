public class Cita {

    private int matricula;
    private String nombre;
    private String motivo;
    private boolean tieneCitaPrevia;

    // Constructor con todos los datos
    public Cita(int matricula, String nombre, String motivo, boolean tieneCitaPrevia) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.motivo = motivo;
        this.tieneCitaPrevia = tieneCitaPrevia;
    }

    // Metodos get y set
    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public boolean getTieneCitaPrevia() {
        return tieneCitaPrevia;
    }

    public void setTieneCitaPrevia(boolean tieneCitaPrevia) {
        this.tieneCitaPrevia = tieneCitaPrevia;
    }
}
