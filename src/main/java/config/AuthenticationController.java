package config;

import dto.UserDTO;
import interfaces.BaseApi;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthenticationController implements BaseApi {
    public Response requestRegLogin(UserDTO userDTO, String url){
       return given()
                .body(userDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL+url)
                .thenReturn();
    }


}
