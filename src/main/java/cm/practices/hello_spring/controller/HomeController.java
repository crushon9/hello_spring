package cm.practices.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") /* 루트(/) 기호로 처음 호출되는 컨트롤러 설정하지 않으면 index파일을 찾아서 반환 */
    public String home() {
        return "home"; /* home.html 파일을 반환 */
    }
}
