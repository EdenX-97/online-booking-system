package net.obs.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;
import net.obs.enums.GenderEnum;

@Entity
@Table(name = "user")
public class User {
    public User() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Pattern(regexp = "^[a-z]{4,16}$")
    private String account;

    @NotNull
    private String password;

    @NotNull
    private String matchPassword;

    @NotNull
    @Pattern(regexp = "^[A-Za-z]{1,10}$")
    private String firstname;

    @NotNull
    @Pattern(regexp = "^[A-Za-z]{1,10}$")
    private String lastname;

    @Nullable
    @Max(value = 120)
    @Min(value = 1)
    private Integer age;

    @Nullable
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @NotNull
    @Pattern(
            regexp = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")
    private String email;

    @Nullable
    @Pattern(regexp = "^04[0-9]{8}$")
    private String phone;

    @Nullable
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Record> records;

    private String role = "ROLE_USER";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchPassword() {
        return matchPassword;
    }

    public void setMatchPassword(String matchPassword) {
        this.matchPassword = matchPassword;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Nullable
    public String getPhone() {
        return phone;
    }

    public void setPhone(@Nullable String phone) {
        this.phone = phone;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public String getRole() {
        return role;
    }
}
