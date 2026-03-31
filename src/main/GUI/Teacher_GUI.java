// Teacher_GUI.java
package main.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main._System;
import main.model.Teacher;
import main.model.Course;
import main.model.Student;
import java.util.ArrayList;

public class Teacher_GUI extends JFrame {
    private _System system;
    private Teacher teacher;

    public Teacher_GUI(_System system, Teacher teacher) {
        this.system = system;
        this.teacher = teacher;

        setTitle("老师界面");
        setSize(300, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("欢迎 " + teacher.getName()+"老师");
        welcomeLabel.setBounds(10, 20, 300, 25);
        panel.add(welcomeLabel);

        JButton viewClassStudentButton = new JButton("查看本班学生");
        viewClassStudentButton.setBounds(10, 60, 200, 25);
        panel.add(viewClassStudentButton);

        JButton modifyStudentButton = new JButton("修改学生信息");
        modifyStudentButton.setBounds(10, 100, 200, 25);
        panel.add(modifyStudentButton);

        JButton addStudentButton = new JButton("添加学生信息");
        addStudentButton.setBounds(10, 140, 200, 25);
        panel.add(addStudentButton);

        JButton deleteStudentButton = new JButton("删除学生信息");
        deleteStudentButton.setBounds(10, 180, 200, 25);
        panel.add(deleteStudentButton);

        JButton viewChangeButton = new JButton("查看修改请求");
        viewChangeButton.setBounds(10, 220, 200, 25);
        panel.add(viewChangeButton);

        JButton viewCoursesButton = new JButton("查看任课信息");
        viewCoursesButton.setBounds(10, 260, 200, 25);
        panel.add(viewCoursesButton);
        JButton findStudentButton = new JButton("查找学生");
        findStudentButton.setBounds(10, 300, 200, 25);
        panel.add(findStudentButton);
        JButton logoutButton = new JButton("退出登录");
        logoutButton.setBounds(10, 340, 200, 25);
        panel.add(logoutButton);

        viewClassStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Student> classStudents = system.getClassStudents(teacher.getClassName());
                StringBuilder classStudentInfo = new StringBuilder("本班学生信息:\n");
                for (Student student : classStudents) {
                    classStudentInfo.append(student.view_stu_Inform()).append("\n");
                }
                JOptionPane.showMessageDialog(panel, classStudentInfo.toString());
            }
        });

        modifyStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int studentId = Integer.parseInt(JOptionPane.showInputDialog("输入要修改的学生id:"));
                Student student = null;
                for (Student s : system.getStudents()) {
                    if (s.getId() == studentId) {
                        student = s;
                        break;
                    }
                }
                if (student != null) {
                    String newName = JOptionPane.showInputDialog("新名字为:", student.getName());
                    int newAge = Integer.parseInt(JOptionPane.showInputDialog("新年龄为:", student.getAge()));
                    String newClass = JOptionPane.showInputDialog("新班级为:", student.getClassName());
                    String newPhoneNumber = JOptionPane.showInputDialog("新手机号为:", student.getPhoneNumber());
                    String newGrade1 = JOptionPane.showInputDialog("新高数成绩为:", student.getGrade1());
                    String newGrade2 = JOptionPane.showInputDialog("新大英成绩为:", student.getGrade2());
                    String newGrade3 = JOptionPane.showInputDialog("新java成绩为:", student.getGrade3());
                    student.setName(newName);
                    student.setAge(newAge);
                    student.setClassName(newClass);
                    student.setPhoneNumber(newPhoneNumber);
                    student.setGrade1(newGrade1);
                    student.setGrade2(newGrade2);
                    student.setGrade3(newGrade3);
                    JOptionPane.showMessageDialog(panel, "该学生信息修改成功.");
                } else {
                    JOptionPane.showMessageDialog(panel, "未找到该学生.");
                }
            }
        });
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("输入学生名字:");
                String password = JOptionPane.showInputDialog("输入学生密码:");
                int age = Integer.parseInt(JOptionPane.showInputDialog("输入学生年龄:"));
                String className = JOptionPane.showInputDialog("输入学生班级:");
                String phoneNumber = JOptionPane.showInputDialog("输入学生手机号:");
                String grade1 = JOptionPane.showInputDialog("输入学生高数成绩:");
                String grade2 = JOptionPane.showInputDialog("输入学生大英成绩:");
                String grade3 = JOptionPane.showInputDialog("输入学生java成绩:");
                system.registerStudent(name, password, age, className, phoneNumber, grade1, grade2, grade3);
                JOptionPane.showMessageDialog(panel, "该学生已添加.");
            }
        });

        // 删除学生信息
        deleteStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int studentId = Integer.parseInt(JOptionPane.showInputDialog("输入要删除的学生ID:"));
                system.removeStudent(studentId);
                JOptionPane.showMessageDialog(panel, "学生已删除.");
            }
        });


        viewChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder changeRequests = new StringBuilder("学生修改申请：\n");
                for (Student student : system.getStudents()) {
                    if (student.getChange() != null) {
                        changeRequests.append("学生ID：").append(student.getId()).append(", 修改请求：").append(student.getChange()).append("\n");
                    }
                }
                JOptionPane.showMessageDialog(panel, changeRequests.toString());
            }
        });

        viewCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Course> myCourses = system.getTeacherCourses(teacher.getName());
                StringBuilder coursesInfo = new StringBuilder("所任课程信息:\n");
                for (Course course : myCourses) {
                    coursesInfo.append(course.view_cu_Inform()).append("\n");
                }
                JOptionPane.showMessageDialog(panel, coursesInfo.toString());
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
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login_GUI(system);
                dispose();
            }
        });
    }
}

