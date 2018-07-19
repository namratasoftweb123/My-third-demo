package org.AltraMotion.po;

import org.AltraMotion.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class AltraPO extends BaseTest {

    public String UserName = "";
    public String Password = "";

    public AltraPO(WebDriver driver) {
        this.driver = driver;
        UserName = System.getProperty("USER_ID");
        Password = System.getProperty("USER_PASS");

        if(UserName==null || Password==null) {
            // Read AppCredentials FROM Text File.
            try {
                String fileName = System.getProperty("user.dir") + "\\AppCredentials.txt";
                String line = null;
                String[] logData = new String[4];

                FileReader fileReader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                for (int i = 0; (line = bufferedReader.readLine()) != null; ++i) {
                    logData[i] = line;
                }
                UserName = logData[0];
                Password = logData[1];

                bufferedReader.close();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    //************************* Test Data *********************************************
    public String FeatureContentTxt = "Feature QA";
    public String FeatureText = "Feature Text QA";
    public String CorporateTxt = "QA";
    public String FilePath = "";

    //************************** Locators *******************************************

    public By login_username_input = By.xpath("//input[@name='UserName']");
    public By login_password_input = By.name("Password");
    public By Login_Btn = By.xpath("//input[@value='Log in']");
    public By ContentEditor_Btn = By.xpath("//a[@title='Content Editor']//descendant::img[@alt='Content Editor']");
    public By ContentMenu=By.xpath("//img[@id='Tree_Glyph_0DE95AE441AB4D019EB067441B7C2450']");
    public By CorporateArrowLink = By.xpath("//img[@id='Tree_Glyph_6A9E9331DA3F404F88101B6344FDE2D9']");
    public By AltramotionLink = By.xpath("//img[@id='Tree_Glyph_110D559FDEA542EA9C1C8A5DF7E70EF9']");
    public By TradeshowLink = By.xpath("//a[@id='Tree_Node_75C7F5CC6FED43658B267F344C0FB5E6']/span[contains(text(),'Tradeshow')]");
    public By InsertLink = By.xpath("//td[@class='scMenuItemCaption' and text()='Insert']");
    public By CorporateTemplateLink = By.xpath("//td[contains(text(),'Corporate Trade Show Template')]");
    public By CorporateNameTxtBox = By.xpath("//div[@class='scStretchAbsolute scDialogContentContainer']/descendant::input[@id='Value']");
    public By OkBtn = By.xpath("//div[@class='footerOkCancel']/button[@id='OK']");
    public By SaveBtn = By.xpath("//a[@title=\"Save any changes. (Ctrl+S)\"]/span[contains(text(),'Save')]");
    public By PublishMenuItem = By.xpath("//a[contains(text(),'Publish')]");
    public By PublishBtn = By.xpath("//a[contains(@title,'Publish the item')]");
    public By OkBtnForPublish = By.xpath("//div[@class='footerOkCancel']/button[@id='OK']");
    public By UsernameTxtbox = By.xpath("//input[@id='altraappcontentplaceholder_0_txtEmail']");
    public By PasswordTxtbox = By.xpath("//input[@id='altraappcontentplaceholder_0_txtPassword']");
    public By LoginBtn = By.xpath("//a[@id='altraappcontentplaceholder_0_lnkLogin']");
    public By TradeshowLink_Altra = By.xpath("//a[@title='Tradeshows']");
    public By ShowText = By.xpath("//table[@id='rt1']/descendant::a[@id='altraappcontentplaceholder_0_rptTradeShowTop_rptTradeShow_0_lnkTradeShow_0']");
    public By DateText = By.xpath("//table[@id='rt1']/descendant::span[@id='altraappcontentplaceholder_0_rptTradeShowTop_rptTradeShow_0_lblTradeShowDate_0']");
    public By DeleteBtn = By.xpath("//td[@class='scMenuItemCaption' and text()='Delete']");
    public By OkBtnForDelete = By.xpath("//div[@class='footerOkCancel']/button[@id='OK']");
    public By TradeshowMenuOpenLink = By.xpath("//a[@class='scContentTreeNodeNormal']/span[contains(text(),'Tradeshow')]/../../img");
    public By showNamesList = By.xpath("//table[@id='rt1']/descendant::a[contains(@id,'altraappcontentplaceholder_0_rptTradeShowTop_rptTradeShow_0_lnkTradeShow_')] ");
    public By AltraAppArrowLink = By.xpath("//img[@id='Tree_Glyph_96881D6A8C154A21984053576602E3F2']");
    public By SettingsArrowLink = By.xpath("//img[@id='Tree_Glyph_A427860A37D946B28507585A28E95ACD']");
    public By FeaturedContentTemplateLink = By.xpath("//div[@id='Popup2']/descendant::td[@class='scMenuItemCaption' and text()='Featured Content Template']");
    public By FeaturedContentLink = By.xpath("//span[contains(text(),'Featured Content')]");
    public By FeatureTextTxtbox = By.xpath("//div[@class='scEditorFieldLabel' and text()='FeatureText:']/following-sibling::div/input");
    public By OpenFileLink = By.xpath("//div[@class='scContentButtons']/a[contains(text(),'Open file')]");
    public By BrowseLink = By.xpath("//span[@id='Tabs_tab_1' and text()='Browse']");
    public By FileArrowLink = By.xpath("//span[text()='Files']/../../img");
    public By LiteratureArrowLink = By.xpath("//span[text()='Literature']/../../img");
    public By BrandArrowLink = By.xpath("//span[text()='Brand']/../../img");
    public By AltraMotionArrowLink = By.xpath("//span[text()='Altra Industrial Motion']/../../img");
    public By CertificatesArrowLink = By.xpath("//span[text()='Certificates']/../../img");
    public By FileLink = By.xpath("//span[text()='Certificates']/../../div/div/a");
    public By NowLink = By.xpath("//div[@class='scContentButtons']/a[contains(text(),'Now')]");
    public By FeatureContentTitle = By.xpath("//div[@id='altraappcontentplaceholder_0_Right_rptFeatureContent_pdfdiv2_0']/h3");
    public By DownloadLink = By.xpath("//div[@id='altraappcontentplaceholder_0_Right_rptFeatureContent_downloaddiv_0']/a[text()='Download']");
    public By FilenameTxtbox = By.xpath("//td[@class='scEditorFieldMarkerInputCell']/descendant::input");


    //************************** Methods *******************************************

    //Enter Username
    public boolean typeUsername(){
        try {
            //   String userNT = System.getProperty("USER_ID");
            WebElement username=(new WebDriverWait(driver,90))
                    .until(ExpectedConditions.visibilityOfElementLocated(login_username_input));
            username.sendKeys(UserName);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    //Enter Password
    public boolean typePassword(){
        try {
            WebElement password=(new WebDriverWait(driver,90))
                    .until(ExpectedConditions.visibilityOfElementLocated(login_password_input));
            password.sendKeys(Password);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    //Click Login Button
    public boolean clickSignInButton(){
        try {
            WebElement login=(new WebDriverWait(driver,90))
                    .until(ExpectedConditions.visibilityOfElementLocated(Login_Btn));
            login.click();
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    //Click Content Button
    public boolean clickOnonContentEditor(){
        try {
            WebElement ContentEditor=(new WebDriverWait(driver,90))
                    .until(ExpectedConditions.visibilityOfElementLocated(ContentEditor_Btn));
            ContentEditor.click();
            WebElement ContentMenU=(new WebDriverWait(driver,90))
                    .until(ExpectedConditions.visibilityOfElementLocated(ContentMenu));
            ContentMenU.click();
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    //Click on Corporate -> Add Tradeshow in site core
    public boolean AddTradeshow() throws Exception {
        try
        {
            driver.findElement(CorporateArrowLink).click();
            driver.findElement(AltramotionLink).click();
            Actions actions = new Actions(driver);
            actions.contextClick(driver.findElement(TradeshowLink));
            actions.build().perform();
            Thread.sleep(4000);
            CommonMethods.tryAgain(()-> {
                WebElement InsertLink1 = driver.findElement(InsertLink);
                InsertLink1.click();
               },5);
            driver.findElements(CorporateTemplateLink).get(1).click();

            switchToFrame();

            WebElement CoporateNameTxtbox = driver.findElement(CorporateNameTxtBox);
            CoporateNameTxtbox.sendKeys(CorporateTxt);
            System.out.println("Entered the text into textbox");
            driver.findElement(OkBtn).click();

            System.out.println("Added one tradeshow successfully.");
            driver.switchTo().defaultContent();
            return true;
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    //Click on Save and Publish
    public boolean SaveAndPublish() throws Exception
    {
        try {
            driver.findElement(SaveBtn).click();
            driver.findElement(PublishMenuItem).click();
            Thread.sleep(1000);
            driver.findElement(PublishBtn).click();

            switchToFrame();

            driver.findElement(OkBtnForPublish).click();
            Thread.sleep(1000);
            System.out.println("Clicked");

            switchToFrame();


            driver.findElement(OkBtnForPublish).click();
            driver.switchTo().defaultContent();
            return true;

        }
        catch (Exception e) {
        throw e;
        }
    }

    //Open Altra URl in new tab
    public boolean openAltraApp() throws Exception
    {
        try
        {
            driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(0));
            driver.get("http://192.168.3.61/");
            return true;
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    //Read Altra Credentials
    public boolean readAltraCredentials() throws Exception
    {
        try
        {
            this.driver = driver;
            UserName = System.getProperty("USER_ID");
            Password = System.getProperty("USER_PASS");

            if (UserName == null || Password == null) {
                // Read AppCredentials FROM Text File.
                try {
                    String fileName = System.getProperty("user.dir") + "\\AppCredentials_Altra.txt";
                    String line = null;
                    String[] logData = new String[4];

                    FileReader fileReader = new FileReader(fileName);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    for (int i = 0; (line = bufferedReader.readLine()) != null; ++i) {
                        logData[i] = line;
                    }
                    UserName = logData[0];
                    Password = logData[1];

                    bufferedReader.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            return true;
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    //Login into Altra app
    public boolean loginAltraApp() throws Exception
    {
        try {
            readAltraCredentials();
            WebElement username = (new WebDriverWait(driver, 90))
                    .until(ExpectedConditions.visibilityOfElementLocated(UsernameTxtbox));
            username.sendKeys(UserName);
            WebElement password = (new WebDriverWait(driver, 90))
                    .until(ExpectedConditions.visibilityOfElementLocated(PasswordTxtbox));
            password.sendKeys(Password);

            driver.findElement(LoginBtn).click();
            return true;
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    //Verify show and Dates in tradeshows
    public boolean verifyShowAndDates(String Showname) throws Exception
    {
        try {
            driver.findElement(TradeshowLink_Altra).click();
            String ActualShowValue = driver.findElement(ShowText).getText();
            String ActualDateValue = driver.findElement(DateText).getText();
            DateFormat originalFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
            //get current date time with Date()
            Date date = new Date();
            // Now format the date
            String ExpectedDateValue = originalFormat.format(date);
            String[] list1 = ExpectedDateValue.split(" ");
            System.out.println(list1[0].substring(0, 3));
            String Firstword = list1[0].substring(0, 3);
            Firstword.concat(" " +list1[1] +" "+ list1[2]);
            System.out.println(Firstword);
            ExpectedDateValue = Firstword.concat(" " + list1[1] + " " + list1[2]);
            System.out.println(ExpectedDateValue);
            if(ActualShowValue.contains(Showname) && ActualDateValue.contains(ExpectedDateValue))
            {
                return true;
            }
            else return false;

        }
        catch (Exception e)
        {
            throw e;
        }
    }

    // Edit Tradeshow in site core
    public boolean EditTradeshow() throws Exception {
        try {
            WebElement ShownameTxtbox= driver.findElement(By.xpath("//input[contains(@value,'"+ CorporateTxt + "')]"));
            ShownameTxtbox.clear();
            ShownameTxtbox.sendKeys(CorporateTxt + " Updated");
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    //Go to Altra App and Verify updated Tradeshow
    public boolean verifyUpdatedTradeshow() throws Exception
    {
        try
        {
            openAltraApp();
            loginAltraApp();
            verifyShowAndDates(CorporateTxt +" Updated");
            return true;
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    //Delete Tradeshow in sitecore
    public boolean DeleteTradeshow() throws Exception
    {
        try
        {
            //driver.findElement(TradeshowMenuOpenLink).click();
            //Thread.sleep(4000);
            String xpath = "//div[@class='scContentTreeNode']/descendant::span[contains(text(),'" + CorporateTxt + "')]";
            System.out.println(xpath);
            WebElement TradeshowListValue = driver.findElement(By.xpath("//div[@class='scContentTreeNode']/descendant::span[contains(text(),'"+ CorporateTxt +"')]"));
            Actions actions = new Actions(driver);
            actions.contextClick(TradeshowListValue);
            actions.build().perform();
            Thread.sleep(2000);
            driver.findElement(DeleteBtn).click();
           // driver.findElements(CorporateTemplateLink).get(1).click();

            switchToFrame();


            driver.findElement(OkBtnForDelete).click();
            return true;
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    //Go to Altra App and Verify deleted Tradeshow is not displaying
    public boolean verifyDeletedTradeshow() throws Exception
    {
        try
        {
            openAltraApp();
            loginAltraApp();
            driver.findElement(TradeshowLink_Altra).click();
            int j=0;
            List<WebElement> ShowList = driver.findElements(showNamesList);
            for(WebElement ele : ShowList)
            {
                if(ele.getText().equals(CorporateTxt))
                {
                    j++;
                    break;
                }
                else continue;
            }
            if(j==0)
            {
                return true;
            }
            else return false;
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    //Add Featured content template in sitecore
    public boolean AddFeaturedContent() throws Exception
    {
        try
        {
            Thread.sleep(4000);
            driver.findElement(AltraAppArrowLink).click();
            Thread.sleep(2000);
            driver.findElement(SettingsArrowLink).click();
            Thread.sleep(2000);
            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(FeaturedContentLink)).build().perform();
            actions.contextClick(driver.findElement(FeaturedContentLink)).build().perform();
            Thread.sleep(2000);

            List<WebElement> e = driver.findElements(InsertLink);
            System.out.println(e.size());
            CommonMethods.tryAgain(() -> {
                WebElement InsertLink1 = driver.findElement(InsertLink);
                InsertLink1.click();
            }, 5);
            driver.findElement(FeaturedContentTemplateLink).click();

            switchToFrame();

            WebElement FeatureNameTxtbox = driver.findElement(CorporateNameTxtBox);
            FeatureNameTxtbox.sendKeys(FeatureContentTxt);
            System.out.println("Entered the text into textbox");
            driver.findElement(OkBtn).click();

            return true;
        }catch (Exception e)
        {
            throw e;
        }
    }

    //Enter Feature Text and Upload file in Featured Content
    public boolean UploadFileInFeatureContent() throws Exception
    {
     try
     {
         driver.findElement(FeatureTextTxtbox).sendKeys(FeatureText);
         driver.findElement(OpenFileLink).click();
         switchToFrame();
         driver.findElement(BrowseLink).click();
         Thread.sleep(2000);
         driver.findElement(FileArrowLink).click();
         Thread.sleep(2000);
         driver.findElements(LiteratureArrowLink).get(0).click();
         Thread.sleep(2000);
         driver.findElements(BrandArrowLink).get(0).click();
         Thread.sleep(2000);
         driver.findElement(AltraMotionArrowLink).click();
         Thread.sleep(2000);
         driver.findElement(CertificatesArrowLink).click();
         Thread.sleep(2000);
         driver.findElement(FileLink).click();
         Thread.sleep(2000);
         driver.findElement(OkBtn).click();
         Thread.sleep(2000);
         System.out.println(driver.findElements(FilenameTxtbox).size());
         FilePath = driver.findElements(FilenameTxtbox).get(1).getAttribute("value");
         System.out.println("This is path : " + FilePath);
         driver.findElements(NowLink).get(0).click();
         return true;
     } catch (Exception e) {
         throw e;
     }
    }

    //Verify Featured Content showing on Altra app
    public boolean verifyFeatureContentOnAltraApp() throws Exception
    {
        try
        {
          String ActualTitle = driver.findElement(FeatureContentTitle).getText();
          if(ActualTitle.equals(FeatureText) && driver.findElement(DownloadLink).isDisplayed())
          {
              return true;
          }
          else return false;
        }
        catch (Exception e){
            throw e;
        }
    }

    //Switch to Frame
    public void switchToFrame() throws Exception {
        //Switch to the frame
        driver.switchTo().frame("jqueryModalDialogsFrame");
        System.out.println("switched to main frame");
        driver.switchTo().frame("scContentIframeId0");
        System.out.println("switched to subframe");

    }

    //Click on Download button and verify file path
    public boolean clickOnDownloadBtnAndVerifyFilePath() throws Exception
    {
        try
        {
            driver.findElement(DownloadLink).click();
            Thread.sleep(2000);
            String winHandleBefore = driver.getWindowHandle();

            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle);
            }
            String newTabURL = driver.getCurrentUrl();
            System.out.println(newTabURL);
            System.out.println(FilePath);
            if(newTabURL.contains(FilePath))
            {
                return true;
            }
            else return false;
        } catch (Exception e) {
            throw e;
        }
    }

}