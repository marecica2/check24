package balla.marek.kredite24;

import balla.marek.kredite24.security.LoggedUserFilter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Kredite24Application {

    public static void main(String[] args) {
        SpringApplication.run(Kredite24Application.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public FilterRegistrationBean<LoggedUserFilter> loggingFilter(){
        FilterRegistrationBean<LoggedUserFilter> registrationBean
                = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoggedUserFilter());
        registrationBean.addUrlPatterns("/task/books/*");
        return registrationBean;
    }
}
