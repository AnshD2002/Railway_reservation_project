package Railway;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

class TrainPage extends JFrame {
    private JComboBox<String> CB1;
    private JComboBox<String> CB2;

    String startCity;
    String stopCity;
    int trainNumber;

    public TrainPage() {
    	
        // Fetch data from railway
        String jdbcUrl = "jdbc:mysql://localhost:3306/Railway";
        String dbUsername = "root";
        String dbPassword = "@nshD8218555";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

            // Populate CB1 with start_city
            String sql = "SELECT * FROM railwayplan;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE);
            ResultSet resultSet = preparedStatement.executeQuery();

            DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<>();
            while (resultSet.next()) {
                model1.addElement(resultSet.getString("start_city"));
            }
            CB1 = new JComboBox<>(model1);

            // Initialize CB2
            CB2 = new JComboBox<>();

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Components
        JLabel l1 = new JLabel("Select Departure city");
        JButton b1 = new JButton("Find Destination");
        JButton b2 = new JButton("Search");

        // Set specific positions for components
        l1.setBounds(230, 50, 400, 50);
        CB1.setBounds(100, 100, 400, 50);
        b1.setBounds(200, 160, 200, 60);
        CB2.setBounds(100, 250, 400, 50);
        b2.setBounds(200, 350, 200, 60);

        // Actions on Buttons
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Fetching value of startCity
                    startCity = (String) CB1.getSelectedItem();

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

                    // Reset result set and populate CB2 with stop_city
                    String sql1 = "SELECT * FROM railwayplan WHERE start_city = ?";
                    PreparedStatement preparedStatement1 = connection.prepareStatement(sql1, ResultSet.TYPE_SCROLL_INSENSITIVE);
                    preparedStatement1.setString(1, startCity);

                    ResultSet resultSet1 = preparedStatement1.executeQuery();

                    DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<>();
                    while (resultSet1.next()) {
                        model2.addElement(resultSet1.getString("stop_city"));
                    }
                    CB2.setModel(model2);
                    stopCity = (String) CB2.getSelectedItem();

                    // Close resources
                    resultSet1.close();
                    preparedStatement1.close();
                    connection.close();
                } catch (Exception i) {
                    System.out.println(i);
                }
            }
        });

        b2.addActionListener(new ActionListener() {
            private int trainNumber;

			@Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Sure? You want to book a train from '" + CB1.getSelectedItem() + "' to '" + CB2.getSelectedItem() + "' .", "Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                	// Call trainno method with startCity and stopCity
                    trainNumber = Trainno(startCity, stopCity);
                    
                    System.out.println("if loop " + trainNumber);
                    
                    SeatPage sPage = new SeatPage();
                    dispose();
                } else if (result == JOptionPane.NO_OPTION) {
                    dispose();
                } else {
                    dispose();
                }
                
                trainNumber = Trainno(startCity, stopCity);
                numentry(trainNumber);
                System.out.print("ActionPerformend"+trainNumber);
            }
        });
        
        
        
        // Set up the JFrame
        setTitle("Railway reservation");
        setSize(600, 800);
        setLayout(null);
        setLocation(700, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.CYAN);

        // Add components directly to the content pane
        add(l1);
        add(CB1);
        add(b1);
        add(CB2);
        add(b2);

        // Make the JFrame visible
        setVisible(true);
           
    }
    
    int Trainno(String startcity, String stopcity) {
        // Fetch Train_no.

    	
        String jdbcUrl = "jdbc:mysql://localhost:3306/Railway";
        String dbUsername = "root";
        String dbPassword = "@nshD8218555";
        int Trainno = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

            String sql = "SELECT * FROM railwayplan where start_city = ? and stop_city = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE);

            preparedStatement.setString(1, startcity);
            preparedStatement.setString(2, stopcity);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) 
            {
                Trainno = resultSet.getInt("train_no");
            }
            
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Trainno;
	
	}
    
    
    private void numentry(int trainnum) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/Railway";
        String dbUsername = "root";
        String dbPassword = "@nshD8218555";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);

            String sql1 = "insert into trainnum_entry(trainno) values(?);";
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement1.setInt(1, trainnum);

            int rowsAffected = preparedStatement1.executeUpdate();
            System.out.println("Rows Affected: " + rowsAffected);

            preparedStatement1.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}




