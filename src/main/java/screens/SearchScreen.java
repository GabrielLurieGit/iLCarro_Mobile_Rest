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

    public void goToRegistrationScreen(){
        clickWait(moreOptionsBtn,5);
        clickWait(registrationBtn,5);
    }

    public boolean validateRegSuccessMessage(String message){
        return textInElementPresent(regSuccessMsg,message,5);
    }







}
