package tobyspring.helloboot;


import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HellobootApplication {

    public static void main(String[] args) {
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> servletContext.addServlet("frontcontroller", new HttpServlet() {
                    HelloController helloController = new HelloController();

                    @Override
                    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                        // 모든 요청이 들어온다.
                        // 인증, 보안, 다국어 처리, 공통 기능들을 앞에서 한다면?
                        // 이후에는 무엇을 해야하나? -> 서블릿 컨트롤러의 매핑기능을 프론트 컨트롤러가 대신한다.
                        // 매칭은 요청을 가지고 처리한다. 메소드, path, url, header, body 그 중 대표는 url

                        if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
                            String name = req.getParameter("name");
                            String ret = helloController.hello(name);

                            resp.setStatus(HttpStatus.OK.value());
                            resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                            resp.getWriter().println(ret); // 받은 쿼리 파라미터를 이용하여 출력함
                        } else if (req.getRequestURI().equals("/user")) {
                            resp.setStatus(HttpStatus.NOT_FOUND.value());
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
