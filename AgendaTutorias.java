import java.util.LinkedList;

public class AgendaTutorias {

    // 1. ARREGLO DE OBJETOS (Estructura estatica - Unidad 1)
    // Guarda las citas previas registradas para el verificador de citas
    Cita citasRegistradas[] = new Cita[5];
    int contadorCitas = 0;

    // 2. LISTA ENLAZADA DE ESPERA (Estructura dinamica lineal - Unidad 2)
    // Guarda a los alumnos que llegan SIN cita y deben formarse
    LinkedList<Cita> listaEspera = new LinkedList<Cita>();

    // 3. LISTA ENLAZADA DE HISTORIAL DE ATENDIDOS (Unidad 2)
    // Guarda los nodos de todos los alumnos que ya recibieron tutoria
    LinkedList<Cita> listaAtendidos = new LinkedList<Cita>();

    public AgendaTutorias() {
        // Se crean objetos Alumno igual que en las diapositivas 8 y 9
        Alumno alumno1 = new Alumno("Juan", 181234);
        Alumno alumno2 = new Alumno("Ana", 193234);

        // Se asignan al arreglo de citas registradas con su hora
        citasRegistradas[0] = new Cita(alumno1, "Dudas de POO", "10:00 AM", true);
        citasRegistradas[1] = new Cita(alumno2, "Recursividad", "10:30 AM", true);
        contadorCitas = 2;
    }

    // Verifica si una matricula ya existe en el arreglo o en la lista de espera
    public boolean existeMatricula(int matriculaBuscada) {
        for (int i = 0; i < contadorCitas; i++) {
            if (citasRegistradas[i] != null) {
                if (citasRegistradas[i].getMatricula() == matriculaBuscada) {
                    return true;
                }
            }
        }
        for (int i = 0; i < listaEspera.size(); i++) {
            if (listaEspera.get(i).getMatricula() == matriculaBuscada) {
                return true;
            }
        }
        return false;
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
                if (citasRegistradas[indice].getMatricula() == matriculaBuscada) {
                    Cita encontrada = citasRegistradas[indice];
                    // Se marca como null porque ya paso directamente con el tutor
                    citasRegistradas[indice] = null;
                    // Se guarda en la lista enlazada de alumnos ya atendidos
                    listaAtendidos.addLast(encontrada);
                    return encontrada;
                }
            }
        }
        return null;
    }

    // Insercion al final de la LISTA ENLAZADA (alumnos sin cita)
    public void registrarAlFinalDeLista(Cita nuevoRegistro) {
        listaEspera.addLast(nuevoRegistro);
    }

    // Insercion al inicio de la LISTA ENLAZADA (caso urgente / prioritario - Diapositiva 6)
    public void registrarAlInicioDeLista(Cita nuevoRegistro) {
        listaEspera.addFirst(nuevoRegistro);
    }

    // Ver el primer alumno en la LISTA ENLAZADA sin eliminarlo (getFirst - Diapositiva 12)
    public void verPrimeroDeLista() {
        if (!listaEspera.isEmpty()) {
            Cita primero = listaEspera.getFirst();
            System.out.println("Siguiente alumno en espera -> "
                    + primero.getNombre() + " | Matricula: " + primero.getMatricula()
                    + " | Motivo: " + primero.getMotivo());
        } else {
            System.out.println("La lista de espera esta vacia.");
        }
    }

    // Atiende y elimina al primer alumno de la LISTA ENLAZADA (removeFirst - Diapositiva 12)
    public void atenderSiguienteDeLista() {
        if (!listaEspera.isEmpty()) {
            Cita atendido = listaEspera.removeFirst();
            listaAtendidos.addLast(atendido);
            System.out.println("Eliminando de lista de espera (Atendiendo) -> "
                    + atendido.getNombre() + " "
                    + atendido.getMatricula());
        } else {
            System.out.println("La lista de espera esta vacia.");
        }
    }

    // Eliminacion de un nodo intermedio por matricula (Reasignacion de enlaces - Diapositiva 7)
    public void cancelarRegistroDeLista(int matriculaBuscada) {
        if (!listaEspera.isEmpty()) {
            boolean eliminado = false;
            for (int i = 0; i < listaEspera.size(); i++) {
                Cita c = listaEspera.get(i);
                if (c.getMatricula() == matriculaBuscada) {
                    listaEspera.remove(i);
                    System.out.println("Se elimino de la lista de espera al alumno: " + c.getNombre());
                    eliminado = true;
                    break;
                }
            }
            if (eliminado == false) {
                System.out.println("No se encontro esa matricula en la lista de espera.");
            }
        } else {
            System.out.println("La lista de espera esta vacia.");
        }
    }

    // Muestra el Arreglo de citas, la Lista de espera y la Lista de atendidos
    public void mostrarAgenda() {
        System.out.println("\n--- 1. ARREGLO DE CITAS REGISTRADAS (PASAN DIRECTO) ---");
        boolean hayCitasEnArreglo = false;
        for (int indice = 0; indice < contadorCitas; indice++) {
            if (citasRegistradas[indice] != null) {
                System.out.println("Indice [" + indice + "] -> Alumno: "
                        + citasRegistradas[indice].getNombre()
                        + " | Matricula: " + citasRegistradas[indice].getMatricula()
                        + " | Hora: " + citasRegistradas[indice].getHora()
                        + " | Motivo: " + citasRegistradas[indice].getMotivo());
                hayCitasEnArreglo = true;
            }
        }
        if (hayCitasEnArreglo == false) {
            System.out.println("No hay citas previas pendientes en el arreglo.");
        }

        System.out.println("\n--- 2. LISTA ENLAZADA DE ESPERA (ALUMNOS SIN CITA) ---");
        if (!listaEspera.isEmpty()) {
            for (int i = 0; i < listaEspera.size(); i++) {
                Cita c = listaEspera.get(i);
                System.out.println("Nodo " + (i + 1) + " -> Alumno: " + c.getNombre()
                        + " | Matricula: " + c.getMatricula()
                        + " | Hora: " + c.getHora()
                        + " | Motivo: " + c.getMotivo());
            }
        } else {
            System.out.println("No hay alumnos formados en la lista de espera.");
        }

        System.out.println("\n--- 3. LISTA ENLAZADA DE ALUMNOS YA ATENDIDOS ---");
        if (!listaAtendidos.isEmpty()) {
            for (int i = 0; i < listaAtendidos.size(); i++) {
                Cita c = listaAtendidos.get(i);
                System.out.println((i + 1) + ". Alumno: " + c.getNombre()
                        + " | Matricula: " + c.getMatricula()
                        + " | Motivo: " + c.getMotivo());
            }
        } else {
            System.out.println("Aun no se han atendido tutorias hoy.");
        }
    }
}
