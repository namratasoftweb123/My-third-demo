package org.AltraMotion.ts;

import com.relevantcodes.extentreports.LogStatus;
import org.AltraMotion.base.BaseTest;
import org.AltraMotion.base.extendreport.ExtentTestManager;
import org.AltraMotion.po.AltraPO;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TradeshowTC extends BaseTest
{
    public int flag = 0;

    public TradeshowTC() {
    }

    public TradeshowTC(WebDriver passDriver, int Flag) {
        driver = passDriver;
        flag = Flag;
    }

    //TEST-2: Verify User is able to add Tradeshow and Save and Publish it successfully.
    @Test
    public void AddTradeShowInSiteCore() throws Exception {
        if (flag > 0) {
            ExtentTestManager.getTest().getTest().setName("TEST-2: Verify User is able to add Tradeshow and Save and Publish it successfully.");
            ExtentTestManager.getTest().assignCategory("Component: Tradeshow");
        }
        LoginTC loginTC = new LoginTC(driver, 0);
        loginTC.LoginTest();

        AltraPO loginPage = new AltraPO(driver);

        //Step 1 :: Click on Content Editor
        Assert.assertEquals(loginPage.clickOnonContentEditor(), true, "Unable to click on Content Editor");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Content Editor", "System should open Content Editor");


        //Step 2 :: Add Tradeshow
        Assert.assertTrue(loginPage.AddTradeshow(), "Unable to add Tradeshow\n");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Add record in Tradeshow ", "User should be able to add record in tradeshow.");

        //Step 3 :: Save and Publish
        Assert.assertTrue(loginPage.SaveAndPublish(), "Unable to Save and Publish.");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Save and Publish the tradeshow ", "Tradeshow Save and Published successfully.");

    }

    //TEST-3: Verify TradeShow into Altra Application.
    @Test
    public void VerifyTradeShowInAltraApp() throws Exception {
        if (flag > 0) {
            ExtentTestManager.getTest().getTest().setName("TEST-3: Verify TradeShow into Altra Application.");
            ExtentTestManager.getTest().assignCategory("Component: Tradeshow");
        }
        TradeshowTC tradeshowTC = new TradeshowTC(driver,0);
        tradeshowTC.AddTradeShowInSiteCore();

        AltraPO loginPage = new AltraPO(driver);

        //Step 1 :: Open altra url in new tab
        Assert.assertTrue(loginPage.openAltraApp(), "Unable to Open altra url in new tab");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Open Altra Application URL in New Tab", "System should open Altra Application url successfully.");

        //Step 2 :: Login into Altra app
        Assert.assertTrue(loginPage.loginAltraApp(), "Unable to Login into Altra app");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Login into Altra app with valid credentials", "User should be able to login successfully.");

        //Step 3 :: Verify show and Dates in tradeshows
        Assert.assertTrue(loginPage.verifyShowAndDates(loginPage.CorporateTxt), "Unable to Verify show and Dates in tradeshows");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verify show and Dates in tradeshows", "System should display same show and date as added in site core.");

    }

    //TEST-4: Edit Tradeshow and verify updated Tradeshow in Altra App
    @Test
    public void EditTradeShowsAndVerifyInAltraApp() throws Exception {
        if (flag > 0) {
            ExtentTestManager.getTest().getTest().setName("TEST-4: Edit Tradeshow and verify updated Tradeshow in Altra App");
            ExtentTestManager.getTest().assignCategory("Component: Tradeshow");
        }

        TradeshowTC tradeshowTC = new TradeshowTC(driver, 0);
        tradeshowTC.AddTradeShowInSiteCore();

        AltraPO loginPage = new AltraPO(driver);

        //Step 2 :: Edit Tradeshow in sitecore
        Assert.assertTrue(loginPage.EditTradeshow(), "Unable to edit Tradeshow\n");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Edit record in Tradeshow ", "User should be able to Edit record in tradeshow.");

        //Step 3 :: Save and Publish
        Assert.assertTrue(loginPage.SaveAndPublish(), "Unable to Save and Publish.");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Save and Publish the tradeshow ", "Tradeshow Save and Published successfully.");

        //Step 4 :: Go to Altra App and Verify updated Tradeshow
        Assert.assertTrue(loginPage.verifyUpdatedTradeshow(), "Unable to Go to Altra App and Veify updated Tradeshow");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Go to Altra App and Veify updated Tradeshow", "Tradeshow should be display updated in Altra app");
    }

    //TEST-5: Delete Tradeshow and verify Tradeshow not displaying in Altra App
    @Test
    public void DeleteTradeShowAndVerifyInAltraApp() throws Exception {
        if (flag > 0) {
            ExtentTestManager.getTest().getTest().setName("TEST-5: Delete Tradeshow and verify Tradeshow not displaying in Altra App");
            ExtentTestManager.getTest().assignCategory("Component: Tradeshow");
        }

        TradeshowTC tradeshowTC = new TradeshowTC(driver, 0);
        tradeshowTC.AddTradeShowInSiteCore();

        AltraPO loginPage = new AltraPO(driver);

        //Step 1 :: Delete Tradeshow in sitecore
        Assert.assertTrue(loginPage.DeleteTradeshow(), "Unable to delete Tradeshow\n");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Delete record in Tradeshow ", "User should be able to Delete record in tradeshow.");

        //Step 2 :: Go to Altra App and Verify deleted Tradeshow is not displaying
        Assert.assertTrue(loginPage.verifyDeletedTradeshow(), "Unable to Verify deleted Tradeshow is not displaying");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Go to Altra App and Verify deleted Tradeshow is not displaying", "Deleted Tradeshow should not display in Altra app");

    }

}
