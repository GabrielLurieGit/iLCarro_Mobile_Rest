package ui_mobile;

import config.AppiumConfig;
import dto.UserDTO;
import data_provider.DataProvider;
import interfaces.MessageConstantsInterface;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.RegistrationScreen;
import screens.SearchScreen;
import screens.SplashScreen;
import static helper.RandomUtils.*;
import java.util.Random;

public class RegistrationTests extends AppiumConfig implements MessageConstantsInterface {

    @BeforeMethod
    public void beforeRegistration(){
        new SplashScreen(driver);
        new SearchScreen(driver).goToRegistrationScreen();
    }

    @Test
    public void registrationPositiveTest(){
        int i = new Random().nextInt(100)+100;
        UserDTO user = UserDTO.builder()
                .firstName(generateString(5))
                .lastName(generateString(10))
                .username(generateEmail(10))
                .password("Qwerty1234!")
                .build();
        Assert.assertTrue(new RegistrationScreen(driver)
                .fillRegForm(user)
                .selectCheckbox()
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
                .selectCheckbox()
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
                .selectCheckbox()
                .clickRegButtonNegative()
                .validateErrorMessage(INVALID_EMAIL));
    }

    @Test(dataProvider = "registrationInvalidPasswordData",dataProviderClass = DataProvider.class)
    public void registrationNegativeTest_InvalidPassword(String password){
        UserDTO user = UserDTO.builder()
                .firstName("Galen")
                .lastName("Marek")
                .username("starkiller@gmail.com")
                .password(password)
                .build();
        Assert.assertTrue(new RegistrationScreen(driver)
                .fillRegForm(user)
                .selectCheckbox()
                .clickRegButtonNegative()
                .validateErrorMessage(INVALID_PASSWORD));
    }

    @Test
    public void registrationNegativeTest_WO_Checkbox(){
        UserDTO user = UserDTO.builder()
                .firstName("Galen")
                .lastName("Marek")
                .username("starkiller003@gmail.com")
                .password("Qwerty1234!")
                .build();
        Assert.assertTrue(new RegistrationScreen(driver)
                .fillRegForm(user)
                .clickRegButtonNegative()
                .validateErrorMessage(MISSING_FIELD));
    }

    @Test
    public void registrationNegativeTest_UserAlreadyExists(){
        UserDTO user = UserDTO.builder()
                .firstName("Galen")
                .lastName("Marek")
                .username("bigbrother@gmail.com")
                .password("Qwerty1234!")
                .build();
        Assert.assertTrue(new RegistrationScreen(driver)
                .fillRegForm(user)
                .selectCheckbox()
                .clickRegButtonNegative()
                .validateErrorMessage(USER_ALREADY_EXISTS));
    }






}
