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

        do {
            System.out.println("\nAGENDA DE CITAS PARA TUTORIAS INDIVIDUALES");
            System.out.println("1. Llegada de alumno (Verificar cita en Arreglo o registrar en Lista)");
            System.out.println("2. Agendar cita previa (Guardar en Arreglo)");
            System.out.println("3. Mostrar citas del Arreglo y Lista de espera");
            System.out.println("4. Atender al primer alumno de la Lista de espera");
            System.out.println("5. Salir");
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
                                + " " + citaEncontrada.getMatricula());
                        System.out.println("Motivo: " + citaEncontrada.getMotivo());
                        System.out.println(">> Pasa directamente con el tutor.");
                    } else {
                        System.out.println("No tienes cita en el arreglo. Debes registrarte en la Lista de espera.");
                        entrada.nextLine();
                        System.out.println("Ingresa tu nombre:");
                        nombre = entrada.nextLine();
                        System.out.println("Ingresa el motivo de la tutoria:");
                        motivo = entrada.nextLine();

                        Alumno nuevoAlumno = new Alumno(nombre, matricula);
                        Cita nuevoRegistro = new Cita(nuevoAlumno, motivo, false);
                        agenda.registrarEnListaEspera(nuevoRegistro);
                        System.out.println("Te has registrado al final de la Lista Enlazada.");
                    }
                    break;

                case 2:
                    System.out.println("Ingresa la matricula del alumno:");
                    matricula = entrada.nextInt();
                    entrada.nextLine();
                    System.out.println("Ingresa el nombre del alumno:");
                    nombre = entrada.nextLine();
                    System.out.println("Ingresa el motivo de la tutoria:");
                    motivo = entrada.nextLine();

                    Alumno al = new Alumno(nombre, matricula);
                    Cita nuevaCita = new Cita(al, motivo, true);
                    agenda.guardarCitaEnArreglo(nuevaCita);
                    break;

                case 3:
                    agenda.mostrarAgenda();
                    break;

                case 4:
                    agenda.atenderSiguienteDeLista();
                    break;

                case 5:
                    System.out.println("Fin del programa.");
                    break;

                default:
                    System.out.println("Error");
            }
        } while (opcion != 5);

        entrada.close();
    }
}
