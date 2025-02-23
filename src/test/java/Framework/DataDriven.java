package Framework;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DataDriven {
    public ArrayList<String> getData(String testCaseName) throws IOException {
        ArrayList<String> a = new ArrayList<String>();
        FileInputStream file = new FileInputStream("C:\\Users\\HP\\Desktop\\MANUAL and AUTOMATION_TESTING\\DataDrivenDemo.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        int sheets = workbook.getNumberOfSheets();

        for (int i = 0; i < sheets; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase("TestData")) {
                XSSFSheet sheet = workbook.getSheetAt(i);

                // Scanning entire 1st row to get the TestCase column
                Iterator<Row> rows = sheet.iterator();
                Row firstRow = rows.next();
                Iterator<Cell> cell = firstRow.cellIterator();
                int k = 0;
                int column = 0;
                while (cell.hasNext()) {
                    Cell value = cell.next();
                    if (value.getStringCellValue().equalsIgnoreCase("TestCase")) {
                        // desired column
                        column = k;
                    }
                    k++;
                }
                System.out.println(column);

                //once TestCase column is identified then iterate through entire TestCase column to get Purchase row
                while (rows.hasNext()) {
                    Row r = rows.next();
                    if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {
                        //after u grab purchase testcase row = pull all the data from that row and feed into test
                        Iterator<Cell> cv = r.cellIterator();
                        while (cv.hasNext()) {
                            Cell c = cv.next();
                            if (c.getCellType() == CellType.STRING) // if cellvalue is String
                            {
                                a.add(c.getStringCellValue());
                            } else {
                                a.add(NumberToTextConverter.toText(c.getNumericCellValue())); // if cellvalue is Integer,NumberToTextConverter converts int to String
                            }
                        }
                    }
                }
            }
        }
        return a;
    }
}
