// === EXPLICA HARUMI: Clase Alumno (Modelo POO basico) ===
// Esta clase guarda los datos personales del estudiante: su nombre y matricula.
public class Alumno {

    private int matricula;
    private String nombre;

    // Constructor: recibe el nombre y la matricula al crear un nuevo alumno
    public Alumno(String nombre, int matricula) {
        this.nombre = nombre;
        this.matricula = matricula;
    }

    // Metodos get y set para obtener y modificar la matricula y el nombre
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
}

