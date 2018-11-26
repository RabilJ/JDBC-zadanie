import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        File file = new File("spis.csv");
        BufferedWriter bfw = new BufferedWriter(new FileWriter(file));

        Scanner sc = new Scanner(System.in);
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/games?characterEncoding=utf8&allowPublicKeyRetrieval=true&serverTimezone=UTC&useSSL=false";
        String user = "root";
        String password = "admin";
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String query = "Select*From games";
        ResultSet resultSet;
//         String query2 = "INSERT INTO games(name, grade,genre,year)" +
//                "VALUES" +
//                "('God of War',10,'Action',2018 ),"+
//                "('Red Dead Redemption',9,'Action',2018 ),"+
//                "('Amazing Spider-Man',9,'Action',2018 ),"+
//                "('Fortnite',1,'FPS',2018 ),"+
//                "('League of Legends',8,'MOBA',2008 )"
//                ;
//        int inserted = statement.executeUpdate(query2);
//        statement.close();
        int option = -1;
        while (option != 4) {
            options();
            System.out.println("Wpisz opcję");
            option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    resultSet = statement.executeQuery(query);
                    while (resultSet.next()) {
                        Game game = getData(new Game(), resultSet);
                        writeToFile(bfw, game);
                        System.out.println(game);
                    }
                    bfw.flush();
                    break;
                case 2:
                    resultSet = statement.executeQuery(query);
                    System.out.println("Jaka jest Twoja minimalna ocena?");
                    int gradeChoice = sc.nextInt();
                    sc.nextLine();
                    while (resultSet.next()) {
                        if (gradeChoice <= resultSet.getInt(3)&&gradeChoice<11&&gradeChoice>0) {
                            Game game = getData(new Game(), resultSet);
                            writeToFile(bfw, game);
                            System.out.println(game);
                        }else{
                            System.out.println("Wyszedłeś poza skale oceny(1-10), spróbuj ponownie");
                            break;
                        }
                    }
                    bfw.flush();
                    break;
                case 3:
                    resultSet = statement.executeQuery(query);
                    System.out.println("Jaka kategoria gier Cię interesuję?");
                        String genreChoice = sc.nextLine();
                        while (resultSet.next()) {
                                if (genreChoice.equals(resultSet.getString(4))) {
                                    Game game = getData(new Game(), resultSet);
                                    writeToFile(bfw, game);
                                    System.out.println(game);
                                }else{
                                    System.out.println("Nie mamy takiej kategorii gier w naszej bazie danych");
                                    break;
                                }
                            }
                        bfw.flush();
                    break;
                case 4:
                    break;
            }
        }
        connection.close();
    }

    private static void options() {
        System.out.println("Opcje do wyboru:\n" +
                "1 - Wyświetl wszystkie gry \n" +
                "2 - Wyświetl gry z oceną większą niż XX(podana przez Ciebie wartość)\n" +
                "3 - Wyświetl gry z podanej przez Ciebie kategorii\n" +
                "4 - Wyjście z programu");
    }

    private static Game getData(Game game, ResultSet resultSet) throws SQLException {
        game.setId(resultSet.getInt(1));
        game.setName(resultSet.getString(2));
        game.setGrade(resultSet.getInt(3));
        game.setGenre(resultSet.getString(4));
        game.setYear(resultSet.getInt(5));
        return game;
    }

    private static void writeToFile(BufferedWriter bfw, Game game) throws IOException {
        bfw.write(game.getId() + ";" + game.getName() + ";" + game.getGrade() + ";" + game.getGenre() + ";" + game.getYear() + "\n");
    }
}
