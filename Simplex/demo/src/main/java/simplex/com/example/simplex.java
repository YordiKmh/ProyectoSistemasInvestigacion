package simplex.com.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class simplex {
    private static final Logger LOGGER = Logger.getLogger(simplex.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("simplex.log", true); 
            // True para agregar al archivo existente
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getCellContents(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return Integer.parseInt(cell.getStringCellValue());
            case NUMERIC:
                return (int) cell.getNumericCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue() ? 1 : 0;
            default:
                return 0;
        }
    }

    private static void printDoubleArray(int numRows, int numCols, double[][] data) {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                System.out.print(data[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void CArchivo(Workbook datos) {
        try {
            datos.close();
        } catch (IOException e) {
            LOGGER.severe("Error al cerrar el archivo de Excel: " + e.getMessage());
            System.out.println("Error al cerrar el archivo de Excel.");
        }
    }

    public static void GInformacion(Workbook datos, String RArchivo) {
        try {
            FileOutputStream Escribir = new FileOutputStream(RArchivo);
            datos.write(Escribir);
            Escribir.close();
        } catch (IOException e) {
            LOGGER.severe("Error al guardar el archivo de Excel: " + e.getMessage());
            System.out.println("Error al guardar el archivo de Excel.");
        }
    }

    public static void simplex(double[][] tableau) {
        // Método simplex implementado como estaba...
    }

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        FileInputStream lector = null;
        Workbook libro = null;
        Sheet hoja = null;
        try {
            LOGGER.info("Inicia la lectura del archivo excel");
            lector = new FileInputStream("C:/Users/LapOneMX/Documents/simplex.xlsx");
            libro = new XSSFWorkbook(lector);
            hoja = libro.getSheetAt(0);
        } catch (IOException e) {
            LOGGER.severe("Error al abrir el archivo de Excel: " + e.getMessage());
            System.out.println("Error al abrir el archivo de Excel.");
            return;
        }

        int numRows = hoja.getLastRowNum() + 1;
        int numCols = hoja.getRow(0).getLastCellNum();

        int[][] datos = new int[numRows][numCols];
        try {
            for (int i = 0; i < numRows; i++) {
                Row row = hoja.getRow(i);

                for (int j = 0; j < numCols; j++) {
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    int contenidoceldas = getCellContents(cell);
                    datos[i][j] = contenidoceldas;
                }
            }
        } catch (Exception e) {
            LOGGER.severe("Error al leer datos del archivo Excel: " + e.getMessage());
            e.printStackTrace();
        }

        double[][] tableau = new double[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                tableau[i][j] = datos[i][j];
            }
        }
        simplex(tableau);
        CArchivo(libro);
    }
}
