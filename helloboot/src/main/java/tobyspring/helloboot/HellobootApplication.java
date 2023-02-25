package tobyspring.helloboot;


import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;

public class HellobootApplication {

    public static void main(String[] args) {
        // Spring Container를 만드는 코드
        // dispatcher servlet은 웹 환경에서 사용하는 GenericWebApplicationContext로 변경해야함
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();

        applicationContext.registerBean(HelloController.class);
        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.refresh();

        // Servlet Container를 코드로 동작하면서 등록하는 작업
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            // 웹 어플리케이션
            servletContext.addServlet("dispatcherServlet", new DispatcherServlet(applicationContext) {


            }).addMapping("/*");// /밑으로 들어오는 모든 요청을 처리하겠다.
        });
        webServer.start();


    }

}
