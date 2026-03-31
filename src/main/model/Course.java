package main.model;

public class Course {
    private static int nextCourseId = 1;

    private int id;
    private String name;
    private String teacher;

    public Course(String name, String teacher) {
        this.id = nextCourseId++;
        this.name = name;
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String view_cu_Inform() {
        return "课程{" +
                "课程id=" + id +
                ", 课程名称:'" + name + '\'' +
                ", 授课老师:" + teacher+ '\'' +
                '}';
    }//方便查看课程信息
}
