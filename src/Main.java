
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main
{
    // TODO  ниже разместите регулярки для определения комманд и работы с текстом
    private static final String ADD_REGEX = "[ad]+(\\s+)(.+)";
    private static final String EXIT_REGEX = "[exit]+(\\s+)?";
    private static final String ADD_INDEX_REGEX = "\\d+.+";
    private static final String DELETE_REGEX = "[delt]+\\s+\\d+";
    private static final String EDIT_REGEX = "([edit]+)\\s+\\d+.+";
    private static final String LIST_REGEX = "[list]+";
    // TODO ниже разместите основные переменные программы.
    private static boolean isContinue = true;
    private static String command;
    private static ArrayList<String> cases = new ArrayList<>();



    public static void main(String[] args) {

                System.out.println("Добро пожалывать в программу по составлению списка дел.\n" +
                "Вам доступны следующие команды:\n" +
                "\t\"LIST\" - распечатать список дел.\n" +
                "\t\"ADD название дела\" - добавить какое то дело в конец списка.\n" +
                "\t\"ADD 4 название дела\" - добавить новое дело на заданное место.\n" +
                "\t\"EDIT 3 название дела\" - изменить старое дело под указаным номером на новое.\n" +
                "\t\"DELETE 7\" - удалить дело под указаным номером.\n" +
                "\t\"EXIT\" - выйти из программы.\n" +
                "Все команды должны быть в указаном формате и без ковычек.\n\n" +
                "Введите комманду: ");
        do{
            Scanner in = new Scanner(System.in);
            command = in.nextLine();
            if (command.toLowerCase().matches(ADD_REGEX)) {
                String toDo = command.substring(3).trim();
                if (toDo.matches(ADD_INDEX_REGEX)) {
                    String toDoo = toDo.replaceAll("(^\\d+)", "").trim();
                    int index = Integer.parseInt(toDo.replaceAll("(\\d+)(\\s+)(.+)", "$1"));
                    addIndexList(index, toDoo);
                }
                else {
                    addList(toDo);
                }
            }
            else if (command.toLowerCase().matches(DELETE_REGEX)) {
                int index = Integer.parseInt(command.replaceAll("([delt]+)(\\s+)(\\d+)", "$3"));
                deleteList(index);
            }
            else if (command.toLowerCase().matches(EXIT_REGEX)) {
                exitList();
            }
            else if (command.toLowerCase().matches(EDIT_REGEX)) {
                String toDo = command.substring(4).trim();
                    String toDoo = toDo.replaceAll("(^\\d+)", "").trim();
                    int index = Integer.parseInt(toDo.replaceAll("(\\d+)(\\s+)(.+)", "$1"));
                    editList(index, toDoo);
            }
            else if (command.toLowerCase().matches(LIST_REGEX)) {
                showList();
            }
            else {
                System.out.println("такой команды нет");
            }
        } while(isContinue);


    }

    private static void showList() {
        // TODO реализуйте метод вывода всех дел в списке.
        for (String s : cases) {
            System.out.println(s);
        }
    }

    private static void addIndexList(int index, String value) {

        if (index <= cases.size()) {
            cases.add(index,value);
        }
        else {
            addList(value);
            System.out.println("вы ввели слишком большой индекс, ваше дело добавлено в конец списка");
        }
        // TODO реализуйте метод добавления дела по номеру.
    }

    private static void addList(String value) {
        // TODO реализуйте метод добавления дела без номера в конец списка.
        cases.add(value);
    }

    private static void deleteList(int index){
        // TODO реализуйте метод удаления дела по номеру.
        if (index <= cases.size() && cases.size() != 0) {
            cases.remove(index);
        }
        else {
            System.out.println("нет, это так не работает");
        }
    }

    private static void editList(int index, String newValue){
        // TODO реализуйте метод изменения дела по номеру.
        if (index<= cases.size() && cases.size() != 0) {
            cases.set(index, newValue);
        }
        else {
            System.out.println("это невозможно изменить");
        }
    }

    private static void exitList(){
        isContinue = false;
        System.out.println("Всего доброго!");
    }
}