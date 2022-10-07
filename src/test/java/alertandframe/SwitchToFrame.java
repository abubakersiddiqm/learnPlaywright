package alertandframe;

import com.microsoft.playwright.*;

public class SwitchToFrame {
    public static void main(String[] args) {
        Playwright pw = Playwright.create();
        Browser bw = pw.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        BrowserContext brw = bw.newContext();
        Page pg = brw.newPage();
        pg.navigate("https://www.leafground.com/frame.xhtml");
        //traverse to first frame
        FrameLocator iframe = pg.frameLocator("(//iframe)[1]");
        iframe.locator("//button[text()='Click Me']").click();
        String buttonText = iframe.locator("#Click").innerText();
        System.out.println("   FRAME 1 : "+buttonText);
        //tFrame 2

        FrameLocator countFrame = pg.frameLocator("(//iframe)[2]");
        String buttonName=countFrame.locator("#Click").innerText();
        System.out.println(buttonName);

        //handle nestd frame
        FrameLocator parent = pg.frameLocator("(//iframe)[3]");
        FrameLocator child = parent.frameLocator("[name='frame2']");
        child.locator("#Click").click();
        String nestframeText = child.locator("#Click").innerText();
        System.out.println("******NestedFrame******");
        System.out.println(nestframeText);

        pw.close();


    }
}
