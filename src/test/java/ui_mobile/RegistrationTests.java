package ui_mobile;

import config.AppiumConfig;
import dto.UserDTO;
import interfaces.MessageConstantsInterface;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.RegistrationScreen;
import screens.SearchScreen;
import screens.SplashScreen;

import java.util.Random;

public class RegistrationTests extends AppiumConfig implements MessageConstantsInterface {

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
                .validateRegSuccessMessage(SUCCESSFULL_REGISTRATION));
    }

    @Test
    public void registrationNegativeTest_WO_LastName(){
        UserDTO user = UserDTO.builder()
                .firstName("Galen")
                .lastName("")
                .username("starkiller003@gmail.com")
                .password("Qwerty1234!")
                .build();
      Assert.assertTrue(new RegistrationScreen(driver)
                .fillRegForm(user)
                .clickRegButtonNegative()
                .validateErrorMessage(MISSING_FIELD));
    }

    @Test
    public void registrationNegativeTest_InvalidEmail(){
        UserDTO user = UserDTO.builder()
                .firstName("Galen")
                .lastName("Marek")
                .username("starkiller")
                .password("Qwerty1234!")
                .build();
      Assert.assertTrue(new RegistrationScreen(driver)
                .fillRegForm(user)
                .clickRegButtonNegative()
                .validateErrorMessage(INVALID_EMAIL));
    }

    @Test
    public void registrationNegativeTest_InvalidPassword(){
        UserDTO user = UserDTO.builder()
                .firstName("Galen")
                .lastName("Marek")
                .username("starkiller@gmail.com")
                .password("Qwe12!")
                .build();
        Assert.assertTrue(new RegistrationScreen(driver)
                .fillRegForm(user)
                .clickRegButtonNegative()
                .validateErrorMessage(INVALID_PASSWORD));
    }





}
