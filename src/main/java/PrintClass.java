import java.util.List;

public class PrintClass {
    public void options() {
        System.out.println("Opcje do wyboru:\n" +
                "1 - Wyświetl wszystkie gry \n" +
                "2 - Wyświetl gry z oceną większą niż XX(podana przez Ciebie wartość)\n" +
                "3 - Wyświetl gry z podanej przez Ciebie kategorii\n" +
                "4 - Wyjście z programu");
    }
    public void printList(List<Game> list){
        for (Game game : list) {
            System.out.println(game);
        }
    }
}
