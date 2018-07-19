package org.AltraMotion.po;

import org.AltraMotion.base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

class CommonMethods extends BaseTest {


    //click by JS
    public void clickByJS(WebElement el) throws Exception {
        String tag = el.getTagName();
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", el);
        } catch (Exception e) {
            throw new Exception("WebAction -> clickByJS(WebElement el) - Error in clicking on the element <%s>: %s" + tag + e.getMessage());
        }
    }

    //Try Again method
    public static void tryAgain(Method method, int tryCount) throws Exception {
        int count = 0;
        while (true) {
            try {
                method.method();
                break;
            } catch (Exception e) {
                Thread.sleep(2000);
                count++;
                if (count == tryCount) {
                    count = 0;
                    throw new Exception(e.getLocalizedMessage());
                }
            }
        }
    }


}
