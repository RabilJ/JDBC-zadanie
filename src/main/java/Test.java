import java.sql.*;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url= "jdbc:mysql://localhost:3306/games?characterEncoding=utf8&allowPublicKeyRetrieval=true&serverTimezone=UTC&useSSL=false";
        String name = "root";
        String password = "admin";
        Connection connection = DriverManager.getConnection(url,name,password);
        Statement statement = connection.createStatement();
        String query = "CREATE TABLE games(" +
                "id INT auto_increment PRIMARY KEY," +
                "name VARCHAR(40)," +
                "grade INT," +
                "genre VARCHAR(40)," +
                "year INT" +
                ");";

        ResultSet resultSet = statement.executeQuery(query);

    }
}
