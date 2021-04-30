import org.openqa.selenium.remote.DesiredCapabilities;

public class PathCapability {

    public void setUp() throws Exception{
        DesiredCapabilities newcapabilities= new DesiredCapabilities();
        newcapabilities.setCapability("app","/Users/maksimkharmak/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

    }
}
