public class Game {
    private int id;
    private String name;
    private int grade;
    private String genre;
    private int year;

    public Game(int id, String name, int grade, String genre, int year) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.genre = genre;
        this.year = year;
    }

    public Game() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Id: " + id + " Name: " + name + " Ocena: " + grade + " Kategoria: " + genre + "\nRok wydania: " + year + "\n\n";
    }

}
