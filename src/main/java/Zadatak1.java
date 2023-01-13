import com.github.javafaker.Faker;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Zadatak1 {
    /*
    Napraviti maven projekat.
Dodati dependencies za apache poi i faker.
Kreirati rucno xlsx fajl koji sadrzi 5 imena i prezimena. Imena ce se nalaziti u koloni A, prezimena u koloni B.
Znaci u ovom koraku rucno pisete u excel, ne kroz kod.
Kroz kod treba da procitate i ispisete tih 5 imena i prezimena jedno do drugog pa novi red (kao u tabeli).
 Nakon toga, dodati jos 5 imena i prezimena koristeci Faker. Nakon toga, trebate da izlistate sada svih 10 imena i prezimena.
     */

    public static void main(String[] args) {
        try {
            readWriteData("domaci.xlsx");
            // writeData();

        } catch (FileNotFoundException e) {
            System.out.println("Nevalidna putanja!");
        } catch (IOException e) {
            System.out.println("Nevalidan excel fajl!");
        }

    }

    public static void readWriteData(String relativePath) throws FileNotFoundException, IOException {
        FileInputStream inputStream = new FileInputStream(relativePath);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheet("imena");
        for (int i = 0; i < 10; i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < 2; j++) {
                XSSFCell cell = row.getCell(j);
                System.out.print(cell.getStringCellValue() + " ");
            }
            System.out.println();// Da bi štampalo i istom formatu

        }

        Faker faker2 = new Faker();

        for (int i = 5; i < 10 ; i++) {
            XSSFRow row = sheet.createRow(i);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue(faker2.name().firstName());
            XSSFCell cell2 = row.createCell(1);
            cell2.setCellValue(faker2.name().lastName());

        }

        FileOutputStream outputStream = new FileOutputStream("domaci.xlsx");
        workbook.write(outputStream);
        outputStream.close();



    }



}

