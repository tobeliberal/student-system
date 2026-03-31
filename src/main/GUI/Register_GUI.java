package main.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main._System;
public class Register_GUI extends JFrame {
    private _System system;

    public Register_GUI(_System system) {
        this.system = system;

        setTitle("注册界面");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(new GridLayout(0, 1)); // 使用网格布局，一列多行

        JLabel roleLabel = new JLabel("请选择注册类型:");
        roleLabel.setHorizontalAlignment(JLabel.CENTER); // 文本居中
        panel.add(roleLabel);

        String[] roles = {"学生", "教师"};
        JComboBox<String> roleComboBox = new JComboBox<>(roles);
        roleComboBox.setAlignmentX(Component.CENTER_ALIGNMENT); // 组件居中
        panel.add(roleComboBox);

        JButton nextButton = new JButton("下一步");
        nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(nextButton);

        JButton backButton = new JButton("退出");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(backButton);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedRole = (String) roleComboBox.getSelectedItem();
                if (selectedRole.equals("学生")) {
                    showStudentRegisterPanel();
                } else if (selectedRole.equals("教师")) {
                    showTeacherRegisterPanel();
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login_GUI(system);
            }
        });
    }


    private void showStudentRegisterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        JLabel userLabel = new JLabel("用户名:");
        JTextField userText = new JTextField(20);

        JLabel passwordLabel = new JLabel("密码:");
        JPasswordField passwordText = new JPasswordField(20);

        JLabel ageLabel = new JLabel("年龄:");
        JTextField ageText = new JTextField(10);

        JLabel classLabel = new JLabel("班级:");
        JTextField classText = new JTextField(10);

        JLabel phoneLabel = new JLabel("手机号:");
        JTextField phoneText = new JTextField(20);

        JLabel grade1Label = new JLabel("高数成绩:");
        JTextField grade1Text = new JTextField(10);

        JLabel grade2Label = new JLabel("大英成绩:");
        JTextField grade2Text = new JTextField(10);

        JLabel grade3Label = new JLabel("Java成绩:");
        JTextField grade3Text = new JTextField(10);

        panel.add(userLabel);
        panel.add(userText);
        panel.add(passwordLabel);
        panel.add(passwordText);
        panel.add(ageLabel);
        panel.add(ageText);
        panel.add(classLabel);
        panel.add(classText);
        panel.add(phoneLabel);
        panel.add(phoneText);
        panel.add(grade1Label);
        panel.add(grade1Text);
        panel.add(grade2Label);
        panel.add(grade2Text);
        panel.add(grade3Label);
        panel.add(grade3Text);

        int result = JOptionPane.showConfirmDialog(null, panel, "学生注册", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String userName = userText.getText();
            String password = new String(passwordText.getPassword());
            int age = Integer.parseInt(ageText.getText());
            String className = classText.getText();
            String phoneNumber = phoneText.getText();
            String grade1 = grade1Text.getText();
            String grade2 = grade2Text.getText();
            String grade3 = grade3Text.getText();

            system.registerStudent(userName, password, age, className, phoneNumber, grade1, grade2, grade3);
            dispose();
            new Login_GUI(system);
        }
    }

    private void showTeacherRegisterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        JLabel userLabel = new JLabel("用户名:");
        JTextField userText = new JTextField(20);

        JLabel passwordLabel = new JLabel("密码:");
        JPasswordField passwordText = new JPasswordField(20);

        JLabel ageLabel = new JLabel("年龄:");
        JTextField ageText = new JTextField(10);

        JLabel classLabel = new JLabel("所教班级:");
        JTextField classText = new JTextField(10);

        JLabel phoneLabel = new JLabel("手机号:");
        JTextField phoneText = new JTextField(20);

        JLabel courseLabel = new JLabel("所教课程:");
        JTextField courseText = new JTextField(20);

        panel.add(userLabel);
        panel.add(userText);
        panel.add(passwordLabel);
        panel.add(passwordText);
        panel.add(ageLabel);
        panel.add(ageText);
        panel.add(classLabel);
        panel.add(classText);
        panel.add(phoneLabel);
        panel.add(phoneText);
        panel.add(courseLabel);
        panel.add(courseText);

        int result = JOptionPane.showConfirmDialog(null, panel, "教师注册", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String userName = userText.getText();
            String password = new String(passwordText.getPassword());
            int age = Integer.parseInt(ageText.getText());
            String className = classText.getText();
            String phoneNumber = phoneText.getText();
            String courseInfo = courseText.getText();

            system.registerTeacher(userName, password, age, className, phoneNumber, courseInfo);
            dispose();
            new Login_GUI(system);
        }
    }
}
