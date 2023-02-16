package tobyspring.helloboot;


import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HellobootApplication {

    public static void main(String[] args) {
        // 설치하고 기본 설정 정보들이 있는데 스프링 부트가 이를 간단하게 만들어주는 클래스가 있다.
        // new Tomcat().start(); 요것만으로는 부족하다
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory(); // 일종의 도우미 클래스
        WebServer webServer = serverFactory.getWebServer(servletContext ->
                servletContext.addServlet("hello", new HttpServlet() {
                    @Override
                    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                        // 상태 라인에서 상태 코드
                        resp.setStatus(200);
                        // 헤드들을 만들고 그 중에서 바디 타입이 꼭 포함
                        resp.setHeader("Content-Type", "text/plain");
                        // 바디 내용
                        resp.getWriter().println("hello Servlet");
                    }
                }).addMapping("/hello")
        ); // 추상화를 했기에 TomcatServletWebServerFactory이 아니라 ServletWebServerFactory으로 받아도 문제 없음
        webServer.start(); //이걸 실행하면 톰캣 서블릿 컨테이너가 동작함

        // 서블릿을 컨테너에 추가하는 방법

    }

}
