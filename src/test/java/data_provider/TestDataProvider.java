package data_provider;

import org.testng.annotations.DataProvider;
public class TestDataProvider {
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

}
