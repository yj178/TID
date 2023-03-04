package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
public class JdbcTemplateTest {

    // 자동 구성에 의해 동작하는가?
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void insertAndQuery(){
        jdbcTemplate.update("insert into hello values(?, ?)", "YuJin", 3);
        jdbcTemplate.update("insert into hello values(?, ?)", "test", 2);

        Long count = jdbcTemplate.queryForObject("select count(*) from hello", Long.class);
        Assertions.assertThat(count).isEqualTo(2);

    }

    @Test
    void insertAndQuery2(){
        jdbcTemplate.update("insert into hello values(?, ?)", "YuJin2", 3);
        jdbcTemplate.update("insert into hello values(?, ?)", "test2", 2);

        Long count = jdbcTemplate.queryForObject("select count(*) from hello", Long.class);
        Assertions.assertThat(count).isEqualTo(2);

    }

}
