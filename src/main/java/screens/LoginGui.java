package screens;

import models.User;
import services.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGui {

    private JFrame frame;
    private JPanel panel;

    private JLabel userLabel;
    private JTextField userText;

    private JLabel passwordLabel;
    private JPasswordField passwordText;

    private JButton loginButton;
    private JButton addUserButton;

    private Login login = new Login();
    private AddUser addUser = new AddUser();

    private JLabel loginResult;

    private UserService userService;

    public LoginGui(){

        userService = new UserService();

        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/main/resources/icon/gold_lock.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

        frame = new JFrame();
        panel = new JPanel();

        userLabel = new JLabel("User");
        userText = new JTextField(20);

        passwordLabel = new JLabel("Password");
        passwordText = new JPasswordField(20);

        loginButton = new JButton("Login");
        addUserButton = new JButton("Sign up");

        loginResult = new JLabel();
        loginResult.setBounds(100, 120, 165, 25);
        panel.add(loginResult);

        loginButton.setBounds(95, 100, 80, 25);
        loginButton.addActionListener(login);
        panel.add(loginButton);

        addUserButton.setBounds(190, 100, 80, 25);
        addUserButton.addActionListener(addUser);
        panel.add(addUserButton);

        userLabel.setBounds(10,20, 80, 25);
        panel.add(userLabel);

        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        passwordLabel.setBounds(10,60, 80, 25);
        panel.add(passwordLabel);

        passwordText.setBounds(100, 60, 165, 25);
        panel.add(passwordText);

        frame.setTitle("Login Screen");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(imageIcon.getImage());
        frame.add(panel);

        panel.setLayout(null);
    }


    class Login implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            User user = new User(userText.getText(), passwordText.getPassword());
            boolean res = userService.verify(user);
            if(res){
                loginResult.setText("LOGIN SUCCESSFUL!");
            }
            else {
                loginResult.setText("LOGIN FAILED!");
            }

        }
    }

    class AddUser implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            AddUserGui addUserGui = new AddUserGui();
        }
    }


}
