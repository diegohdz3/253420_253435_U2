// === EXPLICA HARUMI: Clase Cita (Datos de cada registro de tutoria) ===
// Representa cada cita o turno: guarda alumno, matricula, nombre, motivo y si tiene cita previa.
public class Cita {

    private Alumno alumno;
    private int matricula;
    private String nombre;
    private String motivo;
    private boolean tieneCitaPrevia;

    // Constructor recibiendo el objeto Alumno
    public Cita(Alumno alumno, String motivo, boolean tieneCitaPrevia) {
        this.alumno = alumno;
        this.matricula = alumno.getMatricula();
        this.nombre = alumno.getNombre();
        this.motivo = motivo;
        this.tieneCitaPrevia = tieneCitaPrevia;
    }

    // Constructor recibiendo los datos directos
    public Cita(int matricula, String nombre, String motivo, boolean tieneCitaPrevia) {
        this.alumno = new Alumno(nombre, matricula);
        this.matricula = matricula;
        this.nombre = nombre;
        this.motivo = motivo;
        this.tieneCitaPrevia = tieneCitaPrevia;
    }

    // Metodos get (para consultar los datos) y set (para modificarlos)
    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
        this.matricula = alumno.getMatricula();
        this.nombre = alumno.getNombre();
    }

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
