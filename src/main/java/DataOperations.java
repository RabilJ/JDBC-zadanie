import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DataOperations {

    public void writeToFile(BufferedWriter bfw, List<Game> list) throws IOException {
        for (Game game : list) {
            bfw.write(game.getId() + ";" + game.getName() + ";" + game.getGrade() + ";" + game.getGenre() + ";" + game.getYear() + "\n");
        }
    }

    public List<Game> getAllGames(List<Game> list, ResultSet resultSet) throws SQLException {

        while (resultSet.next()) {
            Game game = createGame(resultSet);
            list.add(game);
        }
        return list;
    }

    public List<Game> getFromUsersGradeUp(List<Game> list, ResultSet resultSet, int gradeChoice) throws SQLException {

        while (resultSet.next()) {
            Game game = createGame(resultSet);
            if (gradeChoice <= resultSet.getInt(3) && gradeChoice < 11 && gradeChoice > 0) {
                list.add(game);
            }
        }
        return list;

    }
    public List<Game> getFromUsersGenre(List<Game> list, ResultSet resultSet, String genreChoice) throws SQLException {
        while (resultSet.next()) {
            if (genreChoice.equals(resultSet.getString(4))) {
                Game game = createGame(resultSet);
                list.add(game);
            }
        }
        return list;

    }
    private static Game createGame(ResultSet resultSet) throws SQLException {
        Game game = new Game();
        game.setId(resultSet.getInt(1));
        game.setName(resultSet.getString(2));
        game.setGrade(resultSet.getInt(3));
        game.setGenre(resultSet.getString(4));
        game.setYear(resultSet.getInt(5));
        return game;
    }

}