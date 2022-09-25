package basicLearning;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.awt.*;

public class Browserauthentication {
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

        //playwright object at end close it to stop background process -recommanded
        pw.close();
    }
}
