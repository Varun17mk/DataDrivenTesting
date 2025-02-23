package DataDrivenTesting;


import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class dataProviderUsingExcel {
    DataFormatter formatter = new DataFormatter();
    @Test(dataProvider = "dataMethod")
    public void testCaseData2(String Greetings, String Name, String age) {
        System.out.println(Greetings + " " + Name + " Your age is : " + age);
    }

    @DataProvider(name = "dataMethod")
    public Object[][] getDataExcel() throws IOException {
        FileInputStream file = new FileInputStream("C:\\Users\\HP\\Desktop\\MANUAL and AUTOMATION_TESTING\\excelDrivenTesting.xlsx");
        XSSFWorkbook WorkBook = new XSSFWorkbook(file);
        XSSFSheet Sheet = WorkBook.getSheetAt(0);
        int RowCount = Sheet.getPhysicalNumberOfRows();
        System.out.println(RowCount);
        XSSFRow row = Sheet.getRow(0);
        int columnCount = row.getLastCellNum();
        System.out.println(columnCount);

        Object[][] Data= new Object[RowCount-1][columnCount];
        for (int i = 0; i < RowCount-1; i++) {
            row = Sheet.getRow(i+1);
            for (int j = 0; j < columnCount; j++) {
               XSSFCell cell= row.getCell(j);
               Data[i][j]= formatter.formatCellValue(cell); //formatter converts any data type of cell to String
            }
        }
        return Data;
    }
}
