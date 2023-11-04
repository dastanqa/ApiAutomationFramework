package com.dastan.requestbuilder;
import com.dastan.enums.PropertiesType;
import com.dastan.utils.PropertyUtils;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
public final class RequestBuilder { // final won't let to extend

    private RequestBuilder(){}

    public static RequestSpecification buildRequestForGetCalls(){

        return given()
                .baseUri(PropertyUtils.getValue(PropertiesType.BASEURL))
                .log().all();
    }

    public static RequestSpecification buildRequestForPostCalls(){

        return buildRequestForGetCalls()
                .contentType(ContentType.JSON);
    }
}
