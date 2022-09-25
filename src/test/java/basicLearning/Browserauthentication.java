package basicLearning;

import com.microsoft.playwright.*;

import java.awt.*;

public class Browserauthentication {
    public static void main(String[] args) {
        //to maximize screen
        GraphicsDevice gd=GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        Playwright pw = Playwright.create();
        Browser brw = pw.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        //context
        BrowserContext browserContext = brw.newContext(new Browser.NewContextOptions().setHttpCredentials("admin","admin"));
        Page page = browserContext.newPage();
        page.setViewportSize(width,height);
        page.navigate("https://the-internet.herokuapp.com/basic_auth");
        String ConfirmAuth = page.locator("div.example>h3").innerText();
        System.out.println(ConfirmAuth);

        //playwright object at end close it to stop background process -recommanded
        pw.close();
    }
}
