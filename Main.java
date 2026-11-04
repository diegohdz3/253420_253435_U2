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
            System.out.println("\nOPERACIONES DE LA AGENDA DE TUTORIAS");
            System.out.println("1. Llegada de alumno (Verificar cita o registrarse)");
            System.out.println("2. Agendar cita previa");
            System.out.println("3. Mostrar lista de citas");
            System.out.println("4. Atender al primer alumno de la lista");
            System.out.println("5. Salir");
            System.out.print("Ingresa una opcion: ");
            opcion = entrada.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Ingresa tu matricula:");
                    matricula = entrada.nextInt();

                    Cita citaEncontrada = agenda.buscarCitaPorMatricula(matricula);

                    if (citaEncontrada != null) {
                        System.out.println("Cita registrada encontrada!");
                        System.out.println("Alumno: " + citaEncontrada.getAlumno().getNombre()
                                + " " + citaEncontrada.getAlumno().getMatricula());
                        System.out.println("Motivo: " + citaEncontrada.getMotivo());
                        System.out.println("Pasa directamente con el tutor.");
                        agenda.atenderConCitaPrevia(citaEncontrada);
                    } else {
                        System.out.println("No tienes cita registrada. Registrate para formarte en la lista.");
                        entrada.nextLine();
                        System.out.println("Ingresa tu nombre:");
                        nombre = entrada.nextLine();
                        System.out.println("Ingresa el motivo de la tutoria:");
                        motivo = entrada.nextLine();

                        Alumno nuevoAlumno = new Alumno(nombre, matricula);
                        Cita nuevoRegistro = new Cita(nuevoAlumno, motivo, false);
                        agenda.agregarCita(nuevoRegistro);
                        System.out.println("Registro completado. Quedaste al final de la lista.");
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
                    agenda.agregarCita(nuevaCita);
                    System.out.println("Cita previa registrada en la lista.");
                    break;

                case 3:
                    agenda.mostrarLista();
                    break;

                case 4:
                    agenda.atenderSiguiente();
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
