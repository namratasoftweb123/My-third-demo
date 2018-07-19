package org.AltraMotion.base;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.relevantcodes.extentreports.LogStatus;
import org.AltraMotion.base.extendreport.ExtentManager;
import org.AltraMotion.base.extendreport.ExtentTestManager;
import org.AltraMotion.base.extendreport.listner.AnnotationTransformer;
import org.AltraMotion.base.extendreport.listner.TestListener;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Listeners({AnnotationTransformer.class, TestListener.class})
public class BaseTest{

    public WebDriver driver = null;
    public static Client client = null;
    public static String projectId = null;
    public static String version = null;
    public static int cycleId = 0;
    public static String baseJiraURL = null;
    public static String jiraUserId = null;
    public static boolean jiraIntregration;
    public String jiraPropertiesFile = System.getProperty("user.dir") + "\\src\\main\\resources\\JiraConfig.properties";

    public BaseTest() {
    }

    @Parameters("updateOnJira")
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(@Optional("false") boolean updateOnJira)throws Exception{
        Properties prop;
        String jiraPassword;
        String environment;
        String testCycleNamePreFix;
        jiraIntregration = updateOnJira;
        if (jiraIntregration) {
            try {
                prop = LoadPropertiesFiles.loadProperties(jiraPropertiesFile);
                jiraUserId = prop.getProperty("UserName");
                jiraPassword = prop.getProperty("Password");
                projectId = prop.getProperty("ProjectId");
                version = prop.getProperty("VersionId");
                baseJiraURL = prop.getProperty("BaseJiraURL");
                environment = prop.getProperty("Environment");
                testCycleNamePreFix = prop.getProperty("TestCycleNamePreFix");
                String testCycleDes = prop.getProperty("TestCycleDes");
                Date dateobj = new Date();
                DateFormat df = new SimpleDateFormat("MMM_dd_yyyy HH:mm:ss");
                String startDate = (new SimpleDateFormat("dd/MMM/yyyy")).format(dateobj);
                String endDate = (new SimpleDateFormat("dd/MMM/yyyy")).format(dateobj);
                ClientConfig clientConfig = new ClientConfig();
                HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(jiraUserId, jiraPassword);
                clientConfig.register(feature);
                clientConfig.register(JacksonFeature.class);
                client = ClientBuilder.newClient(clientConfig);
                String cycleName = testCycleNamePreFix + "_" + df.format(dateobj);
                Entity payload = Entity.json("{  \"name\": \"" + cycleName + "\", \"environment\": \"" + environment + "\",  \"description\": \"" + testCycleDes + "\",  \"startDate\": \"" + startDate + "\",  \"endDate\": \"" + endDate + "\",  \"projectId\": \"" + projectId + "\",  \"versionId\": \"" + version + "\"}");
                Response response = client.target(baseJiraURL + "/rest/zapi/latest/cycle").request(new MediaType[]{MediaType.APPLICATION_JSON_TYPE}).post(payload);
                try {
                    JsonObject jsonObject2 = (new JsonParser()).parse("[" + (String)response.readEntity(String.class) + "]").getAsJsonArray().get(0).getAsJsonObject();
                    cycleId = jsonObject2.get("id").getAsInt();
                    System.out.println("cycleId  "+cycleId);
                } catch (Exception var22) {
                    cycleId = 0;
                }
            } catch (Exception var23) {
                throw var23;
            }
        }
    }

    @Parameters({"browserName", "url"})
    @BeforeMethod(alwaysRun = true)
    public void launchBrowser(ITestResult result, Method method, @Optional("chrome") String browserName, @Optional("https://accounts.google.com/signin") String url) throws Exception{
        this.getLocalDriver(browserName);
        this.driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
        this.driver.get(url);
        ExtentTestManager.startTest("" + method.getName());
        ExtentTestManager.getTest().log(LogStatus.INFO, "Open Browser and navigate to " + url, "Browser Name: chrome" );
    }

    @AfterMethod
    public void terminateBrowser(ITestResult result) throws Exception{
        byte testStatus;
        if (result.getStatus() == 2) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, result.getThrowable());
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed");
            testStatus = 2;
        } else if (result.getStatus() == 3) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
            testStatus = 3;
        } else {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
            testStatus = 1;
        }

        ExtentManager.getReporter().endTest(ExtentTestManager.getTest());
        ExtentManager.getReporter().flush();
        this.driver.quit();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Browser Closed");
        if (jiraIntregration){
            try {
                String methodId = result.getMethod().getMethodName().split("_")[1];
                Entity payloadE = Entity.json("{  \"cycleId\": \"" + cycleId + "\",  \"issueId\": \"" + methodId + "\",  \"projectId\": \"" + projectId + "\",  \"versionId\": \"" + version + "\",  \"assigneeType\": \"assignee\",  \"assignee\": \"" + jiraUserId + "\"}");
                Response responseE = client.target(baseJiraURL + "/rest/zapi/latest/execution").request(new MediaType[]{MediaType.APPLICATION_JSON_TYPE}).post(payloadE);
                String[] array = ((String)responseE.readEntity(String.class)).split(":");
                String resultRest = array[0].substring(2);
                resultRest = resultRest.substring(0, resultRest.length() - 1);
                Entity payload22 = Entity.json("{\"status\": \"" + testStatus + "\"}");
                Response var14 = client.target(baseJiraURL + "/rest/zapi/latest/execution/" + resultRest + "/execute").request(new MediaType[]{MediaType.APPLICATION_JSON_TYPE}).put(payload22);
            } catch (Exception var24) {
                System.out.println("Problem with JIRA Intregration");
            }
        }
    }

    public WebDriver getLocalDriver(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
            this.driver = new ChromeDriver();
            this.driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\driver\\geckodriver.exe");
            this.driver = new FirefoxDriver();
            this.driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
        }
        return this.driver;
    }
}
