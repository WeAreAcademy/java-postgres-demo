import java.sql.*;
import java.util.Random;

public class DBDemo {

    private static final String DATABASE_URL = getEnvVarOrFail("DATABASE_URL");//"jdbc:postgresql://localhost:5432/demos";

    public static void runDemo() {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL);
            System.out.println("Connected to the PostgreSQL server successfully.");
            insertHiScore(RandomNameGenerator.generateRandomName(), generateRandomScore(), connection);
            getAndPrintHiScores(connection);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private static String getEnvVarOrFail(String key)  {
        String result = System.getenv(key);
        if (result != null) {
            return result;
        } else {
            throw new RuntimeException("Missing required env var: " + key);
        }
    }
    private static void getAndPrintHiScores(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM hiscores order by score desc limit 100");
        printHiscoresFromResultSet(resultSet);
    }


    private static void printHiscoresFromResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int score = resultSet.getInt("score");
            String name = resultSet.getString("username");
            System.out.println(name + " scored " + score);
        }
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

    private static int generateRandomScore() {
        return new Random().nextInt(0, 1000);
    }
}
