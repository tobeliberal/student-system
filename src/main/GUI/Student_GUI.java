// Student_GUI.java
package main.GUI;

import main.model.Course;
import main.model.Student;
import main._System;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Student_GUI extends JFrame {
    private _System system;
    private Student student;
    private boolean firstTimeSelectingCourses;

    public Student_GUI(_System system, Student student) {
        this.system = system;
        this.student = student;
        this.firstTimeSelectingCourses = (student.getCourses() == null || student.getCourses().isEmpty());

        setTitle("学生界面");
        setSize(300, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("欢迎 " + student.getName()+"同学");
        welcomeLabel.setBounds(10, 20, 300, 25);
        panel.add(welcomeLabel);

        JButton viewInfoButton = new JButton("查看你的个人信息");
        viewInfoButton.setBounds(10, 60, 200, 25);
        panel.add(viewInfoButton);

        JButton viewGradeButton = new JButton("查看成绩");
        viewGradeButton.setBounds(10, 100, 200, 25);
        panel.add(viewGradeButton);

        JButton viewCoursesButton = new JButton("查看选课信息");
        viewCoursesButton.setBounds(10, 140, 200, 25);
        panel.add(viewCoursesButton);

        JButton selectCourseButton = new JButton("选课");
        selectCourseButton.setBounds(10, 180, 200, 25);
        panel.add(selectCourseButton);

        JButton resetCourseButton = new JButton("重新选课");
        resetCourseButton.setBounds(10, 220, 200, 25);
        panel.add(resetCourseButton);

        JButton findCourseButton = new JButton("查找课程");
        findCourseButton.setBounds(10, 260, 200, 25);
        panel.add(findCourseButton);
        JButton ChangeButton = new JButton("申请修改信息");
        ChangeButton.setBounds(10, 300, 200, 25);
        panel.add(ChangeButton);

        JButton logoutButton = new JButton("退出登录");
        logoutButton.setBounds(10, 340, 200, 25);
        panel.add(logoutButton);

        viewInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(panel, student.view_stu_Inform());
            }
        });

        viewGradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(panel, "高数成绩： " + student.getGrade1() + "\n大英成绩： " + student.getGrade2() + "\njava成绩： " + student.getGrade3());
            }
        });

        viewCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder courseInfo = new StringBuilder("选课信息:\n");
                for (Course course : student.getCourses()) {
                    courseInfo.append(course.view_cu_Inform()).append("\n");
                }
                JOptionPane.showMessageDialog(panel, courseInfo.toString());
            }
        });

        selectCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (firstTimeSelectingCourses) {
                    showCourseSelectionPanel();
                } else {
                    StringBuilder courseInfo = new StringBuilder("你已经选过课了，选课信息如下:\n");
                    for (Course course : student.getCourses()) {
                        courseInfo.append(course.view_cu_Inform()).append("\n");
                    }
                    JOptionPane.showMessageDialog(panel, courseInfo.toString());
                }
            }
        });

        ChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String change = JOptionPane.showInputDialog("输入修改信息:");
                student.setChange(change);
                JOptionPane.showMessageDialog(panel, "修改信息已提交.");
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login_GUI(system);
                dispose();
            }
        });

        resetCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!firstTimeSelectingCourses) {
                    student.setCourses(new ArrayList<>());
                    firstTimeSelectingCourses = true;
                    JOptionPane.showMessageDialog(panel, "已清空选课信息。");
                    showCourseSelectionPanel();
                } else {
                    JOptionPane.showMessageDialog(panel, "你还没有选过课。");
                }
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
    }

    private void showCourseSelectionPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));  // 使用GridLayout，0行表示自动增加行数，1列表示一列

        ArrayList<Course> allCourses = system.getCourses();
        ArrayList<JCheckBox> courseCheckBoxes = new ArrayList<>();

        for (Course course : allCourses) {
            JCheckBox checkBox = new JCheckBox(course.view_cu_Inform());
            courseCheckBoxes.add(checkBox);
            panel.add(checkBox);
        }

        int result = JOptionPane.showConfirmDialog(null, panel, "选择课程", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            ArrayList<Course> selectedCourses = new ArrayList<>();
            for (JCheckBox checkBox : courseCheckBoxes) {
                if (checkBox.isSelected()) {
                    String courseInfo = checkBox.getText();
                    for (Course course : allCourses) {
                        if (courseInfo.equals(course.view_cu_Inform())) {
                            selectedCourses.add(course);
                            break;
                        }
                    }
                }
            }
            if (selectedCourses.size() > 3) {
                JOptionPane.showMessageDialog(this, "你最多只能选择三门课程，请重新选择。");
                showCourseSelectionPanel();
            } else if (selectedCourses.size() == 0) {
                JOptionPane.showMessageDialog(this, "你没有选择任何课程，请重新选择。");
                showCourseSelectionPanel();
            } else {
                // 将选课信息保存到数据库
                for (Course course : selectedCourses) {
                    student.addCourse(course);
                }
                system.updateStudent(student); // 更新学生信息到数据库
                firstTimeSelectingCourses = false;
                JOptionPane.showMessageDialog(this, "选课成功!");
            }
        }
    }
}

