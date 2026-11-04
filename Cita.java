public class Cita {

    private Alumno alumno;
    private int matricula;
    private String nombre;
    private String motivo;
    private String hora;
    private boolean tieneCitaPrevia;

    // Constructor usando objeto Alumno y hora
    public Cita(Alumno alumno, String motivo, String hora, boolean tieneCitaPrevia) {
        this.alumno = alumno;
        this.matricula = alumno.getMatricula();
        this.nombre = alumno.getNombre();
        this.motivo = motivo;
        this.hora = hora;
        this.tieneCitaPrevia = tieneCitaPrevia;
    }

    // Constructor sin hora (asigna "En espera" por defecto)
    public Cita(Alumno alumno, String motivo, boolean tieneCitaPrevia) {
        this.alumno = alumno;
        this.matricula = alumno.getMatricula();
        this.nombre = alumno.getNombre();
        this.motivo = motivo;
        this.hora = "Por turno";
        this.tieneCitaPrevia = tieneCitaPrevia;
    }

    // Metodos get y set
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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public boolean getTieneCitaPrevia() {
        return tieneCitaPrevia;
    }

    public void setTieneCitaPrevia(boolean tieneCitaPrevia) {
        this.tieneCitaPrevia = tieneCitaPrevia;
    }
}
