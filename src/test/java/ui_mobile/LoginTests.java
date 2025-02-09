package ui_mobile;

import config.AppiumConfig;
import dto.UserDTO;
import interfaces.ValidateLogReg;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ErrorScreen;
import screens.LoginScreen;
import screens.SearchScreen;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig implements ValidateLogReg {

    @BeforeMethod
    public void beforeLogin() {
        new SplashScreen(driver);
        new SearchScreen(driver).goToLoginScreen();
    }

    @Test
    public void loginPositiveTest() {
        UserDTO user = UserDTO.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .build();
        Assert.assertTrue(new LoginScreen(driver)
                .loginForm(user)
                .clickLoginBtn()
                .validateLogSuccessMessage(SUCCESSFULL_LOGIN));
    }

    @Test
    public void loginNegativeTest_WO_Password() {
        UserDTO user = UserDTO.builder()
                .username(USERNAME)
                .password("")
                .build();
        new LoginScreen(driver)
                .loginForm(user)
                .clickLoginBtnNegative();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage(MISSING_FIELD));
    }

    @Test
    public void loginNegativeTest_WO_Username() {
        UserDTO user = UserDTO.builder()
                .username("")
                .password(PASSWORD)
                .build();
        new LoginScreen(driver)
                .loginForm(user)
                .clickLoginBtnNegative();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage(MISSING_FIELD));
    }

    @Test
    public void loginNegativeTest_InvalidPassword() {
        UserDTO user = UserDTO.builder()
                .username(USERNAME)
                .password("Qwerty1234!")
                .build();
        new LoginScreen(driver)
                .loginForm(user)
                .clickLoginBtnNegative();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage(LOGIN_OR_PASS_INCORECT));
    }

    @Test
    public void loginNegativeTest_UsernameNotExists() {
        UserDTO user = UserDTO.builder()
                .username("testgaming1414@gmail.com")
                .password("Qwerty1234!")
                .build();
        new LoginScreen(driver)
                .loginForm(user)
                .clickLoginBtnNegative();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage(LOGIN_OR_PASS_INCORECT));
    }



}
