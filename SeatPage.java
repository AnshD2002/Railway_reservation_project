package Railway;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

class SeatPage extends JFrame {
	
	
	//function to fetch no. of seats booked
	
	int numofseats() {
		
		int numofseats =0;
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/Railway";
        String dbUsername = "root";
        String dbPassword = "@nshD8218555";
        
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(jdbcUrl,dbUsername,dbPassword);
			
			String sql = "select count(*) from booked_seats";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			resultSet.next();
			numofseats = resultSet.getInt(1);
			
			//close connections
			resultSet.close();
			preparedStatement.close();
			connection.close();
			
		} catch (Exception e) {
			System.out.print(e);
		}
        
		return numofseats;
        
	}
	
	int trainnum() {
	    String jdbcUrl = "jdbc:mysql://localhost:3306/Railway";
	    String dbUsername = "root";
	    String dbPassword = "@nshD8218555";
	    int trainnum = 0;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

	        String sql = "SELECT * FROM trainnum_entry ORDER BY id DESC LIMIT 1";
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            trainnum = resultSet.getInt("trainno");
	        }

	        // Close connections
	        resultSet.close();
	        preparedStatement.close();
	        connection.close();
	    } catch (Exception e) {
	        System.out.println(e);
	    }

	    return trainnum;
	}

	
	int UserID() {
	    String jdbcUrl = "jdbc:mysql://localhost:3306/Railway";
	    String dbUsername = "root";
	    String dbPassword = "@nshD8218555";
	    int UserID = 0;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

	        String sql = "SELECT * FROM Userentry ORDER BY id DESC LIMIT 1";
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            UserID = resultSet.getInt("UserID");
	        }

	        // Close connections
	        resultSet.close();
	        preparedStatement.close();
	        connection.close();
	    } catch (Exception e) {
	        System.out.println(e);
	    }

	    return UserID;
	}
	
	String UserName() {
	    String jdbcUrl = "jdbc:mysql://localhost:3306/Railway";
	    String dbUsername = "root";
	    String dbPassword = "@nshD8218555";
	    String UserName = "";

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

	        String sql = "SELECT * FROM Userentry ORDER BY id DESC LIMIT 1";
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	        	UserName = resultSet.getString("User_Name");
	        }

	        // Close connections
	        resultSet.close();
	        preparedStatement.close();
	        connection.close();
	    } catch (Exception e) {
	        System.out.println(e);
	    }

	    return UserName;
	}
	
	
	
	
	
	void booking(String seat_no ) {
		
		
		
		
		
		String seatno = seat_no;
		int train_no = trainnum();
		int UserID = UserID();
		String user_name = UserName();
		String Status = "booked";
		
		
		//SQL queries
				//insert into booked_seats (seat_no,train_no,UserID,User_name,Status)values(4,04152,102,'Rakesh Dabral','booked');
		String jdbcUrl = "jdbc:mysql://localhost:3306/Railway";
	    String dbUsername = "root";
	    String dbPassword = "@nshD8218555";
				
		        

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection = DriverManager.getConnection(jdbcUrl,dbUsername,dbPassword);
					
					String sql = "insert into booked_seats (seat_no,train_no,UserID,User_name,Status)values(?,?,?,?,?);";
					PreparedStatement preparedstatement =  connection.prepareStatement(sql);
					
					//variables
					preparedstatement.setString(1, seatno);
					preparedstatement.setInt(2, train_no);
					preparedstatement.setInt(3, UserID);
					preparedstatement.setString(4, user_name);
					preparedstatement.setString(5, Status);
					
					
					int rowsaffected  = preparedstatement.executeUpdate();
					
					System.out.println(" "+seat_no +" Booked");
					
					//close connections
					preparedstatement.close();
					connection.close();
					
					
					
				}catch (Exception e) {
					System.out.println(e);
				}
	}
	
	
	void markBookedSeats() {
        int num = numofseats();

        String[] bookedSeatsArray = new String[num];

        String jdbcUrl = "jdbc:mysql://localhost:3306/Railway";
        String dbUsername = "root";
        String dbPassword = "@nshD8218555";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

            String sql = "select seat_no from booked_seats";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            int i = 0;
            while (resultSet.next()) {
                bookedSeatsArray[i] = resultSet.getString("seat_no");
                i++;
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            System.out.print(e);
        }

        // Mark booked seats as selected
        for (int j = 0; j < bookedSeatsArray.length; j++) {
            Component[] components = getContentPane().getComponents();
            for (Component component : components) {
                if (component instanceof JButton) {
                    JButton button = (JButton) component;
                    if (button.getText().equals(bookedSeatsArray[j])) {
                   
                        button.setSelected(true);
                        button.setEnabled(false); 
                        button.setForeground(Color.RED);
                        button.setBackground(Color.RED);
                        button.setText("Booked");
                        // Optionally disable booked seats
                    }
                }
            }
        }
    }

	

SeatPage(){
		
    
	JLabel l1 = new JLabel(); 
	 JLabel lblBookedSeats = new JLabel("Welcome to Seat booking Panel");
     lblBookedSeats.setBounds(25, 0, 140, 20);
     getContentPane().add(lblBookedSeats);
     
     

// Seats Buttons-------------------------------------------------------------------------------
        JButton SeatS1 = new JButton("SeatS1");
        SeatS1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS1"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you want to book "+x, 
                        "Seat confirmation", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
        	}
        });
        SeatS1.setBounds(24, 24, 79, 54);
        getContentPane().add(SeatS1);
        
        JButton SeatS2 = new JButton("SeatS2");
        SeatS2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS2"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
        	}
        });
        SeatS2.setBounds(115, 24, 80, 54);
        getContentPane().add(SeatS2);
        
        JButton SeatS3 = new JButton("SeatS3");
        SeatS3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS3"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
         	}
        });
        SeatS3.setBounds(207, 24, 79, 54);
        getContentPane().add(SeatS3);
        
        JButton SeatS4 = new JButton("SeatS4");
        SeatS4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS4"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
         	}
        });
        SeatS4.setBounds(385, 24, 79, 54);
        getContentPane().add(SeatS4);
        
        JButton SeatS5 = new JButton("SeatS5");
        SeatS5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS5"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
         	}
        });
        SeatS5.setBounds(24, 101, 79, 54);
        getContentPane().add(SeatS5);
        
        JButton SeatS6 = new JButton("SeatS6");
        SeatS6.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS6"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						} 	
        		}
        });
        SeatS6.setBounds(115, 101, 80, 54);
        getContentPane().add(SeatS6);
        
        JButton SeatS7 = new JButton("SeatS7");
        SeatS7.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS7"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
         	}
        });
        SeatS7.setBounds(207, 101, 79, 54);
        getContentPane().add(SeatS7);
        
        
        
        JButton SeatS8 = new JButton("SeatS1");
        SeatS8.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS8"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
         	}
        });
        SeatS8.setBounds(385, 101, 79, 54);
        getContentPane().add(SeatS8);
        
        JButton SeatS1_1 = new JButton("SeatS1_1");
        SeatS1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS1_1"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
         	}
        });
        SeatS1_1.setBounds(24, 200, 79, 54);
        getContentPane().add(SeatS1_1);
        
        JButton SeatS2_1 = new JButton("SeatS2_1");
        SeatS2_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS2_1"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
         	}
        });
        SeatS2_1.setBounds(115, 200, 80, 54);
        getContentPane().add(SeatS2_1);
        
        JButton SeatS3_1 = new JButton("SeatS3_1");
        SeatS3_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS3_1"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
         	}
        });
        SeatS3_1.setBounds(207, 200, 79, 54);
        getContentPane().add(SeatS3_1);
        
        JButton SeatS5_1 = new JButton("SeatS5_1");
        SeatS5_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS5_1"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
         	}
        });
        SeatS5_1.setBounds(24, 277, 79, 54);
        getContentPane().add(SeatS5_1);
        
        JButton SeatS6_1 = new JButton("SeatS6_1");
        SeatS6_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS6_1"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
         	}
        });
        SeatS6_1.setBounds(115, 277, 80, 54);
        getContentPane().add(SeatS6_1);
        
        JButton SeatS7_1 = new JButton("SeatS7_1");
        SeatS7_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS7_1"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
         	}
        });
        SeatS7_1.setBounds(207, 277, 79, 54);
        getContentPane().add(SeatS7_1);
        
        JButton SeatS4_1 = new JButton("SeatS4_1");
        SeatS4_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS4_1"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
         	}
        });
        SeatS4_1.setBounds(385, 200, 79, 54);
        getContentPane().add(SeatS4_1);
        
        JButton SeatS8_1 = new JButton("SeatS8_1");
        SeatS8_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS8_1"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
         	}
        });
        SeatS8_1.setBounds(385, 277, 79, 54);
        getContentPane().add(SeatS8_1);
        
        JButton SeatS1_2 = new JButton("SeatS1_2");
        SeatS1_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS1_2"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
         	}
        });
        SeatS1_2.setBounds(24, 371, 79, 54);
        getContentPane().add(SeatS1_2);
        
        JButton SeatS2_2 = new JButton("SeatS2_2");
        SeatS2_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "Seat2_2"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
         	}
        });
        SeatS2_2.setBounds(115, 371, 80, 54);
        getContentPane().add(SeatS2_2);
        
        JButton SeatS3_2 = new JButton("SeatS3_2");
        SeatS3_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS3_2"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
         	}
        });
        SeatS3_2.setBounds(207, 371, 79, 54);
        getContentPane().add(SeatS3_2);
        
        JButton SeatS5_2 = new JButton("SeatS5_2");
        SeatS5_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS5_2"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
         	}
        });
        SeatS5_2.setBounds(24, 448, 79, 54);
        getContentPane().add(SeatS5_2);
        
        JButton SeatS6_2 = new JButton("SeatS6_2");
        SeatS6_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS6_2"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
         	}
        });
        SeatS6_2.setBounds(115, 448, 80, 54);
        getContentPane().add(SeatS6_2);
        
        JButton SeatS7_2 = new JButton("Seat S1");
        SeatS5_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS1_2"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
         	}
        });
        SeatS7_2.setBounds(207, 448, 79, 54);
        getContentPane().add(SeatS7_2);
        
        JButton SeatS4_2 = new JButton("SeatS4_2");
        SeatS4_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS4_2"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
         	}
        });
        SeatS4_2.setBounds(385, 371, 79, 54);
        getContentPane().add(SeatS4_2);
        
        JButton SeatS8_2 = new JButton("SeatS8_2");
        SeatS8_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS8_2"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
         	}
        });
        SeatS8_2.setBounds(385, 448, 79, 54);
        getContentPane().add(SeatS8_2);
        
        JButton SeatS1_3 = new JButton("SeatS1_3");
        SeatS1_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS1_3"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
         	}
        });
        SeatS1_3.setBounds(24, 539, 79, 54);
        getContentPane().add(SeatS1_3);
        
        JButton SeatS2_3 = new JButton("SeatS2_3");
        SeatS2_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS2_3"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
         	}
        });
        SeatS2_3.setBounds(115, 539, 80, 54);
        getContentPane().add(SeatS2_3);
        
        JButton SeatS3_3 = new JButton("SeatS3_3");
        SeatS3_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS3_3"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
         	}
        });
        SeatS3_3.setBounds(207, 539, 79, 54);
        getContentPane().add(SeatS3_3);
        
        JButton SeatS5_3 = new JButton("SeatS5_3");
        SeatS5_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS5_3"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
         	}
        });
        SeatS5_3.setBounds(24, 616, 79, 54);
        getContentPane().add(SeatS5_3);
        
        JButton SeatS6_3 = new JButton("SeatS6_3");
        SeatS6_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS6_3"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
         	}
        });
        SeatS6_3.setBounds(115, 616, 80, 54);
        getContentPane().add(SeatS6_3);
        
        JButton SeatS7_3 = new JButton("SeatS7_3");
        SeatS7_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS7_3"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
         	}
        });
        SeatS7_3.setBounds(207, 616, 79, 54);
        getContentPane().add(SeatS7_3);
        
        JButton SeatS4_3 = new JButton("SeatS4_3");
        SeatS4_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS4_3"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
         	}
        });
        SeatS4_3.setBounds(385, 539, 79, 54);
        getContentPane().add(SeatS4_3);
        
        JButton SeatS8_3 = new JButton("SeatS8_3");
        SeatS8_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		String x = "SeatS8_3"; 
        		int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
        				if (selectedOption == JOptionPane.YES_OPTION) 
        				{
        					booking(x);
        				}
        				else {
							dispose();
						}
         	}
        });
        SeatS8_3.setBounds(385, 616, 79, 54);
        getContentPane().add(SeatS8_3);
        
        
        
//window ------------------------------------------------------------------------------
        
        
        setTitle("Railway reservation");
        setSize(500,710);
        getContentPane().setLayout(null);
        setVisible(true);
        setLocation(500, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.yellow);
        
        
     // Calling markBookedSeats after initializing the frame
        markBookedSeats();
        
        
    }


}
