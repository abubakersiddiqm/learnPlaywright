package assignment;

import com.microsoft.playwright.*;

public class OpenTabs {

    public static void main(String[] args) {
        Playwright pw = Playwright.create();
        Browser brw = pw.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        //Browser content
        BrowserContext browserContext = brw.newContext();
        Page page = browserContext.newPage();
        //Store this login info to Storage in json
        page.navigate("http://leaftaps.com/opentaps");
        page.locator("#username").type("demosalesmanager");
        page.locator("#password").type("crmsfa");
        page.locator(".decorativeSubmit").click();
        page.locator("text='CRM/SFA'").click();
        page.locator("text='Leads'").click();
        //click create lead and Create new leads
        page.click("//a[contains(text(),'Create Lead')]");
        page.type("#createLeadForm_companyName", "SUMMA");
        page.type("#createLeadForm_firstName", "abu");
        page.type("#createLeadForm_lastName", "abm");
        page.click("[name=submitButton]");
        //View page get the id from the label
        String innerTextFromView = page.locator("#viewLead_companyName_sp").innerText();

        int start = innerTextFromView.indexOf("(");
        String findLeadsNumber=innerTextFromView.substring(start + 1, innerTextFromView.length() - 1).trim();
        System.out.println(findLeadsNumber);

        //Click on the findleads
        page.locator("//a[contains(text(),'Find Leads')]").click();
        page.type("[name='id']",findLeadsNumber);
        page.locator("//button[contains(text(),'Find Leads')]").click();
        String textFromTable = page.locator("(//table[@class='x-grid3-row-table']//tr//td)[1]/div/a").innerText();
        if (findLeadsNumber.equals(textFromTable)){
            System.out.println("leads matched with createlead  "+innerTextFromView+" findleads "+textFromTable);
        }
        //merge leads-merge the created lead with any other leads
        page.click("//a[contains(text(),'Merge Leads')]");
        page.click("(//table[@class='twoColumnForm']//tr//td//a)[1]");
        Page windowPage = browserContext.waitForPage(() -> {
            page.locator("//span[text()='Find Leads']").isVisible();
        });
        Locator elementFromtabel = windowPage.locator("(//table[@class='x-grid3-row-table']//tr//td)[1]/div/a");
        String textFromTableWindows=elementFromtabel.innerText();
        System.out.println(": : value taken from new window : : "+textFromTableWindows);
        windowPage.type("[name=id]",findLeadsNumber);
        windowPage.click("//button[contains(text(),'Find Leads')]");
        elementFromtabel.click();
     /*   Boolean isValuePresent=page.getAttribute("(//table[@class='twoColumnForm']//tr//td//input)[1]","value").equalsIgnoreCase(findLeadsNumber);
        if (isValuePresent==true){
            System.out.println("value is present in textfield"+isValuePresent);
        }
        else {
            System.out.println("value is present in textfield"+isValuePresent);
        }*/

        /*page.click("(//table[@class='twoColumnForm']//tr//td//a)[2]");*/
        page.type("(//table[@class='twoColumnForm']//tr//td//input)[2]",textFromTable);
        //Click merge button confirm alert is displayed handle with  oncedailog

        page.onceDialog(alert->{
           String alertMess= alert.message();
           System.out.println("alert message text :  " +alertMess);
            alert.accept();
        });

        page.click("a.buttonDangerous");

    }

}
