package assignment;

import com.microsoft.playwright.*;

import java.awt.*;
import java.util.List;

public class LeafGround {
    public static void main(String[] args) {

        //to maximize using java Awt class
        GraphicsDevice grEnv = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = grEnv.getDisplayMode().getWidth();
        int height = grEnv.getDisplayMode().getHeight();


        Playwright pw = Playwright.create();
        Browser bw = pw.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        Page page = bw.newPage();
        page.setViewportSize(width,height);
        page.navigate("https://leafground.com/input.xhtml");
        List<ElementHandle> elementHandles = page.locator("//div[@class='grid formgrid']//input").elementHandles();
        //size of the element
        System.out.println(elementHandles.size());
        //Type username in Textfield
        elementHandles.get(0).type("Abu");
        //Type places and append with exting character
        elementHandles.get(1).type("tamilnadu");
        //verify textbox is disabled
        if (elementHandles.get(2).isDisabled()) {
            System.out.println("text field is disabled");
        }
        //clear the text field
        elementHandles.get(3).fill("");
        //get value from the text field
        String getAttribute = elementHandles.get(4).getAttribute("value");
        System.out.println(getAttribute);
        //Enter email id click tab confirm eleent is moved
        ElementHandle sendValueClickTab = elementHandles.get(5);
        sendValueClickTab.type("abu@gmail.com");
        page.keyboard().press("Tab");
        if(sendValueClickTab.getAttribute("class").
                equals("ui-inputfield ui-inputtext ui-widget ui-state-default ui-corner-all ui-state-filled")){
            System.out.println("cursor moved to textfield");}
        else {
            System.out.println("not moved");
        }
//Fill the describe field
        page.locator("//div[@class='grid formgrid']//textarea").type("I am not Good in programming ,and i am not good in testing");
        ElementHandle sendEnterValue = elementHandles.get(8);
        sendEnterValue.fill("");
        page.keyboard().press("Enter");
        String errorMsgText = page.locator("//div[@class='grid formgrid']//span[text()='Age is mandatory']").innerText();
        System.out.println(errorMsgText);
        pw.close();
    }
}
