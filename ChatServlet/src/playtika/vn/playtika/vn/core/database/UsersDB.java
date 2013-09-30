package playtika.vn.core.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class UsersDB {
    Connection connection;
    ResultSet result;
    private final static String SQLINSERT = "INSERT INTO `usersdata` (`UserName`, `Password`, `Time`) VALUES (?, ?, ?);";
    private final static String SQLSELECT = "SELECT * FROM usersdata;";

    public UsersDB() {
	createConnection();
    }

    private void createConnection() {
	try {
	    Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e1) {
	    e1.printStackTrace();
	}

	try {
	    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "Gfhjkm1");
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	if (connection == null) {
	    System.out.println("No connection to DataBase");
	}

	// getRows(); // test for select from DB
    }

    public String getRows() {
	String rows = null;
	Statement statement = null;

	try {
	    statement = connection.createStatement();
	    result = statement.executeQuery(SQLSELECT);
	    while (result.next()) {
		System.out.println(result.getRow() + ". " + result.getString("UserName") + "\t" + result.getString("Password") + "\t"
			+ result.getString("Time"));
	    }
	    statement.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return rows;
    }

    public void addRow(String login, String pass) {
	PreparedStatement statement = null;

	try {
	    statement = connection.prepareStatement(SQLINSERT);
	    statement.setString(1, login);
	    statement.setString(2, pass);
	    statement.setString(3, Calendar.getInstance().getTime().toString());
	    statement.executeUpdate();
	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }
}
