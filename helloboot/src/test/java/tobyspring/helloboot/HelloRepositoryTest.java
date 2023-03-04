package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class HelloRepositoryTest {
    @Autowired
    HelloRepository helloRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Test
    void findHelloFailed(){
        Assertions.assertThat(helloRepository.findHello("Yujin")).isNull();
    }

    @Test
    void increaseCount(){
        Assertions.assertThat(helloRepository.countOf("Yujin")).isEqualTo(0);
        helloRepository.increaseCount("Yujin");
        Assertions.assertThat(helloRepository.countOf("Yujin")).isEqualTo(1);
        helloRepository.increaseCount("Yujin");
        Assertions.assertThat(helloRepository.countOf("Yujin")).isEqualTo(2);
    }
}
