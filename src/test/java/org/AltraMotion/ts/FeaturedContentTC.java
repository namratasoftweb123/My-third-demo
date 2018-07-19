package org.AltraMotion.ts;

import com.relevantcodes.extentreports.LogStatus;
import org.AltraMotion.base.BaseTest;
import org.AltraMotion.base.extendreport.ExtentTestManager;
import org.AltraMotion.po.AltraPO;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FeaturedContentTC extends BaseTest {

    public int flag = 0;

    public FeaturedContentTC() {
    }

    public FeaturedContentTC(WebDriver passDriver, int Flag) {
        driver = passDriver;
        flag = Flag;
    }

    //TEST-6 : Add Featured Content Template in Sitecore
    @Test
    public void AddFeaturedContentTemplateInSiteCore() throws Exception {

        if (flag > 0) {
            ExtentTestManager.getTest().getTest().setName("TEST-6: Add Featured Content Template in Sitecore");
            ExtentTestManager.getTest().assignCategory("Component: Featured Content Template");
        }

        LoginTC loginTC = new LoginTC(driver, 0);
        loginTC.LoginTest();

        AltraPO loginPage = new AltraPO(driver);

        //Step 1 :: Click on Content Editor
        Assert.assertEquals(loginPage.clickOnonContentEditor(), true, "Unable to click on Content Editor");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Content Editor", "System should open Content Editor");

        //Step 2 : Add Featured content
        Assert.assertTrue(loginPage.AddFeaturedContent(), "Unable to add featured content");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Add Featured Content in Sitecore", "User should be able to add Featured Content successfully.");

    }

    //TEST 7 : Upload file into Featured Content in Sitecore
    @Test
    public void UploadFileInFeaturedContent() throws Exception {
        if (flag > 0) {
            ExtentTestManager.getTest().getTest().setName("Test 7 : Upload file into Featured Content in Sitecore");
            ExtentTestManager.getTest().assignCategory("Component: Featured Content Template");
        }
        FeaturedContentTC featuredContentTC = new FeaturedContentTC(driver, 0);
        featuredContentTC.AddFeaturedContentTemplateInSiteCore();

        AltraPO loginPage = new AltraPO(driver);

        //Step 1 : Enter Feature Text and Upload file in Featured Content
        Assert.assertTrue(loginPage.UploadFileInFeatureContent(), "Unable to Enter Feature Text and Upload file in Featured Content");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Feature Text and Upload file in Featured Content", "User should be able to Upload file");

        //Step 3 :: Save and Publish
        Assert.assertTrue(loginPage.SaveAndPublish(), "Unable to Save and Publish.");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Save and Publish the tradeshow ", "Tradeshow Save and Published successfully.");

        //Step 4 :: Open altra url in new tab
        Assert.assertTrue(loginPage.openAltraApp(), "Unable to Open altra url in new tab");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Open Altra Application URL in New Tab", "System should open Altra Application url successfully.");

        //Step 5 :: Login into Altra app
        Assert.assertTrue(loginPage.loginAltraApp(), "Unable to Login into Altra app");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Login into Altra app with valid credentials", "User should be able to login successfully.");

        //Step 6 :: Verify Featured Content showing on Altra app
        Assert.assertTrue(loginPage.verifyFeatureContentOnAltraApp(), "Unable to  Verify Featured Content showing on Altra app");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Verify Featured Content showing on Altra app", "System should display added Featured Content and file on Altra application.");
    }

    //TEST 8 : Verify Download button and filepath on Altra app
    @Test
    public void verifyDownloadBtnFunctionality() throws Exception {
        if (flag > 0) {
            ExtentTestManager.getTest().getTest().setName("Test 8 : Verify Download button and filepath on Altra app");
            ExtentTestManager.getTest().assignCategory("Component: Featured Content Template");
        }
        FeaturedContentTC featuredContentTC = new FeaturedContentTC(driver, 0);
        featuredContentTC.UploadFileInFeaturedContent();

        AltraPO loginPO = new AltraPO(driver);

        //Step 1 :: Open altra url in new tab
        Assert.assertTrue(loginPO.openAltraApp(), "Unable to Open altra url in new tab");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Open Altra Application URL in New Tab", "System should open Altra Application url successfully.");

        //Step 2 :: Login into Altra app
        //Assert.assertTrue(loginPO.loginAltraApp(), "Unable to Login into Altra app");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Login into Altra app with valid credentials", "User should be able to login successfully.");

        //Step 3 :: Click on Download button and verify file path
        Assert.assertTrue(loginPO.clickOnDownloadBtnAndVerifyFilePath(), "Unable to Click on Download button and verify file path");
        ExtentTestManager.getTest().log(LogStatus.PASS,"Click on Download button and verify file path","Uploaded file path should match with the filepath mentioned in url");

    }


}

