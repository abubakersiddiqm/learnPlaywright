package dropdownAndAuth;

import com.microsoft.playwright.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.List;

public class SelectDropdown {

    static Playwright pw = Playwright.create();
    Browser bw = pw.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
    static Page page;

    @BeforeTest
    public void browser(){
        GraphicsDevice grEnv = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = grEnv.getDisplayMode().getWidth();
        int height = grEnv.getDisplayMode().getHeight();
        page = bw.newPage();
        page.setViewportSize(width, height);
        page.navigate("https://www.leafground.com/select.xhtml");
    }
   @Test
    public static void logicalWayToSelect(){
//to maximize using java Awt class
        //page.click("select.ui-selectonemenu:nth-child(1)");
        //to find the number of options available
        Locator options = page.locator("select.ui-selectonemenu>option");
        System.out.println(options.count());
        //select click the dropdown using list of element
        List<String> drptext = options.allInnerTexts();
        for (int i=0;i<drptext.size();i++) {
            String valueIs = drptext.get(i);
            System.out.println(valueIs);
            if (valueIs.equals("Cypress")){
                //page.click("select.ui-selectonemenu");
                page.type("select.ui-selectonemenu",valueIs);
                //page.click("select.ui-selectonemenu>option:nth-child("+i+")");
                break;
            }
        }
        pw.close();
    }
}
