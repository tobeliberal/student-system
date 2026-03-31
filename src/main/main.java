package main;
import main.GUI.Login_GUI;
import main.model.Student;
import main.model.Teacher;
import main.model.Course;

public class main {
    public static void main(String[] args) {
        _System system = new _System();

        System.out.println("学生列表:");
        for (Student student : system.getStudents()) {
            System.out.println(student.view_stu_Inform());
        }

        System.out.println("教师列表:");
        for (Teacher teacher : system.getTeachers()) {
            System.out.println(teacher.view_tu_Inform());
        }
        System.out.println("课程列表:");
        for (Course course : system.getCourses()) {
            System.out.println(course.view_cu_Inform());
        }

        Login_GUI login = new Login_GUI(system);

    }
}
// 示例课程
////        ("复变函数", "张三");
////        ("线性代数", "李四");
////        ("计算机组成及原理", "王五");
////        ("形势与政策", "赵六");
////        ("高等化学", "钱七");
////        ("离散数学", "孙八");
