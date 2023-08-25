package cm.practices.hello_spring.controller;

public class MemberForm {

    private String name; // private 라서 외부에서는 직접 접근할수없음

    public String getName() {
        return name;
    }

    // form태그의 input name으로 찾아서 들어감
    public void setName(String name) {
        this.name = name;
    }
}
