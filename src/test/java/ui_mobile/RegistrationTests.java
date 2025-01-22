package ui_mobile;

import config.AppiumConfig;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.RegistrationScreen;
import screens.SearchScreen;
import screens.SplashScreen;

import java.util.Random;

public class RegistrationTests extends AppiumConfig {

    @BeforeMethod
    public void beforeTest(){
        new SplashScreen(driver);
        new SearchScreen(driver).goToRegistrationScreen();
    }

    @Test
    public void registrationPositiveTest(){
        int i = new Random().nextInt(100)+100;
        UserDTO user = UserDTO.builder()
                .firstName("Galen")
                .lastName("Marek")
                .username("starkiller"+i+"@gmail.com")
                .password("Qwerty1234!")
                .build();
        Assert.assertTrue(new RegistrationScreen(driver)
                .fillRegForm(user)
                .clickRegButton()
                .validateRegSuccessMessage("Registration success!"));
    }
}
