package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class HelloApiTest {
    @Test
    void helloApi() {
        // http localhost:8080?name=Spring
        // HTTPie HTT파이가 정식 명칭
        // RestTemplate는 테스트 목적으로 사용하기에는 부족한 것이 있음 (400,500일 경우 예외를 던짐)
        // TestRestTemplate를 사용하면 400,500에서도 확인 가능함
        //
        TestRestTemplate rest = new TestRestTemplate();
        // 매개변수 url, response Type, url variable
        // 파라미터를 바꿔서 확인하고 싶은 경우 {} placeholder 사용
        // 타입 바디를 사용해서 확인하기
        // ResponseEntity타입은 response body 타입에 따라 String 혹은 DTO등 클래스를 활용하기도 함
        ResponseEntity<String> res = rest.getForEntity("http://localhost:9090/app/hello?name={name}", String.class, "Spring");

        // status code 200
        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK); // 틀리면 예외발생하면서 종료
        // header(content-type) text/plain
//        Assertions.assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).isEqualTo(MediaType.TEXT_PLAIN_VALUE); // "text/plain;charset=ISO-8859-1"와 같이 인코딩 정보까지 확인함
        Assertions.assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE); // 헤더의 시작값 비교
        // body Hello Spring
        Assertions.assertThat(res.getBody()).isEqualTo("*Hello Spring*");
    }

    @Test
    void failsHelloApi() {
        TestRestTemplate rest = new TestRestTemplate();
        ResponseEntity<String> res = rest.getForEntity("http://localhost:9090/app/hello?name=", String.class);

        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR); // 틀리면 예외발생하면서 종료
    }
}
