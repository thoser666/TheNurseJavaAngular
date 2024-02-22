package biz.brumm.thenursejavaangular.config;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig {

  @NotNull private String url;

  @NotNull
  @Value(value = "${app.front.url:#{null}}")
  private String frontUrl;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getFrontUrl() {
    return frontUrl;
  }

  public void setFrontUrl(String frontUrl) {
    this.frontUrl = frontUrl;
  }
}
