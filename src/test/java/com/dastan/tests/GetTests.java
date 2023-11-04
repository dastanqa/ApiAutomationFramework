package com.dastan.tests;

import com.dastan.annotations.FrameworkAnnotation;
import com.dastan.reports.ExtentLogger;
import com.dastan.reports.ExtentManager;
import com.dastan.requestbuilder.RequestBuilder;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GetTests extends BaseTest {

    @Test
    @FrameworkAnnotation(author = {"Dastan","Aygerim"},category = {"Smoke","Get Call"})
    public void getEmployeeDetails() {

        Response response = RequestBuilder
                .buildRequestForGetCalls()
                .get("/employees");



        ExtentLogger.logResponse(response.asPrettyString());

        assertThat(response.getStatusCode())
                .isEqualTo(200);

        assertThat(response.jsonPath().getList("$").size())
                .isPositive()
                .as("validating the size of the employee list").isLessThan(100);
    }

    @Test(dataProvider = "getData")
    @FrameworkAnnotation(author = {"Dastan"},category = {"Regression","Get Call"})

    public void getEmployeeDetail(Integer id, String lastname) {
        Response response = RequestBuilder
                .buildRequestForGetCalls()
                .pathParams("id", id)
                .log().all()
                .get("/employees/{id}");


        ExtentLogger.logResponse(response.asPrettyString());
        assertThat(response.getStatusCode())
                .isEqualTo(200);

        assertThat(response.jsonPath().getString("last_name"))
                .as("Checking the last_name field").isEqualTo(lastname)
                .hasSizeBetween(3, 20);
    }

    @DataProvider
    public Object[][] getData() {
        return new Object[][]{
                {123, "Nurlan"},
                {946, "Nurlan"},
                {445, "Nurlan"}
        };
    }
}
