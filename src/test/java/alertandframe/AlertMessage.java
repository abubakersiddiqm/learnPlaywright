package alertandframe;

import com.microsoft.playwright.*;

public class AlertMessage {
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
            alert.dismiss();
        });
        System.out.println("****ALERT 1****");
        pg.locator("(//span[text()='Show'])[1]").click();
        System.out.println("****ALERT 2****");
        pg.locator("(//span[text()='Show'])[2]").click();
        String alert2SuccessMessage = pg.locator("//span[@id='result']").innerText();
        System.out.println(alert2SuccessMessage);
        pw.close();
    }
}
