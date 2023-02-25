package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


@Configuration
@ComponentScan // 해당 패키지부터 하위의 모든 패키지에 존재하는 모든 Component를 등록한다.
// 등록되는 Bean이 매우 많다면 번거로울 수 있다.
// 클래스를 모두 뒤져서 컴포넌트 붙은데 이것 저것 있으니까 확인하고자 할 때 모두 찾아보기 힘들다.
// 패키지와 모듈을 잘 활용해서 나누면 찾아보기 편하다.

public class HellobootApplication {

    @Bean
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    public static void main(String[] args) {
        MySpringApplication.run(HellobootApplication.class, args);
    }


}
