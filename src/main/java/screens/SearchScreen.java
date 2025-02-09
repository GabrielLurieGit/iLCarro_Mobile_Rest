package screens;

import dto.SearchDTO;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

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

    @FindBy(xpath = "//*[@text='My Cars']")
    AndroidElement myCarsBtn;
    @FindBy(id = "com.telran.ilcarro:id/editLocation")
    AndroidElement inputLocation;
    @FindBy(id = "com.telran.ilcarro:id/editFrom")
    AndroidElement inputFrom;
    @FindBy(id = "com.telran.ilcarro:id/editTo")
    AndroidElement inputTo;
    @FindBy(id = "com.telran.ilcarro:id/searchBtn")
    AndroidElement searchBtn;
    @FindBy(id = "android:id/button1")
    AndroidElement okCalendarBtn;
    @FindBy(id = "android:id/next")
    AndroidElement nextMonth;
    @FindBy(id = "android:id/date_picker_header_date")
    AndroidElement dataHeader;
    @FindBy(id = "android:id/date_picker_header_year")
    AndroidElement yearPicker;



    public void goToRegistrationScreen(){
        clickWait(moreOptionsBtn,5);
        clickWait(registrationBtn,5);
    }

    public boolean validateRegSuccessMessage(String message){
        return textInElementPresent(regSuccessMsg,message,5);
    }


    public LoginScreen goToLoginScreen() {
        clickWait(moreOptionsBtn,5);
        clickWait(loginBtn,5);
        return new LoginScreen(driver);
    }

    public MyCarsScreen goToMyCarsScreen(){
        clickWait(moreOptionsBtn,5);
        clickWait(myCarsBtn,5);
        return new MyCarsScreen(driver);
    }
    public boolean validateLogSuccessMessage(String message){
        return textInElementPresent(logSuccessMsg,message,5);
    }

    public SearchScreen typeSearchForm(SearchDTO searchDTO, String typingMethod){
        clickWait(inputLocation,5);
        inputLocation.sendKeys(searchDTO.getCity());
        if (typingMethod.equalsIgnoreCase("calendar"))
        {
            typePeriod(searchDTO.getStartDate(),inputFrom);
            typePeriod(searchDTO.getEndDate(),inputTo); 
        } else if (typingMethod.equalsIgnoreCase("input")) {
            inputFrom.sendKeys(searchDTO.getStartDate());
            inputTo.sendKeys(searchDTO.getEndDate());
        }
        else {
            throw new IllegalArgumentException("Unsupported typing method");
        }
        return this;
    }




    public SearchResultScreen clickSearchBtn(){
        searchBtn.click();
        return new SearchResultScreen(driver);
    }

    public SearchScreen clickSearchBtnNegative(){
        searchBtn.click();
        return this;
    }

    private void typePeriod(String date, AndroidElement element) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        String day = String.valueOf(localDate.getDayOfMonth());
        String month = localDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        String year = String.valueOf(localDate.getYear());

        if (element == inputFrom){
            clickWait(inputFrom, 5);
        }else if (element == inputTo){
            clickWait(inputTo,5);
        }else {
            throw new IllegalArgumentException("Invalid element");
        }

        yearPicker.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
               "//*[@resource-id='android:id/text1' and @text='"+year+"']"))).click();
        while (!dataHeader.getText().contains(month)) {
            nextMonth.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='" + day + "']"))).click();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='" + day + "']"))).click();
        okCalendarBtn.click();
    }













}
