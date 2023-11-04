package com.dastan.tests;

import com.dastan.annotations.FrameworkAnnotation;
import com.dastan.constants.FCwithSingleton;
import com.dastan.constants.FrameworkConstants;
import com.dastan.pojo.Employee;
import com.dastan.reports.ExtentLogger;
import com.dastan.requestbuilder.RequestBuilder;
import com.dastan.utils.ApiUtils;
import com.dastan.utils.RandomUtils;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.dastan.utils.RandomUtils.*;

public class PostTest extends BaseTest {

    @Test
    @FrameworkAnnotation(author = {"Aygerim"}, category = {"Smoke", "Post Call"})

    public void postCallTest() {

        //create an employee in the db using post call
        //construct using pojo and lombok builder

        Employee employee = Employee
                .builder()
                .setFname(getFirstName()).setLname(getLastName()).setId(getId()).build();


        RequestSpecification requestSpecification = RequestBuilder
                .buildRequestForPostCalls()
                .body(employee);
        ExtentLogger.logRequest(requestSpecification);
        Response response = requestSpecification.post("/employees");

        response.prettyPrint();
        ExtentLogger.logResponse(response.asPrettyString());

        Assertions.assertThat(response.getStatusCode())
                .isEqualTo(201);
    }

    @Test
    @FrameworkAnnotation

    public void postRequestUsingExternalFile(Method method) {

        String resource = ApiUtils.readJsonAndGetAsString(FrameworkConstants.getRequestJsonFolderPath() + "request.json")
                .replace("fname", RandomUtils.getFirstName())
                .replace("number", String.valueOf(RandomUtils.getId()));

        RequestSpecification requestSpecification = RequestBuilder
                .buildRequestForPostCalls()
                .body(resource);

        ExtentLogger.logRequest(requestSpecification);
        Response response = requestSpecification.post("/employees");

        response.prettyPrint();
        ExtentLogger.logResponse(response.asPrettyString());
        ApiUtils.storeStringAsJsonFile(FrameworkConstants.getResponseJsonFolderPath() + method.getName() + "response.json", response);
        Assertions.assertThat(response.getStatusCode())
                .isEqualTo(201);
    }

    @Test
    @FrameworkAnnotation(author = {"Aygerim"}, category = {"Smoke", "Post Call"})
    public void postRequestUsingExternalFile1(Method method) {

        String resource = ApiUtils.readJsonAndGetAsString(
                        FCwithSingleton.getInstance().getRequestJsonFolderPath() + "request.json")
                .replace("fname", RandomUtils.getFirstName())
                .replace("number", String.valueOf(RandomUtils.getId()));

        //interface
        RequestSpecification requestSpecification = RequestBuilder
                .buildRequestForPostCalls()
                .body(resource);

        ExtentLogger.logRequest(requestSpecification);
        Response response = requestSpecification.post("/employees");

        response.prettyPrint();
        ExtentLogger.logResponse(response.asPrettyString());

        ApiUtils
                .storeStringAsJsonFile(
                        FCwithSingleton.getInstance()
                                .getResponseJsonFolderPath() + method.getName() + "response.json", response);

        Assertions.assertThat(response.getStatusCode())
                .isEqualTo(201);
    }

}
