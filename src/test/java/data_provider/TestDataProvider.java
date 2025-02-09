package data_provider;

import enums.FuelType;
import interfaces.ValidateSearchAddCar;
import org.testng.annotations.DataProvider;
import static helper.RandomUtils.*;
public class TestDataProvider implements ValidateSearchAddCar {
    @DataProvider(name = "registrationInvalidPasswordData")
    public Object[][] registrationInvalidPasswordData(){

        return new Object[][]{
                {"Qw2!"},        //@$#^&*!
                {"qwerty1234!"},
                {"Qwerty12345"},
                {"Qwerty!@$#^"},
                {"2!3@5#6^7&9*"},
                {"qwertyqwerty"},
                {"12345678"},
                {"Qwerty 1234!"}
        };
    }

    @DataProvider(name = "invalidCarData")
    public Object[][] invalidCarData() {
        return new Object[][] {
                { "", "ZAZ", "969", "Haifa", 333.33, "Hi", FuelType.GAS.getFuel(), "1975", 4, "best of the best", MISSING_SERIAL_MANUFACT_MODEL_CITY_PRICE },
                { "num"+generatePhone(5), "", "969", "Haifa", 333.33, "Hi", FuelType.GAS.getFuel(), "1975", 4, "best of the best", MISSING_SERIAL_MANUFACT_MODEL_CITY_PRICE },
                { "num"+generatePhone(5), "ZAZ", "", "Haifa", 333.33, "Hi", FuelType.GAS.getFuel(), "1975", 4, "best of the best", MISSING_SERIAL_MANUFACT_MODEL_CITY_PRICE },
                { "num"+generatePhone(5), "ZAZ", "969", "", 333.33, "Hi", FuelType.GAS.getFuel(), "1975", 4, "best of the best", MISSING_SERIAL_MANUFACT_MODEL_CITY_PRICE },
                { "num"+generatePhone(5), "ZAZ", "969", "Haifa", Double.NaN, "Hi", FuelType.GAS.getFuel(), "1975", 4, "best of the best", MISSING_SERIAL_MANUFACT_MODEL_CITY_PRICE },
        };
    }



}
