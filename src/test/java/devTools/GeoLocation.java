package devTools;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;

import java.lang.reflect.Array;
import java.util.Arrays;

public class GeoLocation {
    public static void main(String[] args) {
        Playwright pw = Playwright.create();
        Browser brw = pw.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
        //to set geolocation ,need to set from the newcontext where it will set fron the browser config
        BrowserContext bwContext = brw.newContext(new Browser.NewContextOptions()
                .setGeolocation(40.741895,-73.989308)
                .setPermissions(Arrays.asList("geolocation"))

        );

        Page page = bwContext.newPage();
        page.navigate("https://www.gps-coordinates.net/my-location");
        String locationPlace = page.locator("span#addr").innerText();
        //wait for the network to load and get the value from the element
        page.waitForLoadState(LoadState.NETWORKIDLE);
        System.out.println(locationPlace);


    }
}
