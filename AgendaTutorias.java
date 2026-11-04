import java.util.LinkedList;

public class AgendaTutorias {

    // Se crea la estructura de datos lineal usando LinkedList
    LinkedList<Cita> listaCitas = new LinkedList<Cita>();

    public AgendaTutorias() {
        // Se crean objetos Alumno igual que en la diapositiva 8 y 9
        Alumno alumno1 = new Alumno("Juan", 181234);
        Alumno alumno2 = new Alumno("Ana", 193234);

        // Se crean sus citas previas registradas
        Cita cita1 = new Cita(alumno1, "Dudas de POO", true);
        Cita cita2 = new Cita(alumno2, "Recursividad", true);

        // Se agregan a la lista enlazada
        listaCitas.addLast(cita1);
        listaCitas.addLast(cita2);
    }

    // Metodo para buscar en la lista si el alumno tiene cita registrada
    public Cita buscarCitaPorMatricula(int matriculaBuscada) {
        for (int i = 0; i < listaCitas.size(); i++) {
            Cita citaActual = listaCitas.get(i);
            if (citaActual.getAlumno().getMatricula() == matriculaBuscada) {
                return citaActual;
            }
        }
        return null;
    }

    // Agrega un nuevo registro al final de la lista
    public void agregarCita(Cita nuevaCita) {
        listaCitas.addLast(nuevaCita);
    }

    // Elimina la cita cuando el alumno con cita previa pasa directo con el tutor
    public void atenderConCitaPrevia(Cita citaEncontrada) {
        listaCitas.remove(citaEncontrada);
    }

    // Atiende y elimina al primer alumno formado en la lista
    public void atenderSiguiente() {
        if (!listaCitas.isEmpty()) {
            Cita atendida = listaCitas.removeFirst();
            System.out.println("Eliminando de la lista (Atendiendo) -> "
                    + atendida.getAlumno().getNombre() + " "
                    + atendida.getAlumno().getMatricula());
        } else {
            System.out.println("La lista esta vacia, no hay alumnos por atender.");
        }
    }

    // Muestra todos los nodos actuales de la lista
    public void mostrarLista() {
        if (!listaCitas.isEmpty()) {
            System.out.println("--- AGENDA ACTUAL DE TUTORIAS ---");
            for (int i = 0; i < listaCitas.size(); i++) {
                Cita c = listaCitas.get(i);
                String estado;
                if (c.getTieneCitaPrevia() == true) {
                    estado = "Tiene cita registrada";
                } else {
                    estado = "Sin cita (En espera)";
                }
                System.out.println((i + 1) + ". Alumno: " + c.getAlumno().getNombre()
                        + " | Matricula: " + c.getAlumno().getMatricula()
                        + " | Motivo: " + c.getMotivo()
                        + " | Estado: " + estado);
            }
        } else {
            System.out.println("La lista de citas esta vacia.");
        }
    }
}
