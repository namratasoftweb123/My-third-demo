package org.AltraMotion.ts;

import com.relevantcodes.extentreports.LogStatus;
import org.AltraMotion.base.BaseTest;
import org.AltraMotion.base.extendreport.ExtentTestManager;
import org.AltraMotion.po.AltraPO;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTC extends BaseTest {

    public int flag = 0;

    public LoginTC(){}

    public LoginTC(WebDriver passDriver, int Flag) {
        driver = passDriver;
        flag = Flag;
    }

    //TEST-1: Verify user should be able to login using valid credential.
    @Test
    public void LoginTest(){
        if(flag > 0) {
            ExtentTestManager.getTest().getTest().setName("TEST-1: Verify user should be able to login using valid credential.");
            ExtentTestManager.getTest().assignCategory("Component: Login Test");
        }
        AltraPO loginPage=new AltraPO(driver);

        //Step 1 :: Enter valid Username
        Assert.assertEquals(loginPage.typeUsername(), true,"User not able to enter User Name");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Enter valid Username", "Username should be entered.");

        //Step 2 :: Click next
        Assert.assertEquals(loginPage.typePassword(), true,"User not able to enter Password");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Next", "Next should be clicked.");

        //Step 3 :: Enter valid Password
        Assert.assertEquals(loginPage.clickSignInButton(), true,"Opss!! unable to click on Login In button");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Enter valid Password", "Password should be entered.");

    }


}