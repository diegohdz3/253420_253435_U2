import java.util.LinkedList;

// === EXPLICA DIEGO: Clase AgendaTutorias (Manejo de Arreglo y Listas Enlazadas) ===
public class AgendaTutorias {

    // Arreglo estatico de objetos para guardar las citas agendadas previamente (cupo de 5)
    Cita citasRegistradas[] = new Cita[5];
    int contadorCitas = 0;

    // Estructura lineal dinamica (LinkedList) para formar a los alumnos que llegan SIN cita
    LinkedList<Cita> listaEspera = new LinkedList<Cita>();

    // Segunda Lista Enlazada para guardar el historial de alumnos que ya fueron atendidos
    LinkedList<Cita> listaAtendidos = new LinkedList<Cita>();

    // Constructor: precarga 2 alumnos con cita previa en el arreglo para hacer pruebas
    public AgendaTutorias() {
        Alumno alumno1 = new Alumno("Juan", 181234);
        Alumno alumno2 = new Alumno("Ana", 193234);

        citasRegistradas[0] = new Cita(alumno1.getMatricula(), alumno1.getNombre(), "Dudas de POO (10:00 AM)", true);
        citasRegistradas[1] = new Cita(alumno2.getMatricula(), alumno2.getNombre(), "Recursividad (10:30 AM)", true);
        contadorCitas = 2;
    }

    // Recorre el arreglo y la lista para evitar que una misma matricula se registre dos veces
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

    // Guarda una nueva cita previa en la siguiente posicion disponible del arreglo
    public void guardarCitaEnArreglo(Cita nuevaCita) {
        if (contadorCitas < citasRegistradas.length) {
            citasRegistradas[contadorCitas] = nuevaCita;
            contadorCitas++;
            System.out.println("Cita guardada en el arreglo de citas registradas.");
        } else {
            System.out.println("El arreglo de citas previas ya esta lleno (Maximo 5 citas).");
        }
    }

    // Busca la matricula en el arreglo: si existe, pasa directo con el tutor y se mueve a atendidos
    public Cita verificarCitaEnArreglo(int matriculaBuscada) {
        for (int indice = 0; indice < contadorCitas; indice++) {
            if (citasRegistradas[indice] != null) {
                if (citasRegistradas[indice].getMatricula() == matriculaBuscada) {
                    Cita encontrada = citasRegistradas[indice];
                    citasRegistradas[indice] = null;
                    listaAtendidos.addLast(encontrada);
                    return encontrada;
                }
            }
        }
        return null;
    }

    // Operacion de Lista: Inserta al alumno al FINAL de la lista de espera con addLast()
    public void registrarEnListaEspera(Cita nuevoRegistro) {
        listaEspera.addLast(nuevoRegistro);
    }

    public void registrarAlFinalDeLista(Cita nuevoRegistro) {
        listaEspera.addLast(nuevoRegistro);
    }

    // Operacion de Lista: Inserta al alumno al INICIO de la lista con addFirst() por urgencia
    public void registrarAlInicioDeLista(Cita nuevoRegistro) {
        listaEspera.addFirst(nuevoRegistro);
    }

    // Operacion de Lista: Consulta quien esta al frente de la lista sin borrarlo con getFirst()
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

    // Operacion de Lista: Atiende y elimina al primer nodo de la lista con removeFirst()
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

    // Operacion de Lista: Busca por matricula y elimina un nodo intermedio con remove(i)
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

    // Recorre e imprime el Arreglo de citas previas, la Lista de espera y la Lista de atendidos
    public void mostrarAgenda() {
        System.out.println("\n--- 1. ARREGLO DE CITAS REGISTRADAS (PASAN DIRECTO) ---");
        boolean hayCitasEnArreglo = false;
        for (int indice = 0; indice < contadorCitas; indice++) {
            if (citasRegistradas[indice] != null) {
                System.out.println("Indice [" + indice + "] -> Alumno: "
                        + citasRegistradas[indice].getNombre()
                        + " | Matricula: " + citasRegistradas[indice].getMatricula()
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
