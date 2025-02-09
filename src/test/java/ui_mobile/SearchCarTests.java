package ui_mobile;

import config.AppiumConfig;
import dto.SearchDTO;
import helper.RetryAnalyzer;
import interfaces.ValidateLogReg;
import interfaces.ValidateSearchAddCar;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.ErrorScreen;
import screens.SearchScreen;

public class SearchCarTests extends AppiumConfig implements ValidateSearchAddCar {
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public static void searchCarPositiveTest(){
        SearchDTO searchDTO = SearchDTO.builder()
                .city("Rehovot")
                .startDate("01/02/2025")
                .endDate("08/02/2025")
                .build();
        Assert.assertTrue(new SearchScreen(driver)
                .typeSearchForm(searchDTO,"input")
                .clickSearchBtn()
                .validateResultTitle("Search result"));
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public static void searchCarPositiveTest_UsingCalendar(){
        SearchDTO searchDTO = SearchDTO.builder()
                .city("Rehovot")
                .startDate("09/07/2026")
                .endDate("08/08/2026")
                .build();
       Assert.assertTrue(new SearchScreen(driver)
                .typeSearchForm(searchDTO,"calendar")
                .clickSearchBtn()
                .validateResultTitle("Search result"));
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public static void searchCarNegativeTest_WO_Location(){
        SearchDTO searchDTO = SearchDTO.builder()
                .city("")
                .startDate("01/02/2025")
                .endDate("08/02/2025")
                .build();
        new SearchScreen(driver)
                .typeSearchForm(searchDTO,"input")
                .clickSearchBtnNegative();
       Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage(SEARCH_EMPTY_CITY));
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public static void searchCarNegativeTest_InvalidToDate(){
        SearchDTO searchDTO = SearchDTO.builder()
                .city("Rehovot")
                .startDate("22/02/2025")
                .endDate("01/02/2025")
                .build();
        new SearchScreen(driver)
                .typeSearchForm(searchDTO,"input")
                .clickSearchBtnNegative();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage(TO_BEFORE_FROM));
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public static void searchCarNegativeTest_InvalidFromDate(){
        SearchDTO searchDTO = SearchDTO.builder()
                .city("Rehovot")
                .startDate("01/02/2023")
                .endDate("01/02/2025")
                .build();
        new SearchScreen(driver)
                .typeSearchForm(searchDTO,"input")
                .clickSearchBtnNegative();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage(FROM_BEFORE_TO));
    }






}
