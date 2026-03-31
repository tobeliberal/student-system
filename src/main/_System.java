package main;

import main.model.Admin;
import main.model.Student;
import main.model.Teacher;
import main.model.Course;
import java.util.ArrayList;
import java.sql.*;

public class _System {
    private ArrayList<Student> students;
    private ArrayList<Teacher> teachers;
    private ArrayList<Course> courses;
    private Connection conn;

    public _System() {
        students = new ArrayList<>();
        teachers = new ArrayList<>();
        courses = new ArrayList<>();
        initializeDatabase();
        loadStudents();
        loadTeachers();
        loadCourses();
    }

    // 初始化数据库连接
    private void initializeDatabase() {
        try {
            // 注册 JDBC 驱动
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:_student_system.db");
            System.out.println("数据库连接成功.");
            createTables();
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("数据库连接失败.");
            e.printStackTrace();
        }
    }

    // 创建表
    private void createTables() {
        String createStudentTable = "CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, password TEXT, age INTEGER, className TEXT, phoneNumber TEXT, grade1 TEXT, grade2 TEXT, grade3 TEXT)";
        String createTeacherTable = "CREATE TABLE IF NOT EXISTS teachers (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, password TEXT, age INTEGER, className TEXT, phoneNumber TEXT, courseInfo TEXT)";
        String createCourseTable = "CREATE TABLE IF NOT EXISTS courses (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, teacher TEXT)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createStudentTable);
            stmt.execute(createTeacherTable);
            stmt.execute(createCourseTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 注册学生
    public void registerStudent(String name, String password, int age, String className, String phoneNumber, String grade1, String grade2, String grade3) {
        String sql = "INSERT INTO students(name, password, age, className, phoneNumber, grade1, grade2, grade3) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            pstmt.setInt(3, age);
            pstmt.setString(4, className);
            pstmt.setString(5, phoneNumber);
            pstmt.setString(6, grade1);
            pstmt.setString(7, grade2);
            pstmt.setString(8, grade3);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        loadStudents(); // 重新加载学生数据
    }

    // 删除学生
    public void removeStudent(int studentId) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadStudents(); // 重新加载学生数据

    }

    // 获取学生列表
    public ArrayList<Student> getStudents() {
        return students;
    }
    // 根据学生ID查找学生
    public Student findStudentById(int studentId) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }
    // 从数据库加载学生数据
    private void loadStudents() {
        students.clear();
        String sql = "SELECT * FROM students";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                int age = rs.getInt("age");
                String className = rs.getString("className");
                String phoneNumber = rs.getString("phoneNumber");
                String grade1 = rs.getString("grade1");
                String grade2 = rs.getString("grade2");
                String grade3 = rs.getString("grade3");

                Student student = new Student(name, password, age, className, phoneNumber, grade1, grade2, grade3, null, null);
                student.setId(id);
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 学生登录
    public Student loginStudent(String name, String password) {
        for (Student student : students) {
            if (student.getName().equals(name) && student.getPassword().equals(password)) {
                return student;
            }
        }
        return null;
    }
    // 更新学生信息到数据库
    public void updateStudent(Student student) {
        String sql = "UPDATE students SET name = ?, password = ?, age = ?, className = ?, phoneNumber = ?, grade1 = ?, grade2 = ?, grade3 = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getPassword());
            pstmt.setInt(3, student.getAge());
            pstmt.setString(4, student.getClassName());
            pstmt.setString(5, student.getPhoneNumber());
            pstmt.setString(6, student.getGrade1());
            pstmt.setString(7, student.getGrade2());
            pstmt.setString(8, student.getGrade3());
            pstmt.setInt(9, student.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 注册教师
    public void registerTeacher(String name, String password, int age, String className, String phoneNumber, String courseInfo) {
        String sql = "INSERT INTO teachers(name, password, age, className, phoneNumber, courseInfo) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            pstmt.setInt(3, age);
            pstmt.setString(4, className);
            pstmt.setString(5, phoneNumber);
            pstmt.setString(6, courseInfo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        loadTeachers(); // 重新加载教师数据
    }

    // 删除教师
    public void removeTeacher(int teacherId) {
        String sql = "DELETE FROM teachers WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, teacherId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadTeachers(); // 重新加载教师数据
    }

    // 教师登录
    public Teacher loginTeacher(String name, String password) {
        for (Teacher teacher : teachers) {
            if (teacher.getName().equals(name) && teacher.getPassword().equals(password)) {
                return teacher;
            }
        }
        return null;
    }

    // 从数据库加载教师数据
    private void loadTeachers() {
        teachers.clear();
        String sql = "SELECT * FROM teachers";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                int age = rs.getInt("age");
                String className = rs.getString("className");
                String phoneNumber = rs.getString("phoneNumber");
                String courseInfo = rs.getString("courseInfo");

                Teacher teacher = new Teacher(name, password, age, className, phoneNumber, courseInfo);
                teacher.setId(id);
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 获取老师所任教的课程
    public ArrayList<Course> getTeacherCourses(String teacherName) {
        ArrayList<Course> teacherCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.getTeacher().equals(teacherName)) {
                teacherCourses.add(course);
            }
        }
        return teacherCourses;
    }
    public ArrayList<Student> getClassStudents(String className) {
        ArrayList<Student> classStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getClassName().equals(className)) {
                classStudents.add(student);
            }
        }
        return classStudents;
    }
    // 根据教师ID查找教师
    public Teacher findTeacherById(int teacherId) {
        for (Teacher teacher : teachers) {
            if (teacher.getId() == teacherId) {
                return teacher;
            }
        }
        return null;
    }
    // 获取教师列表
    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    // 添加课程
    public void addCourse(String courseName, int teacherId) {
        Teacher teacher = null;
        for (Teacher t : teachers) {
            if (t.getId() == teacherId) {
                teacher = t;
                break;
            }
        }

        if (teacher != null) {
            String sql = "INSERT INTO courses(name, teacher) VALUES (?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, courseName);
                pstmt.setString(2, teacher.getName());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            loadCourses(); // 重新加载课程数据
        }
    }

    // 删除课程
    public void removeCourse(int courseId) {
        String sql = "DELETE FROM courses WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadCourses(); // 重新加载课程数据

    }

    // 从数据库加载课程数据
    private void loadCourses() {
        courses.clear();
        String sql = "SELECT * FROM courses ORDER BY id";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String teacher = rs.getString("teacher");

                Course course = new Course(name, teacher);
                course.setId(id);
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 根据课程ID查找课程
    public Course findCourseById(int courseId) {
        for (Course course : courses) {
            if (course.getId() == courseId) {
                return course;
            }
        }
        return null;
    }

    // 获取课程列表
    public ArrayList<Course> getCourses() {
        return courses;
    }

    // 管理员登录
    public Admin loginAdmin(String name, String password) {
        if (name.equals("范泽峥") && password.equals("1")) {
            return new Admin("范泽峥", "1");
        }
        return null;
    }
}
