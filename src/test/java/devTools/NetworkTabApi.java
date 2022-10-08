package devTools;

import com.microsoft.playwright.*;

public class NetworkTabApi {
    public static void main(String[] args) {
        Playwright pw = Playwright.create();
        Browser brw = pw.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        BrowserContext bwContext = brw.newContext();
        Page page = bwContext.newPage();
        /*
         * to get request from the network ,need to use onRequest()
         * to get response from the network ,need to use onResponse()
         */
        page.onRequest(handler->{
            String method = handler.method();
            if(method.equalsIgnoreCase("post")||method.equalsIgnoreCase("delete")){
                System.out.println(method);
                String postdata=handler.postData();
                System.out.println(postdata);
            }
        });
        //response
        page.onResponse(handler->{
            int statuscode = handler.status();
            System.out.println(" response "+statuscode);
        });
        page.navigate("http://leaftaps.com/opentaps");
        page.locator("#username").type("demosalesmanager");
        page.locator("#password").type("crmsfa");
        page.locator(".decorativeSubmit").click();
        page.locator("text='CRM/SFA'").click();
        page.locator("text='Leads'").click();
        //click create lead and Create new leads
        page.click("//a[contains(text(),'Create Lead')]");
        page.type("#createLeadForm_companyName", "SUMMA1");
        page.type("#createLeadForm_firstName", "abu1");
        page.type("#createLeadForm_lastName", "abm1");
        page.click("[name=submitButton]");
        page.locator("text='Delete'").click();
        pw.close();
    }

}
