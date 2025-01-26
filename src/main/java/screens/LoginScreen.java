package screens;

import dto.UserDTO;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class LoginScreen extends BaseScreen{
    public LoginScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(id = "com.telran.ilcarro:id/editLoginEmail")
    AndroidElement inputEmail;
    @FindBy(id = "com.telran.ilcarro:id/editLoginPassword")
    AndroidElement inputPassword;
    @FindBy(id = "com.telran.ilcarro:id/loginBtn")
    AndroidElement loginBtn;

    @FindBy(id = "android:id/message")
    AndroidElement errorMessage;

    public LoginScreen loginForm(UserDTO user){
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
        return this;
    }

    public SearchScreen clickLoginBtn(){
        loginBtn.click();
        return new SearchScreen(driver);
    }

    public LoginScreen clickLoginBtnNegative(){
        loginBtn.click();
        return this;
    }

    public boolean validateErrorMessage(String message){
        return textInElementPresent(errorMessage,message,5);
    }


}
