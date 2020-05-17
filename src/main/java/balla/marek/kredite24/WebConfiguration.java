package balla.marek.kredite24;

import balla.marek.kredite24.security.LoggedUserFilter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {

    @Bean
    public FilterRegistrationBean<LoggedUserFilter> loggingFilter() {
        FilterRegistrationBean<LoggedUserFilter> registrationBean
                = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoggedUserFilter());
        registrationBean.addUrlPatterns("/task/books/*");
        return registrationBean;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
