public class Cita {

    private Alumno alumno;
    private String motivo;
    private boolean tieneCitaPrevia;

    // Constructor de la clase Cita
    public Cita(Alumno alumno, String motivo, boolean tieneCitaPrevia) {
        this.alumno = alumno;
        this.motivo = motivo;
        this.tieneCitaPrevia = tieneCitaPrevia;
    }

    // Metodos get y set
    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
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
