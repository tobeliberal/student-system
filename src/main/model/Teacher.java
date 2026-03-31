package main.model;
public class Teacher {
    private static int nextTeacherId = 1;
    private int id;
    private String name;
    private String password;
    private int age;
    private String className; // 所教授的班级
    private String phoneNumber; // 手机号
    private String courseInfo;  // 课程信息

    public Teacher( String name, String password, int age, String className, String phoneNumber, String courseInfo) {
        this.id = nextTeacherId++;
        this.name = name;
        this.password = password;
        this.age = age;
        this.className = className;
        this.phoneNumber = phoneNumber;
        this.courseInfo = courseInfo;
    }

    // Getter 和 Setter 方法
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public String getClassName() {
        return className;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCourseInfo() {
        return courseInfo;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCourseInfo(String courseInfo) {
        this.courseInfo = courseInfo;
    }
    public String view_tu_Inform() {
        return "老师{" +
                "老师id=" + id +
                ", 姓名:'" + name + '\'' +
                ", 年龄:" + age +
                ", 管理班级:'" + className + '\'' +
                ", 手机号:'" + phoneNumber + '\'' +
                ", 所教主要课程信息:'" + courseInfo + '\'' +
                '}';
    }//方便查询老师信息
}
