package ui_mobile;

import config.AppiumConfig;
import config.CarController;
import data_provider.TestDataProvider;
import dto.CarDTO;
import dto.CarsDto;
import dto.UserDTO;
import helper.RetryAnalyzer;
import interfaces.ValidateLogReg;
import interfaces.ValidateSearchAddCar;
import io.restassured.response.Response;
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

    @Test(retryAnalyzer = RetryAnalyzer.class)
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



    @Test(retryAnalyzer = RetryAnalyzer.class)
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

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void addNewCarPositiveTestValidateRestApi(){
        CarDTO carDTO = CarDTO.builder()
                .serialNumber("num-"+generatePhone(5)) // метод для генерации номеров телефона используется для серийников
                .manufacture("Volvo")
                .model("100")
                .city("Haifa")
                .pricePerDay(1000.99)
                .carClass("C")
                .fuel(FuelType.GAS.getFuel())
                .year("1999")
                .seats(5)
                .about("best of the best car volvo")
                .build();
        new MyCarsScreen(driver)
                .goToAddNewCarScreen()
                .addNewCar(carDTO)
                .clickAddCarBtn();
        CarController carController = new CarController();
        carController.login();
        Response response = carController.getUserCars(carController.tokenDto.getAccessToken());
        CarDTO[] arrayCar = response.body().as(CarsDto.class).getCars();
        int index = 0;
        for (int i = 0; i< arrayCar.length;i++){
            if (arrayCar[i].equals(carDTO)){
                System.out.println("=car.equals(CarDTO)=");
                index =i;
                break;
            }
        }
        Assert.assertEquals(carDTO,arrayCar[index]);
    }





    @Test(retryAnalyzer = RetryAnalyzer.class,dataProvider = "invalidCarData",dataProviderClass = TestDataProvider.class)
    public void addNewCarNegativeTest_WO_SNumber_Manufacture_Model_City_Price(
            String serialNumber, String manufacture, String model, String city, double pricePerDay,
            String carClass, String fuel, String year, int seats, String about, String expectedErrorMessage){ //Serial number, Manufacture, Model, City, Price per day TestDataProvider is in development
        CarDTO carDTO = CarDTO.builder()
                .serialNumber(serialNumber)
                .manufacture(manufacture)
                .model(model)
                .city(city)
                .pricePerDay(pricePerDay)
                .carClass(carClass)
                .fuel(fuel)
                .year(year)
                .seats(seats)
                .about(about)
                .build();
        new MyCarsScreen(driver)
                .goToAddNewCarScreen()
                .addNewCar(carDTO)
                .clickAddCarBtnNegative();
        new ErrorScreen(driver).validateErrorMessage(expectedErrorMessage);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
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

    @Test(retryAnalyzer = RetryAnalyzer.class)
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

    @Test(retryAnalyzer = RetryAnalyzer.class)
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

    @Test(retryAnalyzer = RetryAnalyzer.class)
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
