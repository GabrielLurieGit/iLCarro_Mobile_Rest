package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SplashScreen extends BaseScreen{
    public SplashScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }
    @FindBy(id = "com.telran.ilcarro:id/versionText")
    AndroidElement versionApp;

    public boolean validateVersion(String version){
        return textInElementPresent(versionApp,version,5);
    }

    public boolean validateSplashScreenTime(Long time){
        long startTime = System.currentTimeMillis();
        new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("com.telran.ilcarro:id/versionText")));
        new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("com.telran.ilcarro:id/findTitle")));
        long endTime = System.currentTimeMillis();
        System.out.println("time --> "+(double)(endTime-startTime)/1000);
        if (endTime-startTime <= time*1000){
            return true;
        }
        return false;
    }

    public void goToSearchScreen(){
        try {
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.presenceOfElementLocated(
                            By.id("com.telran.ilcarro:id/findTitle")));
        }catch (TimeoutException e){
            e.printStackTrace();
            System.out.println("element findTitle not find");
        }
    }


}
