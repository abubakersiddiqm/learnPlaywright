package basicLearning;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.awt.*;

public class BrowserActions {
    public static void main(String[] args) {
        //to maximize screen
        GraphicsDevice gd=GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        Playwright pw = Playwright.create();
        Browser brw = pw.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        Page page = brw.newPage();
        page.setViewportSize(width,height);
        page.navigate("http://leaftaps.com/opentaps");
        page.locator("#username").type("demosalesmanager");
        page.locator("#password").type("crmsfa");
        page.locator(".decorativeSubmit").click();
        page.locator("text='CRM/SFA'").click();
        page.click("text='Leads'");
        //browser page to go back
        page.goBack();
        //to get current option
        String getCurrentUrl = page.url();
        System.out.println("cureent url is "+getCurrentUrl);
        //page.pause();
        //browser page to go forward
        page.goForward();

        //page close
        //page.close();
        //browser close
        //brw.close();
        //playwright object at end close it to stop background process -recommanded
        pw.close();
    }
}
