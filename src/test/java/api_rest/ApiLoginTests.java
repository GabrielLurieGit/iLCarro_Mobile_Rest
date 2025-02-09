package api_rest;

import config.AuthenticationController;
import dto.ErrorMessageDtoString;
import dto.UserDTO;
import interfaces.BaseApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiLoginTests extends AuthenticationController implements BaseApi {
    @Test
    public void loginPositiveTest(){
        UserDTO user = UserDTO.builder()
                .username("bigbrother2@gmail.com")
                .password("Tr43123456!")
                .build();
        Response response = requestRegLogin(user,LOGIN);
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),200);
    }


    @Test
    public void loginNegativeTest_invalidEmail(){
        UserDTO user = UserDTO.builder()
                .username("bigbrother2@gmail")
                .password("Tr43123456!")
                .build();
        Response response = requestRegLogin(user,LOGIN);
        System.out.println(response.getStatusCode());
        ErrorMessageDtoString errorMessageDtoString = response.getBody().as(ErrorMessageDtoString.class);
        System.out.println(errorMessageDtoString.toString());
        Assert.assertTrue(errorMessageDtoString.getMessage().toString().equals("Login or Password incorrect"));
    }
}
