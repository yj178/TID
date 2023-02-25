package tobyspring.helloboot;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

//@RestController // 해당 어노테이션은 DispatcherServlet과 직접적인 연관은 없음
@MyComponent
@RequestMapping("/hello")
public class HelloController {
    private final HelloService helloService;
    private final ApplicationContext applicationContext; // final로 만들면 안되는 이유 생성자 완료된 이후에 호출되기 때문에

    public HelloController(HelloService helloService, ApplicationContext applicationContext) {
        this.helloService = helloService;
        this.applicationContext = applicationContext;
    }


    @GetMapping
    @ResponseBody
//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(String name) {

        return helloService.sayHello(Objects.requireNonNull(name));
    }


}
