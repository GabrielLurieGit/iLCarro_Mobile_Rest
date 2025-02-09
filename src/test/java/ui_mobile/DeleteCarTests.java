package ui_mobile;

import config.AppiumConfig;
import config.CarController;
import dto.CarsDto;
import dto.UserDTO;
import interfaces.ValidateLogReg;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.MyCarsScreen;
import screens.SearchScreen;
import screens.SplashScreen;

public class DeleteCarTests extends AppiumConfig implements ValidateLogReg {
    SearchScreen searchScreen;

    @BeforeMethod
    public void login() {
        new SplashScreen(driver);
        searchScreen = new SearchScreen(driver);
        UserDTO user = UserDTO.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .build();
        searchScreen.goToLoginScreen()
                .loginForm(user)
                .clickLoginBtn()
                .goToMyCarsScreen();
    }


    @Test
    public void deleteFirstCarPositiveTest(){
        CarController carController = new CarController();
        carController.login();
       Response responseBeforeTest = carController.getUserCars(carController.tokenDto.getAccessToken());

     //   System.out.println(responseBeforeTest.getStatusCode());
       int quantityCarsBefore = responseBeforeTest.body().as(CarsDto.class).getCars().length;
        new MyCarsScreen(driver).deleteFirstCar();
        Response responseAfterTest = carController.getUserCars(carController.tokenDto.getAccessToken());
        int quantityCarsAfter = responseAfterTest.body().as(CarsDto.class).getCars().length;
        System.out.println("quantity before -->"+quantityCarsBefore+" quantity after --> "+quantityCarsAfter);
        Assert.assertTrue(quantityCarsAfter<quantityCarsBefore);
    }

}
