package ui_mobile;

import config.AppiumConfig;
import dto.CarDTO;
import dto.UserDTO;
import interfaces.MessageConstantsInterface;
import lombok.extern.java.Log;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;

import static helper.RandomUtils.*;

public class AddNewCarTests extends AppiumConfig implements MessageConstantsInterface {
    SearchScreen searchScreen;
    LoginScreen loginScreen;

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
    public void addNewCarPositiveTest(){
        CarDTO carDTO = CarDTO.builder()
                .serialNumber("num-"+generatePhone(5)) // метод для генерации номеров телефона используется для серийников
                .manufacture("ZAZ")
                .model("969")
                .city("Haifa")
                .pricePerDay(333.33)
                .carClass("Hi")
                .fuel("Gas")
                .year("1975")
                .seats(4)
                .about("best of the best")
                .build();
        new MyCarsScreen(driver)
                .goToAddNewCarScreen()
                .addNewCar(carDTO);
    }
}
