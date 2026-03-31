package main.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main._System;
import main.model.Admin;
import main.model.BackgroundPanel;
import main.model.Student;
import main.model.Teacher;

public class Login_GUI extends JFrame {
    private _System system;

    public Login_GUI(_System system) {
        this.system = system;

        setTitle("登录");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建背景面板
        Image backgroundImage = new ImageIcon("D:\\Code_Project\\javaProject\\_student_system\\下载.jpg").getImage();
        BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);

        // 设置内容面板为背景面板
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(new BorderLayout()); // 使用边界布局

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10)); // 创建包含4行1列的面板，间距10像素
        panel.setOpaque(false); // 设置为透明以显示背景面板
        backgroundPanel.add(panel, BorderLayout.CENTER); // 将面板添加到背景面板的中间

        placeComponents(panel); // 添加组件到面板

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        JLabel userLabel = new JLabel("用户名");
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("密码");
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        panel.add(passwordText);

        JLabel roleLabel = new JLabel("角色");
        panel.add(roleLabel);

        String[] roles = {"学生", "教师", "管理员"};
        JComboBox<String> roleCombo = new JComboBox<>(roles);
        panel.add(roleCombo);

        JButton loginButton = new JButton("登录");
        panel.add(loginButton);

        JButton registerButton = new JButton("注册");
        panel.add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = userText.getText();
                String password = new String(passwordText.getPassword());
                String role = (String) roleCombo.getSelectedItem();

                if (role.equals("学生")) {
                    Student student = system.loginStudent(user, password);
                    if (student != null) {
                        new Student_GUI(system, student);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "用户名或密码错误");
                    }
                } else if (role.equals("教师")) {
                    Teacher teacher = system.loginTeacher(user, password);
                    if (teacher != null) {
                        new Teacher_GUI(system, teacher);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "用户名或密码错误");
                    }
                } else if (role.equals("管理员")) {
                    Admin admin = system.loginAdmin(user, password);
                    if (admin != null) {
                        new Admin_GUI(system, admin);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "用户名或密码错误");
                    }
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Register_GUI(system);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        _System system = new _System();
        new Login_GUI(system);
    }
}
