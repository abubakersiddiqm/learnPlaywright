package assignment;

import com.microsoft.playwright.*;

public class InteractWithBasicAuth {
    public static void main(String[] args) {
        Playwright pw = Playwright.create();
        Browser ch = pw.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        BrowserContext bc = ch.newContext(new Browser.NewContextOptions().setHttpCredentials("admin","testleaf"));
        Page page = bc.newPage();
        page.navigate("http://leafground.com:8090/login");
        String valuefromAuth = page.innerHTML("//body");
        System.out.println(valuefromAuth);
        pw.close();


    }
}
