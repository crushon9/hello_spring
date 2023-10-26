package cm.practices.hello_spring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // jap가 ORM(Object Relation Maping)으로 관리하는 annotation
public class Member {

    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //insert시 DB 자동생성
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
