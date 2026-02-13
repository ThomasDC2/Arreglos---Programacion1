import javax.swing.*;
import java.util.Arrays;

public class Estudiante {

    public static void main(String[] args) {

        // Total de notas del curso
        int totalNotas = Integer.parseInt(
                JOptionPane.showInputDialog("¿Cuántas notas tiene el curso en total?")
        );

        // Notas que ya se han realizado
        int notasRealizadas = Integer.parseInt(
                JOptionPane.showInputDialog("¿Cuántas notas ha obtenido hasta ahora?")
        );

        if (notasRealizadas > totalNotas) {
            JOptionPane.showMessageDialog(null,
                    "Error: No puede haber más notas realizadas que el total.");
            return;
        }

        double[] notas = new double[notasRealizadas];

        // Ingresar notas realizadas
        for (int i = 0; i < notasRealizadas; i++) {

            double nota;

            do {
                nota = Double.parseDouble(
                        JOptionPane.showInputDialog(
                                "Ingrese la nota " + (i + 1) + " (0.0 - 5.0):")
                );

                if (nota < 0.0 || nota > 5.0) {
                    JOptionPane.showMessageDialog(null,
                            "Nota inválida. Debe estar entre 0.0 y 5.0");
                }

            } while (nota < 0.0 || nota > 5.0);

            notas[i] = nota;
        }

        int opcion;

        do {
            opcion = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            "----- MENÚ -----\n\n"
                                    + "1. Ordenar notas (Burbuja)\n"
                                    + "2. Mostrar resumen\n"
                                    + "3. Calcular nota necesaria para aprobar\n"
                                    + "4. Contar notas aprobadas y reprobadas\n"
                                    + "5. Salir\n\n"
                                    + "Seleccione una opción:")
            );

            switch (opcion) {

                case 1:
                    ordenarBurbuja(notas);
                    JOptionPane.showMessageDialog(null,
                            "Notas ordenadas:\n" + Arrays.toString(notas));
                    break;

                case 2:
                    mostrarResumen(notas, totalNotas);
                    break;

                case 3:
                    calcularNotaNecesaria(notas, totalNotas);
                    break;

                case 4:
                    contarAprobadas(notas);
                    break;

                case 5:
                    JOptionPane.showMessageDialog(null,
                            "Programa finalizado.");
                    break;

                default:
                    JOptionPane.showMessageDialog(null,
                            "Opción inválida.");
            }

        } while (opcion != 5);
    }

    // Ordenamiento Burbuja
    public static void ordenarBurbuja(double[] notas) {

        for (int i = 0; i < notas.length - 1; i++) {
            for (int j = 0; j < notas.length - 1 - i; j++) {

                if (notas[j] > notas[j + 1]) {

                    double temp = notas[j];
                    notas[j] = notas[j + 1];
                    notas[j + 1] = temp;
                }
            }
        }
    }

    // Mostrar resumen
    public static void mostrarResumen(double[] notas, int totalNotas) {

        if (notas.length == 0) {
            JOptionPane.showMessageDialog(null,
                    "No hay notas registradas.");
            return;
        }

        double mayor = notas[0];
        double menor = notas[0];
        double suma = 0;

        for (int i = 0; i < notas.length; i++) {

            if (notas[i] > mayor) {
                mayor = notas[i];
            }

            if (notas[i] < menor) {
                menor = notas[i];
            }

            suma += notas[i];
        }

        double promedioActual = suma / totalNotas;

        String mensaje = "----- RESUMEN -----\n\n";
        mensaje += "Notas registradas: " + Arrays.toString(notas) + "\n";
        mensaje += "Nota mayor: " + mayor + "\n";
        mensaje += "Nota menor: " + menor + "\n";
        mensaje += "Promedio actual (sobre total del curso): "
                + String.format("%.2f", promedioActual) + "\n";

        if (promedioActual >= 3.0) {
            mensaje += "Estado actual: APROBANDO";
        } else {
            mensaje += "Estado actual: REPROBANDO";
        }

        JOptionPane.showMessageDialog(null, mensaje);
    }

    // Calcular nota necesaria
    public static void calcularNotaNecesaria(double[] notas, int totalNotas) {

        double suma = 0;

        for (int i = 0; i < notas.length; i++) {
            suma += notas[i];
        }

        int notasFaltantes = totalNotas - notas.length;

        if (notasFaltantes == 0) {
            JOptionPane.showMessageDialog(null,
                    "Ya tiene todas las notas registradas.");
            return;
        }

        double notaObjetivo = 3.0 * totalNotas;
        double puntosFaltantes = notaObjetivo - suma;

        double notaNecesaria = puntosFaltantes / notasFaltantes;

        if (notaNecesaria <= 0) {
            JOptionPane.showMessageDialog(null,
                    "Ya aprueba aunque saque 0.0 en las notas restantes.");
        } else if (notaNecesaria > 5.0) {
            JOptionPane.showMessageDialog(null,
                    "Necesita " + String.format("%.2f", notaNecesaria)
                            + " en cada nota faltante.\nNo es posible aprobar.");
        } else {
            JOptionPane.showMessageDialog(null,
                    "Debe sacar mínimo "
                            + String.format("%.2f", notaNecesaria)
                            + " en cada nota faltante para aprobar.");
        }
    }

    // Funcionalidad extra
    public static void contarAprobadas(double[] notas) {

        int aprobadas = 0;
        int reprobadas = 0;

        for (int i = 0; i < notas.length; i++) {

            if (notas[i] >= 3.0) {
                aprobadas++;
            } else {
                reprobadas++;
            }
        }

        JOptionPane.showMessageDialog(null,
                "Notas aprobadas: " + aprobadas +
                        "\nNotas reprobadas: " + reprobadas);
    }
}
