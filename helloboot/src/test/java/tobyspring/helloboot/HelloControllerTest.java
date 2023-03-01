package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloControllerTest {
    @Test
    void helloController() {
        // 생성자에 주입하는 방법
        // 호출되는 구간을 간단하게 만들어서 주입가능 => 테스트 스텁?
        HelloController helloController = new HelloController(name -> name);
        String ret = helloController.hello("Test");
        Assertions.assertThat(ret).isEqualTo("Test");
    }


    @Test
    void failsHelloController() {
        HelloController helloController = new HelloController(name -> name);
        Assertions.assertThatThrownBy(() -> {
            helloController.hello(null);
        }).isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> {
            helloController.hello("");
        }).isInstanceOf(IllegalArgumentException.class);
    }


}
