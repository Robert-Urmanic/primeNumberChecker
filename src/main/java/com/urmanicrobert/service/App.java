package com.urmanicrobert.service;

import com.urmanicrobert.model.PrimeNumber;
import com.urmanicrobert.exceptions.IncorrectPrimeNumberException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class App {

    public static void run(String[] args) {
        try {
            FileInputStream file = new FileInputStream(args[0]);

            XSSFWorkbook wb = new XSSFWorkbook(file);

            for (int i = 0; i < wb.getNumberOfSheets(); i++) {


                XSSFSheet ws = wb.getSheetAt(i);

                Iterator<Row> rowIterator = ws.iterator();
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.cellIterator();

                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        try {
                            if (cell.getCellType() != CellType.BLANK && cell.getCellType() == CellType.STRING) {
                                PrimeNumber toPrintPrimeNumber = new PrimeNumber(Integer.parseInt(cell.getStringCellValue()));
                                PrimeNumber.counter++;
                                System.out.println(toPrintPrimeNumber.getPrimeNumber());
                            }
                        } catch (NumberFormatException | IllegalStateException | IncorrectPrimeNumberException ex) {
                            continue;
                        }
                    }
                }
            }
            file.close();

            if (PrimeNumber.counter == 0) System.out.println("No prime numbers found.");

        } catch (ArrayIndexOutOfBoundsException | FileNotFoundException ex) {
            System.err.println("The file name you entered is not valid, please try again: ");
            Scanner s = new Scanner(System.in);

            args = new String[1];
            args[0] = s.nextLine();

            App.run(args);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
