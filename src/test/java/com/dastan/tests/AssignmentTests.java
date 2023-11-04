package com.dastan.tests;

import com.dastan.annotations.FrameworkAnnotation;
import com.dastan.pojo.Employee;
import com.dastan.reports.ExtentLogger;
import com.dastan.utils.ApiUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static com.dastan.requestbuilder.RequestBuilder.buildRequestForGetCalls;
import static com.dastan.requestbuilder.RequestBuilder.buildRequestForPostCalls;
import static com.dastan.utils.RandomUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AssignmentTests {

    @Test
    @FrameworkAnnotation(author = "Dastan",category = {"assignment"})
    public void assignmentTest() {

        Response response = buildRequestForGetCalls()
                .get("/employees");

        int size = response.jsonPath().getList("$").size();

        ExtentLogger.logResponse(response.prettyPrint());
        Employee employee = Employee.builder().setFname(getFirstName())
                .setLname(getLastName())
                .setId(getId())
                .build();
        RequestSpecification requestSpecification = buildRequestForPostCalls()
                .body(employee);

        ExtentLogger.logRequest(requestSpecification);

        Response postResponse = requestSpecification.post("/employees");
        ExtentLogger.logResponse(postResponse.asPrettyString());

        assertThat(buildRequestForGetCalls().get("/employees")
                .jsonPath().getList("$")).hasSize(size+1);


        employee.setFname("Dastan");
        int id = employee.getId();
        Response putResponse = buildRequestForPostCalls().pathParams("id", id).body(employee).put("employees/{id}");

        ExtentLogger.logResponse(putResponse.asString());
        ApiUtils.storeStringAsJsonFile("putresponse.txt",putResponse);

        buildRequestForGetCalls().pathParams("id",id).delete("/employees/{id}");

        assertThat(buildRequestForGetCalls().get("/employees")
                .jsonPath().getList("$")).hasSize(size);
    }
}
