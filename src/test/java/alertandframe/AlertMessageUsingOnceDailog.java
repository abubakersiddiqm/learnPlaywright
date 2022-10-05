package alertandframe;

import com.microsoft.playwright.*;

public class AlertMessageUsingOnceDailog {
    public static void main(String[] args) {
        Playwright pw = Playwright.create();
        Browser bw = pw.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        BrowserContext brw = bw.newContext();
        Page pg = brw.newPage();
        pg.navigate("https://www.leafground.com/alert.xhtml");

        /*
         * TO handle the different alert we have use onceDialog()
         * this will handle the different alert and action
         *
         */
        pg.onceDialog(alert->
        {
            String getMessage = alert.message();
            System.out.println("popup message" +getMessage);
            //to cancel the alert
            alert.dismiss();
        });
        System.out.println("****ALERT 1****");
        pg.locator("(//span[text()='Show'])[1]").click();
        String AfterTheAlertMessage = pg.locator("//span[@id='simple_result']").innerText();
        System.out.println(AfterTheAlertMessage);

        pg.onceDialog(alert->
        {
            String getMessage = alert.message();
            System.out.println("popup message" +getMessage);
            //to cancel the alert
            alert.accept();
        });
        System.out.println("****ALERT 2****");
        pg.locator("(//span[text()='Show'])[2]").click();
        String alert2SuccessMessage = pg.locator("//span[@id='result']").innerText();
        System.out.println(alert2SuccessMessage);
        pw.close();
    }
}
