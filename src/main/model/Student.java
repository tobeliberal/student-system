package main.model;
import java.util.ArrayList;
public class Student {
    private static int nextStudentId = 1;
    private int id;
    private String name;
    private String password;
    private int age;
    private String className;  // 班级
    private String phoneNumber; // 手机号
    private String grade1, grade2, grade3;
    private String change;  // 申请修改
    private ArrayList<Course> courses;
    public Student(String name, String password, int age, String className, String phoneNumber, String grade1, String grade2, String grade3,String change, ArrayList<Course> courses) {
        this.id = nextStudentId++;
        this.name = name;
        this.password = password;
        this.age = age;
        this.className = className;
        this.phoneNumber = phoneNumber;
        this.grade1 = grade1;
        this.grade2 = grade2;
        this.grade3 = grade3;
        this.change = change != null ? change : "无";
        this.courses = courses != null ? courses : new ArrayList<Course>();
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

    public String getGrade1() {
        return grade1;
    }

    public String getGrade2() {
        return grade2;
    }

    public String getGrade3() {
        return grade3;
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

    public void setGrade1(String grade1) {
        this.grade1 = grade1;
    }

    public void setGrade2(String grade2) {
        this.grade2 = grade2;
    }

    public void setGrade3(String grade3) {
        this.grade3 = grade3;
    }

    public void setCourse(ArrayList<Course> courses) {
        this.courses=courses;
    }

    public void setChange(String change) {
        this.change = change;
    }
    public String getChange() {
        return change;
    }
    // 获取选课信息
    public ArrayList<Course> getCourses() {
        return courses;
    }
    // 添加课程
    public void addCourse(Course course) {
        courses.add(course);
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public String view_stu_Inform() {
        return "学生{" +
                "学生id=" + id +
                ", 姓名:'" + name + '\'' +
                ", 年龄:" + age +
                ", 班级:'" + className + '\'' +
                ", 手机号:'" + phoneNumber + '\'' +
                ", 高数成绩:'" + grade1 + '\'' +
                ", 大英成绩:'" + grade2 + '\'' +
                ", java成绩:'" + grade3 + '\'' +
                ", 选课信息:" + courses +
                '}';
    }//方便查看学生信息
}
