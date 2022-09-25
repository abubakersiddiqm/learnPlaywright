package basicLearning;

import com.microsoft.playwright.*;

import java.awt.*;
import java.nio.file.Paths;

public class LoginSkip {
    public static void main(String[] args) {
        //to maximize screen

        Playwright pw = Playwright.create();
        Browser brw = pw.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        //Browser content
        BrowserContext browserContext = brw.newContext();
        Page page = browserContext.newPage();
        //Store this login info to Storage in json
        page.navigate("http://leaftaps.com/opentaps");
        page.locator("#username").type("demosalesmanager");
        page.locator("#password").type("crmsfa");
        page.locator(".decorativeSubmit").click();
        //to store the login ingo in json using storagestate
        browserContext.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("LOGIN.json")));
    }
}
