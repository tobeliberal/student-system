// Admin_GUI.java
package main.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main._System;
import main.model.Admin;
import main.model.Course;
import main.model.Teacher;
import main.model.Student;
import java.util.List;

public class Admin_GUI extends JFrame {
    private _System system;
    private Admin admin;
    private List<Student> students;
    private List<Teacher> teachers;
    private List<Course> courses;

    public Admin_GUI(_System system, Admin admin) {
        this.system = system;
        this.admin = admin;

        setTitle("管理员界面");
        setSize(250, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("欢迎，" + admin.getName()+"管理员");
        welcomeLabel.setBounds(10, 10, 300, 25);
        panel.add(welcomeLabel);

        JButton addStudentButton = new JButton("添加学生");
        addStudentButton.setBounds(10, 40, 150, 25);
        panel.add(addStudentButton);

        JButton deleteStudentButton = new JButton("删除学生");
        deleteStudentButton.setBounds(10, 70, 150, 25);
        panel.add(deleteStudentButton);
// 添加查找学生按钮
        JButton findStudentButton = new JButton("查找学生");
        findStudentButton.setBounds(10, 100, 150, 25);
        panel.add(findStudentButton);


        JButton checkStudentButton = new JButton("查看当前全部学生");
        checkStudentButton.setBounds(10, 130, 150, 25);
        panel.add(checkStudentButton);

        JButton addTeacherButton = new JButton("添加教师");
        addTeacherButton.setBounds(10, 160, 150, 25);
        panel.add(addTeacherButton);

        JButton deleteTeacherButton = new JButton("删除教师");
        deleteTeacherButton.setBounds(10, 190, 150, 25);
        panel.add(deleteTeacherButton);

        JButton findTeacherButton = new JButton("查找教师");
        findTeacherButton.setBounds(10, 220, 150, 25);
        panel.add(findTeacherButton);


        JButton checkTeacherButton = new JButton("查看当前全部教师");
        checkTeacherButton.setBounds(10, 250, 150, 25);
        panel.add(checkTeacherButton);

        JButton addCourseButton = new JButton("添加课程");
        addCourseButton.setBounds(10, 280, 150, 25);
        panel.add(addCourseButton);

        JButton deleteCourseButton = new JButton("删除课程");
        deleteCourseButton.setBounds(10, 310, 150, 25);
        panel.add(deleteCourseButton);

        JButton findCourseButton = new JButton("查找课程");
        findCourseButton.setBounds(10, 340, 150, 25);
        panel.add(findCourseButton);

        JButton checkCourseButton = new JButton("查看当前全部课程");
        checkCourseButton.setBounds(10, 370, 150, 25);
        panel.add(checkCourseButton);

        JButton logoutButton = new JButton("退出");
        logoutButton.setBounds(10, 400, 150, 25);
        panel.add(logoutButton);

        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("输入学生姓名:");
                String password = JOptionPane.showInputDialog("输入学生密码:");
                int age = Integer.parseInt(JOptionPane.showInputDialog("输入学生年龄:"));
                String className = JOptionPane.showInputDialog("输入学生班级:");
                String phoneNumber = JOptionPane.showInputDialog("输入学生手机号:");
                String grade1 = JOptionPane.showInputDialog("输入高数成绩:");
                String grade2 = JOptionPane.showInputDialog("输入大英成绩:");
                String grade3 = JOptionPane.showInputDialog("输入Java成绩:");

                system.registerStudent(name, password, age, className, phoneNumber, grade1, grade2, grade3);
                JOptionPane.showMessageDialog(panel, "学生已添加.");
            }
        });

        deleteStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int studentId = Integer.parseInt(JOptionPane.showInputDialog("输入要删除的学生ID:"));

                system.removeStudent(studentId);
                JOptionPane.showMessageDialog(panel, "学生已删除.");
            }
        });

        checkStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                students = system.getStudents(); // 获取学生列表
                StringBuilder studentList = new StringBuilder("当前学生列表:\n");
                for (Student student : students) {
                    studentList.append("学生ID: ").append(student.getId())
                            .append(", 姓名: ").append(student.getName())
                            .append(", 班级: ").append(student.getClassName()).append("\n");
                }
                JOptionPane.showMessageDialog(panel, studentList.toString());
            }
        });

        findStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int studentId = Integer.parseInt(JOptionPane.showInputDialog("输入要查找的学生ID:"));
                Student student = system.findStudentById(studentId);
                if (student != null) {
                    JOptionPane.showMessageDialog(panel, "找到学生:\n" + student.view_stu_Inform());
                } else {
                    JOptionPane.showMessageDialog(panel, "未找到该学生.");
                }
            }
        });

        addTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("输入教师姓名:");
                String password = JOptionPane.showInputDialog("输入教师密码:");
                int age = Integer.parseInt(JOptionPane.showInputDialog("输入教师年龄:"));
                String className = JOptionPane.showInputDialog("输入所教班级:");
                String phoneNumber = JOptionPane.showInputDialog("输入教师手机号:");
                String courseInfo = JOptionPane.showInputDialog("输入所教课程:");

                system.registerTeacher(name, password, age, className, phoneNumber, courseInfo);
                JOptionPane.showMessageDialog(panel, "教师已添加.");
            }
        });

        deleteTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int teacherId = Integer.parseInt(JOptionPane.showInputDialog("输入要删除的教师ID:"));

                system.removeTeacher(teacherId);
                JOptionPane.showMessageDialog(panel, "教师已删除.");
            }
        });

        checkTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teachers = system.getTeachers(); // 获取教师列表
                StringBuilder teacherList = new StringBuilder("当前教师列表:\n");
                for (Teacher teacher : teachers) {
                    teacherList.append("老师ID: ").append(teacher.getId())
                            .append(", 姓名: ").append(teacher.getName())
                            .append(", 班级: ").append(teacher.getClassName())
                            .append(", 课程: ").append(teacher.getCourseInfo()).append("\n");
                }
                JOptionPane.showMessageDialog(panel, teacherList.toString());
            }
        });

        findTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int teacherId = Integer.parseInt(JOptionPane.showInputDialog("输入要查找的教师ID:"));
                Teacher teacher = system.findTeacherById(teacherId);
                if (teacher != null) {
                    JOptionPane.showMessageDialog(panel, "找到教师:\n" + teacher.view_tu_Inform());
                } else {
                    JOptionPane.showMessageDialog(panel, "未找到该教师.");
                }
            }
        });

        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseName = JOptionPane.showInputDialog("输入课程名称:");
                int teacherId = Integer.parseInt(JOptionPane.showInputDialog("输入教师ID:"));

                system.addCourse(courseName, teacherId);
                JOptionPane.showMessageDialog(panel, "课程已添加.");
            }
        });

        deleteCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int courseId = Integer.parseInt(JOptionPane.showInputDialog("输入要删除的课程ID:"));

                system.removeCourse(courseId);
                JOptionPane.showMessageDialog(panel, "课程已删除.");
            }
        });

        checkCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courses = system.getCourses(); // 获取课程列表
                StringBuilder courseList = new StringBuilder("当前课程列表:\n");
                for (Course course : courses) {
                    courseList.append("课程ID: ").append(course.getId())
                            .append(", 课程名称: ").append(course.getName())
                            .append(", 教师: ").append(course.getTeacher()).append("\n");
                }
                JOptionPane.showMessageDialog(panel, courseList.toString());
            }
        });

        findCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int courseId = Integer.parseInt(JOptionPane.showInputDialog("输入要查找的课程ID:"));
                Course course = system.findCourseById(courseId);
                if (course != null) {
                    JOptionPane.showMessageDialog(panel, "找到课程:\n" + course.view_cu_Inform());
                } else {
                    JOptionPane.showMessageDialog(panel, "未找到该课程.");
                }
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login_GUI(system);
            }
        });
    }
}

