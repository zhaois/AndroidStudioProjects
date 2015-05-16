package itisme.com.cn.serializeabletest.Test;

import java.io.Serializable;

/**
 * Created by zhao on 5/9/15.
 */
public class MyTestClass implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userName;
    private String password;
    private String age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
