package tobyspring.helloboot;


import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;

public class HellobootApplication {

    public static void main(String[] args) {
        // 설치하고 기본 설정 정보들이 있는데 스프링 부트가 이를 간단하게 만들어주는 클래스가 있다.
        // new Tomcat().start(); 요것만으로는 부족하다
        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory(); // 일종의 도우미 클래스
        WebServer webServer = serverFactory.getWebServer(); // 추상화를 했기에 TomcatServletWebServerFactory이 아니라 ServletWebServerFactory으로 받아도 문제 없음
        webServer.start(); //이걸 실행하면 톰캣 서블릿 컨테이너가 동작함

    }

}
