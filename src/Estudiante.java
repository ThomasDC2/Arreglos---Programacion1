import javax.swing.JOptionPane;
import java.util.Arrays;

public class Estudiante {

    public static void main(String[] args) {

        int cantidadNotas = Integer.parseInt(
                JOptionPane.showInputDialog(null,
                        "Ingrese número de notas:")
        );

        double[] notas = new double[cantidadNotas];

        // Llenar notas
        for (int i = 0; i < cantidadNotas; i++) {

            double nota;

            do {
                nota = Double.parseDouble(
                        JOptionPane.showInputDialog(null,
                                "Ingrese la nota " + (i + 1) + " (0.0 - 5.0):")
                );

                if (nota < 0.0 || nota > 5.0) {
                    JOptionPane.showMessageDialog(null,
                            "Nota inválida. Debe estar entre 0.0 y 5.0");
                }

            } while (nota < 0.0 || nota > 5.0);

            notas[i] = nota;
        }

        // Menú principal
        int opcion;

        do {
            opcion = Integer.parseInt(
                    JOptionPane.showInputDialog(null,
                            "----- MENÚ -----\n\n"
                                    + "1. Ordenar notas\n"
                                    + "2. Mostrar resumen\n"
                                    + "3. Calcular nota necesaria para aprobar\n"
                                    + "4. Salir\n\n"
                                    + "Seleccione una opción:")
            );

            switch (opcion) {

                case 1:
                    Arrays.sort(notas);
                    JOptionPane.showMessageDialog(null,
                            "Notas ordenadas:\n" + Arrays.toString(notas));
                    break;

                case 2:
                    mostrarResumen(notas);
                    break;

                case 3:
                    calcularNotaNecesaria(notas);
                    break;

                case 4:
                    JOptionPane.showMessageDialog(null,
                            "Programa finalizado.");
                    break;

                default:
                    JOptionPane.showMessageDialog(null,
                            "Opción inválida.");
            }

        } while (opcion != 4);
    }

    public static void mostrarResumen(double[] notas) {

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

        double promedio = suma / notas.length;

        String mensaje = "----- RESUMEN -----\n\n";
        mensaje += "Notas: " + Arrays.toString(notas) + "\n";
        mensaje += "Nota mayor: " + mayor + "\n";
        mensaje += "Nota menor: " + menor + "\n";
        mensaje += "Promedio: " + promedio + "\n";

        if (promedio >= 3.0) {
            mensaje += "Estado: APROBADO";
        } else {
            mensaje += "Estado: REPROBADO";
        }

        JOptionPane.showMessageDialog(null, mensaje);
    }

    public static void calcularNotaNecesaria(double[] notas) {

        double suma = 0;

        for (int i = 0; i < notas.length - 1; i++) {
            suma += notas[i];
        }

        double notaNecesaria = (3.0 * notas.length) - suma;

        if (notaNecesaria <= 0) {
            JOptionPane.showMessageDialog(null,
                    "Ya aprueba incluso con 0.0 en la última nota.");
        } else if (notaNecesaria > 5.0) {
            JOptionPane.showMessageDialog(null,
                    "Necesita " + notaNecesaria
                            + " pero supera 5.0.\nNo es posible aprobar.");
        } else {
            JOptionPane.showMessageDialog(null,
                    "Necesita sacar mínimo "
                            + String.format("%.2f", notaNecesaria)
                            + " en la última nota para aprobar.");
        }
    }
}
