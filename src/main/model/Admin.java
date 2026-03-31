
package main.model;

public class Admin {

    private String name;
    private String password;

    public Admin(String name, String password) {

        this.name = name;
        this.password = password;
    }

    // Getter 和 Setter 方法

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }
}
