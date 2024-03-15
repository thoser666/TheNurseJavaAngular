import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

// page_url = about:blank
public class LoginPage {
  public LoginPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }
}
