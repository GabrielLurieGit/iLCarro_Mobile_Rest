package ui_mobile;

import config.AppiumConfig;
import dto.UserDTO;
import interfaces.ValidateLogReg;
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
        new MyCarsScreen(driver).deleteFirstCar();
    }

}
