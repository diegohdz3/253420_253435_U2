import java.util.LinkedList;

public class AgendaTutorias {

    // 1. ARREGLO DE OBJETOS (Estructura estatica - Unidad 1)
    // Guarda las citas previas registradas para el verificador de citas
    Cita citasRegistradas[] = new Cita[5];
    int contadorCitas = 0;

    // 2. LISTA ENLAZADA (Estructura dinamica lineal - Unidad 2)
    // Guarda a los alumnos que llegan SIN cita y deben registrarse para esperar turno
    LinkedList<Cita> listaEspera = new LinkedList<Cita>();

    public AgendaTutorias() {
        // Se crean objetos Alumno igual que en las diapositivas 8 y 9
        Alumno alumno1 = new Alumno("Juan", 181234);
        Alumno alumno2 = new Alumno("Ana", 193234);

        // Se asignan al arreglo de citas registradas
        citasRegistradas[0] = new Cita(alumno1, "Dudas de POO", true);
        citasRegistradas[1] = new Cita(alumno2, "Recursividad", true);
        contadorCitas = 2;
    }

    // Metodo para agendar una nueva cita previa dentro del ARREGLO
    public void guardarCitaEnArreglo(Cita nuevaCita) {
        if (contadorCitas < citasRegistradas.length) {
            citasRegistradas[contadorCitas] = nuevaCita;
            contadorCitas++;
            System.out.println("Cita guardada en el arreglo de citas registradas.");
        } else {
            System.out.println("El arreglo de citas previas ya esta lleno (Maximo 5 citas).");
        }
    }

    // Verificador de citas: Busca en el ARREGLO si el alumno tiene cita registrada
    public Cita verificarCitaEnArreglo(int matriculaBuscada) {
        for (int indice = 0; indice < contadorCitas; indice++) {
            if (citasRegistradas[indice] != null) {
                if (citasRegistradas[indice].getAlumno().getMatricula() == matriculaBuscada) {
                    Cita encontrada = citasRegistradas[indice];
                    // Se marca como null porque ya paso directamente con el tutor
                    citasRegistradas[indice] = null;
                    return encontrada;
                }
            }
        }
        return null;
    }

    // Agrega al alumno SIN cita a la LISTA ENLAZADA de espera
    public void registrarEnListaEspera(Cita nuevoRegistro) {
        listaEspera.addLast(nuevoRegistro);
    }

    // Atiende al primer alumno formado en la LISTA ENLAZADA
    public void atenderSiguienteDeLista() {
        if (!listaEspera.isEmpty()) {
            Cita atendido = listaEspera.removeFirst();
            System.out.println("Atendiendo de la lista de espera -> "
                    + atendido.getAlumno().getNombre() + " "
                    + atendido.getAlumno().getMatricula());
        } else {
            System.out.println("La lista de espera esta vacia.");
        }
    }

    // Muestra tanto el Arreglo de citas registradas como la Lista Enlazada de espera
    public void mostrarAgenda() {
        System.out.println("\n--- ARREGLO DE CITAS REGISTRADAS (PASAN DIRECTO) ---");
        boolean hayCitasEnArreglo = false;
        for (int indice = 0; indice < contadorCitas; indice++) {
            if (citasRegistradas[indice] != null) {
                System.out.println("Indice [" + indice + "] -> Alumno: "
                        + citasRegistradas[indice].getAlumno().getNombre()
                        + " | Matricula: " + citasRegistradas[indice].getAlumno().getMatricula()
                        + " | Motivo: " + citasRegistradas[indice].getMotivo());
                hayCitasEnArreglo = true;
            }
        }
        if (hayCitasEnArreglo == false) {
            System.out.println("No hay citas previas pendientes en el arreglo.");
        }

        System.out.println("\n--- LISTA ENLAZADA DE ESPERA (ALUMNOS SIN CITA) ---");
        if (!listaEspera.isEmpty()) {
            for (int i = 0; i < listaEspera.size(); i++) {
                Cita c = listaEspera.get(i);
                System.out.println((i + 1) + ". Alumno: " + c.getAlumno().getNombre()
                        + " | Matricula: " + c.getAlumno().getMatricula()
                        + " | Motivo: " + c.getMotivo());
            }
        } else {
            System.out.println("No hay alumnos formados en la lista de espera.");
        }
    }
}
