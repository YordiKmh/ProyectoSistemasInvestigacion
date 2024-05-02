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

    // Método para encontrar el elemento más negativo en la fila de la función objetivo
    public static int encontrarColumnaPivote(double[][] tableau) {
        int m = tableau.length;
        int n = tableau[0].length;
        int columnaPivote = -1;
        double menorValor = 0;

        for (int j = 0; j < n - 1; j++) {
            if (tableau[m - 1][j] < menorValor) {
                menorValor = tableau[m - 1][j];
                columnaPivote = j;
            }
        }
        return columnaPivote;
    }

    // Método para ordenar la tabla en función del elemento más negativo en la fila de la función objetivo
    public static void ordenarTabla(double[][] tableau, int columnaPivote) {
        int m = tableau.length;
        int n = tableau[0].length;

        // Intercambiar las columnas en la fila de la función objetivo
        for (int i = 0; i < m; i++) {
            double temp = tableau[i][columnaPivote];
            tableau[i][columnaPivote] = tableau[i][n - 1];
            tableau[i][n - 1] = temp;
        }
    }

    // Método simplex
    public static void simplex(double[][] tableau) {
        int columnaPivote = encontrarColumnaPivote(tableau);
        ordenarTabla(tableau, columnaPivote);
    }

    // Método para imprimir el tableau
    public static void printTableau(double[][] tableau) {
        for (double[] row : tableau) {
            for (double cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
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