package screens;

import dto.UserDTO;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationScreen extends BaseScreen{
    public RegistrationScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(id = "com.telran.ilcarro:id/editRegName")
    AndroidElement inputName;
    @FindBy(id = "com.telran.ilcarro:id/editRegLastName")
    AndroidElement inputLastName;
    @FindBy(id = "com.telran.ilcarro:id/editRegEmail")
    AndroidElement inputEmail;
    @FindBy(id = "com.telran.ilcarro:id/editRegPassword")
    AndroidElement inputPassword;
    @FindBy(id = "com.telran.ilcarro:id/checkBoxAgree")
    AndroidElement checkboxReg;
    @FindBy(id = "com.telran.ilcarro:id/regBtn")
    AndroidElement regBtn;
    @FindBy(id = "android:id/message")
    AndroidElement errorMessage;


    public RegistrationScreen fillRegForm(UserDTO user){
        inputName.sendKeys(user.getFirstName());
        inputLastName.sendKeys(user.getLastName());
        inputEmail.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
        return this;
    }

    public RegistrationScreen selectCheckbox(){
        checkboxReg.click();
        return this;
    }

   public SearchScreen clickRegButton(){
        regBtn.click();
        return new SearchScreen(driver);
   }

   public RegistrationScreen clickRegButtonNegative(){
       regBtn.click();
       return this;
   }


   public boolean validateErrorMessage(String message){
      return textInElementPresent(errorMessage,message,5);
   }







}
