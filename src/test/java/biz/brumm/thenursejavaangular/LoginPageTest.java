package biz.brumm.thenursejavaangular;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.Testcontainers;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.junit.jupiter.Container;

// page_url = about:blank
@org.testcontainers.junit.jupiter.Testcontainers
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = LoginPageTest.Initializer.class)
class LoginPageTest {
  @LocalServerPort private int port;

  @Container
  public BrowserWebDriverContainer chrome =
      new BrowserWebDriverContainer()
          .withCapabilities(new ChromeOptions())
          .withRecordingMode(
              BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL, new File("build"));

  LoginPageTest(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  @Test
  void callLoginPageTest() {
    RemoteWebDriver driver = new RemoteWebDriver(chrome.getSeleniumAddress(), new ChromeOptions());
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    driver.get("http://host.testcontainers.internal:" + port + "/login.component.html");
    List<WebElement> hElement = driver.findElements(By.tagName("username"));

    assertThat(hElement).as("The username is found").isNotEmpty();
  }

  static class Initializer
      implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
      applicationContext.addApplicationListener(
          (ApplicationListener<WebServerInitializedEvent>)
              event -> {
                Testcontainers.exposeHostPorts(event.getWebServer().getPort());
              });
    }
  }
}
