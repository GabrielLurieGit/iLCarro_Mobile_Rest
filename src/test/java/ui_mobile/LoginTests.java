package ui_mobile;

import config.AppiumConfig;
import dto.UserDTO;
import interfaces.MessageConstantsInterface;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.LoginScreen;
import screens.SearchScreen;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig implements MessageConstantsInterface {

    @BeforeMethod
    public void beforeLogin(){
        new SplashScreen(driver);
        new SearchScreen(driver).goToLoginScreen();
    }

    @Test
    public void loginPositiveTest(){
        UserDTO user = UserDTO.builder()
                .username("bigbrother2@gmail.com")
                .password("Tr43123456!")
                .build();
        new LoginScreen(driver)
                .loginForm(user)
                .clickLoginBtn()
                .validateLogSuccessMessage(SUCCESSFULL_LOGIN);
    }
}
