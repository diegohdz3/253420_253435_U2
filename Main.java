import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        visualizarOperaciones();
    }

    public static void visualizarOperaciones() {
        Scanner entrada = new Scanner(System.in);
        AgendaTutorias agenda = new AgendaTutorias();
        int opcion;
        int matricula;
        String nombre;
        String motivo;
        String hora;
        int tipoUrgencia;

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

            switch (opcion) {
                case 1:
                    System.out.println("Ingresa tu matricula:");
                    matricula = entrada.nextInt();

                    // Primero verificamos en el ARREGLO si el alumno tiene cita registrada
                    Cita citaEncontrada = agenda.verificarCitaEnArreglo(matricula);

                    if (citaEncontrada != null) {
                        System.out.println("Cita registrada encontrada en el arreglo!");
                        System.out.println("Alumno: " + citaEncontrada.getNombre()
                                + " | Matricula: " + citaEncontrada.getMatricula());
                        System.out.println("Hora programada: " + citaEncontrada.getHora());
                        System.out.println("Motivo: " + citaEncontrada.getMotivo());
                        System.out.println(">> Pasa directamente con el tutor.");
                    } else {
                        // Validamos que no este ya formado en la lista de espera
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
                            Cita nuevoRegistro = new Cita(nuevoAlumno, motivo, "Por turno", false);

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
                        Cita nuevaCita = new Cita(al, motivo, hora, true);
                        agenda.guardarCitaEnArreglo(nuevaCita);
                    }
                    break;

                case 3:
                    agenda.mostrarAgenda();
                    break;

                case 4:
                    agenda.verPrimeroDeLista();
                    break;

                case 5:
                    agenda.atenderSiguienteDeLista();
                    break;

                case 6:
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
