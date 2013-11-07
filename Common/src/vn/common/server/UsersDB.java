package vn.common.server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Properties;

public class UsersDB {
    private Connection connection;
    private ResultSet result;
    private String loginDB;
    private String passDB;

    public UsersDB() {
	createConnection();
    }

    private void createConnection() {
	try {
	    Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e1) {
	    e1.printStackTrace();
	}
	loadProperties();
	try {
	    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", loginDB, passDB);
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	if (connection == null) {
	}

	// getRows(); // test for select from DB
    }

    private void loadProperties() {
	Properties props = new Properties();
	FileInputStream in;
	try {
	    String dir = System.getProperty("user.dir");
	    in = new FileInputStream("D:/database.properties");
	    props.load(in);
	    loginDB = props.getProperty("login");
	    passDB = props.getProperty("pass");
	    in.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public String getRows() {
	String rows = null;
	Statement statement = null;

	try {
	    statement = connection.createStatement();
	    result = statement.executeQuery(GeneralSqlQuery.SQLSELECT);
	    statement.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return rows;
    }

    public void addRow(String login, String pass) {
	PreparedStatement statement = null;
	try {
	    statement = connection.prepareStatement(GeneralSqlQuery.SQLINSERT);
	    statement.setString(1, login);
	    statement.setString(2, pass);
	    statement.setString(3, Calendar.getInstance().getTime().toString());
	    statement.executeUpdate();
	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }
}
