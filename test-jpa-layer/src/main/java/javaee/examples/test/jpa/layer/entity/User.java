package javaee.examples.test.jpa.layer.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "User.select.adults", query = "select u from User as u where u.age > 17")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    private String name;
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, age, name);
    }

    @Override
    public boolean equals(Object obj) {
        if ( ! (obj instanceof User)) {
            return false;
        }
        User param = (User) obj;
        return Objects.equals(id,   param.id)
            && Objects.equals(name, param.name)
            && Objects.equals(age,  param.age);
    }

    
}
