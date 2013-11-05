package vn.playtika.server.datebase;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import vn.playtika.config.GeneralSqlQuery;

public class UsersDB {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private Connection connection;
    private ResultSet result;
    private String loginDB;
    private String passDB;

    public UsersDB() {
	createConnection();
    }

    private void createConnection() {
	LOGGER.debug("UsersDB : createConnection()");
	try {
	    Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e1) {
	    LOGGER.error("createConnection : SQLException {}", e1);
	    e1.printStackTrace();
	}
	loadProperties();
	try {
	    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", loginDB, passDB);
	} catch (SQLException e) {
	    LOGGER.error("createConnection : SQLException {}", e);
	    e.printStackTrace();
	}

	if (connection == null) {
	    LOGGER.error("No connection to DataBase");
	}

	// getRows(); // test for select from DB
    }

    private void loadProperties() {
	LOGGER.debug("loadProperties");
	Properties props = new Properties();
	FileInputStream in;
	try {
	    String dir = System.getProperty("user.dir");
	    LOGGER.debug("user.dir : {}", dir);
	    in = new FileInputStream("D:/database.properties");
	    props.load(in);
	    loginDB = props.getProperty("login");
	    passDB = props.getProperty("pass");
	    in.close();
	} catch (FileNotFoundException e) {
	    LOGGER.error("loadProperties : FileNotFoundException {}", e);
	    e.printStackTrace();
	} catch (IOException e) {
	    LOGGER.error("loadProperties : IOException {}", e);
	    e.printStackTrace();
	}
    }

    public String getRows() {
	String rows = null;
	Statement statement = null;

	try {
	    statement = connection.createStatement();
	    result = statement.executeQuery(GeneralSqlQuery.SQLSELECT);
	    while (result.next()) {
		LOGGER.info(result.getRow() + ". " + result.getString("UserName") + "\t" + result.getString("Password") + "\t" + result.getString("Time"));
	    }
	    statement.close();
	} catch (SQLException e) {
	    LOGGER.error("UsersDB : getRows() : SQLException {}", e);
	    e.printStackTrace();
	}

	return rows;
    }

    public void addRow(String login, String pass) {
	PreparedStatement statement = null;
	LOGGER.debug("UsersDB : addRow() : login = {}, pass = {}", login, pass);
	try {
	    statement = connection.prepareStatement(GeneralSqlQuery.SQLINSERT);
	    statement.setString(1, login);
	    statement.setString(2, pass);
	    statement.setString(3, Calendar.getInstance().getTime().toString());
	    statement.executeUpdate();
	} catch (SQLException e) {
	    LOGGER.error("UsersDB : addRow() : SQLException {}", e);
	    e.printStackTrace();
	}

    }
}
