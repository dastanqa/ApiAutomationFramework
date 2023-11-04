package com.dastan.utils;

import com.dastan.constants.FrameworkConstants;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public final class JiraUtils {
    private JiraUtils(){
    }
    public static String createIssueJira(String message){
        String body = ApiUtils.readJsonAndGetAsString(FrameworkConstants.getRequestJsonFolderPath()+"request1.json")
                .replace("KEY","DEM")
                .replace("DESCRIPTION",message);

        Response response = given()
                .contentType(ContentType.JSON)
                .auth()
                .basic("dastankgb7", "11@Bronaldo")
                .body(body)
                .post("http://localhost:8080/rest/api/2/issue/");

        response.prettyPrint();

        return response.jsonPath().getString("key");
    }
}
