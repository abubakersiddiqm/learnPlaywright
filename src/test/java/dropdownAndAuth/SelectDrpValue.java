package dropdownAndAuth;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;

public class SelectDrpValue {
    public static void main(String[] args) throws InterruptedException {
        Playwright pw = Playwright.create();
        Browser bw = pw.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        BrowserContext brw = bw.newContext();
        Page pg = brw.newPage();
        pg.navigate("https://the-internet.herokuapp.com/dropdown");
        Locator drpElement = pg.locator("select#dropdown");
        //select by value
        drpElement.selectOption("2");
        Thread.sleep(3000);
        //select by text
        drpElement.selectOption(new SelectOption().setLabel("Please select an option"));
    }
}
