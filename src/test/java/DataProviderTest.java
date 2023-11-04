import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DataProviderTest {
    //Object [] [] is not always the return type
    //number of tests will be one if we use for loop

    @Test(dataProvider = "getData1")
    public void dpTest(Map<String,String>data) {

        System.out.println(data.get("username"));
        System.out.println(data.get("password"));
    }

    @DataProvider
    public Object[][] getData1() {

        Object[][] data = new Object[3][1];

        Map<String,String> map1 = new HashMap<>();
        map1.put("username","user1");
        map1.put("password","pass1");
        map1.put("email","email1");

        Map<String,String> map2 = new HashMap<>();
        map2.put("username","user2");
        map2.put("password","pass2");
        map2.put("email","email12");

        Map<String,String> map3 = new HashMap<>();
        map3.put("username","user3");
        map3.put("password","pass3");
        map3.put("email","email3");

        data[0][0] = map1;
        data[1][0] = map2;
        data[2][0] = map3;

        return data;
    }
    @DataProvider
    public Object[][] getData() {

        // first dimension = number of times executed
        // second dimension = indicates number of parameters to the method

        return new Object[][]{ // 3x1
                {"abc", "pass1", "email", "address", "responsecode"},
                {"efg", "pass2", "email", "address", "responsecode"},
                {"hij", "pass3", "email", "address", "responsecode"}
        };

        //map  -- username : abc , pass: pass1 etc...

        //list --> map1,map2,map3
    }
}
