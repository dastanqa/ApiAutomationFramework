import com.dastan.constants.FrameworkConstants;
import com.dastan.utils.ApiUtils;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
public class DemoAuth {

    @Test
    public void basicAuthTest(){
        //blacklist headers
        Response response = given()

                .header("Authorization","Basic cG9zdG1hbjpwYXNzd29yZA== ")

                .log().all()
                .get("https://postman-echo.com/basic-auth");

        response.prettyPrint();
    }

    @Test
    public void getAllWorkspaces(){
        given()
                .header("X-Api-Key","PMAK-64ba5d2390a71300372b8ddf-180740de5efa0b56beccc437ff0faeb65d")
                .log().all()
                .get("https://api.getpostman.com/workspaces")
                .prettyPrint();

    }
    
    @Test
    public void getRepositories(){
        given()
                .header("Authorization","Bearer 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")
                .queryParam("per_page",1)
                .log().all()
                .get("https://api.github.com/user/repos")
                .prettyPrint();
    }

    //@Test
    public void createIssue(){

        String body = ApiUtils.readJsonAndGetAsString(FrameworkConstants.getRequestJsonFolderPath()+"request1.json")
                .replace("KEY","DEM")
                .replace("SUMMARY","DUMMy defect created using rest api")
                .replace("DESCRIPTION","Some description");

        Response response = given()
                .config(RestAssuredConfig.config().logConfig(LogConfig.logConfig().blacklistHeader("Authorization")))
                .header("Authorization","Basic ...")
                .header("Content-Type","application/json")
                .body(body)
                .post("http://localhost:8080/rest/api/2/issue/");
    }
}
