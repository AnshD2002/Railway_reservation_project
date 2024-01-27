package Railway;

import Railway.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

class SignUpPage extends JFrame{
     SignUpPage(){

    	 
         String arr[] = {"male","female"};

     JTextField t1 = new JTextField("First Name");
     JTextField t2 = new JTextField("Last Name");
     JLabel l1 = new JLabel("Gender: ");
     JComboBox <String>jl1 = new JComboBox(arr);
     JTextField t3 = new JTextField("Mobile no.",10);
     JTextField t4 = new JTextField("Email");
     JTextField t5 = new JPasswordField("Password");
     JButton b1 = new JButton("Sign Up");
     JButton b2 = new JButton("Cancel");

         // Adding Components
         add(t1);
         add(t2);
         add(l1);
         add(jl1);
         add(t3);
         add(t4);
         add(t5);
         add(b1);
         add(b2);

         // Positioning Components

         t1.setBounds(100,60,110, 40);
         t2.setBounds(240,60,110, 40);
         l1.setBounds(100,120,100, 40);
         jl1.setBounds(190,120,80, 40);
         t3.setBounds(100,180,250, 40);
         t4.setBounds(100,240,250, 40);
         t5.setBounds(100,300,250, 40);
         b1.setBounds(150,360,100, 40);
         b2.setBounds(250,360,100, 40);

         //Gender radio button


         //Actions on button
         b1.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 String jdbcUrl = "jdbc:mysql://localhost:3306/Railway";
                 String dbUsername = "root";
                 String dbPassword = "@nshD8218555";

                 try {
                     Class.forName("com.mysql.cj.jdbc.Driver");
                     Connection connection = DriverManager.getConnection(jdbcUrl,dbUsername,dbPassword);

                     //generate UserID
                     String sql = "select count(*) from Users";
                     PreparedStatement preparedStatement = connection.prepareStatement(sql);
                     ResultSet result = preparedStatement.executeQuery(sql);
                     result.next();
                     int id = result.getInt(1);
                     //inserting values in database

                     int Userid = (101+id) ;
                     String fname = t1.getText();
                     String lname = t2.getText();
                     String gender = (String) jl1.getSelectedItem();
                     String mno = t3.getText();
                     String email = t4.getText();
                     String psswd = t5.getText();


//insert into Users(UserID ,User_FirstName ,User_LastName ,Email_id ,Mobile_no ,Gender,UserPassword)
                     String sql1 = String.format("insert into Users (UserID ,User_FirstName ,User_LastName ,Email_id ,Mobile_no ,Gender,UserPassword) values (?,?,?,?,?,?,?)");
                     PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);

                     preparedStatement1.setInt(1, Userid);
                     preparedStatement1.setString(2, fname);
                     preparedStatement1.setString(3, lname);
                     preparedStatement1.setString(4, email);
                     preparedStatement1.setString(5,mno);
                     preparedStatement1.setString(6, gender);
                     preparedStatement1.setString(7, psswd);


                     int rowsAffected = preparedStatement1.executeUpdate();
                     System.out.println("Rows affected: " + rowsAffected);


                     if (rowsAffected>0){
                         new SignInPage();
                         dispose();
                     }
                 }
                 catch (Exception g){
                     System.out.println(g);
                 }

             }
         });

         b2.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 dispose();
             }
         });

         //Window Frame

         setTitle("Railway reservation");
         setSize(500,700);
         setLayout(null);
         setVisible(true);
         setLocation(700,250);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         getContentPane().setBackground(Color.CYAN);

    }
}



