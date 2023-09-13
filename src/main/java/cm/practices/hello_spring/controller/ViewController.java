package cm.practices.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ViewController {

    @GetMapping("view-data")
    public String hello(Model model) {
        model.addAttribute("data", "컨트롤러에서 Modeal에 담아 보낸 data이다");
        return "view_data";
    }

    // 컨트롤러가 View를 반환하는 3가지 방식
    // 1. 정적 컨텐츠 static : 컨트롤러에 url이 없다면 static폴더에서 파일을 뒤져서 html 파일 자체를 반환 (컨트롤러가 정적 파일보다 우선순위가 높다)

    // 2. MVC와 템플릿 엔진 : viewResolver로 변환된 html를 반환하는 방식
    @GetMapping("view-mvc")
    // @RequestParam(value="name",required=false) 로 설정하면 파라미터 값이 없어도 에러나지않음 null 로 표기
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "view_template";
    }

    // 3. API : @ResponseBody를 사용하면 viewResolver를 사용하지 않고 HttpMessageConverter(String컨버터,Json컨버터..)가 동작하여 내용 그대로를 HTTP body에 반환
    @GetMapping("view-string")
    @ResponseBody // String 내용 그대로 HTTP body에 반환
    public String helloString(@RequestParam("name") String name) {
        return "<h1>hello " + name + "</h1>";
    }

    @GetMapping("view-api")
    @ResponseBody // 객체를 JSON으로 변환하여 HTTP body에 반환
    public HelloApi helloAPI(@RequestParam("name") String name) {
        HelloApi h = new HelloApi();
        h.setName(name);
        return h;
    }

    static class HelloApi {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
