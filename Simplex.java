import java.util.Scanner;

public class Simplex {

    // Método para leer los datos del usuario
    public static double[][] leerDatos() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n");
        System.out.println("Ingrese el número de restricciones (filas):");
        int m = scanner.nextInt();
        System.out.println("Ingrese el número de variables (columnas):");
        int n = scanner.nextInt();

        double[][] tableau = new double[m + 1][n + m + 1];

        System.out.println("Ingrese los coeficientes de las restricciones en forma Ax <= b:");
        for (int i = 0; i < m; i++) {
            System.out.println("Ingrese los coeficientes de la restricción " + (i + 1) + ":");
            for (int j = 0; j < n; j++) {
                tableau[i][j] = scanner.nextDouble();
            }
            System.out.println("Ingrese el valor de b para la restricción " + (i + 1) + ":");
            tableau[i][n + i] = scanner.nextDouble();
        }

        System.out.println("Ingrese los coeficientes de la función objetivo:");
        for (int j = 0; j < n; j++) {
            tableau[m][j] = scanner.nextDouble();
        }
        return tableau;
    }

    // Método simplex
    public static void simplex(double[][] tableau) {
    }

    // Método para imprimir el tabla
    public static void printTableau(double[][] tableau) {
    }

    public static void main(String[] args) {
        // Leer los datos del usuario
        double[][] tableau = leerDatos();

        // Resolver el problema utilizando el método simplex
        simplex(tableau);

        // Imprimir la solución óptima
        System.out.println("Solución óptima:");
        printTableau(tableau);
    }
}