package exercise1;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @file JDBCDatabaseManager.java
 * @author Kevin Ma | #: 300867968
 * @date December 3, 2016
 * @version 0.2.1 implemented insertIntoGame functionality
 * @description This class handles CRUD operations on the Game and Player tables
 *              in the database.
 * 
 */

public class JDBCDatabaseManager {

	// Instance variables
	private PreparedStatement pst;
	private Connection conn;
	private ResultSet rs;
	private ObservableList<Game> gameList;
	private Game gameRecord;

	// since JDBC 4.0, DriverManager automatically loads and registers all
	// drivers, thus do not need the following statment and
	// Class.forName(DRIVER)
	private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String DATABSE_URL = "jdbc:sqlserver://localhost:1433;";
	private static final String DATABASE_USER = "kevin";
	private static final String DATABASE_PASSWORD = "kevin";

	// Constructors
	public JDBCDatabaseManager() {
		// Initializes the connection with the SQL database
		try {
			Class.forName(DRIVER);

			gameList = FXCollections.observableArrayList();
			conn = DriverManager.getConnection(DATABSE_URL, DATABASE_USER, DATABASE_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Public Methods
	// CRUD for Game database
	public ObservableList<Game> selectFromGame() {
		gameList.clear();
		try {
			pst = conn.prepareStatement("select * from [COMP228-F2016-OnlineGameTracker].[dbo].[Game]");
			rs = pst.executeQuery();
			while (rs.next()) {
				gameRecord = new Game(rs.getInt(1));
				gameRecord.setGameTitle(rs.getString(2));
				gameList.add(gameRecord);
			}
		} catch (SQLException e) {
			System.out.println("ERROR - Faild to select games");
		}
		return gameList;
	}

	public int insertIntoGame(String title) {
		try {
			pst = conn.prepareStatement(
					"insert into [COMP228-F2016-OnlineGameTracker].[dbo].[Game] (game_title) values(?)");
			pst.setString(1, title);
			return pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.printf("ERROR - Faild to add %s to the GAME table.%n", title);
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
