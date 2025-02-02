package ui_mobile;

import config.AppiumConfig;
import dto.CarDTO;
import dto.UserDTO;
import interfaces.ValidateLogReg;
import interfaces.ValidateSearchAddCar;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;
import enums.FuelType;

import static helper.RandomUtils.*;

public class AddNewCarTests extends AppiumConfig implements ValidateLogReg, ValidateSearchAddCar {
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
                .fuel(FuelType.GAS.getFuel())
                .year("1975")
                .seats(4)
                .about("best of the best")
                .build();
       Assert.assertTrue(new MyCarsScreen(driver)
                .goToAddNewCarScreen()
                .addNewCar(carDTO)
                .clickAddCarBtn()
                .validateSuccessMessageAddCar("Car was added!"));
    }



    @Test
    public void addNewCarPositiveTestValidateAddedCar(){
        CarDTO carDTO = CarDTO.builder()
                .serialNumber("num-"+generatePhone(5)) // метод для генерации номеров телефона используется для серийников
                .manufacture("ZAZ")
                .model("969")
                .city("Haifa")
                .pricePerDay(333.33)
                .carClass("Hi")
                .fuel(FuelType.GAS.getFuel())
                .year("1975")
                .seats(4)
                .about("best of the best")
                .build();
      Assert.assertEquals(new MyCarsScreen(driver)
                .goToAddNewCarScreen()
                .addNewCar(carDTO)
                .clickAddCarBtn()
                .scrollToLastElementAuto(),carDTO.getSerialNumber());
    }


    @Test
    public void addNewCarNegativeTest_WO_SerialNumber(){ //Serial number, Manufacture, Model, City, Price per day TestDataProvider is in development
        CarDTO carDTO = CarDTO.builder()
                .serialNumber("")
                .manufacture("ZAZ")
                .model("969")
                .city("Haifa")
                .pricePerDay(333.33)
                .carClass("Hi")
                .fuel(FuelType.GAS.getFuel())
                .year("1975")
                .seats(4)
                .about("best of the best")
                .build();
        new MyCarsScreen(driver)
                .goToAddNewCarScreen()
                .addNewCar(carDTO)
                .clickAddCarBtnNegative();
        new ErrorScreen(driver).validateErrorMessage(MISSING_SERIAL_MANUFACT_MODEL_CITY_PRICE);
    }

    @Test
    public void addNewCarNegativeTest_WO_CarClass(){
        CarDTO carDTO = CarDTO.builder()
                .serialNumber("serial"+generatePhone(5))
                .manufacture("ZAZ")
                .model("969")
                .city("Haifa")
                .pricePerDay(333.33)
                .carClass("")
                .fuel(FuelType.GAS.getFuel())
                .year("1975")
                .seats(4)
                .about("best of the best")
                .build();
        new MyCarsScreen(driver)
                .goToAddNewCarScreen()
                .addNewCar(carDTO)
                .clickAddCarBtnNegative();
        new ErrorScreen(driver).validateErrorMessage(CAR_CLASS_EMPTY);
    }

    @Test
    public void addNewCarNegativeTest_CityInvalidName(){
        CarDTO carDTO = CarDTO.builder()
                .serialNumber("serial"+generatePhone(5))
                .manufacture("ZAZ")
                .model("969")
                .city("Rome")
                .pricePerDay(333.33)
                .carClass("A")
                .fuel(FuelType.GAS.getFuel())
                .year("1975")
                .seats(4)
                .about("best of the best")
                .build();
        new MyCarsScreen(driver)
                .goToAddNewCarScreen()
                .addNewCar(carDTO)
                .clickAddCarBtnNegative();
        new ErrorScreen(driver).validateErrorMessage("City "+carDTO.getCity()+" not available");
    }

    @Test
    public void addNewCarNegativeTest_WO_Year() {
        CarDTO carDTO = CarDTO.builder()
                .serialNumber("serial" + generatePhone(5))
                .manufacture("ZAZ")
                .model("969")
                .city("Haifa")
                .pricePerDay(333.33)
                .carClass("A")
                .fuel(FuelType.GAS.getFuel())
                .year("")
                .seats(4)
                .about("best of the best")
                .build();
        new MyCarsScreen(driver)
                .goToAddNewCarScreen()
                .addNewCar(carDTO)
                .clickAddCarBtnNegative();
        new ErrorScreen(driver).validateErrorMessage(YEAR_EMPTY);
    }

    @Test
    public void addNewCarNegativeTest_InvalidSeatNumber() {
        CarDTO carDTO = CarDTO.builder()
                .serialNumber("serial" + generatePhone(5))
                .manufacture("ZAZ")
                .model("969")
                .city("Haifa")
                .pricePerDay(333.33)
                .carClass("A")
                .fuel(FuelType.GAS.getFuel())
                .year("1995")
                .seats(4)
                .about("best of the best")
                .build();
        new MyCarsScreen(driver)
                .goToAddNewCarScreen()
                .addNewCar(carDTO)
                .validateIntEmptySeat() // additional method that deletes value from the seats field, because Seats is integer
                .clickAddCarBtnNegative();
        new ErrorScreen(driver).validateErrorMessage(INVALID_SEATS_NUMBER);
    }






}
