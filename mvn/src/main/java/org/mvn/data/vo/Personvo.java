package org.mvn.data.vo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({"id","address","name","lastName","gender","enable"})
public class Personvo extends ResourceSupport implements Serializable {
    private static final long serialVersionUID=1L;

    @Mapping("id")
    @JsonProperty("id")
    private long key;
    private String name;

    @JsonProperty("last_name")
    private String lastName;
    private String address;

    @JsonIgnore
    private String gender;

    private boolean enable;

    public Personvo() {
    }

    public long getKey() {
        return key;
    }

    public void setKey(long id) {
        this.key = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Personvo personvo = (Personvo) o;
        return key == personvo.key &&
                enable == personvo.enable &&
                name.equals(personvo.name) &&
                lastName.equals(personvo.lastName) &&
                address.equals(personvo.address) &&
                gender.equals(personvo.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), key, name, lastName, address, gender, enable);
    }
}
