package helper;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private static int maxTryValue = 3;
    @Override
    public boolean retry(ITestResult result) {
        if (result.getThrowable()!= null && result.getThrowable().getMessage().contains("socket hang up")){
            if (retryCount < maxTryValue){
                retryCount++;
                return true;
            }
        }

        return false;
    }
}
