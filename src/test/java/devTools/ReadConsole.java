package devTools;

import com.microsoft.playwright.*;

public class ReadConsole {
    public static void main(String[] args) {
        Playwright pw = Playwright.create();
        Browser brw = pw.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        BrowserContext bwContext = brw.newContext();
        Page page = bwContext.newPage();
        //read console ,need to add listener on
        page.onConsoleMessage(handler->{
            System.out.println(handler.type());
            System.out.println(page.title());
        });

        page.navigate("https://www.redbus.in/");
        pw.close();
    }
}
