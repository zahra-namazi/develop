package ir.rahgozin.branch;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableAutoConfiguration
@ComponentScan(basePackages = "ir.rahgozin.branch")
@Configuration
@EnableJpaAuditing
public class BaseConfiguration {

    @Bean
    public MessageSource messageSourceBundle() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("i18n/bundle.*", "i18n/messages_*");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
    }
}