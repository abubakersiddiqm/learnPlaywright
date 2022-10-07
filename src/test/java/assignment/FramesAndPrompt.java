package assignment;

import com.microsoft.playwright.*;

public class FramesAndPrompt {
    public static void main(String[] args) {
        Playwright plw = Playwright.create();
        Browser bw = plw.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        BrowserContext brContext = bw.newContext();
        Page page = brContext.newPage();
        page.navigate("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_prompt");
        //Switching to frame
        FrameLocator parentframe = page.frameLocator("[name='iframeResult']");
        /*
         * click on the try it button alert prompt displays
         * 1. Handle to alert using page.onceDailog
         * 2. And Send name in the prompt and click ok
         * 3. Then vaild the message
         */
        page.onceDialog(alert->{alert.accept("message from prompt");});
        parentframe.locator("//button[text()='Try it']").click();
        String succesMessage = parentframe.locator("#demo").innerText();
        System.out.println(succesMessage);
        plw.close();

    }
}
