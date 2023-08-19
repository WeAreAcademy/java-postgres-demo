import java.sql.*;
import java.util.Random;

public class DBDemo {

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/demos";
    private static final String USER = "academy";
    private static final String PASSWORD = "";


    public static void runDemo(){
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            System.out.println("Connected to the PostgreSQL server successfully.");
            insertHiScore(RandomNameGenerator.generateRandomName(), generateRandomScore(), connection);
            getAndPrintHiScores(connection);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getAndPrintHiScores(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM hiscores limit 100");
        printHiscoresFromResultSet(resultSet);
    }


    private static int generateRandomScore() {
        return new Random().nextInt(0, 1000);
    }

    private static void insertHiScore(String personName, int score, Connection connection) throws SQLException {
        String query = "INSERT INTO hiscores (username, score) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        // Set the values for the placeholders
        preparedStatement.setString(1, personName);
        preparedStatement.setInt(2, score);

        // Execute the statement
        preparedStatement.executeUpdate();
    }

    private static void printHiscoresFromResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int score = resultSet.getInt("score");
            String name = resultSet.getString("username");
            System.out.println(score + " " + name);
        }
    }


}
