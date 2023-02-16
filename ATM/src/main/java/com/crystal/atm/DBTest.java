package com.crystal.atm;

import java.sql.*;

public class DBTest {
    public static void main1(String[] args) {
        try {
            //2b. Register the Driver
            Class.forName("com.mysql.jdbs.driver");
            //3. Establish the connection
            Connection connection = DriverManager.getConnection("", "", "");
            //4. Create the statement
            Statement st = connection.createStatement();
            //5. Execute the Query
            ResultSet resultSet = st.executeQuery("");
            //6. Process the result
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(0) + " " + resultSet.getString(1));
            }

            //7. Close
            st.close();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
