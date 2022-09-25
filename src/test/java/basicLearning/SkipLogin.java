package basicLearning;

import com.microsoft.playwright.*;

import java.awt.*;
import java.nio.file.Paths;

public class SkipLogin {
    public static void main(String[] args) {

        Playwright pw = Playwright.create();
        Browser brw = pw.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        //context get the login from the json using setStorageStatePath
        BrowserContext browserContext = brw.newContext(new Browser.NewContextOptions().
                setStorageStatePath(Paths.get("LOGIN.json")));
        Page page = browserContext.newPage();
        page.navigate("http://leaftaps.com/opentaps");
        page.locator("text='CRM/SFA'").click();
        page.locator("text='Leads'").click();
        page.locator("//a[contains(text(),'Find Leads')]").click();
        page.locator("(//input[@name='firstName'])[3]").type("babu");
        page.locator("//button[contains(text(),'Find Leads')]").click();
        String textFromTable = page.locator("(//table[@class='x-grid3-row-table']//tr//td)[1]/div/a").innerText();
        System.out.println("table value "+textFromTable);


        //playwright object at end close it to stop background process -recommanded
        pw.close();
    }
}
