package tobyspring.helloboot;

import org.springframework.web.bind.annotation.*;

import java.util.Objects;

//@RestController // 해당 어노테이션은 DispatcherServlet과 직접적인 연관은 없음
@RequestMapping("/hello")
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }


    @GetMapping
    @ResponseBody
//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(String name) {

        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
