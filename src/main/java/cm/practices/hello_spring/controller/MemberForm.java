package cm.practices.hello_spring.controller;

public class MemberForm {

    private String name; // private 라서 외부에서는 직접 접근할수없음

    public String getName() {
        return name;
    }

    // setName에서 set빼고 첫글자를 소문자로 바꾼 결과(=name)이 input name="name"으로 찾아서 들어감
    public void setName(String name) {
        this.name = name;
    }
}
