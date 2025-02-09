package config;

import dto.CarDTO;
import dto.TokenDto;
import dto.UserDTO;
import interfaces.BaseApi;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.given;

public class CarController implements BaseApi {
   public TokenDto tokenDto;
    public void login(){
        UserDTO user = UserDTO.builder()
                .username("bigbrother2@gmail.com")
                .password("Tr43123456!")
                .build();
        AuthenticationController authenticationController = new AuthenticationController();
        Response response = authenticationController.requestRegLogin(user,LOGIN);
        if (response.getStatusCode() == 200){
            tokenDto = response.getBody().as(TokenDto.class);
        }else System.out.println("Something went wrong"+response.getStatusCode());
    }


    public Response getUserCars(String token){
        return given()
                .header("Authorization",token)
                .when()
                .get(BASE_URL+GET_USER_CARS)
                .thenReturn();
    }

    public Response addNewCar(CarDTO car, String token){
        return given()
                .body(car)
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .when()
                .post(BASE_URL+ADD_NEW_CAR)
                .thenReturn();
    }
}
