package DataDrivenTesting;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dataProviderExample {
    @Test(dataProvider = "driveTest")
    public void testCaseData(String Greetings, String Name, String age){
        System.out.println(Greetings+" "+Name+" Your age is : "+age);
    }

    @DataProvider(name = "driveTest")
    public Object[][] getData(){
        Object[][] data = {{"Hello", "Varun",24},{"Hi","Varsha",22},{"Hello","Chaya",50}};
        return data;
    }
}
