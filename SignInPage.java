//import sub classes 
package Railway;
import Railway.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

class SignInPage extends JFrame{
	
	int UserID;
	String UserName;
	
	private void Userentry(String mobile, String passwd) {
	    String jdbcUrl = "jdbc:mysql://localhost:3306/Railway";
	    String dbUsername = "root";
	    String dbPassword = "@nshD8218555";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

	        // fetch data from User Database
	        String sql = "SELECT * FROM Users WHERE Mobile_no = ? AND UserPassword = ?";
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, mobile);
	        preparedStatement.setString(2, passwd);
	        ResultSet resultSet = preparedStatement.executeQuery();

	        System.out.println("first " + preparedStatement);

	        if (resultSet.next()) {
	            UserID = resultSet.getInt("UserID");
	            UserName = resultSet.getString("User_FirstName") + " " + resultSet.getString("User_LastName");
	        }

	        // insert in Userentry table
	        String sql1 = "INSERT INTO Userentry(UserID, User_Name) VALUES (?, ?)";
	        PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
	        preparedStatement1.setInt(1, UserID);
	        preparedStatement1.setString(2, UserName);

	        System.out.println("second " + preparedStatement1);

	        int rowsAffected = preparedStatement1.executeUpdate();
	        System.out.println("Rows Affected: " + rowsAffected);

	        connection.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	
	
    SignInPage(){

         // Components Declaring
        JLabel l1 = new JLabel("Login Page");
        JTextField t1 = new JTextField("Mobile no.",30);
        JTextField t2 = new JPasswordField("Password",15);
        JButton b1=new JButton("Login");
        JButton b2=new JButton("Cancel");
        JButton b3=new JButton("Forget password");

        // Components Locations
        l1.setBounds(150, 10,200, 80);
        t1.setBounds(100,120,200, 40);
        t2.setBounds(100,160,200, 40);
        b1.setBounds(100,300,100, 40);
        b2.setBounds(200,300,100, 40);
        b3.setBounds(280,400,100, 40);

        //Actions on button
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String jdbcUrl = "jdbc:mysql://localhost:3306/Railway";
                String dbUsername = "root";
                String dbPassword = "@nshD8218555";

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
                    {
                    	
                        String mobile = t1.getText();//"9432109876"
                        String password = t2.getText();//"Rakesh9432"

                        Statement statement = connection.createStatement();
                        String sql = "Select * from Users where Mobile_no ='"+mobile+"'and UserPassword ='"+password+"'";
                        ResultSet result = statement.executeQuery(sql);

                        if(result.next()){
                        	Userentry(mobile, password);
                            SwingUtilities.invokeLater(() -> new TrainPage());
                            dispose();
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Invalid Authentication");
                        }
                    }
                } catch (Exception f) {
                    // Handle exceptions more gracefully in a production environment
                    System.out.println(f.getMessage());
                }
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                dispose();
            }
        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new SignUpPage();
                dispose();
            }
        });

         // Adding Components
        add(l1);
        add(t1);
        add(t2);
        add(b1);
        add(b2);
        add(b3);

        //Window Frame
         setTitle("Railway reservation");
         setSize(400,500);
         setLayout(new BorderLayout());
         setVisible(true);
         setLocation(700,250);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         getContentPane().setBackground(Color.LIGHT_GRAY);
     }
    
    public static void main(String[] args) {

        SignInPage signin = new SignInPage();
        //new SeatPage();
        //new TrainPage();

    }
}
