import java.util.Scanner;

public class Main {

    // === EXPLICA COMPANERA: Metodo main y estructura del Menu con do-while ===
    public static void main(String[] args) {
        visualizarOperaciones();
    }

    public static void visualizarOperaciones() {
        // Scanner para leer los datos que el usuario escribe en la terminal
        Scanner entrada = new Scanner(System.in);
        AgendaTutorias agenda = new AgendaTutorias();
        int opcion;
        int matricula;
        String nombre;
        String motivo;
        String hora;
        int tipoUrgencia;

        // Ciclo do-while que mantiene el menu repitiendose hasta elegir la opcion 7 (Salir)
        do {
            System.out.println("\nAGENDA DE CITAS PARA TUTORIAS INDIVIDUALES");
            System.out.println("1. Llegada de alumno (Verificar cita en Arreglo o registrar en Lista)");
            System.out.println("2. Agendar cita previa (Guardar en Arreglo)");
            System.out.println("3. Mostrar Arreglo de citas, Lista de espera e Historial");
            System.out.println("4. Ver primer alumno en la Lista de espera (getFirst)");
            System.out.println("5. Atender al primer alumno de la Lista de espera (removeFirst)");
            System.out.println("6. Cancelar turno de la Lista de espera por matricula (remove)");
            System.out.println("7. Salir");
            System.out.print("Ingresa una opcion: ");
            opcion = entrada.nextInt();

            // === EXPLICA DIEGO: Opciones del switch y ejecucion en terminal ===
            switch (opcion) {
                case 1:
                    // Caso principal: Verifica si la matricula esta en el arreglo de citas
                    System.out.println("Ingresa tu matricula:");
                    matricula = entrada.nextInt();

                    Cita citaEncontrada = agenda.verificarCitaEnArreglo(matricula);

                    if (citaEncontrada != null) {
                        // Si tiene cita registrada, pasa directo con el tutor
                        System.out.println("Cita registrada encontrada en el arreglo!");
                        System.out.println("Alumno: " + citaEncontrada.getNombre()
                                + " | Matricula: " + citaEncontrada.getMatricula());
                        System.out.println("Motivo: " + citaEncontrada.getMotivo());
                        System.out.println(">> Pasa directamente con el tutor.");
                    } else {
                        // Si no tiene cita, se registra y se inserta en la Lista Enlazada (LinkedList)
                        if (agenda.existeMatricula(matricula) == true) {
                            System.out.println("Ya te encuentras formado en la Lista de espera. Aguarda tu turno.");
                        } else {
                            System.out.println("No tienes cita en el arreglo. Debes registrarte en la Lista de espera.");
                            entrada.nextLine();
                            System.out.println("Ingresa tu nombre:");
                            nombre = entrada.nextLine();
                            System.out.println("Ingresa el motivo de la tutoria:");
                            motivo = entrada.nextLine();
                            System.out.println("Tipo de registro: 1. Normal (Al final) | 2. Urgente (Al inicio):");
                            tipoUrgencia = entrada.nextInt();

                            Alumno nuevoAlumno = new Alumno(nombre, matricula);
                            Cita nuevoRegistro = new Cita(nuevoAlumno.getMatricula(), nuevoAlumno.getNombre(), motivo, false);

                            if (tipoUrgencia == 2) {
                                agenda.registrarAlInicioDeLista(nuevoRegistro);
                                System.out.println("Te has registrado al INICIO de la Lista Enlazada (addFirst).");
                            } else {
                                agenda.registrarAlFinalDeLista(nuevoRegistro);
                                System.out.println("Te has registrado al FINAL de la Lista Enlazada (addLast).");
                            }
                        }
                    }
                    break;

                case 2:
                    // Registra una nueva cita previa dentro del arreglo estatico
                    System.out.println("Ingresa la matricula del alumno:");
                    matricula = entrada.nextInt();

                    if (agenda.existeMatricula(matricula) == true) {
                        System.out.println("Error: Esa matricula ya tiene un registro activo.");
                    } else {
                        entrada.nextLine();
                        System.out.println("Ingresa el nombre del alumno:");
                        nombre = entrada.nextLine();
                        System.out.println("Ingresa el motivo de la tutoria:");
                        motivo = entrada.nextLine();
                        System.out.println("Ingresa la hora de la cita (ejemplo 11:00 AM):");
                        hora = entrada.nextLine();

                        Alumno al = new Alumno(nombre, matricula);
                        Cita nuevaCita = new Cita(al.getMatricula(), al.getNombre(), motivo + " (" + hora + ")", true);
                        agenda.guardarCitaEnArreglo(nuevaCita);
                    }
                    break;

                case 3:
                    // Muestra el contenido actual del arreglo y de las listas enlazadas
                    agenda.mostrarAgenda();
                    break;

                case 4:
                    // Muestra al primer alumno de la lista sin eliminarlo (getFirst)
                    agenda.verPrimeroDeLista();
                    break;

                case 5:
                    // Atiende y elimina al primer alumno de la lista de espera (removeFirst)
                    agenda.atenderSiguienteDeLista();
                    break;

                case 6:
                    // Busca y elimina a un alumno de la lista de espera por su matricula (remove)
                    System.out.println("Ingresa la matricula que deseas dar de baja de la Lista de espera:");
                    matricula = entrada.nextInt();
                    agenda.cancelarRegistroDeLista(matricula);
                    break;

                case 7:
                    System.out.println("Fin del programa.");
                    break;

                default:
                    System.out.println("Error: Opcion no valida");
            }
        } while (opcion != 7);

        entrada.close();
    }
}
