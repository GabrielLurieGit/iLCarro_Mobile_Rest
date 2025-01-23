package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class SearchScreen extends BaseScreen{
    public SearchScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }
    @FindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
    AndroidElement moreOptionsBtn;
    @FindBy (xpath = "//*[@text='Registration']")
    AndroidElement registrationBtn;
    @FindBy (xpath = "//*[@text='Login']")
    AndroidElement loginBtn;
    @FindBy(xpath = "//*[@text='Registration success!']")
    AndroidElement regSuccessMsg;
    @FindBy(xpath = "//*[@text='Login success!']")
    AndroidElement logSuccessMsg;


    public void goToRegistrationScreen(){
        clickWait(moreOptionsBtn,5);
        clickWait(registrationBtn,5);
    }

    public boolean validateRegSuccessMessage(String message){
        return textInElementPresent(regSuccessMsg,message,5);
    }


    public void goToLoginScreen() {
        clickWait(moreOptionsBtn,5);
        clickWait(loginBtn,5);
    }

    public boolean validateLogSuccessMessage(String message){
        return textInElementPresent(logSuccessMsg,message,5);
    }
}
