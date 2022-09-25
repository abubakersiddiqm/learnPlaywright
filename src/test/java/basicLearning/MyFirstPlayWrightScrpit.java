package basicLearning;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class MyFirstPlayWrightScrpit {
    public static void main(String[] args) {
        Playwright pw = Playwright.create();
        Browser brw = pw.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        Page page = brw.newPage();
        page.navigate("http://leaftaps.com/opentaps");
        page.locator("#username").type("demosalesmanager");
        page.locator("#password").type("crmsfa");
        page.locator(".decorativeSubmit").click();
        page.locator("text='CRM/SFA'").click();
        page.locator("text='Leads'").click();
        page.locator("//a[contains(text(),'Find Leads')]").click();
        page.locator("(//input[@name='firstName'])[3]").type("babu");
        page.locator("//button[contains(text(),'Find Leads')]").click();
        String textFromTable = page.locator("(//table[@class='x-grid3-row-table']//tr//td)[1]/div/a").innerText();
        System.out.println("table value "+textFromTable);
        page.locator("(//table[@class='x-grid3-row-table']//tr//td)[1]/div/a").click();
        String pageTitle = page.title();
        System.out.println(pageTitle);
        if (pageTitle.contains("View Lead")) {
            page.locator("text='Delete'").click();
            page.locator("//a[contains(text(),'Find Leads')]").click();
            page.locator("[name='id']").type(textFromTable);
            page.locator("(//table[@class='x-btn-wrap x-btn '])[2]//td//button").click();
            String resultSearch = page.locator("//div[contains(text(),'No records to display')]").innerText();
            System.out.println(resultSearch);
        }
        //page close
        //page.close();
        //browser close
        //brw.close();
        //playwright object at end close it to stop background process -recommanded
        pw.close();
    }
}
