package alertandframe;

import com.microsoft.playwright.*;

public class AlertMessage2 {
    public static void main(String[] args) {
        Playwright pw = Playwright.create();
        Browser bw = pw.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        BrowserContext brw = bw.newContext();
        Page pg = brw.newPage();
        pg.navigate("https://www.leafground.com/alert.xhtml");
        //In playwright Alert will be accept automatically
        pg.locator("(//span[text()='Show'])[1]").click();
        String AfterTheAlertMessage = pg.locator("//span[@id='simple_result']").innerText();
        System.out.println(AfterTheAlertMessage);

        /*
         * Basic way to Handle the alert Using listeners pg.onDialog()
         * Using the onDialog we can dismiss,get message from the alert popup
         * With we can handle multiple alerts
         */
        pg.onDialog(alert->
        {
            String getMessage = alert.message();
            System.out.println("popup message" +getMessage);
            //to cancel the alert
            alert.accept("abu");
        });
        System.out.println("****ALERT 3 prompt message to Send  the value in Alert****");
        pg.locator("(//span[text()='Show'])[5]").click();
        String alert3SuccessMessage = pg.locator("//span[@id='confirm_result']").innerText();
        System.out.println(alert3SuccessMessage);
        pw.close();
    }
}
