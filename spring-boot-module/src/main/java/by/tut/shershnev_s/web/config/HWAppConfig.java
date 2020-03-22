package by.tut.shershnev_s.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"by.tut.shershnev_s.service", "by.tut.shershnev_s.repository", "by.tut.shershnev_s.web"})
public class HWAppConfig {
}
