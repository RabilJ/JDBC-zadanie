import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {

    public static void Loop() throws IOException, ClassNotFoundException, SQLException {
        File file = new File("spis.csv");
        BufferedWriter bfw = new BufferedWriter(new FileWriter(file));
         DataOperations dao = new DataOperations();
        Scanner sc = new Scanner(System.in);
        PrintClass pc = new PrintClass();
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/games?characterEncoding=utf8&allowPublicKeyRetrieval=true&serverTimezone=UTC&useSSL=false";
        String user = "root";
        String password = "admin";
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String query = "Select*From games";
        ResultSet resultSet;

        int option = -1;
        while (option != 4) {
            List<Game> lista = new ArrayList<>();
            pc.options();
            System.out.println("Wpisz opcję");
            option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    resultSet = statement.executeQuery(query);
                    dao.getAllGames(lista, resultSet);
                    dao.writeToFile(bfw, lista);
                    bfw.flush();
                    pc.printList(lista);
                    break;
                case 2:
                    resultSet = statement.executeQuery(query);
                    System.out.println("Jaka jest Twoja minimalna ocena?");
                    int gradeChoice = sc.nextInt();
                    sc.nextLine();
                    dao.getFromUsersGradeUp(lista, resultSet, gradeChoice);
                    dao.writeToFile(bfw, lista);
                    bfw.flush();
                    pc.printList(lista);
                    break;
                case 3:
                    resultSet = statement.executeQuery(query);
                    System.out.println("Jaka kategoria gier Cię interesuję?");
                    String genreChoice = sc.nextLine();
                    dao.getFromUsersGenre(lista, resultSet, genreChoice);
                    dao.writeToFile(bfw, lista);
                    bfw.flush();
                    pc.printList(lista);
                    break;
                case 4:
                    break;
            }
        }
        connection.close();
    }

}
