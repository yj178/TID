package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@HellobootTest
//@Transactional
//@Rollback(value = false)
public class JdbcTemplateTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

//    @BeforeEach
//    void init(){
//        jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
//
//    }

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
