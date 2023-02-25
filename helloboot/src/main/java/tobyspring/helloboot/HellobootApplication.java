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

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;

public class HellobootApplication {

    public static void main(String[] args) {
        // 스프링 컨테이너 만들기
        // 애플리케이션을 구성하고 있는 많은 작업들을 구현하는 부분을
        // 코드로 쉽게 만들 수 있게 도와주는 클래스
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        // 오브젝트를 직접 만들어서 넣는것도 가능하지만 어떤 클래스를 활용해서 bean 오브젝트를 만들것인지 등록한다.
        // 클래스 정보만 넘김
        applicationContext.registerBean(HelloController.class); // 등록 완료
        applicationContext.refresh(); // bean 오브젝트를 전부 생성해줌


        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> servletContext.addServlet("frontcontroller", new HttpServlet() {

                    @Override
                    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


                        if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
                            String name = req.getParameter("name");

                            // 이름을 직접 지정할 수도 있고, 클래스 타입을 지정할 수도 있다.
                            HelloController helloController = applicationContext.getBean(HelloController.class);
                            String ret = helloController.hello(name);

                            resp.setStatus(HttpStatus.OK.value());
//                            resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                            resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
                            resp.getWriter().println(ret); // 받은 쿼리 파라미터를 이용하여 출력함
                        } else {
                            resp.setStatus(HttpStatus.NOT_FOUND.value());
                            resp.getWriter().println("NOT FOUND");
                        }
                    }
                }).addMapping("/*") // /밑으로 들어오는 모든 요청을 처리하겠다.
        );
        webServer.start();


    }

}
