package exercise1;

import java.sql.*;

/**
 * @file JDBCDatabaseManager.java
 * @author Kevin Ma | #: 300867968
 * @date December 3, 2016
 * @version 0.0.1
 * @description This class handles CRUD operations on the Game and Player tables
 *              in the database.
 * 
 */

public class JDBCDatabaseManager {

	// Instance variables
	private PreparedStatement pst;
	private Connection conn;

	// since JDBC 4.0, DriverManager automatically loads and registers all
	// drivers, thus do not need the following statment and
	// Class.forName(DRIVER)
	// private static final String DRIVER
	// ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String DATABSE_URL = "jdbc:sqlserver://localhost:1433;";
	private static final String DATABASE_USER = "kevin";
	private static final String DATABASE_PASSWORD = "kevin";

	// Constructors
	public JDBCDatabaseManager() {
		// Initializes the connection with the SQL database
		try {
			conn = DriverManager.getConnection(DATABSE_URL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Public Methods
	// CRUD for Game database
//	public void
	public int insertIntoGame(String title) {
		try {
			pst = conn.prepareStatement("insert into [COMP228-F2016-OnlineGameTracker].[dbo].[Game] values(?)");
			pst.setString(1, title);
			return pst.executeUpdate();
		} catch (SQLException e) {
		}
		return 0;
	}

	// CRUD for Player database
	public void insertIntoPlayer(String fname, String lname, String addr, String pcode, String prov, String phone) {

	}

	// R for players and played games information
	public void displayPlayerAndPlayedGames() {

	}
}
