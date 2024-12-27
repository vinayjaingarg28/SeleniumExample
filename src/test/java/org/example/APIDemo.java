package org.example;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
import org.testng.annotations.Test;

public class APIDemo {

    @Test
    public void getApi(){
        baseURI = "https://reqres.in/";

        Response response = (Response) get("/api/users?page=2")
                .andReturn()
                ;
        response.getBody().prettyPrint();
//        System.out.println(response.getBody().prettyPrint());

    }
}
